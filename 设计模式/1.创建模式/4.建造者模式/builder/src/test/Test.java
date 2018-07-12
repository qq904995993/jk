package test;

import builder.Builder;
import builder.HPBuilder;
import builder.LGBuilder;
import computer.Computer;
import director.Director;

public class Test {
	
	public static void main(String[] args) {
		Director director = new Director();
		
		Builder hPbuilder = new HPBuilder();
		Computer hPComputer = director.constructComputer(hPbuilder);
		System.out.println(hPComputer.getCup() + "," + hPComputer.getMainboard() + "," + hPComputer.getHD());
		
		
		Builder lGbuilder = new LGBuilder();
		Computer lGComputer = director.constructComputer(lGbuilder);
		System.out.println(lGComputer.getCup() + "," + lGComputer.getMainboard() + "," + lGComputer.getHD());
	}

}
