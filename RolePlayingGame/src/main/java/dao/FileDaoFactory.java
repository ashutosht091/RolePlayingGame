package dao;

/**
 *  FileDaoFactory is the File persistence implementation of DaoFactory class.
 * @author ashutosh
 *
 */
public class FileDaoFactory extends DaoFactory {

	
	@Override
	public GameDao getGameDao() {
		return GameDaoImpl.INSTANCE;
	}

	@Override
	public PlayerDao getGamePlayerDao() {
		return PlayerDaoImpl.INSTANCE;
	}

}
