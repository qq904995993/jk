package instance.caffeine_beverage;

public class Coffe extends CaffeineBeverage {

    @Override
    public void brew() {
        System.out.println("Dripping Coffe through filter");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }

    @Override
    public boolean wantsCondiments() {
        return false;
    }
}
