package fr.meijin.run4win.model;

import java.io.Serializable;

public class PlayerRanking implements Serializable {

	private static final long serialVersionUID = 6728654137356869659L;

	public String nickname;
	
	public int prestige;
	public int points;
	public int opponentsStrength;
	public int opponentsPoints;
	
	public int malusPrestige;
	
	public String getNickname() {
		return nickname;
	}
	
	public int getPrestige() {
		return prestige;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getOpponentsStrength() {
		return opponentsStrength;
	}
	
	public int getOpponentsPoints() {
		return opponentsPoints;
	}
	
}
