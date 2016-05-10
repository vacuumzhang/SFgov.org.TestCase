package com.sfgov.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadProperties {
	
	public static String pValue;

	public static String PropertiesPath="/config.properties";

	public static Logger logger= Logger.getLogger(ReadProperties.class);
	public String propertiesReader(String file, String key) throws IOException {
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream(file);
		prop.load(input);
		String outVale = prop.getProperty(key);
		return outVale;
	}

}
