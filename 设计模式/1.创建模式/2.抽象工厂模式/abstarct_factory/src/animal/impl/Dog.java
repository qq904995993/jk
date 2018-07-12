package animal.impl;

import animal.Animal;

public class Dog implements Animal{
	@Override
	public void whatAnimal() {
		System.out.println("Im a dog!");
	}
}
