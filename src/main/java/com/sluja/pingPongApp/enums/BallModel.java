package com.sluja.pingPongApp.enums;

import com.sluja.pingPongApp.model.ball.RoundedBall;

public enum BallModel {
	
	// ----------------------- //
	// ----- ENUM VALUES ----- //
	// ----------------------- //
	
	ROUNDED("Rounded Ball", RoundedBall.class.getName());

	// ----- Variables ----- //
	private String modelName;
	private String classModel;

	private BallModel(String modelName, String classModel) {
		this.modelName = modelName;
		this.classModel = classModel;
	}

	public String getModelName() {
		return this.modelName;
	}

	public String getClassModel() {
		return this.classModel;
	}

}
