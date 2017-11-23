package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import constants.Constants;
import exception.RolePlayGameException;
import helper.GenericHelper;
import model.Experience;
import model.Game;
import model.GameCharacter;
import model.GameMap;
import model.Player;
import model.Position;
import model.Question;
import staticdata.GameStaticData;
import util.FileUtility;
import util.StringUtil;

/**
 * GameDaoImpl is implementation for GameDao Interface
 * @author ashutosh
 *
 */
class GameDaoImpl implements GameDao {

	public static final GameDao INSTANCE = new GameDaoImpl();
	FileUtility fileUtility = FileUtility.INSTANCE;

	private DaoFactory dataStore = DaoFactory.getDaoStore(); 
	private PlayerDao playerDao = dataStore.getGamePlayerDao();







	/**
	 * This method reads record as a text and create game object 
	 * we can use getEntityList in File Util as well .
	 * This method always shows updated state of game between user session
	 */
	@Override
	public List<Game> getGameList(String gmaeFileName, String userName,String playerFileName) throws RolePlayGameException  {


		List<String> userRecords = FileUtility.getRecordsFromFile(gmaeFileName)
				.stream()
				.filter(record-> record.contains(userName))
				.collect(Collectors.toList());
		Map<Integer,Game> gameIdMap = new HashMap<Integer,Game>();
		if(userRecords!=null && !userRecords.isEmpty()){
			for(String record:userRecords)
			{
				if(!StringUtil.isEmpty(record)){
					String  values[] = record.split(Constants.DELIMITER_SPACE);
					Integer gameId=Integer.parseInt(values[1]);
					Integer characterID = Integer.parseInt(values[2]);
					Integer mapId = Integer.parseInt(values[3]);
					Integer currX =Integer.parseInt(values[4]);
					Integer currY = Integer.parseInt(values[5]);
					Position currPosition = new Position(currX,currY,true);
					GameCharacter gameCharacter = GameStaticData.getGamecharacters()
							.stream()
							.filter((ch)-> ch.getCharacterId()==characterID)
							.collect(Collectors.toList()).get(0);
					gameCharacter.setPosition(currPosition);
					Integer experiencePoint = Integer.parseInt(values[6]);
					gameCharacter.setExperience(new Experience(experiencePoint));
					
					GameMap gameMap = GameStaticData.getMaps().stream().filter(m->m.getMapId()==mapId).collect(Collectors.toList()).get(0);
					Set<Integer> asnweredQuestions = GenericHelper.createSetFromArray(values[7].split(Constants.FILE_DATA_DELIMITER));
					for(Question question :gameMap.getQuestions().values())
					{
						if(asnweredQuestions.contains(question.getQuestionId()))
						{
							question.setAnswered(Boolean.TRUE);
						}
					}
					
					String positionString = values[8];
					Position[][] grid = GenericHelper.getPostionMatrixFromString(positionString,gameMap.getBordersX(),gameMap.getBordersY()); 
					Player player = playerDao.getPlayer(userName,playerFileName);
					Game game = new Game(gameId,player,gameCharacter,gameMap);
					game.setPositionGrid(grid);
					gameIdMap.put(gameId,game);
				}
			}
		}

		return new ArrayList<Game>(gameIdMap.values());
	}







	/**
	 * This function writes state of the game into file 
	 */
	@Override
	public boolean saveGameState(String filename,Game game) throws RolePlayGameException
	{
		StringBuilder record = new StringBuilder();
		record.append(GameStaticData.currentPlayer.getUserName()).append(Constants.DELIMITER_SPACE);
		record.append(game.getGameId()).append(Constants.DELIMITER_SPACE);
		record.append(game.getGameCharacter().getCharacterId()).append(Constants.DELIMITER_SPACE);
		record.append(game.getMap().getMapId()).append(Constants.DELIMITER_SPACE);
		record.append(game.getGameCharacter().getPosition().getPositionX()).append(Constants.DELIMITER_SPACE);
		record.append(game.getGameCharacter().getPosition().getPositionY()).append(Constants.DELIMITER_SPACE);
		record.append(game.getGameCharacter().getExperience().getPoint()).append(Constants.DELIMITER_SPACE);
		record.append(GenericHelper.getQuestionString(game.getMap().getQuestions())).append(Constants.DELIMITER_SPACE);
		record.append(GenericHelper.getZeroOneStringfromPostionArray(game.getPositionGrid()));

		return FileUtility.writeRecordsIntoFile(filename, record.toString());
	}




}
