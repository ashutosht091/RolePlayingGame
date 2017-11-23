package util;


import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.Constants;
import exception.GameExceptionType;
import exception.RolePlayGameException;

/**
 * This is a Utility class . This has functions to read and write data in a file
 * @author ashutosh
 *
 */
/**
 * @author ashutosh
 *
 */
public class FileUtility {

	public static final Logger logger = LoggerFactory.getLogger(FileUtility.class);
	public static final FileUtility INSTANCE = new  FileUtility();

	private FileUtility()
	{

	}


	/**
	 * This  method is used to Serialize object into file
	 * @param fileName
	 * @param t
	 * @return
	 * @throws RolePlayGameException
	 */
	public static <T> boolean saveObjectIntoFile(String fileName ,T t) throws RolePlayGameException
	{
		boolean isPersisted = false;
		try(FileOutputStream fileOutPutStream = new FileOutputStream(new File(fileName)))
		{
			ObjectOutputStream objectOutPutStream = new ObjectOutputStream(fileOutPutStream);
			objectOutPutStream.writeObject(t);
			isPersisted = true;

		} catch (FileNotFoundException e) {
			logger.error("file not found",fileName,e);
			throw new RolePlayGameException(GameExceptionType.FILE_WRITE_ERROR);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			throw new RolePlayGameException(GameExceptionType.FILE_WRITE_ERROR);
		}
		return isPersisted;

	}
	/**
	 * This class is used to DE-Serialize object from file
	 * It loads all the object from the file in param
	 * @param fileName
	 * @param clazz
	 * @return
	 * @throws RolePlayGameException
	 */
	public static <T> List<T> getObjectFromFile(String fileName , Class<T> clazz) throws RolePlayGameException
	{

		List<T> entities = new ArrayList<>();
		boolean cont = true;
		try (FileInputStream fi = new FileInputStream(new File(fileName)))
		{
			ObjectInputStream oi = new ObjectInputStream(fi);
			while(cont){
				T obj = clazz.cast(oi.readObject());
				if(obj != null)
					entities.add(obj);
				else
					cont = false;
			}
		}catch(EOFException e)
		{
			logger.info(e.getMessage(),e);
		}
		catch(ClassNotFoundException |IOException e1)
		{
			logger.error(e1.getMessage(),e1);
			throw new RolePlayGameException(GameExceptionType.FILE_READ_ERROR);
		} 

		return 	 entities ;
	}


	/**
	 * this function write record as text 
	 * @param fileName
	 * @param record
	 * @return savedState
	 * @throws RolePlayGameException
	 */
	public static boolean writeRecordsIntoFile(String fileName , String record ) throws RolePlayGameException
	{
		boolean isdataWritten  = false;

		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)))
		{
			writer.write(record);
			writer.newLine();
			isdataWritten = true;
		}catch(IOException ex)
		{
			logger.error(ex.getMessage(),ex);
			throw new RolePlayGameException(GameExceptionType.FILE_WRITE_ERROR);
		}


		return isdataWritten;



	}
	/**
	 * This method writes an object into file . file has headers which specifies which value goes to which column
	 * This method get values of the object through reflection and then write it to file
	 * @param fileName
	 * @param t
	 * @return
	 * @throws RolePlayGameException
	 */

	public static  <T> boolean writeEntityIntoFile(String fileName,T t) throws RolePlayGameException
	{
		boolean isWritten = false;
		Class clazz = t.getClass();
		Method[] methods = clazz.getMethods();
		List<String> records = getRecordsFromFile(fileName);
		String[] columns = records.get(0).trim().split(Constants.DELIMITER_SPACE);
		records.remove(0);
		StringBuilder recordToUppend = new StringBuilder();

		try {

			for(String column :columns)
			{
				for(Method method : methods)
				{
					if(ReflectionUtil.isGetter(method) && method.getName().contains(column))
					{

						recordToUppend.append(method.invoke(t).toString());
						recordToUppend.append(Constants.DELIMITER_SPACE);

					}

				}
			}
			isWritten = writeRecordsIntoFile(fileName,recordToUppend.toString());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | RolePlayGameException  e) {
			logger.error(e.getMessage(),e);
			throw new RolePlayGameException(GameExceptionType.FILE_WRITE_ERROR);
		}
		return isWritten;
	}


	/**
	 * This method gets input as class of entity and file name. It reads column information from file and create list of entities
	 * @param fileName
	 * @param clazz
	 * @return list of Entity
	 * @throws RolePlayGameException
	 */

	public static <T> List<T> getEntityListFromFile(String fileName,Class<T> clazz) throws RolePlayGameException
	{
		Method[] methods = clazz.getMethods();
		List<String> records = getRecordsFromFile(fileName);
		String[] columns = records.get(0).split(Constants.DELIMITER_SPACE);
		records.remove(0);
		List<T> result = new ArrayList<>();
		try {

			for(String record:records){
				T t = clazz.newInstance();
				if(record.trim().length()>0){
					String[] values = record.trim().split(Constants.DELIMITER_SPACE);
					int i = 0;
					for(String column :columns)
					{
						for(Method method : methods)
						{

							if(ReflectionUtil.isSetter(method) && method.getName().contains(column))
							{

								method.invoke(t, values[i++]);
								break;

							}
						}
					}
					result.add(t);
				}
			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			logger.error(e.getMessage(),e);
			throw new RolePlayGameException(GameExceptionType.FILE_READ_ERROR);
		}
		return result;



	}



	/**
	 * This method return record as list of lines in file
	 * @param fileName
	 * @return
	 * @throws RolePlayGameException
	 */
	public static List<String> getRecordsFromFile(String fileName) throws RolePlayGameException
	{
		List<String> record = null;
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			record = stream.collect(Collectors.toList());

		}catch(IOException exception)
		{
			logger.error(exception.getMessage(),exception);
			throw new RolePlayGameException(GameExceptionType.FILE_READ_ERROR);

		}
		return record;
	}


}
