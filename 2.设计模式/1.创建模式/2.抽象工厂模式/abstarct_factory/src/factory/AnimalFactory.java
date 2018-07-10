package factory;

import animal.Animal;
import animal.impl.Cat;
import animal.impl.Dog;
import animal.impl.Mouse;
import color.Color;
import factory.AbstractFactory;

public class AnimalFactory extends AbstractFactory{
	public static String CAT = "cat";
	public static String DOG = "dog"; 
	public static String MOUSE = "mouse";
	
	@Override
	public Animal createAnimal(String animalType) {
		if(animalType.equals(CAT))	return new Cat();
		if(animalType.equals(DOG))	return new Dog();
		if(animalType.equals(MOUSE))	return new Mouse();
		return null;
	}

	@Override
	public Color createColor(String colorType) {
		return null;
	}
}
