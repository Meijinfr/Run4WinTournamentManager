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
		switch (tieBreak) {
		case 1:
			return getPrestigeV2();

		default:
			return getPrestigeV1();
		}
	}
	
	public int getPrestige(int roundNumber){
		switch (tieBreak) {
		case 1:
			return getPrestigeV2(roundNumber);

		default:
			return getPrestigeV1(roundNumber);
		}
	}
	
	public int getPrestigeV1(){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(Game g : games.values()){
			ret+=getPrestigeV1(g);
		}
		
		return ret;
	}
	
	public int getPrestigeV1(int roundNumber){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(int i = 1; i <= roundNumber; i++){
			ret+=getPrestigeV1(games.get(String.valueOf(i)));
		}
		
		return ret;
	}
	
	private int getPrestigeV1(Game g) {
		
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
	
	public int getOpponentsStrength (){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(Game g : games.values()){
			
			if(g.player1.id == this.id){
				
				switch(tieBreak){
				case 1:
					ret += g.player2.getPrestigeV2();
					break;
				default : 
					ret += g.player2.getPrestigeV1();
					break;
				}
				
			} else {
				switch(tieBreak){
				case 1:
					ret += g.player1.getPrestigeV2();
					break;
				default : 
					ret += g.player1.getPrestigeV1();
					break;
				}
			}
		}
		
		return ret;
	}
	
	public int getOpponentsStrength (int roundNumber){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(int i = 1; i <= roundNumber; i++){
			Game g = games.get(String.valueOf(i));
			if(g.player1.id == this.id){
				
				switch(tieBreak){
				case 1:
					ret += g.player2.getPrestigeV2();
					break;
				default : 
					ret += g.player2.getPrestigeV1();
					break;
				}
				
			} else {
				switch(tieBreak){
				case 1:
					ret += g.player1.getPrestigeV2();
					break;
				default : 
					ret += g.player1.getPrestigeV1();
					break;
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
			ret += (this.getPrestigeV2() - p.getPrestigeV2())*100000;
			if (ret == 0)
				ret += (this.getWeakestSide() - p.getWeakestSide())*1000;
			if(ret == 0)
				ret += (this.getOpponentsStrength() - p.getOpponentsStrength())*10;
			break;
		default :
			ret += (this.getPrestigeV1() - p.getPrestigeV1())*100000;
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

	public int getWeakestSide() {
		int winRunner = 0;
		int winCorp = 0;
		
		if(this.id == 0)
			return 0;
		
		for(Game g : games.values()){
			
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
	
	public int getWeakestSide(int roundNumber) {
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
	
	public int getPrestigeV2(){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(Game g : games.values()){
			ret+=getPrestigeV2(g);
		}
		
		return ret;
	}
	
	public int getPrestigeV2(int roundNumber){
		int ret = 0;
		
		if(this.id == 0)
			return 0;
		
		for(int i = 1; i <= roundNumber; i++){
			System.out.println("Get prestige for round "+i+" : "+games.get(String.valueOf(i)));
			ret+=getPrestigeV2(games.get(String.valueOf(i)));
		}
		
		return ret;
	}
	
	private int getPrestigeV2(Game g) {
		
		int prestige = 0;

		if(g.player1.id == this.id){
			
			if(g.p1Result.resultCorporation == 10){
				prestige += 2;
			} else if (g.p1Result.resultCorporation > g.p2Result.resultRunner){
				prestige += 1;
			}
			
			if(g.p1Result.resultRunner == 10){
				prestige += 2;
			}else if (g.p1Result.resultRunner > g.p2Result.resultCorporation){
				prestige += 1;
			}

		} else if (g.player2.id == this.id) {
			
			if(g.p2Result.resultCorporation == 10){
				prestige += 2;
			} else if (g.p2Result.resultCorporation > g.p1Result.resultRunner){
				prestige += 1;
			}
			
			if(g.p2Result.resultRunner == 10){
				prestige += 2;
			}else if (g.p2Result.resultRunner > g.p1Result.resultCorporation){
				prestige += 1;
			}
		}
		
		return prestige;
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
