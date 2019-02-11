package instance.caffeine_beverage;

public abstract class CaffeineBeverage {

    public void boidWater() {
        System.out.println("Bolling Water");
    }

    public void pourInCup() {
        System.out.println("Pouring into cup");
    }

    public abstract void brew();

    public abstract void addCondiments();

    public boolean wantsCondiments() {
        return true;
    }

    public final void prepraeBeverage() {
        boidWater();
        brew();
        pourInCup();
        if(wantsCondiments()) {
            addCondiments();
        }
    }

    public static void main(String[] args) {
        CaffeineBeverage tea = new Tea();
        tea.prepraeBeverage();
        System.out.println();
        CaffeineBeverage coffe = new Coffe();
        coffe.prepraeBeverage();
    }

}
