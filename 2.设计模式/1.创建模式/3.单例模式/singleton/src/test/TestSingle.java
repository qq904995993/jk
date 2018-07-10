package test;

import singleton.Singleton1;

public class TestSingle {

	public static void main(String[] args) {
		Singleton1 single = Singleton1.getInstance();
		single.showMessage();
	}

}
