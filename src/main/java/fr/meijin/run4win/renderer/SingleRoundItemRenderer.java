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

public class SingleRoundItemRenderer implements ListitemRenderer<Game>{

	@Override
	public void render(Listitem item, Game g, int i) throws Exception {
		createLabelCell(String.valueOf(g.tableNumber)).setParent(item);
		createLabelCell(g.player1.nickname).setParent(item);
		createResultCorporationCell(g.p1Result).setParent(item);
		createResultRunnerCell(g.p1Result).setParent(item);
		createLabelCell(g.player2.nickname).setParent(item);
		createResultCorporationCell(g.p2Result).setParent(item);
		createResultRunnerCell(g.p2Result).setParent(item);
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
	
	private Listcell createResultCorporationCell(Result result) {
		
		if (result.flatlineWin)
			return new Listcell(result.resultCorporation+" (Flatline)");
		else if(result.resultCorporation != null)
			return new Listcell(result.resultCorporation.toString());
		
		return new Listcell();
	}
	
	private Listcell createResultRunnerCell(Result result) {
		if (result.millWin)
			return new Listcell(result.resultRunner+" (Meule)");
		else if(result.resultRunner != null)
			return new Listcell(result.resultRunner.toString());
		
		return new Listcell();
	}
	
	private Listcell createListcellButtons(final Game g){
		Listcell listcell = new Listcell();
		
		Button editButton = new Button("Modifier");
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
