package dao;

import exception.RolePlayGameException;
import model.Player;

/**
 * PlayerDao is the interface to access to the Player persistent storage
 * @author ashutosh
 *
 */

public interface PlayerDao {
	/**
	 * Used to create a new player record in player persistent storage
	 * @param player
	 * @return
	 * @throws RolePlayGameException
	 */
	Player createNewPlayer(Player player,String fileName)throws RolePlayGameException;

	/**
	 * 	Used to get a Player record from storage
	 * @param userName
	 * @return
	 * @throws RolePlayGameException
	 */

	Player getPlayer(String userName,String fileName)throws RolePlayGameException;

}
