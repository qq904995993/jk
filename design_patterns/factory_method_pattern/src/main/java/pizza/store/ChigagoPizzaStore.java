package pizza.store;

import pizza.pizza.*;

public class ChigagoPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String pizzaType) {
        if("Cheese".equals(pizzaType)) {
            return new ChigagoCheesePizza();
        } else if ("Clam".equals(pizzaType)) {
            return new ChigagoClamPizza();
        } else if ("Pepperoni".equals(pizzaType)) {
            return new ChigagoPepperoniPizza();
        } else if ("Veggile".equals(pizzaType)) {
            return new ChigagoVeggiePizza();
        } else {
            return null;
        }
    }

}
