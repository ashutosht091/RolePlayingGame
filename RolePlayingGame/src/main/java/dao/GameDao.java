package dao;

import java.util.List;

import exception.RolePlayGameException;
import model.Game;




/**
 * GameDao is the interface to access to the game list persistent storage
 * @author ashutosh
 *
 */

public interface GameDao {

	/**
	 *Used to get the game list from the given file for a user as a List of Game object.
	 * @param filename
	 * @return List<Game>
	 * @throws RolePlayeGameException
	 */
	public List<Game> getGameList(String gmaeFileName, String userName,String playerFileName)throws RolePlayGameException;



	/**
	 * This method is use to save the game for a players
	 * @param filename
	 * @param game
	 * @return save Status
	 * @throws RolePlayeGameException
	 */
	public boolean saveGameState(String filename,Game game)throws RolePlayGameException;


}
