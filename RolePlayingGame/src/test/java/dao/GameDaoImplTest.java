package dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
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

public class GameDaoImplTest {

	

	
	@Test
	public void testGameSaveAndGetGameList()
	{
		GameDao gameDaoImpl  = DaoFactory.getDaoStore().getGameDao();
		GameStaticData.currentPlayer = new Player("test", "test", "testUser" ,"pass");
		try {
		GameCharacter gameCharacter  = GameStaticData.getGamecharacters().get(0);
		gameCharacter.setExperience(new Experience(1));
		gameCharacter.setPosition(new Position(0,0,true));
		GameMap map = GameStaticData.getMaps().get(0);
		Game game = new Game(NumberUtil.generateSecureRandom(),TestConstants.player,gameCharacter,map);
		
			gameDaoImpl.saveGameState(TestConstants.FILE_NAME_SAVE_GAME, game);
			List<Game> gameList =  gameDaoImpl.getGameList(TestConstants.FILE_NAME_SAVE_GAME, "testUser",TestConstants.TEST_FILE_NAME);
			Boolean isPresent=false;
			for(Game gameReturned:gameList)
			{
				if(gameReturned.getGameId()==game.getGameId())
					isPresent = true;
			}
			assertTrue(isPresent);
		
		} catch (RolePlayGameException e) {
			Assert.fail(e.getErrorMsg());
		}
	

	}

}
