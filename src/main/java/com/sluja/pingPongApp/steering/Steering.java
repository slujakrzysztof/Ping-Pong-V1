package com.sluja.pingPongApp.steering;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.model.paddle.Paddle;
import com.sluja.pingPongApp.panel.GamePanel;

public class Steering{

	ArrayList<Paddle> paddles;
	GameForm gameForm;
    InputMap inputMap;
    ActionMap actionMap;
    GamePanel gamePanel;
    
	public Steering(ArrayList<Paddle> paddles, GameForm gameForm, GamePanel gamePanel) {
		this.paddles = paddles;
		inputMap = gamePanel.getInputMap(2);
		actionMap = gamePanel.getActionMap();
		this.setSteering();
		this.initializeActionMap();
	    gamePanel.setFocusable(true);
	    gamePanel.requestFocusInWindow(); 
	}
	
	public GameForm getGameForm() {
		return this.gameForm;
	}
	
	public ArrayList<Paddle> getPaddles() {
		return this.paddles;
	}
	
	private void initializeActionMap() {
		actionMap.put("movingUp", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
				getPaddles().get(0).setMovingUp(true);
				getPaddles().get(0).move();
	        }
	    });

	    actionMap.put("movingDown", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
				getPaddles().get(0).setMovingUp(false);
				getPaddles().get(0).move();
	        }
	    });
	    
		actionMap.put("movingUpSecondPlayer", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
				getPaddles().get(1).setMovingUp(true);
				getPaddles().get(1).move();
	        }
	    });

	    actionMap.put("movingDownSecondPlayer", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
				getPaddles().get(1).setMovingUp(false);
				getPaddles().get(1).move();
	        }
	    });
	}

	private void setSteering() {
		
		this.singlePlayerSteering();
		if (this.getGameForm() == GameForm.MULTIPLAYER)
			this.multiPlayerSteering();
		this.functionSteering();

	}
	
	private void singlePlayerSteering() {
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "movingUp");
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "movingDown");
	}
	
	private void multiPlayerSteering() {
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "movingUpSecondPlayer");
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "movingDownSecondPlayer");
	}

	private void functionSteering() {

	}
}
