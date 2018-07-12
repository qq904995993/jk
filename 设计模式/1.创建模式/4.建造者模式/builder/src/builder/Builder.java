package builder;

import computer.Computer;

public abstract class Builder {
	
	public abstract void buildCPU();

	public abstract void buildMainbord();
	
	public abstract void buildHD();
	
	public abstract Computer getComputer();
}
