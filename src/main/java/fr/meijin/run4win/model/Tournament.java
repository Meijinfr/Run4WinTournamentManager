package fr.meijin.run4win.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.meijin.run4win.util.lang.LangEnum;
import fr.meijin.run4win.util.lang.LangUtils;


public class Tournament implements Serializable { 

	private static final long serialVersionUID = 4795850745848913367L;

	public String name;
	
	public int playersCount;
	
	public int tablesCount;
	
	public int rounds;
	
	public int tieBreak;
	
	public List<Player> players;
	
	public List<Round> roundsList;
	
	public List<Ranking> rankings;

	public Tournament(){
		name = LangUtils.getMessage(LangEnum.TOURNAMENT);
		players = new ArrayList<Player>();
		roundsList = new ArrayList<Round>();
		rankings = new ArrayList<Ranking>();
		playersCount = 1;
		tieBreak = 1;
	}
}
