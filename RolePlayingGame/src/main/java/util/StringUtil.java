package util;

public  class StringUtil {
	
	private StringUtil()
	{
		
	}
	
	public static boolean isEmpty(String input) {
		if (input == null || input.trim().length() == 0)
			return true;
		else
			return false;
	}

}
