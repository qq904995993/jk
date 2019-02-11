package instance.factory;

import instance.pizza.*;

public class PizzaFactory {

    public static Pizza getPizza(String pizzaType) {
        if("Cheese".equals(pizzaType)) {
            return new CheesePizza();
        } else if ("Clam".equals(pizzaType)) {
            return new ClamPizza();
        } else if ("Pepperoni".equals(pizzaType)) {
            return new PepperoniPizza();
        } else if ("Veggile".equals(pizzaType)) {
            return new VeggiePizza();
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
        Pizza pizza = PizzaFactory.getPizza("Veggile");
        pizza.getName();
    }

}
