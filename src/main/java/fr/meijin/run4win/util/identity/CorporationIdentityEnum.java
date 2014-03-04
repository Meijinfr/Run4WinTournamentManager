package fr.meijin.run4win.util.identity;


public enum CorporationIdentityEnum implements IdentityEnum {

	BLANK									("cross","","","",""),
	
	HB_ENGINEERING_THE_FUTURE				("engineering_the_future","Engineering the Future", "color : #ac69bb;","01054","Haas-Bioroid"),
	HB_STRONGER_TOGETHER					("stronger_together","Stronger Together","color : #ac69bb;","02010","Haas-Bioroid"),
	CUSTOM_BIOTICS							("custom_biotics","Custom Biotics","color : #ac69bb;","03002","Haas-Bioroid"),
	CEREBRAL_IMAGING						("cerebral_imaging","Cerebral Imaging","color : #ac69bb;","03001","Haas-Bioroid"),
	NEXT_DESIGN								("next_design","NEXT Design","color : #ac69bb;","03003","Haas-Bioroid"),
	
	JINTEKI_PERSONAL_EVOLUTION				("personal_evolution","Personal Evolution","color : #c53127;","01067","Jinteki"),
	JINTEKI_REPLICATING_PERFECTION			("replicating_perfection","Replicating Perfection","color : #c53127;","02031","Jinteki"),
	HARMONY_MEDTECH							("harmony_medtech","Harmony Medtech","color : #c53127;", "05001","Jinteki"),
	TENNIN_INSTITUTE						("tennin_institute","Tennin Institute","color : #c53127;", "05002","Jinteki"),
	NISEI_DIVISION							("nisei_division","Nisei Division","color : #c53127;", "05003","Jinteki"),
	
	NBN_MAKING_NEWS							("making_news","Making News","color : #ecaa2c;","01080","NBN"),
	NBN_THE_WORLD_IS_YOURS					("the_world_is_yours","The World is Yours","color : #ecaa2c;","02114","NBN"),
	
	WEYLAND_BUILDING_A_BETTER_WORLD			("building_a_better_world","Building a Better World","color : #4b796c;","01093","Weyland"),
	WEYLAND_BECAUSE_WE_BUILT_IT				("because_we_built_it","Because We Built It","color : #4b796c;","02076","Weyland"),
	GRNDL									("grndl","GRNDL","color : #4b796c;","04097","Weyland");
	
	private String filename;
	private String displayName;
	private String style;
	private String octgnCode;
	private String faction;
	
	private CorporationIdentityEnum(String filename, String displayName, String style, String octgnCode, String faction) {
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
	
	public String getFaction() {
		return faction;
	}
	
}
