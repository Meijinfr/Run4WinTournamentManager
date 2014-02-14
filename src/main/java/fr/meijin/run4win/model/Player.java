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

	public int tieBreak;
	
	public int malusPrestige;
	
	public int prestige;
	public int weakestSideWins;
	public int opponentsStrength;
	public int points;
	public int opponentsPoints;
	
	public Player () {
		super();
		this.games = new HashMap<String,Game>();
		this.forfeit = false;
		this.tieBreak = 0;
	}
	
	public int getPrestige(){
		switch (tieBreak) {
		case 1:
			this.prestige = calculatePrestigeV2(games.size());
			return prestige;

		default:
			this.prestige = calculatePrestigeV1(games.size());
			return prestige;
		}
	}
	
	public int getPrestigeByRound(int roundNumber){
		switch (tieBreak) {
		case 1:
			this.prestige = calculatePrestigeV2(roundNumber);
			return prestige;

		default:
			this.prestige = calculatePrestigeV1(roundNumber);
			return prestige;
		}
	}

	public int getPoints(){
		this.points = calculatePoints(games.size());
		return points;
	}

	public int getPointsByRound(int roundNumber){
		return calculatePoints(roundNumber);
	}
	
	public int getOpponentsStrength(){
		this.opponentsStrength = calculateOpponentsStrength(games.size());
		return opponentsStrength;
	}
	
	public int getOpponentsStrengthByRound (int roundNumber){
		return calculateOpponentsStrength(roundNumber);
	}
	
	public int getOpponentsPoints (){
		this.opponentsPoints = calculateOpponentPoints(games.size());
		return opponentsPoints;
	}

	public int getOpponentsPointsByRound(int roundNumber){
		return calculateOpponentPoints(roundNumber);
	}
	
	public int getWeakestSideWins() {
		this.weakestSideWins = calculateWeakestSideWins(games.size());
		return weakestSideWins;
	}
	
	public int getWeakestSideWins(int roundNumber) {
		return calculateWeakestSideWins(roundNumber);
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

		switch(tieBreak) {
		case 1 :
			ret += (p.getPrestige() - this.getPrestige())*100000;
			if (ret == 0)
				ret += (p.getWeakestSideWins() - this.getWeakestSideWins())*1000;
			if(ret == 0)
				ret += (p.getOpponentsStrength() - this.getOpponentsStrength())*10;
			break;
		default :
			ret += (p.getPrestige() - this.getPrestige())*100000;
			if(ret == 0)
				ret += (p.getOpponentsStrength() - this.getOpponentsStrength())*1000;
			
			if (ret == 0)
				ret += (p.getPoints() - this.getPoints())*10;
			break;
		}
		
		if(ret == 0)
			ret -= p.getOpponentsPoints() - this.getOpponentsPoints();
		
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
	
	private int calculatePrestigeV1(int roundNumber){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(int i = 1; i <= roundNumber; i++){
			ret+=calculatePrestigeV1(games.get(String.valueOf(i)));
		}
		
		return ret;
	}
	
	private int calculatePrestigeV1(Game g) {
		
		if(g.p1Result.resultCorporation == g.p2Result.resultCorporation 
				&& g.p1Result.resultRunner == g.p2Result.resultRunner 
				&& (g.p1Result.resultCorporation == 10 || g.p1Result.resultRunner == 10)){
				return 3;
		}
		
		if(g.player1.id == this.id){
			
			if(g.p1Result.resultCorporation == 10 && g.p1Result.resultRunner == 10){
				return 6;
			}
			
			if((g.p1Result.resultCorporation+g.p1Result.resultRunner) > (g.p2Result.resultCorporation+g.p2Result.resultRunner)){
				
				if(g.p2Result.resultCorporation == 10 || g.p2Result.resultRunner == 10){
					return 4;
				}else {
					return 5;
				}
			} else {
				
				if(g.p2Result.resultCorporation == 10 && g.p2Result.resultRunner == 10){
					return 0;
				} else if (g.p1Result.resultCorporation == 10 || g.p1Result.resultRunner == 10) {
					return 2;
				}else {
					return 1;
				}
			}

		} else if (g.player2.id == this.id) {
			
			if(g.p2Result.resultCorporation == 10 && g.p2Result.resultRunner == 10){
				return 6;
			}
			
			if((g.p2Result.resultCorporation+g.p2Result.resultRunner) > (g.p1Result.resultCorporation+g.p1Result.resultRunner)){
				
				if(g.p1Result.resultCorporation == 10 || g.p1Result.resultRunner == 10){
					return 4;
				} else {
					return 5;
				}
				
			} else {
				
				if(g.p1Result.resultCorporation == 10 && g.p1Result.resultRunner == 10){
					return 0;
				} else if (g.p2Result.resultCorporation == 10 || g.p2Result.resultRunner == 10) {
					return 2;
				} else {
					return 1;
				}
			}
		}
		
		return 0;
	}
	
	private int calculatePrestigeV2(int roundNumber){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		
		for(int i = 1; i <= roundNumber; i++){
			ret+=calculatePrestigeV2(games.get(String.valueOf(i)));
		}
		
		return ret;
	}
	
	private int calculatePrestigeV2(Game g) {
		int prestige = 0;
		

		if(g.player1.id == this.id){
			
			if(g.p1Result.resultCorporation == 10){
				prestige += 2;
			} else if (g.p1Result.resultCorporation >= g.p2Result.resultRunner){
				prestige += 1;
			}
			
			if(g.p1Result.resultRunner == 10){
				prestige += 2;
			}else if (g.p1Result.resultRunner >= g.p2Result.resultCorporation){
				prestige += 1;
			}

		} else if (g.player2.id == this.id) {
			
			if(g.p2Result.resultCorporation == 10){
				prestige += 2;
			} else if (g.p2Result.resultCorporation >= g.p1Result.resultRunner){
				prestige += 1;
			}
			
			if(g.p2Result.resultRunner == 10){
				prestige += 2;
			}else if (g.p2Result.resultRunner >= g.p1Result.resultCorporation){
				prestige += 1;
			}
		}
		
		return prestige;
	}
	
	private int calculatePoints(int roundNumber) {
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(int i=1;i <= games.values().size() && i < roundNumber; i++){
			Game g = games.get(String.valueOf(i));
			if(g != null){
				if(g.player1.id == this.id){
					ret+= g.p1Result.resultCorporation + g.p1Result.resultRunner;
				} else {
					ret+= g.p2Result.resultCorporation + g.p2Result.resultRunner;
				}
			}
		}
		
		return ret;
	}
	
	private int calculateOpponentsStrength(int roundNumber){
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
	
	private int calculateOpponentPoints(int roundNumber){
		int ret = 0;
		for(int i=1;i <= roundNumber; i++){
			Game g = games.get(String.valueOf(i));
			if(g != null){
				if(g.player1.id == this.id){
					ret+= g.p2Result.resultCorporation+g.p2Result.resultRunner;
				} else if (g.player2.id == this.id) {
					ret+= g.p1Result.resultCorporation+g.p1Result.resultRunner;
				}
			}
		}
		return ret;
	}
	
	private int calculateWeakestSideWins(int roundNumber) {
		int winRunner = 0;
		int winCorp = 0;
		
		if(this.id == 0)
			return 0;
		
		for(int i = 1; i <= roundNumber; i++){
			Game g = games.get(String.valueOf(i));
			if(g.player1.id == this.id){
				winCorp += (g.p1Result.resultCorporation > g.p2Result.resultRunner) ? 1 : 0;
				winRunner += (g.p1Result.resultRunner > g.p2Result.resultCorporation) ? 1 : 0;
			} else if (g.player2.id == this.id) {
				winCorp += (g.p2Result.resultCorporation > g.p1Result.resultRunner) ? 1 : 0;
				winRunner += (g.p2Result.resultRunner > g.p1Result.resultCorporation) ? 1 : 0;
			}
		}

		return winRunner <= winCorp ? winRunner : winCorp;
	}

}
