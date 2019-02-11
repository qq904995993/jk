package instance.gumball_machine;

public class NoQuarterState implements State {

    private GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("You insert a quarter!");
        gumballMachine.setHasQuarterState();
    }

    public void ejectQuarter() {
        System.out.println("You haven't insert a quarter!");
    }

    public void turnCrank() {
        System.out.println("You turned,but there's no quarter!");
    }

    public void dispense() {
        System.out.println("You need pay first!");
    }
}
