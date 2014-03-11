package fr.meijin.run4win.renderer;

import javax.crypto.ExemptionMechanismSpi;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Separator;

import fr.meijin.run4win.converter.IdentityConverter;
import fr.meijin.run4win.model.Game;
import fr.meijin.run4win.model.Player;
import fr.meijin.run4win.model.Result;
import fr.meijin.run4win.util.lang.LangEnum;
import fr.meijin.run4win.util.lang.LangUtils;

public class SingleRoundRowRenderer implements RowRenderer<Game>{

	private static final IdentityConverter IDENTITY_CONVERTER = new IdentityConverter();
	
	@Override
	public void render(Row row, Game g, int i) throws Exception {
		
		createLabel(String.valueOf(g.tableNumber)).setParent(row);
		
		createLabel(g.player1.nickname).setParent(row);
		
		createResultLayout(g.player1, g.p1Result).setParent(row);
		
		createLabel(g.player2.nickname).setParent(row);
		
		createResultLayout(g.player2, g.p2Result).setParent(row);
		
	}

	private Label createLabel(String label) {
		if (label != null)
			return new Label(label);

		return new Label();
	}
	
	private Hlayout createResultLayout(Player player, final Result result) {
		Hlayout hlayout = new Hlayout();
		hlayout.setSpacing("5px");
		
		Image idRunner = new Image();
		idRunner.setSrc((String) IDENTITY_CONVERTER.coerceToUi(player.idRunner, idRunner));
		idRunner.setWidth("36px");
		idRunner.setParent(hlayout);
		
		Hlayout resultRunnerHlayout = createResultHlayoutRunner(result);
		resultRunnerHlayout.setStyle("margin-top : 6px; cursor : pointer;");
		resultRunnerHlayout.setParent(hlayout);
		
		final Image imageMill = new Image();
		imageMill.setTooltiptext(LangUtils.getMessage(LangEnum.MILL));
		imageMill.setStyle("margin-top : 6px; cursor : pointer;");
		if(result.millWin){
			imageMill.setSrc("/images/results/button_mill_active.png");
			imageMill.setHover("/images/results/button_mill_hover_active.png");
		} else {
			imageMill.setSrc("/images/results/button_mill.png");
			imageMill.setHover("/images/results/button_mill_hover.png");
		}
		imageMill.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.millWin = !result.millWin;
				if(result.millWin){
					imageMill.setSrc("/images/results/button_mill_active.png");
					imageMill.setHover("/images/results/button_mill_hover_active.png");
				} else {
					imageMill.setSrc("/images/results/button_mill.png");
					imageMill.setHover("/images/results/button_mill_hover.png");
				}
			}
		});
		imageMill.setParent(hlayout);
		
		Separator separator = new Separator();
		separator.setWidth("15px");
		separator.setParent(hlayout);
		
		Image idCorporation = new Image();
		idCorporation.setSrc((String) IDENTITY_CONVERTER.coerceToUi(player.idCorporation, idCorporation));
		idCorporation.setWidth("36px");
		idCorporation.setParent(hlayout);
		
		Hlayout resultCorporationHlayout = createResultHlayoutCorporation(result);
		resultCorporationHlayout.setStyle("margin-top : 6px");
		resultCorporationHlayout.setParent(hlayout);
		
		final Image imageFlat = new Image();
		imageFlat.setStyle("margin-top : 6px; cursor : pointer;");
		imageFlat.setTooltiptext("Flatline");
		if(result.flatlineWin){
			imageFlat.setSrc("/images/results/button_flat_active.png");
			imageFlat.setHover("/images/results/button_flat_hover_active.png");
		} else {
			imageFlat.setSrc("/images/results/button_flat.png");
			imageFlat.setHover("/images/results/button_flat_hover.png");
		}
		imageFlat.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.flatlineWin = !result.flatlineWin;
				if(result.flatlineWin){
					imageFlat.setSrc("/images/results/button_flat_active.png");
					imageFlat.setHover("/images/results/button_flat_hover_active.png");
				} else {
					imageFlat.setSrc("/images/results/button_flat.png");
					imageFlat.setHover("/images/results/button_flat_hover.png");
				}
			}
		});
		imageFlat.setParent(hlayout);
		
		return hlayout;
	}

	private void setActive(Image image, int value) {
		image.setHover(null);
		image.setSrc("/images/results/button_"+value+"_active.png");
	}

	private void setInactive(Image image, int value) {
		image.setHover("/images/results/button_"+value+"_hover.png");
		image.setSrc("/images/results/button_"+value+".png");
	}
	
	private Hlayout createResultHlayoutCorporation(final Result result){
		Hlayout hlayout = new Hlayout();
		hlayout.setSpacing("0");
		
		final Image image0Corporation = new Image();
		final Image image1Corporation  = new Image();
		final Image image2Corporation  = new Image();
		
		image0Corporation.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultCorporation = 0;
				setActive(image0Corporation , 0);
				setInactive(image1Corporation , 1);
				setInactive(image2Corporation , 2);
			}
		});
		image1Corporation.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultCorporation = 1;
				setActive(image1Corporation , 1);
				setInactive(image0Corporation , 0);
				setInactive(image2Corporation , 2);
			}
		});
		image2Corporation.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultCorporation = 2;
				setActive(image2Corporation , 2);
				setInactive(image1Corporation , 1);
				setInactive(image0Corporation , 0);
			}
		});
		
		switch (result.resultCorporation) {
		case 2:
			setActive(image2Corporation, 2);
			setInactive(image0Corporation, 0);
			setInactive(image1Corporation, 1);
			break;
		case 1:
			setActive(image1Corporation, 1);
			setInactive(image0Corporation, 0);
			setInactive(image2Corporation, 2);
			break;
		default:
			setActive(image0Corporation, 0);
			setInactive(image2Corporation, 2);
			setInactive(image1Corporation, 1);
			break;
		}
		
		image0Corporation.setStyle("cursor : pointer");
		image1Corporation.setStyle("cursor : pointer");
		image2Corporation.setStyle("cursor : pointer");
		
		image0Corporation.setParent(hlayout);
		image1Corporation.setParent(hlayout);
		image2Corporation.setParent(hlayout);
		
		return hlayout;
	}
	
	private Hlayout createResultHlayoutRunner(final Result result){
		Hlayout hlayout = new Hlayout();
		hlayout.setSpacing("0");
		
		final Image image0Runner = new Image();
		final Image image1Runner = new Image();
		final Image image2Runner = new Image();
		
		image0Runner.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultRunner = 0;
				setActive(image0Runner, 0);
				setInactive(image1Runner, 1);
				setInactive(image2Runner, 2);
			}
		});
		image1Runner.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultRunner = 1;
				setActive(image1Runner, 1);
				setInactive(image0Runner, 0);
				setInactive(image2Runner, 2);
			}
		});
		image2Runner.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultRunner = 2;
				setActive(image2Runner, 2);
				setInactive(image1Runner, 1);
				setInactive(image0Runner, 0);
			}
		});
		
		switch (result.resultRunner) {
		case 2:
			setActive(image2Runner, 2);
			setInactive(image0Runner, 0);
			setInactive(image1Runner, 1);
			break;
		case 1:
			setActive(image1Runner, 1);
			setInactive(image0Runner, 0);
			setInactive(image2Runner, 2);
			break;
		default:
			setActive(image0Runner, 0);
			setInactive(image2Runner, 2);
			setInactive(image1Runner, 1);
			break;
		}
		
		image0Runner.setStyle("cursor : pointer");
		image1Runner.setStyle("cursor : pointer");
		image2Runner.setStyle("cursor : pointer");
		
		image0Runner.setParent(hlayout);
		image1Runner.setParent(hlayout);
		image2Runner.setParent(hlayout);
		
		return hlayout;
	}
}
