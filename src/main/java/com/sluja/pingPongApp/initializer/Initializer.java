package com.sluja.pingPongApp.initializer;

import com.sluja.pingPongApp.enums.GameForm;
import com.sluja.pingPongApp.properties.SizeManager;

public class Initializer {
	
	private static final Initializer initializer = new Initializer();

	private GameForm gameForm;

	public GameForm getGameForm() {
		return gameForm;
	}

	public void setGameForm(GameForm gameForm) {
		this.gameForm = gameForm;
	}
	
	public static Initializer getInstance() {
		return initializer;
	}
}
