package fr.meijin.run4win.util;

public enum IdentityEnum {

	HB_ENGINEERING_THE_FUTURE		("hb_engineering_the_future","HB 1"),
	HB_STRONGER_TOGETHER			("hb_stronger_together","HB 2"),
	
	JINTEKI_PERSONAL_EVOLUTION		("jinteki_personal_evolution","Jinteki 1"),
	JINTEKI_REPLICATING_PERFECTION	("jinteki_replicating_perfection","Jinteki 2"),
	
	NBN_MAKING_NEWS					("nbn_making_news","NBN"),
	
	WEYLAND_BUILDING_A_BETTER_WORLD	("weyland_building_a_better_world","Weyland 1"),
	WEYLAND_BECAUSE_WE_BUILT_IT		("weyland_because_we_built_it","Weyland 2"),
	
	NOISE							("noise","Noise"),
	WHIZZARD						("whizzard","Whizzard"),
	
	GABRIEL							("gabriel","Gabriel"),
	ANDROMEDA						("andromeda","Andromeda"),
	
	KATE							("kate","Kate"),
	CHAOS_THEORY					("chaosthoery","Chaos Theory");
	
	private String filename;
	
	private String displayName;

	private IdentityEnum(){
		
	}
	
	private IdentityEnum(String filename, String displayName) {
		this.filename = filename;
		this.displayName = displayName;
	}

	public String getFilename() {
		return filename;
	}

	public String getDisplayName() {
		return displayName;
	}
	
}
