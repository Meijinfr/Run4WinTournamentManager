package fr.meijin.run4win.renderer;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
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
		
		final Button buttonMill = new Button();
		buttonMill.setTooltiptext(LangUtils.getMessage(LangEnum.MILL));
		buttonMill.setImage("/images/style/mill.png");
		if(result.millWin){
			buttonMill.setSclass("button icon active");
		} else {
			buttonMill.setSclass("button icon");
		}
		buttonMill.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.millWin = !result.millWin;
				if(result.millWin){
					buttonMill.setSclass("button icon active");
				} else {
					buttonMill.setSclass("button icon");
				}
			}
		});
		buttonMill.setParent(hlayout);
		
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
		
		final Button buttonFlat = new Button();
		buttonFlat.setImage("/images/style/flat.png");
		buttonFlat.setTooltiptext("Flatline");
		if(result.flatlineWin){
			buttonFlat.setSclass("button icon active");
		} else {
			buttonFlat.setSclass("button icon");
		}
		buttonFlat.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.flatlineWin = !result.flatlineWin;
				if(result.flatlineWin){
					buttonFlat.setSclass("button icon active");
				} else {
					buttonFlat.setSclass("button icon");
				}
			}
		});
		buttonFlat.setParent(hlayout);
		
		return hlayout;
	}

	private void setActive(Button button, int value) {
		switch (value){
		case 2:
			button.setSclass("button right button_2");
			break;
		case 1:
			button.setSclass("button center button_1");
			break;
		default:
			button.setSclass("button left button_0");
			break;
		}
	}

	private void setInactive(Button button, int value) {
		switch (value){
		case 2:
			button.setSclass("button right");
			break;
		case 1:
			button.setSclass("button center");
			break;
		default:
			button.setSclass("button left");
			break;
		}
	}
	
	private Hlayout createResultHlayoutCorporation(final Result result){
		Hlayout hlayout = new Hlayout();
		hlayout.setSpacing("0");
		
		final Button button0Corporation = new Button("0");
		final Button button1Corporation  = new Button("1");
		final Button button2Corporation  = new Button("2");
		
		button0Corporation.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultCorporation = 0;
				setActive(button0Corporation , 0);
				setInactive(button1Corporation , 1);
				setInactive(button2Corporation , 2);
			}
		});
		button1Corporation.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultCorporation = 1;
				setActive(button1Corporation , 1);
				setInactive(button0Corporation , 0);
				setInactive(button2Corporation , 2);
			}
		});
		button2Corporation.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultCorporation = 2;
				setActive(button2Corporation , 2);
				setInactive(button1Corporation , 1);
				setInactive(button0Corporation , 0);
			}
		});
		
		switch (result.resultCorporation) {
		case 2:
			setActive(button2Corporation, 2);
			setInactive(button0Corporation, 0);
			setInactive(button1Corporation, 1);
			break;
		case 1:
			setActive(button1Corporation, 1);
			setInactive(button0Corporation, 0);
			setInactive(button2Corporation, 2);
			break;
		default:
			setActive(button0Corporation, 0);
			setInactive(button2Corporation, 2);
			setInactive(button1Corporation, 1);
			break;
		}
		
		button0Corporation.setParent(hlayout);
		button1Corporation.setParent(hlayout);
		button2Corporation.setParent(hlayout);
		
		return hlayout;
	}
	
	private Hlayout createResultHlayoutRunner(final Result result){
		Hlayout hlayout = new Hlayout();
		hlayout.setSpacing("0");
		
		final Button button0Runner = new Button("0");
		final Button button1Runner = new Button("1");
		final Button button2Runner = new Button("2");
		
		button0Runner.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultRunner = 0;
				setActive(button0Runner, 0);
				setInactive(button1Runner, 1);
				setInactive(button2Runner, 2);
			}
		});
		button1Runner.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultRunner = 1;
				setActive(button1Runner, 1);
				setInactive(button0Runner, 0);
				setInactive(button2Runner, 2);
			}
		});
		button2Runner.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event e) throws Exception {
				result.resultRunner = 2;
				setActive(button2Runner, 2);
				setInactive(button1Runner, 1);
				setInactive(button0Runner, 0);
			}
		});
		
		switch (result.resultRunner) {
		case 2:
			setActive(button2Runner, 2);
			setInactive(button0Runner, 0);
			setInactive(button1Runner, 1);
			break;
		case 1:
			setActive(button1Runner, 1);
			setInactive(button0Runner, 0);
			setInactive(button2Runner, 2);
			break;
		default:
			setActive(button0Runner, 0);
			setInactive(button2Runner, 2);
			setInactive(button1Runner, 1);
			break;
		}
		
		button0Runner.setParent(hlayout);
		button1Runner.setParent(hlayout);
		button2Runner.setParent(hlayout);
		
		return hlayout;
	}
}
