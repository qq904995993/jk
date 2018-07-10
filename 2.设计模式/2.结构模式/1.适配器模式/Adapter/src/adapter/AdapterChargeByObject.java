package adapter;

import charger.AmericanCharger;
import charger.ChineseCharger;

/**
 * ∂‘œÛ  ≈‰
 */
public class AdapterChargeByObject implements ChineseCharger{
	
	private AmericanCharger americanCharger = new AmericanCharger();
	
	
	public int getAmericanVoltage() {
		return americanCharger.getAmericanVoltage();
	}
	
	public int getChineseVoltage() {
		return 5;
	}

}
