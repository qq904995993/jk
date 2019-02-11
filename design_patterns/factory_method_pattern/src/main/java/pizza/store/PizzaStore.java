package pizza.store;

import pizza.pizza.Pizza;

public abstract class PizzaStore {

    public void getPizza(String pizzaType) {
        Pizza pizza =   createPizza(pizzaType);
        pizza.getName();
    }

    protected abstract Pizza createPizza(String pizzaType);

}

class main {
    public static void main(String[] args) {
        PizzaStore pizzaStore = new ChigagoPizzaStore();
        pizzaStore.getPizza("Cheese");
        pizzaStore = new NYStylePizzaStore();
        pizzaStore.getPizza("Cheese");
    }
}
