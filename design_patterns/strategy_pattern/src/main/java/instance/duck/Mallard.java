package instance.duck;

import instance.duck.fly.FlyBehavior;
import instance.duck.fly.FlyWithWings;
import instance.duck.quack.Quack;
import instance.duck.quack.QuackBehavior;

public class Mallard extends Duck {

    public Mallard() {
        this.quackBehavior = new Quack();
        this.flyBehavior = new FlyWithWings();
    }

}
