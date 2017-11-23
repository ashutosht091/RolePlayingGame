package util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import resources.Resources;

/**
 * This class is used for resource related  operations
 * @author ashutosh
 *
 */
public class ResourceUtil {

	public static final Logger logger = LoggerFactory.getLogger(ResourceUtil.class);
	
	private ResourceUtil()
	{

	}

	public static String getPropertyFromPropFile(final String fileName, String name) {
		Properties prop = new Properties();
		String propertyValue=null;
		try (InputStream input = Resources.class.getResourceAsStream((fileName)))
		{
			prop.load(input);
			propertyValue = prop.getProperty(name);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		} 
		return propertyValue;
	}


	public static String readResourcFromProp(String fileName,String propertyName)
	{
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input =  Resources.class.getResourceAsStream(fileName);
			prop.load(input);

			return prop.getProperty(propertyName);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}
	public static Properties loadPropFile(String fileName) {
		Properties gameProperties = new Properties();
		InputStream input = null;

		try {
			input=Resources.class.getResourceAsStream(fileName);
			gameProperties.load(input);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return gameProperties;
	}


	
}
