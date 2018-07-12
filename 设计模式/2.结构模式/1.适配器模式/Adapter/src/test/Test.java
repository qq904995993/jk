package test;

import adapter.AdapterChargeByClass;
import adapter.AdapterChargeByObject;

public class Test {

	public static void main(String[] args) {
		AdapterChargeByClass adapterChargeByClass = new AdapterChargeByClass();
		System.out.println("American:" + adapterChargeByClass.getAmericanVoltage());
		System.out.println("Chinese:" + adapterChargeByClass.getChineseVoltage());
		
		AdapterChargeByObject adapterChargeByObject = new AdapterChargeByObject();
		System.out.println("American:" + adapterChargeByObject.getAmericanVoltage());
		System.out.println("Chinese:" + adapterChargeByObject.getChineseVoltage());
	}

}
