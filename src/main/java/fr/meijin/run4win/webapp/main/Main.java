package fr.meijin.run4win.webapp.main;

import java.awt.Desktop;
import java.net.URI;

import fr.meijin.run4win.webapp.server.JettyServer;
import fr.meijin.run4win.webapp.server.ServerRunner;

public class Main {
	
	public static void main(String[] args) {
		
		final JettyServer jettyServer = new JettyServer();
		
		Launcher launcher = new Launcher(jettyServer);
		try {
			
			launcher.start();
			new ServerRunner(jettyServer);
			
			Desktop d = Desktop.getDesktop();
			d.browse(new URI("http://localhost:8180/Run4Win"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}