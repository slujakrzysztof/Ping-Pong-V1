package com.sluja.pingPongApp.enums;

public enum BallModel {
	ROUNDED_BALL("RoundedBall");
	
	private String className;
	
	private BallModel(String className) {
		this.className = className;
	}
	
	public String getClassName() {
		return this.className;
	}
}
