package builder;

import computer.Computer;

public class LGBuilder extends Builder{
	
	private Computer computer = new Computer();
	
	public void buildCPU() {
		computer.setCup("LG-CPU");
	};

	public void buildMainbord() {
		computer.setMainboard("LG-Mainbord");
	}
	
	public void buildHD() {
		computer.setHD("LG-HD");
	}
	
	public Computer getComputer() {
		return this.computer;
	}
}
