package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class N13_Roman {
	public static Scanner cin=new Scanner(System.in);
	public static int romanToInt(String s) {
		Map<String, Integer> map = new HashMap<>();
		map.put("M", 1000);
		map.put("D", 500);
		map.put("CM", 900);
		map.put("CD", 400);
		map.put("C", 100);
		map.put("L", 50);
		map.put("XC", 90);
		map.put("XL", 40);
		map.put("X", 10);
		map.put("V", 5);
		map.put("IX", 9);
		map.put("IV", 4);
		map.put("I", 1);
		int k = 0;
		int sum = 0;
		while (k < s.length()) {
			if (k + 2 <=s.length()) {
				String sb = s.substring(k, k+2);
				if (map.containsKey(sb)) {
					sum += map.get(sb);
					k += 2;
				} else {
					char c = s.charAt(k);
					sum += map.get(c + "");
					k++;
				}
			} else {
				char c = s.charAt(k);
				sum += map.get(c + "");
				k++;
			}
		}

		return sum;
	}
	public static void main(String[] args){
		System.out.println(romanToInt(cin.next()));
	}
}
