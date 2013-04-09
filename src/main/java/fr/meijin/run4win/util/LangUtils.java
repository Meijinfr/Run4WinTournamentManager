package fr.meijin.run4win.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;

import org.apache.commons.lang.StringUtils;

public class LangUtils {
	
	private static String lang;
	
	private static LangUtils instance;
	
	private LangUtils(){}
	
	static{
		instance = new LangUtils();
	}

	public static String getLanguage() {
		
		InputStream stream = LangUtils.class.getResourceAsStream("lang.properties");
		PropertyResourceBundle configProperties;
		try {
			configProperties = new PropertyResourceBundle(stream);
		} catch (IOException e) {
			//Failed to load property file, return default
			return "EN";
		}

		
		return configProperties.getString("lang");
	}
	
	public static LangUtils getInstance(){
		if(instance == null){
			LangUtils utils = new LangUtils();
			LangUtils.lang = LangUtils.getLanguage();
			return utils;
		}else{
			return instance;
		}
	}
	
	public static String getTablesErrorMessage(){
		if(instance == null){
			LangUtils.getInstance();
		}
			
		if(StringUtils.equals(lang, "FR")){
			return "Vous devez spécifier le nombre de tables avant d'ajouter une ronde";
		}
		return "You must specify the number of tables before adding round";
	}
	
	public static String getPlayersErrorMessage(){
		if(instance == null){
			LangUtils.getInstance();
		}
		
		if(StringUtils.equals(lang, "FR")){
			return "Vous devez ajouter des joueurs avant d'ajouter une ronde";
		}
		return "You must add players before adding round";
	}
}
