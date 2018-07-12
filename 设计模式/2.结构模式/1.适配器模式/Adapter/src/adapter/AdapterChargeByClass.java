package adapter;

import charger.AmericanCharger;
import charger.ChineseCharger;

/**
 * ¿‡  ≈‰
 */
public class AdapterChargeByClass extends AmericanCharger implements ChineseCharger{
	public int getChineseVoltage() {
		return 5;
	}
}
