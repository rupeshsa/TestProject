package com.test;

public class SingletonTest {

	private volatile static SingletonTest instance = null;

	private SingletonTest() {

	}

	synchronized public static SingletonTest getInstance() {
		if (instance == null) {
			instance = new SingletonTest();
		}
		return instance;
	}
}
