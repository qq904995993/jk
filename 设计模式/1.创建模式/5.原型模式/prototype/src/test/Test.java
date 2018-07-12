package test;

import prototype.Prototype;

public class Test {

	public static void main(String[] args) {
		Prototype prototype = new Prototype();
		prototype.setName("a");
		prototype.setContent("aa");
		System.out.println("prototype:" + prototype.getName() + "," + prototype.getContent());
		
		Prototype copyPrototype = prototype.clone();
		System.out.println("copyPrototype:" + copyPrototype.getName() + "," + copyPrototype.getContent());
	}

}
