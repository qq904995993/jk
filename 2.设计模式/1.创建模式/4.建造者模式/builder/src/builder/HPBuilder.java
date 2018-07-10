package builder;

import computer.Computer;

public class HPBuilder extends Builder{
private Computer computer = new Computer();
	
	public void buildCPU() {
		computer.setCup("HP-CPU");
	};

	public void buildMainbord() {
		computer.setMainboard("HP-Mainbord");
	}
	
	public void buildHD() {
		computer.setHD("HP-HD");
	}
	
	public Computer getComputer() {
		return this.computer;
	}
}
