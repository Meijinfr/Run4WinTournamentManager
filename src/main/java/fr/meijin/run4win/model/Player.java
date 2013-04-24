package fr.meijin.run4win.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Player implements Comparable<Player>, Serializable {
	
	private static final long serialVersionUID = 2757430029692649285L;

	public int id;
	
	public String firstName;
	
	public String lastName;
	
	public String nickname;
	
	public String idRunner;
	
	public String idCorporation;
	
	public boolean forfeit;
	
	public Map<String, Game> games;

	public int tieBreak;
	
	public int malusPrestige;
	
	public Player () {
		super();
		this.games = new HashMap<String,Game>();
		this.forfeit = false;
		this.tieBreak = 0;
	}
	
	public int getPrestige(){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(Game g : games.values()){
			ret+=getPrestige(g);
		}
		
		return ret;
	}
	
	public int getPrestige(int roundNumber){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(int i=0;i < games.values().size() && i < roundNumber; i++){
			Game g = games.get(String.valueOf(i));
			
			if(g != null){
				ret+= getPrestige(g);
			}
		}
		
		return ret;
	}
	
	private int getPrestige(Game g) {
		int prestige = 0;
		
		if(g.p1Result.resultCorporation == g.p2Result.resultCorporation 
				&& g.p1Result.resultRunner == g.p2Result.resultRunner 
				&& (g.p1Result.resultCorporation == 10 || g.p1Result.resultRunner == 10)){
				return 3;
		}
		
		if(g.player1.id == this.id){
			if(g.p1Result.resultCorporation == 10){
				prestige += 2;
			}
			if(g.p1Result.resultRunner == 10){
				prestige += 2;
			}
			if((g.p1Result.resultRunner < 10 && g.p2Result.resultCorporation < 10)
				||(g.p1Result.resultCorporation < 10 && g.p2Result.resultRunner < 10)){
				prestige += 1;
			}
			if(prestige == 4 || (g.p1Result.resultCorporation+g.p1Result.resultRunner) > (g.p2Result.resultCorporation+g.p2Result.resultRunner)){
				prestige += 2;
			}
		} else {
			if(g.p2Result.resultCorporation == 10){
				prestige += 2;
			}
			if(g.p2Result.resultRunner == 10){
				prestige += 2;
			}
			if((g.p2Result.resultRunner < 10 && g.p1Result.resultCorporation < 10)
					||(g.p2Result.resultCorporation < 10 && g.p1Result.resultRunner < 10)){
					prestige += 1;
			}
			if(prestige == 4 || (g.p2Result.resultCorporation+g.p2Result.resultRunner) > (g.p1Result.resultCorporation+g.p1Result.resultRunner)){
				prestige += 2;
			}
		}
		return prestige;
	}

	public int getPoints(){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(Game g : games.values()){
			
			if(g.player1.id == this.id){
				ret += g.p1Result.resultCorporation + g.p1Result.resultRunner;
			} else {
				ret += g.p2Result.resultCorporation + g.p2Result.resultRunner;
			}
		}
		return ret;
	}
	
	public int getPoints(int roundNumber){
		int ret = 0;
		for(int i=1;i < games.values().size() && i < roundNumber; i++){
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
	
	public int getOpponentsStrength (){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(Game g : games.values()){
			
			if(g.player1.id == this.id){
				ret += g.player2.getPrestige();
			} else {
				ret += g.player1.getPrestige();
			}
		}
		
		return ret;
	}
	
	public int getOpponentsStrength(int roundNumber){
		int ret = 0;
		for(int i=0;i < games.values().size() && i < roundNumber; i++){
			Game g = games.get(String.valueOf(i));
			if(g != null){
				if(g.player1.id == this.id){
					ret+= g.player2.getPrestige();
				} else {
					ret+= g.player1.getPrestige();
				}
			}
		}
		return ret;
	}
	
	public int getOpponentsPoints (){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(Game g : games.values()){
			
			if(g.player1.id == this.id){
				ret += g.p2Result.resultCorporation+g.p2Result.resultRunner;
			} else if (g.player2.id == this.id) {
				ret += g.p1Result.resultCorporation+g.p1Result.resultRunner;
			}
		}

		return ret;
	}

	public int getOpponentsPoints(int roundNumber){
		int ret = 0;
		for(int i=0;i < games.values().size() && i < roundNumber; i++){
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

		ret += (this.getPrestige() - p.getPrestige())*100000;
		
		switch(tieBreak) {
		case 1 :
			if (ret == 0)
				ret += (this.getPoints() - p.getPoints())*1000;
			
			if(ret == 0)
				ret += (this.getOpponentsStrength() - p.getOpponentsStrength())*10;
			break;
		case 2 :
			if (ret == 0)
				ret += (this.getPoints() - p.getPoints())*1000;
			break;
		default :
			if(ret == 0)
				ret += (this.getOpponentsStrength() - p.getOpponentsStrength())*1000;
			
			if (ret == 0)
				ret += (this.getPoints() - p.getPoints())*10;
			break;
		}
		
		if(ret == 0)
			ret -= this.getOpponentsPoints() - p.getOpponentsPoints();
		
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

	public String getIdRunner() {
		return idRunner;
	}

	public String getIdCorporation() {
		return idCorporation;
	}

	public boolean isForfeit() {
		return forfeit;
	}

	public Map<String, Game> getGames() {
		return games;
	}

}
