package director;

import builder.Builder;
import computer.Computer;

public class Director {

	 public Computer constructComputer(Builder computerBuilder) {  
		 computerBuilder.buildCPU();
	     computerBuilder.buildMainbord();
	     computerBuilder.buildHD();
	     return computerBuilder.getComputer();
	}   
	
}
