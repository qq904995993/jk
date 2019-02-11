package pizza.factory;

import pizza.ingredient.cheese.Cheese;
import pizza.ingredient.cheese.ReggianoCheese;
import pizza.ingredient.clams.Clams;
import pizza.ingredient.clams.FrozenClams;
import pizza.ingredient.dough.Dough;
import pizza.ingredient.dough.ThinCrustDough;
import pizza.ingredient.sauce.MarinaraSauce;
import pizza.ingredient.sauce.Sauce;

public class ChigagoPizzaIngredientFactory implements PizzaIngredientFactory {

    public Dough createDough() {
        return new ThinCrustDough();
    }

    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    public Clams createClams() {
        return new FrozenClams();
    }

}
