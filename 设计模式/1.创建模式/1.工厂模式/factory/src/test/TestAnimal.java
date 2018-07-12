package test;

import animal.Animal;
import factory.AnimalFactory;

public class TestAnimal {

	public static void main(String[] args) {
		Animal cat = AnimalFactory.createAnimal(AnimalFactory.CAT);
		Animal dog = AnimalFactory.createAnimal(AnimalFactory.DOG);
		Animal mouse = AnimalFactory.createAnimal(AnimalFactory.MOUSE);
		cat.whatAnimal();
		dog.whatAnimal();
		mouse.whatAnimal();
	}

}
