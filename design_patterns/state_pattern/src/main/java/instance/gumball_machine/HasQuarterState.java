package instance.gumball_machine;

public class HasQuarterState implements State {

    private GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("You can't insert another quarter!");
    }

    public void ejectQuarter() {
        System.out.println("Quarter return!");
        gumballMachine.setHasQuarterState();
    }

    public void turnCrank() {
        System.out.println("you turned!");
        gumballMachine.setSoldState();
    }

    public void dispense() {
        System.out.println("No gumball dispense!");
    }
}
