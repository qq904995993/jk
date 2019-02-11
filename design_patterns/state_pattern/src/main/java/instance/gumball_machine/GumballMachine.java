package instance.gumball_machine;

public class GumballMachine {

    private State state;
    private int gumballCount;

    private State noQuarterState = new NoQuarterState(this);
    private State hasQuarterState = new HasQuarterState(this);
    private State soldState = new SoldState(this);
    private State soldOutState = new SoldOutState(this);

    public GumballMachine(int gumballCount) {
        this.gumballCount = gumballCount;
        if(gumballCount > 0) {
            state = noQuarterState;
        } else {
            state = soldOutState;
        }
    }

    public int getGumballCount() {
        return gumballCount;
    }

    public void setNoQuarterState() {
        this.state = noQuarterState;
    }

    public void setHasQuarterState() {
        this.state = hasQuarterState;
    }

    public void setSoldState() {
        this.state = soldState;
    }

    public void setSoldOutState() {
        this.state = soldOutState;
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public void releaseBall() {
        if(gumballCount >= 1) {
            gumballCount --;
            System.out.println("A gumball comes rolling out!");
        } else {
            System.out.println("No gumball!");
        }
    }

    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(10);
        gumballMachine.insertQuarter();
        gumballMachine.releaseBall();
        gumballMachine.insertQuarter();
    }

}
