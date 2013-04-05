package com.kuaileren.util;

import java.util.Comparator;

/**
 * ≈≈–Ú¿‡
 */
public class TagCloudComparable implements Comparator<Integer> {

	public int compare(Integer o1, Integer o2) {
		if (o1 > o2) {
			return -1;
		} else if (o1 < o2) {
			return 1;
		} else {
			return 0;
		}
	}

}