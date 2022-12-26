package com.sluja.pingPongApp.button;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelButton extends JButton {

	JPanel panel;
	private int positionX;
	private int positionY;

	public PanelButton(String text, JPanel panel) {
		super(text);
		this.panel = panel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	public void placeButton() {
		this.setLocation(this.getPositionX(), this.getPositionY());
		this.getPanel().add(this);
	}
}
