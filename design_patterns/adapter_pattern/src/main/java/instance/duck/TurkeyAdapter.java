package instance.duck;

public class TurkeyAdapter implements Duck {

    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    public void quark() {
        turkey.gobble();
    }

    public void fly() {
        turkey.fly();
    }

    public static void main(String[] args) {
        Duck duck = new TurkeyAdapter(new WildTurkey());
        duck.quark();
        duck.fly();
    }

}

