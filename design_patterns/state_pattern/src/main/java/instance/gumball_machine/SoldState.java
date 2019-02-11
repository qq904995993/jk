package instance.gumball_machine;

public class SoldState implements State {

    private GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball!");
    }

    public void ejectQuarter() {
        System.out.println("Sorry, you already turned the crank!");
    }

    public void turnCrank() {
        System.out.println("Turning twice doesn't get you another gumball!");
    }

    public void dispense() {
        gumballMachine.releaseBall();
        if(gumballMachine.getGumballCount() > 0) {
            gumballMachine.setNoQuarterState();
        } else {
            gumballMachine.setSoldOutState();
        }
    }
}
