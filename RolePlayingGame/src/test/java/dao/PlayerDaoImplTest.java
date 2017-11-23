package dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import constant.TestConstants;
import dao.DaoFactory;
import exception.GameExceptionType;
import exception.RolePlayGameException;
import model.Player;

public class PlayerDaoImplTest {

	Player player ;
	@Before
	public void init()
	{
		 player = new Player("Tom", "Jay", "tomm", "abc");
	
		
	}
	@Test
	public void testduplicateRegistratonOFPlayer()
	{
		try {
			DaoFactory.getDaoStore().getGamePlayerDao().createNewPlayer(player,TestConstants.TEST_FILE_NAME);
			DaoFactory.getDaoStore().getGamePlayerDao().createNewPlayer(player,TestConstants.TEST_FILE_NAME);
		} catch (RolePlayGameException e) {
			Assert.assertEquals(GameExceptionType.USER_ALREADY_EXIST.getErrorCode(),e.getErrorCode());
		}
	}
	
	
	public void testWrongUserName()
	{
		player.setUserName("Foo");
		try {
			DaoFactory.getDaoStore().getGamePlayerDao().getPlayer(player.getUserName(),TestConstants.TEST_FILE_NAME);
		} catch (RolePlayGameException e) {
			Assert.assertEquals(GameExceptionType.USERNAME_ERROR, e.getErrorCode());
		}
		
	}
}
