package fr.meijin.run4win.util;

import org.apache.commons.lang.StringUtils;

public enum IdentityEnum {

	HB_ENGINEERING_THE_FUTURE				("hb_engineering_the_future","Haas-Bioroid EF"),
	HB_STRONGER_TOGETHER					("hb_stronger_together","Haas-Bioroid ST"),
	CUSTOM_BIOTICS_ENGINEERED_FOR_SUCCESS	("custombiotics","Custom Biotics"),
	CEREBRAL_IMAGING_INFINITE_FRONTIERS		("cerebralimaging","Cerebral Imaging"),
	NEXT_DESIGN_GUARDING_THE_NET			("nextdesign","Next Design"),
	
	JINTEKI_PERSONAL_EVOLUTION				("jinteki_personal_evolution","Jinteki PE"),
	JINTEKI_REPLICATING_PERFECTION			("jinteki_replicating_perfection","Jinteki RP"),
	
	NBN_MAKING_NEWS							("nbn_making_news","NBN MN"),
	NBN_THE_WORLD_IS_YOURS					("nbn_the_world_is_yours","NBN TWY"),
	
	WEYLAND_BUILDING_A_BETTER_WORLD			("weyland_building_a_better_world","Weyland BBW"),
	WEYLAND_BECAUSE_WE_BUILT_IT				("weyland_because_we_built_it","Weyland BWBI"),
	GRNDL									("grndl","GRNDL"),
		
	NOISE									("noise","Noise"),
	WHIZZARD								("whizzard","Whizzard"),
	REINA_ROJA								("reinaroja","Reina Roja"),
	
	GABRIEL									("gabriel","Gabriel"),
	ANDROMEDA								("andromeda","Andromeda"),
	
	KATE									("kate","Kate"),
	CHAOS_THEORY							("chaostheory","Chaos Theory"),
	RIELLE									("rielle","Rielle"),
	THE_PROFESSOR							("theprofessor","The Professor"),
	EXILE									("exile","Exile");
	
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
	
	public static String getDisplayName(String filename){
		for(IdentityEnum identity : values()){
			if(StringUtils.equals(identity.filename, filename)){
				return identity.displayName;
			}
		}
		return null;
	}
}
