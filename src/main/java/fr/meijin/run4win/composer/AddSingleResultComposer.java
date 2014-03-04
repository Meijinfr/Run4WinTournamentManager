package fr.meijin.run4win.composer;

import org.apache.commons.lang.StringUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
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

	private Image image0RunnerP1;
	private Image image1RunnerP1;
	private Image image2RunnerP1;
	
	private Image image0CorporationP1;
	private Image image1CorporationP1;
	private Image image2CorporationP1;
	
	private Image image0RunnerP2;
	private Image image1RunnerP2;
	private Image image2RunnerP2;
	
	private Image image0CorporationP2;
	private Image image1CorporationP2;
	private Image image2CorporationP2;

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
		
		setImages(g);
		
		page.setAttribute("game", g);
		binder.loadAll();
	}
	
	private void setImages(Game g) {
		switch(g.p1Result.resultCorporation){
		case 2:
			setInactive(image0CorporationP1,0);
			setInactive(image1CorporationP1,1);
			setActive(image2CorporationP1,2);
			break;
		case 1:
			setInactive(image0CorporationP1,0);
			setInactive(image2CorporationP1,2);
			setActive(image1CorporationP1,1);
			break;
		default:
			setInactive(image2CorporationP1,2);
			setInactive(image1CorporationP1,1);
			setActive(image0CorporationP1,0);
			break;
		}
		
		switch(g.p1Result.resultRunner){
		case 2:
			setInactive(image0RunnerP1,0);
			setInactive(image1RunnerP1,1);
			setActive(image2RunnerP1,2);
			break;
		case 1:
			setInactive(image0RunnerP1,0);
			setInactive(image2RunnerP1,2);
			setActive(image1RunnerP1,1);
			break;
		default:
			setInactive(image2RunnerP1,2);
			setInactive(image1RunnerP1,1);
			setActive(image0RunnerP1,0);
			break;
		}
		
		switch(g.p2Result.resultCorporation){
		case 2:
			setInactive(image0CorporationP2,0);
			setInactive(image1CorporationP2,1);
			setActive(image2CorporationP2,2);
			break;
		case 1:
			setInactive(image0CorporationP2,0);
			setInactive(image2CorporationP2,2);
			setActive(image1CorporationP2,1);
			break;
		default:
			setInactive(image2CorporationP2,2);
			setInactive(image1CorporationP2,1);
			setActive(image0CorporationP2,0);
			break;
		}
		
		switch(g.p2Result.resultRunner){
		case 2:
			setInactive(image0RunnerP2,0);
			setInactive(image1RunnerP2,1);
			setActive(image2RunnerP2,2);
			break;
		case 1:
			setInactive(image0RunnerP2,0);
			setInactive(image2RunnerP2,2);
			setActive(image1RunnerP2,1);
			break;
		default:
			setInactive(image2RunnerP2,2);
			setInactive(image1RunnerP2,1);
			setActive(image0RunnerP2,0);
			break;
		}
	}

	private void setActive(Image image, int value) {
		image.setHover(null);
		image.setSrc("/images/button_"+value+"_active.png");
	}

	private void setInactive(Image image, int value) {
		image.setHover("/images/button_"+value+"_hover.png");
		image.setSrc("/images/button_"+value+".png");
	}

	public void onClick$validateButton (Event e){
		Game g = (Game) page.getAttribute("game");
		if(StringUtils.equals(player2Combobox.getText(), player1Combobox.getText())){
			Messagebox.show(LangUtils.getMessage(LangEnum.IMPOSSIBLE_MATCH));
			return;
		}

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
	
	/** RESULT BUTTONS **/
	
	public void onClick$image0RunnerP1 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p1Result.resultRunner = 0;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
	public void onClick$image1RunnerP1 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p1Result.resultRunner = 1;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
	public void onClick$image2RunnerP1 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p1Result.resultRunner = 2;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
	
	public void onClick$image0CorporationP1 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p1Result.resultCorporation = 0;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
	public void onClick$image1CorporationP1 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p1Result.resultCorporation = 1;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
	public void onClick$image2CorporationP1 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p1Result.resultCorporation = 2;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
	
	public void onClick$image0RunnerP2 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p2Result.resultRunner = 0;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
	public void onClick$image1RunnerP2 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p2Result.resultRunner = 1;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
	public void onClick$image2RunnerP2 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p2Result.resultRunner = 2;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
	
	public void onClick$image0CorporationP2 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p2Result.resultCorporation = 0;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
	public void onClick$image1CorporationP2 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p2Result.resultCorporation = 1;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
	public void onClick$image2CorporationP2 (Event e){
		Game g = (Game) page.getAttribute("game");
		g.p2Result.resultCorporation = 2;
		setImages(g);
		page.setAttribute("game", g);
		binder.loadAll();
	}
}
