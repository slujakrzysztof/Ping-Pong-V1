package com.sluja.pingPongApp.appWindow;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.sluja.pingPongApp.properties.PropertyReader;

public class GameFrame extends JFrame {

	private final String TITLE = PropertyReader.getInstance().getProperty("title");
	private final int WINDOW_WIDTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.width"));
	private final int WINDOW_HEIGTH = Integer.parseInt(PropertyReader.getInstance().getProperty("window.heigth"));
	private final int POSITION_X = (Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.WINDOW_WIDTH / 2);
	private final int POSITION_Y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.WINDOW_HEIGTH / 2);

	private int gameForm = 0;

	public GameFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(TITLE);
		this.setBounds(POSITION_X, POSITION_Y, WINDOW_WIDTH, WINDOW_HEIGTH);
	}
}
