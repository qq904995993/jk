package factory;

import animal.Animal;
import color.Color;

public abstract class AbstractFactory {
	public abstract Animal createAnimal(String animalType);
	public abstract Color createColor(String colorType) ;
}