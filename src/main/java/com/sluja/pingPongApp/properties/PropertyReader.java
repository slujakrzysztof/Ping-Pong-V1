package com.sluja.pingPongApp.properties;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	// ----- CONSTANTS ----- //
	private static final PropertyReader propertyReader = new PropertyReader();

	// ----- Variables ----- //
	private String propertyFilename;
	private Properties property = new Properties();
	ClassLoader loader = Thread.currentThread().getContextClassLoader();

	// ------------------------ //
	// ----- CONSTRUCTORS ----- //
	// ------------------------ //

	public PropertyReader() {
		this.setPropertyFilename("com/sluja/pingPongApp/properties/config.properties");
		this.setConnection();
	}

	// ------------------- //
	// ----- GETTERS ----- //
	// ------------------- //
	
	public String getPropertyFilename() {
		return this.propertyFilename;
	}
	
	public String getFilename() {
		return this.propertyFilename;
	}

	// ------------------- //
	// ----- SETTERS ----- //
	// ------------------- //

	public void setPropertyFilename(String name) {
		this.propertyFilename = name;
	}

	// ------------------- //
	// ----- METHODS ----- //
	// ------------------- //

	// Connecting with given file with data
	public void setConnection() {

		InputStream input = null;
		try {
			input = loader.getResourceAsStream(this.getPropertyFilename());// getClass().getClassLoader().getResourceAsStream(getPropertyFilename());//
			property.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex2) {

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ex1) {
					ex1.printStackTrace();
				}
			}
		}
	}

	// Getting property by specific key
	public String getProperty(String key) {
		return property.getProperty(key);
	}

	// Thanks to this method there is only one instance of class and user can get it
	public static PropertyReader getInstance() {
		return propertyReader;
	}
}
