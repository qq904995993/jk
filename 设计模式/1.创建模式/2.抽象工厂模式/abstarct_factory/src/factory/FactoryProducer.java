package factory;

public class FactoryProducer {
	public static String COLOR = "color";
	public static String ANIMAL = "animal";
	
	public static AbstractFactory getFactory(String factoryType) {
		if(COLOR.equals(factoryType))		return new ColorFactory();
		if(ANIMAL.equals(factoryType))		return new AnimalFactory();
		return null;
	}
}
