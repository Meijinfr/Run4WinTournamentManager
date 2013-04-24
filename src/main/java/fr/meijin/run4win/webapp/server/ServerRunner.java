package fr.meijin.run4win.webapp.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UIManager;

public class ServerRunner extends JWindow implements ActionListener {

	private static final long serialVersionUID = 8246957846370294364L;
	
	private JettyServer jettyServer;

	public ServerRunner(JettyServer jettyServer) {
		this.jettyServer = jettyServer;
		try {
			showSplash();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void showSplash() throws Exception {

		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.black);
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		int width = 710;
		int height = 325;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		
		URL imgURL = getClass().getResource("/splash.jpg");
		
	    
		JLabel image = new JLabel(new ImageIcon(imgURL));
		Color green = new Color(88, 254, 110);

		UIManager.put("ProgressBar.selectionForeground", green);
		UIManager.put("ProgressBar.selectionBackground", Color.white);
		UIManager.put("ProgressBar.foreground", green);
		UIManager.put("ProgressBar.background", Color.black);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setBorderPainted(false);

		content.add(image, BorderLayout.CENTER);
		content.add(progressBar, BorderLayout.SOUTH);
		content.setBorder(BorderFactory.createLineBorder(Color.black, 10));

		setVisible(true);

		while (!jettyServer.isStarted()) {
			Thread.sleep(60);
			if(progressBar.getValue()==100)
				progressBar.setValue(0);
			progressBar.setValue(progressBar.getValue()+1);
		}

		progressBar.setVisible(false);
		content.remove(progressBar);

		JButton button = new JButton("Quit");
		button.setSize(50, 20);
		button.addActionListener(this);
		JPanel south = new JPanel();
		south.setBackground(Color.black);
		south.add(button, BorderLayout.CENTER);
		south.setSize(new Dimension(60, 30));
		content.add(south, BorderLayout.SOUTH);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(jettyServer.isStarted())
			try {
				System.out.println("Stopping jetty");
				jettyServer.stop();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		
		System.exit(0);
	}

	public JettyServer getJettyServer() {
		return jettyServer;
	}

	public void setJettyServer(JettyServer jettyServer) {
		this.jettyServer = jettyServer;
	}
}