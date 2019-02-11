package instance.beverage.condiment;


import instance.beverage.Beverage;

public class Milk implements CondimentDecorator {

    private Beverage beverage;

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    public double getCost() {
        return 2 + beverage.getCost();
    }
}
