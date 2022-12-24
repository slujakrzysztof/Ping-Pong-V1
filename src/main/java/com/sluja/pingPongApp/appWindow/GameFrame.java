package com.sluja.pingPongApp.appWindow;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private final String TITLE = "Ping Pong V1";
	private final int WINDOW_WIDTH = 1000;
	private final int WINDOW_HEIGTH = 600;
	private final int POSITION_X = (Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (this.WINDOW_WIDTH / 2);
	private final int POSITION_Y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (this.WINDOW_HEIGTH / 2);

	private int gameForm = 0;

	public GameFrame() {
		this.setResizable(false);
		this.setTitle(TITLE);
		this.setBounds(POSITION_X, POSITION_Y, WINDOW_WIDTH, WINDOW_HEIGTH);
	}
}
