package me.jupdyke01.customenchantsredone.enchant.utils;

public class EnchantUtils {

	public static int getEnchantLevelFromString(String str) {
		switch(str) {
		case "I":
			return 1;
		case "II":
			return 2;
		case "III":
			return 3;
		case "IV":
			return 4;
		case "V":
			return 5;
		}
		return 0;
	}

	public static String getEnchantLevelFromInt(int tier) {
		switch(tier) {
		case 1:
			return "I";
		case 2:
			return "II";
		case 3:
			return "III";
		case 4:
			return "IV";
		case 5:
			return "V";
		}
		return "-";
	}
	
}

