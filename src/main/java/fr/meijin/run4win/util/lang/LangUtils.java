package fr.meijin.run4win.util.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class LangUtils {
	
	private static Properties configProperties;

	public static String getMessage(LangEnum messageType) {
		
		if(configProperties == null){
			InputStream stream = instance.getClass().getClassLoader().getResourceAsStream("lang.properties");
			configProperties = new Properties();
			
			try {
				configProperties.load(stream);
			}catch (Exception e) {
				// failed to load
			}finally{
				try {
					stream.close();
				} catch (IOException e) {
					// fail silently
				}
			}
		}
		
		return configProperties.getProperty(messageType.toString());
	}
	
	private static LangUtils instance;
	
	private LangUtils(){
		
	}
	
	static{
		instance = new LangUtils();
	}

}
