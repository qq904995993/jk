package instance.duck;

public class DuckAdapter implements Turkey {

    private Duck duck;

    public DuckAdapter(Duck duck) {
        this.duck = duck;
    }

    public void gobble() {
        duck.quark();
    }

    public void fly() {
        duck.fly();
    }

    public static void main(String[] args) {
        Turkey turkey = new DuckAdapter(new MallerDuck());
        turkey.gobble();
        turkey.fly();
    }

}

