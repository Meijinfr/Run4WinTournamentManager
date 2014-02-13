package fr.meijin.run4win.util;

import java.util.HashMap;
import java.util.List;

import fr.meijin.run4win.model.Game;
import fr.meijin.run4win.model.Player;
import fr.meijin.run4win.model.Round;

public class RankingUtils {

	public static void updatePlayersRanking(List<Round> roundsList, List<Player> players) {
		if(roundsList.size() > 0){
			for(Player player : players){
				player.games = new HashMap<String, Game>();
				for(Round round : roundsList){
					for(Game game : round.games){
						if(player.id == game.player1.id || player.id == game.player2.id)
							player.games.put(String.valueOf(game.roundNumber),game);
					}
				}
			}
		}
	}
	
}
