package com.sluja.pingPongApp.button;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelButton extends JButton {

	JPanel panel;
	private int positionX;
	private int positionY;
	private int placeMultipler;
	private int height;
	private int width;
	
	public PanelButton(String text, JPanel panel, int placeMultipler, int positionY) {
		super(text);
		this.panel = panel;
		this.placeMultipler = placeMultipler;
		this.setPositionY(positionY);
	}
	
	public void setLocation(int positionX, int positionY) {
		this.setPositionX(positionX);
		this.setPositionY(positionY);
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
	
	public int getPlaceMultipler() {
		return this.placeMultipler;
	} 
	
	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void calculatePosition(int panelBorder, int gap) {
		int locationX;
		locationX = panelBorder + ((gap + this.getWidth()) * (this.getPlaceMultipler() - 1));
		System.out.println("LOCX: " + locationX);
		this.setPositionX(locationX);
		this.placeButton();
	}
	
	public void placeButton() {
		this.setBounds(positionX, positionY,this.getWidth(), this.getHeight());
		this.getPanel().add(this);
	}
}
