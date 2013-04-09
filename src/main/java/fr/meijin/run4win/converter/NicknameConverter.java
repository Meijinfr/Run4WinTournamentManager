package fr.meijin.run4win.converter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.TypeConverter;

import fr.meijin.run4win.model.Player;

public class NicknameConverter implements TypeConverter {

	@Override
	public Object coerceToBean(Object o, Component c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object coerceToUi(Object o, Component c) {
		if(o instanceof Player){
			Player p = (Player) o;
			return p.nickname;
		}
		return null;
	}

}
