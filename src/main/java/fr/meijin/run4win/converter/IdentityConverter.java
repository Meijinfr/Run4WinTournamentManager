package fr.meijin.run4win.converter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;

import fr.meijin.run4win.util.identity.CorporationIdentityEnum;
import fr.meijin.run4win.util.identity.IdentityEnum;
import fr.meijin.run4win.util.identity.RunnerIdentityEnum;

public class IdentityConverter implements TypeConverter {

	@Override
	public Object coerceToBean(Object o, Component c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object coerceToUi(Object o, Component c) {
		if(o instanceof IdentityEnum){
			IdentityEnum id = (IdentityEnum) o;
			
			
			if (c instanceof Image) {
				return "/images/"+id.getFilename()+".png";
			} else if (c instanceof Label) {
				return id.getDisplayName();
			} else {
				return id.getDisplayName();
			}
		}
		return "";
	}
	
	public static String coerceToHTML(IdentityEnum id) {
		if(id == null)
			return "";
		
		if (RunnerIdentityEnum.CHAOS_THEORY ==  id)
			return "http://www.run4games.com/forum/images/smilies/chaostheory.png";
		else if (CorporationIdentityEnum.CUSTOM_BIOTICS == id)
			return "http://www.run4games.com/forum/images/smilies/custombiotic.png";
		else if (RunnerIdentityEnum.THE_PROFESSOR == id)
			return "http://www.run4games.com/forum/images/smilies/theprofessor.png";
		else if (RunnerIdentityEnum.RIELLE == id)
			return "http://www.run4games.com/forum/images/smilies/rielle.png";
		else if (RunnerIdentityEnum.EXILE == id)
			return "http://www.run4games.com/forum/images/smilies/exile.png";

		return "http://www.run4games.com/forum/images/smilies/"+ id + ".gif";
	}

	public String displayFromName(String name) {
		IdentityEnum identity = CorporationIdentityEnum.valueOf(name) != null ? CorporationIdentityEnum.valueOf(name) : RunnerIdentityEnum.valueOf(name);
		return identity.getDisplayName();
	}
}
