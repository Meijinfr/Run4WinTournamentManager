package fr.meijin.run4win.util.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class LangUtils {

	public static String getMessage(LangEnum messageType) {
		System.out.println("Searching for message type "+messageType.toString());
		InputStream stream = instance.getClass().getClassLoader().getResourceAsStream("lang.properties");
		Properties configProperties;
		String message = null;
		try {
			configProperties = new Properties();
			configProperties.load(stream);
			message = configProperties.getProperty(messageType.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return message;
	}
	
	private static LangUtils instance;
	
	private LangUtils(){}
	
	static{
		instance = new LangUtils();
	}

}
