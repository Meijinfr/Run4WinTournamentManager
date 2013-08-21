package fr.meijin.run4win.renderer;

import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;

import fr.meijin.run4win.model.Player;

public class PlayerResultComboitemRenderer implements ComboitemRenderer<Player>{


	
	@Override
	public void render(Comboitem c, Player p, int i) throws Exception {
		c.setValue(p);
		c.setLabel(p.nickname);
	}

}
