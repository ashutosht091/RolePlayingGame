package util;

import static org.junit.Assert.*;

import org.junit.Test;

import util.StringUtil;

public class StringUtilTest {
	String emptyString="";
	String nonEmptyString="ABC";
	
	
	@Test
	public void testEmptyString()
	{
		assertEquals(Boolean.TRUE,StringUtil.isEmpty(emptyString));
		assertEquals(Boolean.FALSE,StringUtil.isEmpty(nonEmptyString));
	}
	
}
