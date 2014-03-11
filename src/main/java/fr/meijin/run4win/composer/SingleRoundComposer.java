package fr.meijin.run4win.composer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Window;

import fr.meijin.run4win.model.Game;
import fr.meijin.run4win.model.Player;
import fr.meijin.run4win.model.Round;
import fr.meijin.run4win.model.Tournament;
import fr.meijin.run4win.util.MatchingUtils;
import fr.meijin.run4win.util.TournamentUtils;
import fr.meijin.run4win.util.file.ExportUtils;
import fr.meijin.run4win.util.lang.LangEnum;
import fr.meijin.run4win.util.lang.LangUtils;

public class SingleRoundComposer extends GenericForwardComposer<Grid> {

	private static final long serialVersionUID = 8348836580852634722L;

	private AnnotateDataBinder binder;
	
	private Round round;
	
	private Grid gridRound;
	
	public void doAfterCompose (Grid grid) throws Exception{
		super.doAfterCompose(grid);
		binder = new AnnotateDataBinder(grid);
		round = (Round) execution.getAttribute("round");
		
		Collections.sort(round.games);
		
		gridRound.setModel( new SimpleListModel<Game>(round.games));
		page.setAttribute("round", round);
		binder.loadAll();
	}

	public void onEditGame(Event e){
		
		Game game = (Game) e.getData();
		
		if(game != null) {
			Tournament tournament = (Tournament) session.getAttribute("tournament");

			round.challenges.add(game);
			tournament.roundsList.remove(round);
			List<Player> toMatch = new ArrayList<Player>();
			toMatch.addAll(tournament.players);
			for(Game g : round.challenges){
				toMatch.remove(g.player1);
				toMatch.remove(g.player2);
			}
			round = MatchingUtils.doSingleMatch(tournament.rounds, tournament.roundsList, toMatch, round.challenges);
			tournament.roundsList.add(round);
		}
		gridRound.setModel(new SimpleListModel<Game>(round.games));
		gridRound.renderAll();
		gridRound.invalidate();
		binder.loadAll();
	}
	
	public void onClick$deleteRound(Event e){
		int ret = Messagebox.show(LangUtils.getMessage(LangEnum.DELETE_ROUND_MESSAGE), LangUtils.getMessage(LangEnum.DELETE_ROUND_TITLE), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		
		if(ret == Messagebox.YES){
			Events.postEvent("onDeleteRound", page.getFellow("divIndex"),round.roundNumber);
		}
	}
	
	public void onClick$printAppariement (Event e) throws Exception{
		File roundFile = ExportUtils.exportMatchesAsHTML(round);
		Filedownload.save(roundFile, "application/excel");
	}
	
	public void onClick$printFeuilleResultat (Event e) throws Exception{
		File roundFile = ExportUtils.exportRoundAsXLS(round);
		Filedownload.save(roundFile, "application/excel");
	}
}
