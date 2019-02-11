package instance.beverage;

import instance.beverage.beverage.HouseBlend;
import instance.beverage.condiment.Milk;
import instance.beverage.condiment.Mocha;

public interface Beverage {

    double getCost();

}

class main {
    public static void main(String[] args) {
        Beverage beverage = new HouseBlend();
        System.out.println(beverage.getCost());
        beverage = new Mocha(beverage);
        System.out.println(beverage.getCost());
        beverage = new Mocha(beverage);
        beverage = new Milk(beverage);
        System.out.println(beverage.getCost());
    }
}
