package helper;

import static util.PrintUtil.printLn;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.Constants;
import exception.GameExceptionType;
import exception.RolePlayGameException;
import model.Position;
import model.Question;




/**
 * GenericHelper holds helper methods which are useful for service classes.
 * @author ashutosh
 *
 */
public  class GenericHelper {

	public static final Logger logger = LoggerFactory.getLogger(GenericHelper.class);

	private GenericHelper() {

	}

	public static boolean goPrevMenu(String userInput) {
		return Constants.EXIT.equals(userInput);
	}

	public static void checkForExit(String userInput) {
		if (Constants.EXIT.equals(userInput))
			System.exit(0);
	}

	public static String formatMessage(String input, String... params) {
		MessageFormat mf = new MessageFormat(input);
		return mf.format(params);
	}

	public static String getEncodedPassword(String input) throws RolePlayGameException {
		String password = null;
		try {
			byte[] pwdByte = input.getBytes(Constants.ENCODING);
			password = Base64.getEncoder().encodeToString(pwdByte.length >= 2 ? pwdByte : (new String(pwdByte) + Constants.PWD_PADDER).getBytes(Constants.ENCODING));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
			throw new RolePlayGameException(GameExceptionType.ERROR);
		}
		return password;
	}

	
	public static boolean needDifferentInputs(RolePlayGameException exception)
	{
		boolean isHandled = true;
		if(exception.getErrorCode()==GameExceptionType.USER_ALREADY_EXIST.getErrorCode())
		{
			printLn(GameExceptionType.USER_ALREADY_EXIST.getMsg());

		}else if(exception.getErrorCode()==GameExceptionType.USERNAME_ERROR.getErrorCode())
		{
			printLn(GameExceptionType.USERNAME_ERROR.getMsg());
		}
		else if(exception.getErrorCode()==GameExceptionType.PASSWORD_INCORRECT.getErrorCode())
		{
			printLn(GameExceptionType.PASSWORD_INCORRECT.getMsg());
		}else
		{
			isHandled = false;
		}
		return isHandled;

	}

	public static String getZeroOneStringfromPostionArray(Position[][] positionMatrix)
	{
		StringBuilder resultString = new StringBuilder();

		for(int i = 0;i<positionMatrix.length;i++)
		{
			for(int j =0;j<positionMatrix[i].length;j++)
			{
				if(positionMatrix[i][j].isVisited())
				{
					resultString.append(Constants.ONE);
				}else
				{
					resultString.append(Constants.ZERO);
				}
			}
		}

		return resultString.toString();


	}

	public static Position[][] getPostionMatrixFromString(String ZeroOneString ,int row, int column)
	{
		char[] chars = ZeroOneString.toCharArray();
		Position[][] positions = new Position[row][column]; 
		int k = 0;
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<column;j++)
			{
				if(Character.compare('1', chars[k++])==0)
				{

					positions[i][j] =  new Position(i,j,true);
				}else
				{
					positions[i][j] =  new Position(i,j,false);
					
				}
			}
		}
		return positions;

	}
	
	
	public static String getQuestionString(Map<Position,Question> questions)
	{
		
		Set<Position> keys = questions.keySet();
		StringBuilder record  = new StringBuilder();
		for(Position key:keys)
		{
			if(questions.get(key).isAnswered()){
			record.append(questions.get(key).getQuestionId());
			record.append(Constants.FILE_DATA_DELIMITER);
			}
			
		}
		
		return record.toString();
		
	}
	
	
	public static Set<Integer> createSetFromArray(String[] values)
	{
		Set<Integer> set = null;
		if(values!=null && values.length>0)
		{
			set = new HashSet<Integer>();
			for(String value:values)
			{
				try{
				set.add(Integer.parseInt(value));
				}catch(NumberFormatException ne)
				{
					logger.error(ne.getMessage(),ne);
				}
			}
		}
		return set;
	}
}
