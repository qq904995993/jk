package test;

import animal.Animal;
import color.Color;
import factory.AbstractFactory;
import factory.AnimalFactory;
import factory.ColorFactory;
import factory.FactoryProducer;

public class testAbstractFactory {
	public static void main(String[] args) {
		AbstractFactory animalFactory = FactoryProducer.getFactory(FactoryProducer.ANIMAL);
		Animal cat = animalFactory.createAnimal(AnimalFactory.CAT);
		Animal dog = animalFactory.createAnimal(AnimalFactory.DOG);
		Animal mouse = animalFactory.createAnimal(AnimalFactory.MOUSE);
		cat.whatAnimal();
		dog.whatAnimal();
		mouse.whatAnimal();
		
		AbstractFactory colorFactory = FactoryProducer.getFactory(FactoryProducer.COLOR);
		Color yellow = colorFactory.createColor(ColorFactory.YELLOW);
		Color white = colorFactory.createColor(ColorFactory.WHITE);
		Color black = colorFactory.createColor(ColorFactory.BLACK);
		yellow.whatColor();
		white.whatColor();
		black.whatColor();
	}
}
