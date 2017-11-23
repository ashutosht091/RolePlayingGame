package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import constant.TestConstants;
import exception.RolePlayGameException;
import model.Experience;
import model.Game;
import model.GameCharacter;
import model.GameMap;
import model.Player;
import model.Position;
import staticdata.GameStaticData;
import util.NumberUtil;

public class GameServiceHelperTest {


	private Game expectedGame ;


	@Before
	public void init()
	{
		GameStaticData.currentPlayer=new Player("test", "test", "testUser", "pass" );
		GameCharacter currentCharacter;
		try {
			currentCharacter = GameStaticData.getGamecharacters().get(0);
			currentCharacter.setPosition(new Position(0,0,false));
			currentCharacter.setExperience(new Experience(0));
			GameMap currentMap = GameStaticData.getMaps().get(0);
			expectedGame =  new Game(NumberUtil.generateSecureRandom(),GameStaticData.currentPlayer,currentCharacter,currentMap);
		} catch (RolePlayGameException e) {
			Assert.fail(e.getErrorMsg());
		}

		}

		@Test
		public void testStartGameForNewGame()
		{


			File file = new File(TestConstants.USER_INPUT_FILE_NEWGAME);
			try {

				Scanner scanner = new Scanner(file);
				Game newGame = GameServiceHelper.INSTANCE.newGame(scanner);
				Assert.assertEquals(expectedGame.getMap(),newGame.getMap());
				Assert.assertEquals(expectedGame.getGameCharacter(), newGame.getGameCharacter());
				Assert.assertEquals(expectedGame.getPlayer(), newGame.getPlayer());

			} catch (FileNotFoundException | RolePlayGameException e) {
				Assert.fail(e.getMessage());
			}
		}

		@Test
		public void testSaveGame()
		{
			File file = new File(TestConstants.USER_INPUT_FILE_SAVE_GAME);
			try {

				Scanner scanner = new Scanner(file);
				Game savedGame = GameServiceHelper.INSTANCE.saveTheGame(expectedGame, scanner);
				Assert.assertEquals(expectedGame, savedGame);
			}catch(FileNotFoundException e)
			{
				Assert.fail(e.getMessage());
			} catch (RolePlayGameException e) {
				Assert.fail(e.getMessage());
			}


		}


	}

