package fr.meijin.run4win.composer;

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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Window;

import fr.meijin.run4win.model.Game;
import fr.meijin.run4win.model.Player;
import fr.meijin.run4win.model.Round;
import fr.meijin.run4win.model.Tournament;
import fr.meijin.run4win.util.TournamentUtils;

public class SingleRoundComposer extends GenericForwardComposer<Listbox> {

	private static final long serialVersionUID = 8348836580852634722L;

	private AnnotateDataBinder binder;
	
	private Round round;
	
	private Listbox listboxRound;
	
	public void doAfterCompose (Listbox listbox) throws Exception{
		super.doAfterCompose(listbox);
		binder = new AnnotateDataBinder(listbox);
		round = (Round) execution.getAttribute("round");
		
		Collections.sort(round.games);
		
		listboxRound.setModel(new SimpleListModel<Game>(round.games));
		binder.loadAll();
	}

	public void onEditGame(Event e){
		Game game = (Game) e.getData();
		int idPlayer1 = game.player1.id;
		int idPlayer2 = game.player2.id;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("game", game);
		
		Window window = (Window) Executions.createComponents("add_single_result.zul", null, map);
		window.doModal();
		
		Game newGame = (Game) desktop.getAttribute("newGame");
		
		if(newGame != null) {
			Tournament tournament = (Tournament) session.getAttribute("tournament");
			if(newGame.player1.id != idPlayer1 || newGame.player2.id != idPlayer2){
				round.challenges.add(newGame);
				List<Player> toMatch = new ArrayList<Player>();
				for(Player p : tournament.players){
					if(!p.forfeit || p.id==0){
						toMatch.add(p);
					}
				}
				
				for(Game g : round.challenges){
					for(Player p : tournament.players){
						if(g.player1.id == p.id || g.player2.id == p.id){
							toMatch.remove(p);
						}
					}
				}

				int index = tournament.roundsList.indexOf(round);
				tournament.roundsList.remove(index);
				round = TournamentUtils.doSingleMatch(tournament.roundsList.subList(0, (newGame.roundNumber-1)), toMatch, round.challenges);

				tournament.roundsList.add(index, round);
				
				Collections.sort(round.games);
				listboxRound.setModel(new SimpleListModel<Game>(round.games));
				
			} else {
				System.out.println("Setting game");
				List<Game> games = tournament.roundsList.get(newGame.roundNumber-1).games;
				int indexToremove = -1;
				for(Game g : games)
					if(g.tableNumber == newGame.tableNumber)
						indexToremove = games.indexOf(g);
					
				if(indexToremove >= 0){
					games.remove(indexToremove);
					games.add(newGame);
				}
			}
			
		}
		desktop.removeAttribute("newGame");
		listboxRound.setModel(new SimpleListModel<Game>(round.games));
		listboxRound.renderAll();
		listboxRound.invalidate();
		binder.loadAll();
	}
	
	public void onClick$deleteRound(Event e){
		int ret = Messagebox.show("Etes-vous sûr(e) de vouloir supprimer cette ronde ?", "Supprimer la ronde", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		
		if(ret == Messagebox.YES){
			Events.postEvent("onDeleteRound", page.getFellow("divIndex"),round.roundNumber);
		}
	}
	
}
