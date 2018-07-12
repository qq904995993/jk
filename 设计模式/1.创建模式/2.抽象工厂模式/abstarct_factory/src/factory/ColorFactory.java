package factory;

import animal.Animal;
import color.Color;
import color.impl.Black;
import color.impl.White;
import color.impl.Yellow;

public class ColorFactory extends AbstractFactory{
	public static String YELLOW = "yellow";
	public static String WHITE = "white"; 
	public static String BLACK = "black"; 
	
	@Override
	public Animal createAnimal(String animalType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color createColor(String colorType) {
		if(colorType.equals(YELLOW))	return new Yellow();
		if(colorType.equals(WHITE))		return new White();
		if(colorType.equals(BLACK))		return new Black();
		return null;
	}
}
