package instance.duck;

import instance.duck.fly.FlyBehavior;
import instance.duck.fly.FlyNoWay;
import instance.duck.quack.MuteQuack;
import instance.duck.quack.QuackBehavior;

public class ModelDuck extends Duck {

    public ModelDuck() {
        this.quackBehavior = new MuteQuack();
        this.flyBehavior = new FlyNoWay();
    }

}
