package instance.duck.quack;

public class MuteQuack implements QuackBehavior {

    public void quack() {
        System.out.println("silence");
    }
}
