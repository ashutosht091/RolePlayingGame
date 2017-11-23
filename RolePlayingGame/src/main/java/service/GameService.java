package service;

import static constants.Constants.GAME_SAVED_DATA;

import java.util.List;
import java.util.Scanner;

import constants.Constants;
import dao.DaoFactory;
import dao.GameDao;
import exception.RolePlayGameException;
import helper.GameServiceHelper;
import helper.IGameServiceHelper;
import model.Game;
import staticdata.GameStaticData;


/**
 * GameService is implementation for IGameService interface
 * @author ashutosh
 *
 */
public class GameService implements IGameService{



	private DaoFactory dataStore = DaoFactory.getDaoStore(); 
	private GameDao gameDao = dataStore.getGameDao();
	private IGameServiceHelper gameServiceHelper = GameServiceHelper.INSTANCE;
	private static final IGameService INSTANCE = new GameService();
	public static IGameService getInstance()
	{
		return GameService.INSTANCE;
	}

	/**
	 *  this method first loads list of game of user then calls helper to run the game
	 */
	@Override
	public Game startGame(Scanner scanner) throws RolePlayGameException {
		List<Game> games = gameDao.getGameList(GAME_SAVED_DATA, GameStaticData.currentPlayer.getUserName(),Constants.GAME_PLAYERS_DATA);
		return gameServiceHelper.startGame(games, scanner);



	}

}
