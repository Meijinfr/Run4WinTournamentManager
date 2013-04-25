package fr.meijin.run4win.converter;

import org.apache.commons.lang.StringUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

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
			return "/images/"+id.replaceAll(" ", "").toLowerCase()+".gif";
		}
		return "";
	}
	
	public static String coerceToHTML(String id) {
		if(id == null)
			return "";
		if (StringUtils.equals("HB 1", id))
			id = "hb_engineering_the_future";
		else if (StringUtils.equals("HB 2", id))
			id = "hb_stronger_together";
		else if (StringUtils.equals("Jinteki 1", id))
			id = "jinteki_personal_evolution";
		else if (StringUtils.equals("Jinteki 2", id))
			id = "jinteki_replicating_perfection";
		else if (StringUtils.equals("NBN", id))
			id = "nbn_making_news";
		else if (StringUtils.equals("Weyland 1", id))
			id = "weyland_building_a_better_world";
		else if (StringUtils.equals("Weyland 2", id))
			id = "weyland_because_we_built_it";
		else if (StringUtils.equals("Chaos Theory", id))
			return "http://www.run4games.com/forum/images/smilies/chaostheory.png";

		return "http://www.run4games.com/forum/images/smilies/"
				+ id.replaceAll(" ", "").toLowerCase() + ".gif";
	}
}
