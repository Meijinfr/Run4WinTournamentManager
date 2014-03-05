package fr.meijin.run4win.renderer;

import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import fr.meijin.run4win.converter.IdentityConverter;
import fr.meijin.run4win.util.identity.IdentityEnum;

public class IdentityComboitemRenderer implements ListitemRenderer<IdentityEnum>{

	private static final IdentityConverter CONVERTER = new IdentityConverter();
	@Override
	public void render(Listitem item, IdentityEnum data, int index) throws Exception {
		item.setLabel((String) CONVERTER.coerceToUi(data, null));
		item.setStyle(data.getStyle());
		item.setValue(data.name());
	}

}
