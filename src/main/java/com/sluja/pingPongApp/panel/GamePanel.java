package com.sluja.pingPongApp.panel;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.enums.GameLevel;
import com.sluja.pingPongApp.frame.GameFrame;
import com.sluja.pingPongApp.model.paddle.Paddle;
import com.sluja.pingPongApp.steering.Steering;

public class GamePanel extends JPanel {

	GameFrame gameFrame;
	GameForm gameForm;
	GameLevel gameLevel;
	Color backgroundColor = Color.BLACK;
	Steering steering;

	public GamePanel(GameFrame gameFrame, GameForm gameForm, GameLevel gameLevel) {
		this.gameFrame = gameFrame;
		this.gameForm = gameForm;
		this.gameLevel = gameLevel;
		this.setBackground(backgroundColor);
		this.setFocusable(true);
	}
	
	public GameForm getGameForm() {
		return this.gameForm;
	}
	
	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}
	
	public void setSteering(ArrayList<Paddle> paddles) {
		steering = new Steering(paddles, this.getGameForm());
		this.addKeyListener(steering);
	}
}
