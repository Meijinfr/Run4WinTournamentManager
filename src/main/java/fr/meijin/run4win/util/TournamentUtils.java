package fr.meijin.run4win.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.meijin.run4win.model.Game;
import fr.meijin.run4win.model.Player;
import fr.meijin.run4win.model.Round;


public class TournamentUtils {
	
	public static Round doSingleMatch(int roundNumber, List<Round> lastRounds, List<Player> toMatch, List<Game> matched){
		Round sr = new Round();
		sr.roundNumber = roundNumber;
		
		List<Player> matchedPlayers = new ArrayList<Player>();
		
		while(toMatch.size()>0){
			boolean foundMatch = false;
			while (!foundMatch){
				
				for(int i = 0; i < toMatch.size(); i++){
					for(int j = 0; j < toMatch.size(); j++ ){
						if(i < toMatch.size() && j < toMatch.size()){
							if(i != j && !havePlayedEachOther(lastRounds, toMatch.get(j), toMatch.get(i))){
								Player p1 = toMatch.get(i);
								Player p2 = toMatch.get(j);
								Game g  = createMatchGame(sr.roundNumber, lastRounds, p1, p2, matched);
								matchedPlayers.add(p1);
								matchedPlayers.add(p2);
								
								toMatch.remove(p1);
								toMatch.remove(p2);
								
								matched.add(g);
								foundMatch = true;
								System.out.println("Matched : "+g.player1.nickname+" ["+g.player1.getPrestige()+"] vs "+g.player2.nickname+" ["+g.player2.getPrestige()+"] on "+g.tableNumber);
								break;
							}
						}
					}
					if(foundMatch)
						break;
				}
				
				if(!foundMatch){
					Player p1 = toMatch.get(0);
					Player p2 = toMatch.get(1);
					Game g  = createMatchGame(sr.roundNumber, lastRounds, p1, p2, matched);
					matchedPlayers.add(p1);
					matchedPlayers.add(p2);
					
					toMatch.remove(p1);
					toMatch.remove(p2);
					
					matched.add(g);
					foundMatch = true;
					System.out.println("Matched : "+g.player1.nickname+" ["+g.player1.getPrestige()+"] vs "+g.player2.nickname+" ["+g.player2.getPrestige()+"] on "+g.tableNumber);
				}
			}
		}
		
		sr.games = matched;
		return sr;
	}
	
	private static Game createMatchGame(int roundNumber, List<Round> lastRounds, Player p1, Player p2, List<Game> matched) {
		Game sg = new Game();
		sg.player1 = p1;
		sg.player2 = p2;
		sg.roundNumber = roundNumber;
		
		int tableNumber = 1;
		for(Game g : matched)
			if(g.tableNumber == tableNumber)
				tableNumber++;
			else
				break;
		
		sg.tableNumber = tableNumber;
		
		if(sg.player1.forfeit){
			sg.p1Result.resultCorporation = 0;
			sg.p1Result.resultRunner = 0;
			sg.p2Result.resultCorporation = 10;
			sg.p2Result.resultRunner = 10;
		}else if (sg.player2.forfeit){
			sg.p2Result.resultCorporation = 0;
			sg.p2Result.resultRunner = 0;
			sg.p1Result.resultCorporation = 10;
			sg.p1Result.resultRunner = 10;
		}

		return sg;
	}

	private static boolean havePlayedEachOther(List<Round> lastRounds, Player p1, Player p2){
		for(Round r : lastRounds){
			for(Game g : r.games){
				if(g.player1 == p1 && g.player2 == p2 || g.player1 == p2 && g.player2 == p1)
					return true;
			}
		}
		return false;
	}

	public static void updatePlayersRanking(List<Round> roundsList, List<Player> players) {
		if(roundsList.size() > 0){
			for(Player player : players){
				player.games = new HashMap<String, Game>();
				for(Round round : roundsList){
					for(Game game : round.games){
						if(player.id == game.player1.id || player.id == game.player2.id)
							player.games.put(String.valueOf(game.roundNumber),game);
						System.out.println("New record in player "+player.nickname+" with game on round "+game.roundNumber);
					}
				}
			}
		}
	}
}
