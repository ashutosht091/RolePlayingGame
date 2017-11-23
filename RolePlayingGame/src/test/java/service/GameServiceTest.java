package service;

import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import constant.TestConstants;
import constants.Constants;
import dao.DaoFactory;
import exception.RolePlayGameException;
import helper.GameServiceHelper;
import model.Game;
import model.GameCharacter;
import model.GameMap;
import model.Player;
import staticdata.GameStaticData;
import util.NumberUtil;

public class GameServiceTest {


	private String expectedUserSelection=Constants.USER_OPTION_NEW_GAME;
	private Game expectedGame ;

	@Before
	public void init()
	{
		GameStaticData.currentPlayer=new Player("dinkar","kamat","dinkar","abcd");
		GameCharacter currentCharacter;
		try {
			currentCharacter = GameStaticData.getGamecharacters().get(0);
			GameMap currentMap = GameStaticData.getMaps().get(0);
			expectedGame =  new Game(NumberUtil.generateSecureRandom(),GameStaticData.currentPlayer,currentCharacter,currentMap);
		} catch (RolePlayGameException e) {
			Assert.fail(e.getErrorMsg());
		}
	}
	@Test
	public void testStartGame()
	{

		try {
			Game game = null;
			String userOption="ExistingGame";
			List<Game> games =  DaoFactory.getDaoStore().getGameDao().getGameList(TestConstants.FILE_NAME_SAVE_GAME, "dinkar",TestConstants.TEST_FILE_NAME);
			if(!userOption.equals(expectedUserSelection))
			{
				Assert.assertNotNull(games);
			}else
			{
				game =	GameServiceHelper.INSTANCE.exploreGameAndConquer(expectedGame, new Scanner(System.in));
				Assert.assertNotNull(game);
			}
		} catch (RolePlayGameException e) {
			Assert.fail(e.getErrorMsg());
		}
	}

}
