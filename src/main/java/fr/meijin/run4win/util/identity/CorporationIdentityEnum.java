package fr.meijin.run4win.util.identity;


public enum CorporationIdentityEnum implements IdentityEnum {

	BLANK									("cross","","","",""),
	
	HB_ENGINEERING_THE_FUTURE				("engineering","Engineering the Future", "color : #ac69bb;","01054","Haas-Bioroid"),
	HB_STRONGER_TOGETHER					("stronger","Stronger Together","color : #ac69bb;","02010","Haas-Bioroid"),
	CEREBRAL_IMAGING						("cerebral","Cerebral Imaging","color : #ac69bb;","03001","Haas-Bioroid"),
	CUSTOM_BIOTICS							("custom","Custom Biotics","color : #ac69bb;","03002","Haas-Bioroid"),
	NEXT_DESIGN								("next","NEXT Design","color : #ac69bb;","03003","Haas-Bioroid"),
	THE_FOUNDRY								("foundry","The Foundry","color : #ac69bb;","06021","Haas-Bioroid"),
	
	JINTEKI_PERSONAL_EVOLUTION				("personal","Personal Evolution","color : #c53127;","01067","Jinteki"),
	JINTEKI_REPLICATING_PERFECTION			("replicating","Replicating Perfection","color : #c53127;","02031","Jinteki"),
	HARMONY_MEDTECH							("harmony","Harmony Medtech","color : #c53127;", "05001","Jinteki"),
	TENNIN_INSTITUTE						("tennin","Tennin Institute","color : #c53127;", "05002","Jinteki"),
	NISEI_DIVISION							("nisei","Nisei Division","color : #c53127;", "05003","Jinteki"),
	//GENOMICS_INDUSTRIES					("genomics","Genomics Industries","color : #c53127;", "00003","Jinteki"),
	CHRONOS_PROTOCOL						("chronos","Chronos Protocol","color : #c53127;", "00003","Jinteki"),
	
	NBN_MAKING_NEWS							("making","Making News","color : #ecaa2c;","01080","NBN"),
	NBN_THE_WORLD_IS_YOURS					("world","The World is Yours","color : #ecaa2c;","02114","NBN"),
	NEAR_EARTH								("nearearth","Near-Earth","color : #ecaa2c;","06005","NBN"),
	
	WEYLAND_BUILDING_A_BETTER_WORLD			("building","Building a Better World","color : #4b796c;","01093","Weyland"),
	WEYLAND_BECAUSE_WE_BUILT_IT				("because","Because We Built It","color : #4b796c;","02076","Weyland"),
	GRNDL									("grndl","GRNDL","color : #4b796c;","04097","Weyland"),
	BLUE_SUN								("blue","Blue Sun","color : #4b796c;","06068","Weyland"),
	GAGARIN									("gagarin","Gagarin Deep Space","color : #4b796c;","07002","Weyland"),
	
	THE_SHADOW								("shadow","The Shadow","color : #ccc;","00005","Corporation");
	
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
