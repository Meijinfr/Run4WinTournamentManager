package fr.meijin.run4win.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import fr.meijin.run4win.util.identity.IdentityEnum;


public class Player implements Comparable<Player>, Serializable {
	
	private static final long serialVersionUID = 2757430029692649285L;

	public int id;
	
	public String firstName;
	public String lastName;
	public String nickname;
	
	public IdentityEnum idRunner;
	public IdentityEnum idCorporation;
	
	public boolean forfeit;
	
	public Map<String, Game> games;

	public boolean byeFirstRound;
	
	public int malusPrestige;
	
	public Player () {
		super();
		this.games = new HashMap<String,Game>();
		this.forfeit = false;
		this.byeFirstRound = false;
	}
	
	public int getPrestige(){
		int prestige = 0;
		for(Game game : games.values()){
			if(game.player1.id == this.id){
				prestige += game.p1Result.resultCorporation + game.p1Result.resultRunner;
			} else {
				prestige += game.p2Result.resultCorporation + game.p2Result.resultRunner;
			}
		}
		return prestige;
	}
	
	public int getPrestigeByRound(int roundNumber){
		int prestige = 0;
		for(int i = 1; i <= roundNumber; i++){
			Game game = games.get(String.valueOf(i));
			if(game.player1.id == this.id){
				prestige += game.p1Result.resultCorporation + game.p1Result.resultRunner;
			} else {
				prestige += game.p2Result.resultCorporation + game.p2Result.resultRunner;
			}
		}
		return prestige;
	}
	
	public int getOpponentsStrength(){
		int os = 0;
		for(Game game : games.values()){
			if(game.player1.id == this.id){
				os += game.player2.getPrestige();
			} else {
				os += game.player1.getPrestige();
			}
		}
		return os;
	}
	
	public int getOpponentsStrengthByRound (int roundNumber){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(int i = 1; i <= roundNumber; i++){
			Game g = games.get(String.valueOf(i));
			if(g.player1.id == this.id){
				ret += g.player2.getPrestigeByRound(roundNumber);
			} else {
				ret += g.player1.getPrestigeByRound(roundNumber);
			}
		}
		
		return ret;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Player){
			Player p = (Player) obj;
			return p.id == this.id;
		}
		return false;
	}

	@Override
	public int compareTo(Player p) {
		int ret = 0;

		ret += (p.getPrestige() - this.getPrestige())*100000;
		
		/*if (ret == 0)
			ret += (p.getWeakestSideWins() - this.getWeakestSideWins())*1000;*/
		
		if(ret == 0)
			ret += (p.getOpponentsStrength() - this.getOpponentsStrength())*1000;
		
		/** RANDOM TY FFG ! **/
		if(ret == 0)
			ret += Math.round((Math.random() - Math.random()))*1000;

		return ret;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNickname() {
		return nickname;
	}

	public IdentityEnum getIdRunner() {
		return idRunner;
	}

	public IdentityEnum getIdCorporation() {
		return idCorporation;
	}

	public boolean isForfeit() {
		return forfeit;
	}

	public Map<String, Game> getGames() {
		return games;
	}

}
