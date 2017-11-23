package helper;

import static util.PrintUtil.print;
import static util.PrintUtil.printLn;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import constants.Constants;
import dao.DaoFactory;
import dao.GameDao;
import exception.GameExceptionType;
import exception.RolePlayGameException;
import model.Experience;
import model.Game;
import model.GameCharacter;
import model.GameMap;
import model.Position;
import model.Question;
import staticdata.GameStaticData;
import util.NumberUtil;


/**
 * This is Helper class which helps in playing game.
 * @author ashutosh
 *
 */
public class GameServiceHelper implements IGameServiceHelper {
	public static final IGameServiceHelper INSTANCE = new GameServiceHelper ();

	private DaoFactory dataStore = DaoFactory.getDaoStore(); 
	private GameDao gameDao = dataStore.getGameDao();


	public Game newGame(Scanner scanner) throws RolePlayGameException{
		printLn(Constants.CHOOSE_CHARACTER);
		String choosenCharacter;
		List<GameCharacter> gameCharacter = GameStaticData.getGamecharacters();
		List<GameMap> maps = GameStaticData.getMaps();
		GameCharacter currentCharacter = null;
		do {
			for (GameCharacter character : gameCharacter) {
				printLn(character.getCharacterId() + Constants.ARROW + character.getName());
			}
			choosenCharacter = scanner.next();
			if (GenericHelper.goPrevMenu(choosenCharacter))
				return null;
			int indx = Integer.parseInt(choosenCharacter);
			if (indx != -1 && indx <= gameCharacter.size())
				currentCharacter = gameCharacter.get(indx - 1);
			if (currentCharacter == null) {
				printLn(Constants.WRONG_INFO_TRY_AGAIN);
				printLn(Constants.CHOOSE_CHARACTER);
			}
		} while (currentCharacter == null);
		printLn(Constants.CHOOSE_MAP);
		String choosenMap;
		GameMap currentMap = null;
		do {
			for (GameMap map : maps) {
				printLn(map.getMapId() + Constants.ARROW + map.getMapName());
			}
			choosenMap = scanner.next();
			if (GenericHelper.goPrevMenu(choosenMap))
				return null;
			int indx = Integer.parseInt(choosenMap);
			if (indx != -1 && indx <=maps.size())
				currentMap = maps.get(indx - 1);
			if (currentCharacter == null) {
				printLn(Constants.WRONG_INFO_TRY_AGAIN);
				printLn(Constants.CHOOSE_MAP);
			}
		} while (currentMap == null);

		Position position = new Position(0, 0,true);
		currentCharacter.setPosition(position);
		currentCharacter.setExperience(new Experience(0));
		return new Game(NumberUtil.generateSecureRandom(),GameStaticData.currentPlayer,currentCharacter,currentMap);

	}


	public  Game exploreGameAndConquer(Game game,Scanner scanner) throws RolePlayGameException
	{
		String userMove = Constants.NO_MOVE;
		GameCharacter currentCharacter = game.getGameCharacter();
		GameMap currentMap = game.getMap();
		int borderX = currentMap.getBordersX();
		int borderY = currentMap.getBordersY();
		boolean needExit = false;

		do {
			Position tmpPosition = currentCharacter.getPosition();
			Position prevPosition = tmpPosition;
			if(!isGameComplete(game)){
				boolean move = updatePlayerPosition(tmpPosition,userMove,borderX,borderY);

				if (move || userMove.equals(Constants.NO_MOVE)) {
					move(currentCharacter, tmpPosition,prevPosition, currentMap,game);
					boolean isEnemyPresent = game.getMap().getQuestions().containsKey(tmpPosition)?
							!game.getMap().getQuestions().get(tmpPosition).isAnswered()?
									Boolean.TRUE:Boolean.FALSE:Boolean.FALSE;
					if(isEnemyPresent)
					{
						fightWithEnemy(scanner,currentCharacter,currentMap);
					}
					printLn(Constants.MOVE_OPTIONS);
				}else
				{
					printLn(Constants.OUT_OF_BORDER + Constants.DELIMITER_SPACE + Constants.MOVE_OPTIONS);
				}
				userMove = scanner.next();
				userMove = userMove.isEmpty() ? scanner.nextLine():userMove;
				if(userMove.equals(Constants.NO_MOVE))
				{
					needExit = verifyExit(scanner);
				}
			}else
			{
				printLn(Constants.MAP_COMPLETE);
				userMove = scanner.next();
				userMove = userMove.isEmpty() ? scanner.nextLine():userMove;
				if(userMove.equals(Constants.NO_MOVE))
				{
					needExit = verifyExit(scanner);
				}else if(userMove.equals(Constants.USER_OPTION_NEW_GAME))
				{     printLn(Constants.GAME_MENU_OPTION);
					  newGame(scanner);
				}
			}
		}while(!needExit);

		return saveTheGame(game,scanner);

	}

	private boolean verifyExit(Scanner scanner)
	{
		boolean isConfirmedExit = true;
		String sure;
		do {
			printLn(Constants.ARE_YOU_SURE_EXIT);
			sure = scanner.next();
			if (Constants.NO.equalsIgnoreCase(sure)) {
				isConfirmedExit = false;
			}
		} while (!(Constants.YES.equalsIgnoreCase(sure) || Constants.NO.equalsIgnoreCase(sure)));
		return isConfirmedExit;
	}

	public Game startGame(List<Game> games,Scanner scanner)throws RolePlayGameException
	{

		printLn(Constants.WELCOME+GameStaticData.currentPlayer.getFirstName());
		boolean isGameSelected = Boolean.FALSE;
		Game gameToplay = null;
		boolean isNewGame = Boolean.FALSE;
		if(games!=null &&!games.isEmpty()){
			do{
				printLn(Constants.RESUME_GAME);
				printLn(Constants.GAME_MENU_OPTION);
				String userOption = null;
				int i =0;
				for(Game game:games)
				{
					++i;
					printLn(i+Constants.ARROW+game.getGameId()+Constants.SPACE+game.getGameCharacter().getName()+Constants.SPACE+Constants.EXPLORING+
							game.getMap().getMapName());

				}
				userOption = scanner.next();
				if(Constants.USER_OPTION_NEW_GAME.equalsIgnoreCase(userOption))
				{
					isNewGame = Boolean.TRUE;
					isGameSelected = Boolean.TRUE;
				}else
				{
					int indx = Integer.parseInt(userOption);
					if (indx != -1 && indx <= games.size())
						gameToplay = games.get(indx - 1);
					if (gameToplay == null) {
						printLn(Constants.WRONG_INFO_TRY_AGAIN);
					}else
					{
						isGameSelected = Boolean.TRUE;
					}
				}
			}while(!isGameSelected);
		}
		if(gameToplay==null||isNewGame)
		{
			gameToplay = newGame(scanner);
		}


		return exploreGameAndConquer(gameToplay,scanner);


	}


	private boolean updatePlayerPosition(Position currentPosition, String userMove, int bordersX, int bordersY)
	{

		boolean isUpdated = false;
		if (userMove.equals(Constants.MOVE_RIGTH)) {
			if (currentPosition.getPositionX() + 1 < bordersX) {
				currentPosition.setPositionX(currentPosition.getPositionX() + 1);
				isUpdated = true;
			}
		} else if (userMove.equals(Constants.MOVE_LEFT)) {
			if (currentPosition.getPositionX() > 0) {
				currentPosition.setPositionX(currentPosition.getPositionX() - 1);
				isUpdated = true;
			}
		} else if (userMove.equals(Constants.MOVE_UP)) {
			if (currentPosition.getPositionY() > 0) {
				currentPosition.setPositionY(currentPosition.getPositionY() - 1);
				isUpdated = true;
			}
		} else if (userMove.equals(Constants.MOVE_DOWN)) {
			if (currentPosition.getPositionY() + 1 < bordersY) {
				currentPosition.setPositionY(currentPosition.getPositionY() + 1);
				isUpdated = true;
			}
		}
		return isUpdated;
	}




	private void move(GameCharacter currentCharacter, Position currentPosition,Position previousPosition, GameMap map,Game game) {
		String characterName = currentCharacter.getName();
		String[] nameArr = characterName.split(Constants.DELIMITER_SPACE);
		currentPosition.setVisited(Constants.isVisited);
		game.getPositionGrid()[currentPosition.getPositionX()][currentPosition.getPositionY()].setVisited(Constants.isVisited);
		int len = 0;
		String visibleName = null;
		if (nameArr.length == 1) {
			len = characterName.length();
			visibleName = characterName;
		} else {
			visibleName = String.valueOf(nameArr[0].charAt(0)) + Constants.UNDERSCORE;
			for (int i = 1; i < nameArr.length; i++) {
				visibleName += Constants.DELIMITER_SPACE + nameArr[i];
			}
			len = visibleName.length();
		}
		for (int i = 0; i < map.getBordersY(); i++) {
			for (int j = 0; j < map.getBordersX(); j++) {
				if (currentPosition.getPositionX() == j && currentPosition.getPositionY() == i) {
					print(Constants.SPACE_FOR_MAP + visibleName);
				} else {
					print(Constants.SPACE_FOR_MAP);
					drawMap(game.getPositionGrid()[j][i].isVisited(),len);

				}
				print(Constants.SPACE_FOR_MAP);
			}
			printLn(Constants.DELIMITER_SPACE);
			printLn(Constants.DELIMITER_SPACE);
		}
	}
	private void drawMap(boolean isPrevVisited,int length)
	{
		int visit = length/2;
		for (int j2 = 0; j2 < length; j2++) {
			if(visit==j2&&isPrevVisited)
			{
				print(Constants.STAR);
			}else{
				print(Constants.UNDERSCORE);
			}
		}
	}


	private void fightWithEnemy(Scanner scanner , GameCharacter character,GameMap map) throws RolePlayGameException
	{

		GameCharacter enemy = GameStaticData.getEnemies().get(new Random().nextInt(GameStaticData.getEnemies().size()));
		printLn(GenericHelper.formatMessage(Constants.ENCOUNTERED_ENEMY, enemy.getName()));
		Question question = map.getQuestions().get( character.getPosition());
		question.setAnswered(Boolean.TRUE);
		String[] questionAndAnswer = map.getQuestions().get( character.getPosition()).getQuestionDescription().trim().split(Constants.DELIMITER_QADELIMIT);
		printLn(questionAndAnswer[0]+ Constants.DELIMITER_SPACE + Constants.YES_OR_NO);
		String answer = scanner.nextLine();
		while (!Constants.YES.equalsIgnoreCase(answer) && !Constants.NO.equalsIgnoreCase(answer)) {
			printLn(Constants.ANSWER_THE_QUESTION);
			answer = scanner.nextLine();
		}if(questionAndAnswer[1].trim().equalsIgnoreCase(answer.trim()))
		{
			character.getExperience().setPoint(character.getExperience().getPoint() + 1);
			printLn(GenericHelper.formatMessage(Constants.WIN_THE_FIGHT, String.valueOf(character.getExperience().getPoint())));

		}else
		{
			print(GenericHelper.formatMessage(Constants.LOST_THE_FIGHT, questionAndAnswer[1].trim()));
			printLn(GenericHelper.formatMessage(Constants.NEW_EXPERIENCE,String.valueOf(character.getExperience().getPoint())));

		}

	}

	public Game saveTheGame(Game game, Scanner scanner) throws RolePlayGameException  {
		String save ;
		do {
			printLn(Constants.SAVE_THE_GAME);
			save = scanner.next();
			if (Constants.YES.equalsIgnoreCase(save)) {
				boolean result = gameDao.saveGameState(Constants.GAME_SAVED_DATA, game);
				if (!result){
					throw new RolePlayGameException(GameExceptionType.GAME_SAVE_ERROR);
				}
				else {
					printLn(Constants.SAVED_SUCCESS);

				}
			}
		} while (!(Constants.YES.equalsIgnoreCase(save) || Constants.NO.equalsIgnoreCase(save)));
		printLn(Constants.GOODBYE);
		return game;
	}

	private  boolean isGameComplete(Game game)
	{
		boolean isComplete = true;
		for(Position[] positions:game.getPositionGrid())
		{
			for(Position position:positions)
			{
				if(! position.isVisited())
				{
					isComplete = false;
				}

			}
		}
		return isComplete;
	}




}