package instance.beverage.condiment;

import instance.beverage.Beverage;

public class Mocha implements CondimentDecorator {

    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    public double getCost() {
        return 2 + beverage.getCost();
    }
}
