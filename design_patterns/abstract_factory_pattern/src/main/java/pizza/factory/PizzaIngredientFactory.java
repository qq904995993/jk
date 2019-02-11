package pizza.factory;

import pizza.ingredient.cheese.Cheese;
import pizza.ingredient.clams.Clams;
import pizza.ingredient.dough.Dough;
import pizza.ingredient.sauce.Sauce;

public interface PizzaIngredientFactory {

    Dough createDough();

    Sauce createSauce();

    Cheese createCheese();

    Clams createClams();

}
