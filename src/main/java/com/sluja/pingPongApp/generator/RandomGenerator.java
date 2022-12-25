package com.sluja.pingPongApp.generator;

import java.util.Random;

import com.sluja.pingPongApp.properties.PropertyReader;

public class RandomGenerator {

	private Random generator = new Random();
	private int reflectionAmount;
	private int startBallPosition;

	public void generateStartBallPosition() {
		this.startBallPosition = 20 + generator
				.nextInt(Integer.parseInt(PropertyReader.getInstance().getProperty("ball.startPositionBorder")));
	}

	public void generateReflectionAmount() {
		this.reflectionAmount = 1 + generator
				.nextInt(Integer.parseInt(PropertyReader.getInstance().getProperty("ball.maxReflectionAmount ")));
	}

	public Random getGenerator() {
		return generator;
	}

	public int getReflectionAmount() {
		return reflectionAmount;
	}

	public int getStartBallPosition() {
		return startBallPosition;
	}

}
