package animal.impl;

import animal.Animal;

public class Cat implements Animal{
	@Override
	public void whatAnimal() {
		System.out.println("Im a cat!");
	}
}
