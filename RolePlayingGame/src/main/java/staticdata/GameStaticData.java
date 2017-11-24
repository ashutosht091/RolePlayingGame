package staticdata;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.Constants;
import exception.GameExceptionType;
import exception.RolePlayGameException;
import model.GameCharacter;
import model.GameMap;
import model.Player;
import model.Position;
import model.Question;
import util.ResourceUtil;

/**
 * This class serves as container which holds static data of the game
 * It loads data from property files and initialize game attributes
 * all attributes except currentPlayer are final . When the final attributes 
 * are accessed , deep cloned copies are returned .
 * 
 * @author ashutosh
 *
 */
public class GameStaticData {

	public static final Logger logger = LoggerFactory.getLogger(GameStaticData.class);

	private GameStaticData()
	{

	}

	public static   Player currentPlayer = null;
	private static final  List<GameCharacter> gameCharacters = new ArrayList<>();
	private static final  List<GameCharacter> enemies = new ArrayList<>();
	private static final  List<GameMap> maps = new ArrayList<>();
	private static final  Map<Integer,List<Question>> gameMapToquestions = new HashMap<>();


	static 
	{
		loadGameCharacters(Constants.GAME_CHARACTER_PROP_FILE,gameCharacters);
		loadGameCharacters(Constants.GAME_ENEMY_PROP_FILE,enemies);
		loadMapQuestions(Constants.GAME_QUESTION_PROP_FILE,gameMapToquestions);
		loadGameMaps(Constants.GAME_MAP_PROP_FILE,maps);
	}


	public static void loadGameCharacters(String fileName,List<GameCharacter> characterList)
	{
		Properties gameCharacterProp = ResourceUtil.loadPropFile(fileName);
		String gameCharacter;
		int i =1;
		do {
			gameCharacter = gameCharacterProp.getProperty(Constants.GAME_CHARACTER_NAME_PATTERN + i++);
			if (gameCharacter != null) {
				String[] characterData = gameCharacter.split(Constants.FILE_DATA_DELIMITER);
				GameCharacter character = new GameCharacter();
				character.setCharacterId(Integer.parseInt(characterData[0]));
				character.setName(characterData[1]);
				characterList.add(character);
			}
		} while (gameCharacter != null);




	}

	public static void loadGameMaps(String mapFileName,List<GameMap> maps)
	{

		Properties gameMapProperties = ResourceUtil.loadPropFile(mapFileName);
		String gameMap;
		int i =1;
		do {
			gameMap = gameMapProperties.getProperty(Constants.GAME_MAP_PATTERN + i++);
			if (gameMap != null) {
				String[] mapData = gameMap.split(Constants.FILE_DATA_DELIMITER);
				GameMap map = new GameMap();
				map.setMapId(Integer.parseInt(mapData[0]));
				String[] borders = mapData[1].split(",");
				map.setBordersX(Integer.parseInt(borders[0]));
				map.setBordersY(Integer.parseInt(borders[1]));
				map.setQuestions(generateMapQuestions(gameMapToquestions,map.getMapId()));
				map.setMapName(mapData[2]);
				maps.add(map);
			}
		} while (gameMap != null);





	}

	private static void loadMapQuestions(String fileName,Map<Integer,List<Question>> gameMapToquestions)
	{

		Properties gameQuestionProperties = ResourceUtil.loadPropFile(fileName);
		String gameQuestion;
		int i =1;

		do{
			gameQuestion = gameQuestionProperties.getProperty(Constants.GAME_MAP_QUESTION_PATTERN + i++);
			if(gameQuestion!=null){
				String[] questionData = gameQuestion.split(Constants.FILE_DATA_DELIMITER);
				String[] positionCordinates = questionData[2].split(",");
				Position questionPositon = new Position(Integer.parseInt(positionCordinates[0]),Integer.parseInt(positionCordinates[1]),true);
				Question question  = new Question();
				int mapId = Integer.parseInt(questionData[1]);
				question.setMapId(mapId);
				question.setQuestionId(Integer.parseInt(questionData[0]));
				question.setPosition(questionPositon);
				question.setQuestionDescription(questionData[3]);
				if(gameMapToquestions.containsKey(mapId))
				{
					gameMapToquestions.get(mapId).add(question);
				}else
				{
					List<Question> questions = new ArrayList<>();
					questions.add(question);
					gameMapToquestions.put(mapId, questions);

				}
			}
		}while(gameQuestion!=null);



	}

	private static Map<Position,Question> generateMapQuestions( Map<Integer,List<Question>> mapToQuestion , Integer mapId)
	{
		List<Question> questions = mapToQuestion.get(mapId);
		Map<Position,Question> positionToQuestion = new HashMap<Position,Question>();
		if(questions!=null){
			for(Question question:questions)
			{
				question.getPosition().setVisited(Constants.isVisited);
				positionToQuestion.put(question.getPosition(), question);
			}
		}
		return positionToQuestion;
	}

	

	public static List<GameCharacter> getGamecharacters() throws RolePlayGameException {
		return deepClone(gameCharacters);
	}

	public static List<GameCharacter> getEnemies() throws RolePlayGameException {
		return deepClone(enemies);
	}

	public static List<GameMap> getMaps() throws RolePlayGameException{
		return deepClone(maps);
	}

	public static Map<Integer, List<Question>> getGamemaptoquestions() throws RolePlayGameException{
		return deepClone(gameMapToquestions);
	}
	/**
	 * This method is used for deep cloning a object. It uses serialization for making
	 * copy of object
	 * @param t
	 * @return
	 * @throws RolePlayGameException
	 */

	public static <T>T deepClone(T t) throws RolePlayGameException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		T object =null;
		try {
			oos = new ObjectOutputStream(baos);

			oos.writeObject(t);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			object = (T) ois.readObject();
		} catch (IOException |ClassNotFoundException e) {
			logger.error(e.getMessage(),e);
			throw new RolePlayGameException(GameExceptionType.ERROR);
		} 
		return object;
	}


}
