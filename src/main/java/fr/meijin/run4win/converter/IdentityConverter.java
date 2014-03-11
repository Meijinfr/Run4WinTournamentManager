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
				return "/images/identities/"+id.getFilename()+".png";
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

		return "http://meijin91.free.fr/idnetrunner/"+ id.getFilename() + ".png";
	}

	public String displayFromName(String name) {
		IdentityEnum identity = CorporationIdentityEnum.valueOf(name) != null ? CorporationIdentityEnum.valueOf(name) : RunnerIdentityEnum.valueOf(name);
		return identity.getDisplayName();
	}
}
