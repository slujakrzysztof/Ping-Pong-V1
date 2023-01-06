package com.sluja.pingPongApp.enums;

import com.sluja.pingPongApp.interfaces.Ball;
import com.sluja.pingPongApp.model.ball.RoundedBall;

public enum BallModel {
	ROUNDED_BALL("RoundedBall", RoundedBall.class.getName());
	
	private String className;
	private String classModel;
	
	private BallModel(String className, String classModel) {
		this.className = className;
		this.classModel = classModel;
	}
	
	public String getClassName() {
		return this.className;
	}
	
	public String getClassModel() {
		return this.classModel;
	}
}
