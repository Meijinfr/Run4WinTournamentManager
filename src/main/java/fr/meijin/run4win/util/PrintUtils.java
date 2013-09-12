package fr.meijin.run4win.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import fr.meijin.run4win.model.Round;

public class PrintUtils {

	public File exportAppariement(Round round) throws Exception {

		File file = new File("Ronde"+round.roundNumber+".html");

		Properties properties = new Properties();
		properties.put("input.encoding", "utf-8");
		properties.setProperty("resource.loader", "class");
		properties.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		 
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("round", round);
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		 
		VelocityEngine engine = new VelocityEngine();
		engine.init(properties);
		Template template = engine.getTemplate("/round.html");

		 
		// Create a context and add parameters
		VelocityContext context = new VelocityContext(parameters);
		 
		// Render the template for the context into a string
		StringWriter stringWriter = new StringWriter();
		template.merge(context, stringWriter);
		String content = stringWriter.toString();
		
		out.write(content);
		out.close();

		return file;
	}
	
	public File exportFeuilleResultat(Round round) throws Exception {

		File file = new File("/round"+round.roundNumber+".xls");

		Map games = new HashMap<String, List>();
		
		games.put("games", round.games);
		XLSTransformer transformer = new XLSTransformer();
        
		transformer.transformXLS(getXlsTemplatePath(), games, file.getPath());

		return file;
	}
	
	public String getXlsTemplatePath(){
		return this.getClass().getResource("/round.xls").getPath();
	}
}
