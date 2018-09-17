package me.jupdyke01.customenchantsredone.enchant.utils;

import java.util.Random;

public class Utils {

	public static int getRandom(int lower, int upper) {
		Random random = new Random();
		return random.nextInt((upper - lower) + 1) + lower;
	}
	
}
