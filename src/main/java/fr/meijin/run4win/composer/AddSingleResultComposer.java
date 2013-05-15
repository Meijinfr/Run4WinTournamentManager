package fr.meijin.run4win.composer;

import org.apache.commons.lang.StringUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

import fr.meijin.run4win.model.Game;
import fr.meijin.run4win.model.Player;
import fr.meijin.run4win.model.Tournament;
import fr.meijin.run4win.util.lang.LangEnum;
import fr.meijin.run4win.util.lang.LangUtils;

public class AddSingleResultComposer extends GenericForwardComposer<Window>{

	private static final long serialVersionUID = -706570803908008843L;
	
	private AnnotateDataBinder binder;

	private Intbox intboxP1ResultRunner;
	private Intbox intboxP1ResultCorporation;
	private Intbox intboxP2ResultRunner;
	private Intbox intboxP2ResultCorporation;
	
	private Checkbox checkboxP1Flatline;
	private Checkbox checkboxP1Mill;
	private Checkbox checkboxP2Flatline;
	private Checkbox checkboxP2Mill;

	private Combobox player1Combobox;
	private Combobox player2Combobox;
	
	private Rows player1Rows;
	private Rows player2Rows;
	
	private Button validateButton;
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		Game g = (Game) arg.get("game");
		
		Tournament t = (Tournament) session.getAttribute("tournament");
		for(Player p : t.players){
			player1Combobox.appendItem(p.nickname);
			player2Combobox.appendItem(p.nickname);
		}
		player1Combobox.setValue(g.player1.nickname);
		player2Combobox.setValue(g.player2.nickname);
		
		page.setAttribute("game", g);
		binder.loadAll();
	}
	
	public void onClick$validateButton (Event e){
		Game g = (Game) page.getAttribute("game");
		if(StringUtils.equals(player2Combobox.getText(), player1Combobox.getText())){
			Messagebox.show(LangUtils.getMessage(LangEnum.IMPOSSIBLE_MATCH));
			return;
		}
		
		if(intboxP1ResultRunner.getValue() == 10 && intboxP2ResultCorporation.getValue() == 10){
			intboxP1ResultRunner.setSclass("red_border");
			intboxP2ResultCorporation.setSclass("red_border");
			binder.loadAll();
			return;
		} else if(intboxP2ResultRunner.getValue() == 10 && intboxP1ResultCorporation.getValue() == 10){
			intboxP2ResultRunner.setSclass("red_border");
			intboxP1ResultCorporation.setSclass("red_border");
			binder.loadAll();
			return;
		}
		
		g.p1Result.resultCorporation = intboxP1ResultCorporation.getValue();
		g.p1Result.resultRunner = intboxP1ResultRunner.getValue();
		g.p1Result.flatlineWin = checkboxP1Flatline.isChecked();
		g.p1Result.millWin = checkboxP1Mill.isChecked();
		
		g.p2Result.resultCorporation = intboxP2ResultCorporation.getValue();
		g.p2Result.resultRunner = intboxP2ResultRunner.getValue();
		g.p2Result.flatlineWin = checkboxP2Flatline.isChecked();
		g.p2Result.millWin = checkboxP2Mill.isChecked();

		Tournament t = (Tournament) session.getAttribute("tournament");
		
		for(Player p : t.players){
			if(!p.forfeit){
				if(StringUtils.equals(p.nickname, player1Combobox.getText())){
					System.out.println("Setting player 1 : "+p.nickname);
					g.setPlayer1(p);
				}
				
				if(StringUtils.equals(p.nickname, player2Combobox.getText())){
					System.out.println("Setting player 2 : "+p.nickname);
					g.setPlayer2(p);
				}
			}
		}
		desktop.setAttribute("newGame",g);
		self.detach();
	}
	
	public void onClick$player1Combobox(Event e){
		player1Rows.setVisible(false);
		player2Rows.setVisible(false);
		binder.loadAll();
	}
	
	public void onClick$player2Combobox(Event e){
		onClick$player1Combobox(null);
	}
	
	public void onClick$cancelButton (Event e){
		self.detach();
	}
	
	public void onChangeResult (){
		
		if(intboxP1ResultRunner.getValue() == 10 && intboxP2ResultCorporation.getValue() == 10){
			intboxP1ResultRunner.setSclass("red_border");
			intboxP2ResultCorporation.setSclass("red_border");
			validateButton.setDisabled(true);
		} else if(intboxP2ResultRunner.getValue() == 10 && intboxP1ResultCorporation.getValue() == 10){
			intboxP2ResultRunner.setSclass("red_border");
			intboxP1ResultCorporation.setSclass("red_border");
			validateButton.setDisabled(true);
		} else {
			validateButton.setDisabled(false);
		}
		
		
		binder.loadAll();
		return;
	}
}
