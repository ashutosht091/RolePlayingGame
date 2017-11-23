package service;

import static util.PrintUtil.*;

import java.util.Scanner;

import constants.Constants;
import dao.DaoFactory;
import dao.PlayerDao;
import exception.GameExceptionType;
import exception.RolePlayGameException;
import helper.GenericHelper;
import model.Player;

public class GamePlayerService implements IGamePlayerService {


	private DaoFactory dataStore = DaoFactory.getDaoStore(); 
	private PlayerDao playerDao = dataStore.getGamePlayerDao();

	private static final IGamePlayerService INSTANCE = new GamePlayerService();

	public static IGamePlayerService getInstance() {

		return INSTANCE;
	}


	@Override
	public Player signIn(Scanner scanner) throws RolePlayGameException {
		printLn(Constants.ENTER_USERNAME);
		String userName = scanner.next();
		printLn(Constants.ENTER_PASSWORD);
		String password = scanner.next();
		Player player = playerDao.getPlayer(userName,Constants.GAME_PLAYERS_DATA);
		if((player!=null) && !GenericHelper.getEncodedPassword(password).equals(player.getPassword()))
		{
			throw new RolePlayGameException(GameExceptionType.PASSWORD_INCORRECT);
		}
		return player;
	}


	@Override
	public Player registerPlyer(Scanner scanner) throws RolePlayGameException  {

		printLn(Constants.ENTER_FIRST_NAME);
		String firstName = scanner.next();
		printLn(Constants.ENTER_LAST_NAME);
		String lastName = scanner.next();
		printLn(Constants.ENTER_USERNAME);
		String userName = scanner.next();
		printLn(Constants.ENTER_PASSWORD);
		String password = scanner.next();
		password = GenericHelper.getEncodedPassword(password);
		Player player = new Player(firstName,lastName,userName,password);
		playerDao.createNewPlayer(player,Constants.GAME_PLAYERS_DATA);
		return player;

	}

}
