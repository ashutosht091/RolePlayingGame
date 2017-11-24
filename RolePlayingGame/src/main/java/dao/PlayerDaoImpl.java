package dao;


import java.util.List;

import constants.Constants;
import exception.GameExceptionType;
import exception.RolePlayGameException;
import model.Player;
import util.FileUtility;

/**
 * This class is implementation of PlayerDao interface
 * @author ashutosh
 *
 */

class PlayerDaoImpl implements PlayerDao {

	public static final PlayerDao INSTANCE = new PlayerDaoImpl();


	FileUtility fileUtility = FileUtility.INSTANCE;

	@Override
	public Player createNewPlayer(Player player,String fileName)throws RolePlayGameException {
		List<Player> players = FileUtility.getEntityListFromFile(fileName,Player.class);
		if(players!=null && !players.isEmpty())
		{

			for(Player existingPlayer : players)
			{
				if(existingPlayer.getUserName().equals(player.getUserName()))
				{
					throw new RolePlayGameException(GameExceptionType.USER_ALREADY_EXIST);
				}
			}
		}
		FileUtility.writeEntityIntoFile(fileName, player);
		return player;

	}

	@Override
	public Player getPlayer(String userName,String fileName) throws RolePlayGameException{
		Player result = null;
		List<Player> players = FileUtility.getEntityListFromFile(fileName,Player.class);
		if(players!=null && !players.isEmpty())
		{
			for(Player playerModel: players)
			{
				if(playerModel.getUserName().equals(userName))
				{
					result = playerModel;
					break;
				}
			}

		}if(result==null)
		{
			throw new RolePlayGameException(GameExceptionType.USERNAME_ERROR);
		}
		return result;


	}

}
