package com.htdweb.utils;

public class NumberUtils {
	public static boolean isLong(String a) {
		try {
			Integer number = Integer.parseInt(a);
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
}
