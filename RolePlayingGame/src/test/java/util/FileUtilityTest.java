package util;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import constant.TestConstants;
import exception.RolePlayGameException;
import model.Player;

public class FileUtilityTest {


	@Test 
	public void testReadWriteInFile()
	{
		Player player = new Player("test","test","testUser","pass");

		try {
			FileUtility.writeEntityIntoFile(TestConstants.TEST_FILE_NAME, player);
			List<Player> results = (List<Player>) FileUtility.getEntityListFromFile(TestConstants.TEST_FILE_NAME, Player.class);
			Set<Player> players  = new HashSet<>(results);
			assertTrue(players.contains(player));		
		}catch (RolePlayGameException e) {
			Assert.fail(e.getMessage());
		}
	}



}
