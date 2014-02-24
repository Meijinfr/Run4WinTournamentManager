package fr.meijin.run4win.util;

import java.util.ArrayList;

import fr.meijin.run4win.model.Player;
import fr.meijin.run4win.model.PlayerRanking;
import fr.meijin.run4win.model.Ranking;
import fr.meijin.run4win.model.Tournament;
import fr.meijin.run4win.util.identity.CorporationIdentityEnum;
import fr.meijin.run4win.util.identity.RunnerIdentityEnum;



public class TournamentUtils {

	public static void reloadRanking(Tournament tournament){
		tournament.rankings = new ArrayList<Ranking>();
		RankingUtils.updatePlayersRanking(tournament.roundsList, tournament.players);
		if(tournament.rounds >= 1){
			for(int i = 1; i < tournament.roundsList.size(); i++){
				Ranking ranking = new Ranking();
				ranking.roundNumber = i;
				for(Player p : tournament.players){
					PlayerRanking pr = new PlayerRanking();
					pr.nickname = p.nickname;
					pr.prestige = p.getPrestigeByRound(i);
					pr.weakestSideWins = p.getWeakestSideWins(i);
					pr.opponentsStrength = p.getOpponentsStrengthByRound(i);
					ranking.playerRankings.add(pr);
				}
				
				tournament.rankings.add(ranking);
			}
		}
	}
	
	public static Player createByePlayer(){
		Player p = new Player();
		p.id=0;
		p.nickname = "BYE";
		p.forfeit = true;
		p.idCorporation = CorporationIdentityEnum.BLANK;
		p.idRunner = RunnerIdentityEnum.BLANK;
		return p;
	}
}
