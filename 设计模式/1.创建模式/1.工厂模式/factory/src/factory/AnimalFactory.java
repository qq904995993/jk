package factory;

import animal.Animal;
import animal.impl.Cat;
import animal.impl.Dog;
import animal.impl.Mouse;

public class AnimalFactory {
	
	public static String CAT = "cat";
	public static String DOG = "dog"; 
	public static String MOUSE = "mouse"; 
	
	public static Animal createAnimal(String animalType) {
		if(CAT.equals(animalType))	return new Cat();
		if(DOG.equals(animalType))	return new Dog();
		if(MOUSE.equals(animalType))	return new Mouse();
		return null;
	}
}
