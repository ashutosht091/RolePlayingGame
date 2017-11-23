package util;

import java.io.PrintStream;
import java.text.MessageFormat;

public class PrintUtil {

	public static final PrintStream outPutStream = System.out;

	private PrintUtil()
	{

	}
	
	public static final void print(String input) {
		outPutStream.print(input);
	}


	public static final void printLn(String input)
	{
		outPutStream.println(input);
	}
	
	



}
