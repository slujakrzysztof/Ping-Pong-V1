package com.sluja.pingPongApp.steering;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.model.paddle.Paddle;

public class Steering extends KeyAdapter {

	ArrayList<Paddle> paddles;
	GameForm gameForm;

	public Steering(ArrayList<Paddle> paddles, GameForm gameForm) {
		this.paddles = paddles;
	}
	
	public GameForm getGameForm() {
		return this.gameForm;
	}
	
	public ArrayList<Paddle> getPaddles() {
		return this.paddles;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.singlePlayerSteering(e);
		if (this.getGameForm() == GameForm.MULTIPLAYER)
			this.multiPlayerSteering(e);
		this.functionSteering(e);
	}

	private void singlePlayerSteering(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			this.getPaddles().get(0).setMovingUp(true);

		if (e.getKeyCode() == KeyEvent.VK_S)
			this.getPaddles().get(0).setMovingUp(false);
		this.getPaddles().get(0).move();
	}

	private void multiPlayerSteering(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			this.getPaddles().get(1).setMovingUp(true);

		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			this.getPaddles().get(1).setMovingUp(false);
		this.getPaddles().get(1).move();
	}

	private void functionSteering(KeyEvent e) {

	}
}
