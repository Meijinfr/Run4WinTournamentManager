package fr.meijin.run4win.renderer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import fr.meijin.run4win.model.Game;
import fr.meijin.run4win.model.Result;
import fr.meijin.run4win.util.lang.LangEnum;
import fr.meijin.run4win.util.lang.LangUtils;

public class SingleRoundItemRenderer implements ListitemRenderer<Game>{

	@Override
	public void render(Listitem item, Game g, int i) throws Exception {
		createLabelCell(String.valueOf(g.tableNumber)).setParent(item);
		createLabelCell(g.player1.nickname).setParent(item);
		createResultCell(g.p1Result).setParent(item);
		createLabelCell(g.player2.nickname).setParent(item);
		createResultCell(g.p2Result).setParent(item);
		Listcell cell = createListcellButtons(g);
		
		if(g.p1Result.resultCorporation == 0 && g.p1Result.resultRunner == 0 
			&& g.p2Result.resultCorporation == 0 && g.p2Result.resultRunner == 0){
			cell.setStyle("background : #FFADAD;");
		}
		cell.setParent(item);
	}

	private Listcell createLabelCell(String label) {
		if (label != null)
			return new Listcell(label);

		return new Listcell();
	}
	
	private Listcell createResultCell(Result result) {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(result.resultCorporation+result.resultRunner);
		
		if (result.flatlineWin)
			builder.append(" (Flatline)");

		if (result.millWin)
			builder.append(" "+LangUtils.getMessage(LangEnum.MILL));
		
		return new Listcell(builder.toString());
	}
	
	private Listcell createListcellButtons(final Game g){
		Listcell listcell = new Listcell();
		
		Button editButton = new Button(LangUtils.getMessage(LangEnum.EDIT));
		editButton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event e) throws Exception {
				Events.postEvent("onEditGame", (Component) e.getTarget().getParent().getParent().getParent(),  g);
			}
			
			
		});
		editButton.setMold("trendy");
		editButton.setParent(listcell);
		
		return listcell;
	}
}
