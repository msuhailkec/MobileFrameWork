package com.utils;

import java.io.FileReader;
import java.util.Properties;

public class BaseClass 
{
	private static FileReader fs;
	
	public static Properties readProperties() throws Exception
	{
		fs = new FileReader(System.getProperty("user.dir")+"/src/main/resources/global.properties");
		Properties p = new Properties();  ;
		p.load(fs);
		return p;
	}

}
