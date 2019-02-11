package pizza.factory;

import pizza.ingredient.cheese.Cheese;
import pizza.ingredient.cheese.MozzarellaCheese;
import pizza.ingredient.clams.Clams;
import pizza.ingredient.clams.FreshClams;
import pizza.ingredient.dough.Dough;
import pizza.ingredient.dough.ThickCrustDough;
import pizza.ingredient.sauce.PlumTomatoSauce;
import pizza.ingredient.sauce.Sauce;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    public Dough createDough() {
        return new ThickCrustDough();
    }

    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    public Clams createClams() {
        return new FreshClams();
    }

}
