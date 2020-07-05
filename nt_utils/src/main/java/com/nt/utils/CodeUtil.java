package com.nt.utils;

import java.util.Random;

/**
 * @author 99362
 */
public class CodeUtil {

	public static String getCode() {
		String content = "1234567890";
		Random random = new Random();
		String result = "";
		for(int i = 0 ;i < 6; i++){
			int index = random.nextInt(content.length());
			char c = content.charAt(index);
			result += c;
		}
		return result;
	}
	

}