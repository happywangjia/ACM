package leetcode;

import java.util.Scanner;

/**
 * 
 * @tag:Integer to Roman
 * @link:https://leetcode.com/problems/integer-to-roman/
 * @Num:N12_Integer.java
 * @author hijj Create at: 2015年10月10日 下午7:06:24
 */
public class N12_Integer {
	public static Scanner cin = new Scanner(System.in);
	public static final char[] ROMAN = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };

	public static String intToRoman(int num) {
		StringBuffer sb = new StringBuffer();
		int shu = 1000;
		while (shu >= 1) {
			int kk = (int) Math.log10(shu);
			while (num >= shu) {
				sb.append(ROMAN[kk * 2]);
				num -= shu;
			}
			if (num >= shu - shu / 10) {
				sb.append(ROMAN[kk * 2 - 2] + "" + ROMAN[kk * 2]);
				num -= shu - shu / 10;
			}
			shu /= 2;
			if (shu == 0)
				break;
			while (num >= shu) {
				sb.append(ROMAN[kk * 2 - 1]);
				num -= shu;
			}
			if (num >= shu - shu / 5) {
				sb.append(ROMAN[kk * 2 - 2] + "" + ROMAN[kk * 2 - 1]);
				num -= shu - shu / 5;
			}
			shu /= 5;
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		while (true) {
			System.out.println(intToRoman(cin.nextInt()));
		}
	}
}
