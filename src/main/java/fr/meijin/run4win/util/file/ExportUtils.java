package fr.meijin.run4win.util.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;

import au.com.bytecode.opencsv.CSVWriter;
import fr.meijin.run4win.converter.IdentityConverter;
import fr.meijin.run4win.model.Game;
import fr.meijin.run4win.model.Player;
import fr.meijin.run4win.model.Round;
import fr.meijin.run4win.model.Tournament;

public class ExportUtils {

	public static File exportAsJSON (Tournament tournament) throws Exception{
		
		FileWriter fileWriter = new FileWriter(generateFileName(tournament.name, ".json"));
		
		JSONObject jsonTournament = new JSONObject();
		jsonTournament.put("name", tournament.name);
		int i = 1;
		
		JSONArray playersArray = new JSONArray();
		
		Collections.sort(tournament.players);
		for(Player player : tournament.players){
			JSONObject jsonPlayer = new JSONObject();
			jsonPlayer.put("id", player.id);
			jsonPlayer.put("corpFaction", player.idCorporation.getFaction());
			jsonPlayer.put("matchPoints", player.getPoints());
			jsonPlayer.put("corpIdentity", player.idCorporation.getDisplayName());
			jsonPlayer.put("opponentStrength", player.getOpponentsStrength());
			jsonPlayer.put("runnerFaction", player.idRunner.getFaction());
			jsonPlayer.put("rank", i++);
			jsonPlayer.put("weakSideWins", player.getWeakestSideWins());
			jsonPlayer.put("forfeit", player.forfeit);
			jsonPlayer.put("prestige", player.getPrestige());
			jsonPlayer.put("runnerIdentity", player.idRunner.getDisplayName());
			jsonPlayer.put("name", player.nickname);
			
			playersArray.add(jsonPlayer);
		}
		jsonTournament.put("players", playersArray);
		
		
		fileWriter.write(jsonTournament.toJSONString());
		fileWriter.flush();
		fileWriter.close();
		
		return null;
	}
	
	public static File exportAsHTML (Tournament tournament) throws Exception {

		File file = new File(generateFileName(tournament.name, ".html"));

		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("rounds", tournament.roundsList);
		parameters.put("name", tournament.name);
		
		Collections.sort(tournament.players);
		
		parameters.put("players", tournament.players);
		parameters.put("date", date);
		
		velocityWriteFile(file, "/template.html", parameters);

		return file;
	}
	
	public static File exportAsR4W (Tournament tournament) throws Exception {
		File file = new File(generateFileName(tournament.name, ".r4w"));

		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(tournament);
		oos.flush();
		oos.close();
		fos.flush();
		fos.close();

		return file;
	}
	
	public static File exportAsCSV (Tournament tournament) throws Exception {
		
		File file = new File(generateFileName(tournament.name, ".csv"));
		
		CSVWriter writer = new CSVWriter(new FileWriter(file), ';');
		
		Collections.sort(tournament.players);
		
		int i = 1;
		for(Player player : tournament.players){
			 writer.writeNext  (new String[]{	String.valueOf(i++),
					 							player.nickname,
					 							String.valueOf(player.getPrestige()),
					 							String.valueOf(player.getWeakestSideWins()),
					 							String.valueOf(player.getOpponentsStrength()),
					 							player.idCorporation.getOctgnCode(),
					 							player.idRunner.getOctgnCode()
					 						}
					 			);
		}
		
		writer.close();
		
		return file;
	}
	
	public static File exportRoundAsXLS(Round round) throws Exception {

		File file = new File("/round"+round.roundNumber+".xls");

		Map<String , List<Game>> games = new HashMap<String, List<Game>>();
		
		games.put("games", round.games);
		XLSTransformer transformer = new XLSTransformer();
        
		transformer.transformXLS(getXlsTemplatePath(), games, file.getPath());

		return file;
	}
	
	public static File exportMatchesAsHTML(Round round) throws Exception {

		File file = new File("Ronde"+round.roundNumber+".html");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("round", round);

		velocityWriteFile(file, "/round.html", parameters);

		return file;
	}
	
	private static String generateFileName (String tournamentName, String fileExtension) {
		
		String fileTitle = "Run4Win_"+tournamentName+fileExtension;
				
		StringBuilder finalName = new StringBuilder();
		for (char c : fileTitle.toCharArray()) {
			if (c=='.' || Character.isJavaIdentifierPart(c)) {
				 finalName.append(c);
			}
		}
		
		return finalName.toString();
	}
	
	private static void velocityWriteFile(File file, String templateName, Map<String, Object> parameters) throws Exception{
		Properties properties = new Properties();
		properties.put("input.encoding", "utf-8");
		properties.setProperty("resource.loader", "class");
		properties.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		 
		VelocityEngine engine = new VelocityEngine();
		engine.init(properties);
		Template template = engine.getTemplate(templateName);

		 
		// Create a context and add parameters
		VelocityContext context = new VelocityContext(parameters);
		context.put("identityConverter", new IdentityConverter());
		 
		// Render the template for the context into a string
		StringWriter stringWriter = new StringWriter();
		template.merge(context, stringWriter);
		String content = stringWriter.toString();
		
		out.write(content);
		out.close();
	}

	
	private static String getXlsTemplatePath(){
		return ExportUtils.class.getResource("/round.xls").getPath();
	}
}
