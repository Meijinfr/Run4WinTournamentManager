package fr.meijin.run4win.composer;

import org.apache.commons.lang.StringUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import fr.meijin.run4win.model.Player;
import fr.meijin.run4win.model.Tournament;
import fr.meijin.run4win.util.identity.CorporationIdentityEnum;
import fr.meijin.run4win.util.identity.RunnerIdentityEnum;
import fr.meijin.run4win.util.lang.LangEnum;
import fr.meijin.run4win.util.lang.LangUtils;

public class AddPlayerComposer extends GenericForwardComposer<Window>{

	private static final long serialVersionUID = 4975753713510981075L;
	
	private AnnotateDataBinder binder;
	private Textbox firstNameTextbox;
	private Textbox lastNameTextbox;
	private Textbox nicknameTextbox;
	private Listbox idCorporationListbox;
	private Listbox idRunnerListbox;
	private Checkbox forfeitCheckbox;
	private Checkbox byeFirstRoundCheckbox;
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		
		page.setAttribute("runnerIdentities", new SimpleListModel<RunnerIdentityEnum>(RunnerIdentityEnum.values()));
		page.setAttribute("corporationIdentities", new SimpleListModel<CorporationIdentityEnum>(CorporationIdentityEnum.values()));

		binder.loadAll();
	}
	
	public void onOK(){
		onClick$validateButton(null);
	}

	public void onClick$validateButton (Event e){
		Tournament tournament = (Tournament) session.getAttribute("tournament");
		
		Player p = (Player) arg.get("oldPlayer");
		if(p == null){
			p = new Player();
			
			for(Player player : tournament.players){
				if(StringUtils.equals(player.nickname, nicknameTextbox.getValue())){
					Messagebox.show(LangUtils.getMessage(LangEnum.NICKNAME_ALREADY_TAKEN));
					return;
				}
			}
			
			p.id = tournament.playersCount++;
		}
		
		p.firstName = firstNameTextbox.getValue();
		p.lastName = lastNameTextbox.getValue();
		p.nickname = nicknameTextbox.getValue();
		if(idCorporationListbox.getSelectedItem() != null)
			p.idCorporation = CorporationIdentityEnum.valueOf((String) idCorporationListbox.getSelectedItem().getValue());
		else 
			p.idCorporation = CorporationIdentityEnum.BLANK;
		
		if(idRunnerListbox.getSelectedItem() != null)
			p.idRunner = RunnerIdentityEnum.valueOf((String) idRunnerListbox.getSelectedItem().getValue());
		else 
			p.idRunner = RunnerIdentityEnum.BLANK;
		
		p.forfeit = forfeitCheckbox.isChecked();
		p.byeFirstRound = byeFirstRoundCheckbox.isChecked();
		
		session.setAttribute("newPlayer",p);
		self.detach();
	}
	
	public void onClick$cancelButton (Event e){
		self.detach();
	}
}
