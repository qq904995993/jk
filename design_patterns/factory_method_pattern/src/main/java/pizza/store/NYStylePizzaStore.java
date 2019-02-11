package pizza.store;

import pizza.pizza.*;

public class NYStylePizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String pizzaType) {
        if("Cheese".equals(pizzaType)) {
            return new NYStyleCheesePizza();
        } else if ("Clam".equals(pizzaType)) {
            return new NYStyleClamPizza();
        } else if ("Pepperoni".equals(pizzaType)) {
            return new NYStylePepperoniPizza();
        } else if ("Veggile".equals(pizzaType)) {
            return new NYStyleVeggiePizza();
        } else {
            return null;
        }
    }

}
