package fr.meijin.run4win.model;

import java.io.Serializable;

public class Result implements Serializable {

	private static final long serialVersionUID = 1392362184518917508L;

	public Integer resultCorporation;
	
	public Integer resultRunner;
	
	public boolean flatlineWin;
	
	public boolean millWin;
	
	public Result(){
		resultCorporation = 0;
		resultRunner = 0;
	}

	public Integer getResultCorporation() {
		return resultCorporation;
	}

	public Integer getResultRunner() {
		return resultRunner;
	}
	
}
