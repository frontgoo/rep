package org.frontgoo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {

	public static String getRegStr(String reg,String srcStr) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(srcStr);
        while (m.find()) {
        	srcStr =  m.group();
        }
		return srcStr; 
	}
	public static void main(String[] args) {
		System.out.println("BEGIN!");
		String reg = "\\d{11}";
		String srcStr="http://detail.1688.com/offer/45090212143.html";
		getRegStr(reg,srcStr);
		System.out.println("END!");
	}
}
