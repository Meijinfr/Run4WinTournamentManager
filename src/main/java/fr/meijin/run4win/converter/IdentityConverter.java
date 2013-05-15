package fr.meijin.run4win.converter;

import org.apache.commons.lang.StringUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;

import fr.meijin.run4win.util.IdentityEnum;

public class IdentityConverter implements TypeConverter {

	@Override
	public Object coerceToBean(Object o, Component c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object coerceToUi(Object o, Component c) {
		if(o instanceof String){
			String id = (String) o;
			if (c instanceof Image) {
				
				return "/images/"+id.replaceAll(" ", "").toLowerCase()+".gif";
			} else if (c instanceof Label) {
				
				if(StringUtils.equals(id, IdentityEnum.HB_ENGINEERING_THE_FUTURE.getFilename()))
					return IdentityEnum.HB_ENGINEERING_THE_FUTURE.getDisplayName();
				else if(StringUtils.equals(id, IdentityEnum.HB_STRONGER_TOGETHER.getFilename()))
					return IdentityEnum.HB_STRONGER_TOGETHER.getDisplayName();
				else if(StringUtils.equals(id, IdentityEnum.JINTEKI_PERSONAL_EVOLUTION.getFilename()))
					return IdentityEnum.JINTEKI_PERSONAL_EVOLUTION.getDisplayName();
				else if(StringUtils.equals(id, IdentityEnum.JINTEKI_REPLICATING_PERFECTION.getFilename()))
					return IdentityEnum.JINTEKI_REPLICATING_PERFECTION.getDisplayName();
				else if(StringUtils.equals(id, IdentityEnum.NBN_MAKING_NEWS.getFilename()))
					return IdentityEnum.NBN_MAKING_NEWS.getDisplayName();
				else if(StringUtils.equals(id, IdentityEnum.WEYLAND_BUILDING_A_BETTER_WORLD.getFilename()))
					return IdentityEnum.WEYLAND_BUILDING_A_BETTER_WORLD.getDisplayName();
				else if(StringUtils.equals(id, IdentityEnum.WEYLAND_BECAUSE_WE_BUILT_IT.getFilename()))
					return IdentityEnum.WEYLAND_BECAUSE_WE_BUILT_IT.getDisplayName();
				
				else if(StringUtils.equals(id, IdentityEnum.NOISE.getFilename()))
					return IdentityEnum.NOISE.getDisplayName();
				else if(StringUtils.equals(id, IdentityEnum.WHIZZARD.getFilename()))
					return IdentityEnum.WHIZZARD.getDisplayName();
				else if(StringUtils.equals(id, IdentityEnum.GABRIEL.getFilename()))
					return IdentityEnum.GABRIEL.getDisplayName();
				else if(StringUtils.equals(id, IdentityEnum.ANDROMEDA.getFilename()))
					return IdentityEnum.ANDROMEDA.getDisplayName();
				else if(StringUtils.equals(id, IdentityEnum.KATE.getFilename()))
					return IdentityEnum.KATE.getDisplayName();
				else if(StringUtils.equals(id, IdentityEnum.CHAOS_THEORY.getFilename()))
					return IdentityEnum.CHAOS_THEORY.getDisplayName();
				
			}
		}
		return "";
	}
	
	public static String coerceToHTML(String id) {
		if(id == null)
			return "";
		
		if (StringUtils.equals("Chaos Theory", id))
			return "http://www.run4games.com/forum/images/smilies/chaostheory.png";

		return "http://www.run4games.com/forum/images/smilies/"+ id + ".gif";
	}
}
