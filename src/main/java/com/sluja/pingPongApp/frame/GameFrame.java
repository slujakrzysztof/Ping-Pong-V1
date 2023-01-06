package com.sluja.pingPongApp.frame;

import java.awt.FlowLayout;
import java.awt.Point;
import javax.swing.JFrame;
import com.sluja.pingPongApp.panel.MainPanel;
import com.sluja.pingPongApp.properties.PropertyReader;
import com.sluja.pingPongApp.properties.SizeManager;

public class GameFrame extends JFrame {

	private final String TITLE = PropertyReader.getInstance().getProperty("title");
	private final int SCREEN_POSITION_X = SizeManager.getInstance().SCREEN_POSITION_X;
	private final int SCREEN_POSITION_Y = SizeManager.getInstance().SCREEN_POSITION_Y;

	private MainPanel mainPanel;

	public GameFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel = new MainPanel(this);
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle(TITLE);
		this.setLocation(new Point(SCREEN_POSITION_X, SCREEN_POSITION_Y));
		this.pack();
	}

	public MainPanel getMainPanel() {
		return this.mainPanel;
	}
}