package dao;


import constants.Constants;
import util.ResourceUtil;


/**
 * This abstract class is the top level abstraction used in Abstract Factory pattern. 
 * It holds reference to all the persistent stores as separate Factories which are extends the DaoFactory. 
 * To change a DAO implementation just change the class to be loaded in property file.
 * We can switch to another persistence storage without even touching the code.
 * @author ashutosh
 *
 */
public abstract class DaoFactory {

	private static final DaoFactory DATA_STORE =loadDataStore();
	
	
	/**
	 * This abstract method returns the PlayerDao interface to be able to use on the persistence operations.
	 *  The implementation of this class will return the corresponding persistence type's implementation.
	 * 
	 * @return PlayerDao
	 */
	public abstract PlayerDao getGamePlayerDao();

	/**
	 * This abstract method returns the GameDao interface to be able to use on the persistence operations.
	 *  The implementation of this class will return the corresponding persistence type's implementation.
	 * 
	 * @return GameDao
	 */
	public abstract GameDao getGameDao();

	
	
	
	public static DaoFactory getDaoStore()
	{
		return DATA_STORE;
	}

	private static DaoFactory loadDataStore()
	{
		String persistence = ResourceUtil.readResourcFromProp(Constants.PERSISTENCE_CONTEXT_FILE, Constants.PERSISTENCE_CONTEXT_NAME);
		return instantiate(persistence,DaoFactory.class);


	}

	public static <T> T instantiate(final String className, final Class<T> type){
		try{
			return type.cast(Class.forName(className).newInstance());
		} catch(InstantiationException
				| IllegalAccessException
				| ClassNotFoundException e){
			throw new IllegalStateException(e);
		}
	}
}
