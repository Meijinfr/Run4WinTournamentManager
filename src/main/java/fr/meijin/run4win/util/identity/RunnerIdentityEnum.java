package fr.meijin.run4win.util.identity;


public enum RunnerIdentityEnum implements IdentityEnum {

	BLANK									("cross","","","",""),
	
	NOISE									("noise","Noise","color : #e45f30;","01001","Anarch"),
	WHIZZARD								("whizzard","Whizzard","color : #e45f30;","02001","Anarch"),
	REINA_ROJA								("reina","Reina Roja","color : #e45f30;","04041","Anarch"),
	QUETZAL									("quetzal","Quetzal","color : #e45f30;","06052","Anarch"),
	EDWARD_KIM								("edward","Edward Kim","color : #e45f30;","07028","Anarch"),
	
	GABRIEL									("gabriel","Gabriel Santiago","color : #3e6ea9;","01017","Criminal"),
	ANDROMEDA								("andromeda","Andromeda","color : #3e6ea9;","02083","Criminal"),
	LARAMY_FISK								("laramy","Laramy Fisk","color : #3e6ea9;","00002","Criminal"),
	SILHOUETTE								("silhouette","Silhouette","color : #3e6ea9;","05030","Criminal"),
	KEN										("ken","Ken 'Express' Tenma","color : #3e6ea9;","05029","Criminal"),
	IAIN_STIRLING							("iain","Iain Stirling","color : #3e6ea9;","05028","Criminal"),
	LEELA_PATEL								("leela","Leela Patel","color : #3e6ea9;","06095","Criminal"),
	
	KATE									("kate","Kate 'Mac' McCaffrey","color : #6b9141;","01033","Shaper"),
	CHAOS_THEORY							("chaos","Chaos Theory","color : #6b9141;","02046","Shaper"),
	RIELLE									("rielle","Rielle 'Kit' Peddler","color : #6b9141;","03028","Shaper"),
	THE_PROFESSOR							("professor","The Professor","color : #6b9141;","03029","Shaper"),
	EXILE									("exile","Exile","color : #6b9141;","03030","Shaper"),
	NASIR_MEIDAN							("nasir","Nasir Meidan","color : #6b9141;","06017","Shaper"),
	
	THE_MASQUE								("masque","The Masque","color : #ccc;","00006","Runner");
											
	private String filename;
	private String displayName;
	private String style;
	private String octgnCode;
	private String faction;
	
	private RunnerIdentityEnum(String filename, String displayName, String style, String octgnCode, String faction) {
		this.filename = filename;
		this.displayName = displayName;
		this.style = style;
		this.octgnCode = octgnCode;
		this.faction = faction;
	}

	public String getFilename() {
		return filename;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getOctgnCode() {
		return octgnCode;
	}

	public String getStyle() {
		return style;
	}
	
	public String getFaction(){
		return faction;
	}
}
