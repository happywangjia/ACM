package leetcode;

import java.util.Scanner;

public class N8_string {
	public static int myAtoi(String str) {
		int len = str.length();
		if (len == 0)
			return 0;
		if(str.charAt(0)!='-'&&str.charAt(0)!='+'&&!(str.charAt(0) >= '0' && str.charAt(0) <= '9'))
			return 0;
		boolean flag = true;
		if (str.charAt(0) == '-') {
			flag = false;
		} else {
			flag = true;
		}
		int sum = 0;
		boolean zero = true;
		for (int i =0; i <len; i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				if (zero == false) {
					sum = sum * 10 + str.charAt(i) - '0';
					continue;
				}
				if (zero == true && str.charAt(i) != '0') {
					sum = sum * 10 + str.charAt(i) - '0';
					zero = false;
				}
			}
			if(i>0&&(str.charAt(i)<'0'||str.charAt(i)>'0'))
				return 0;
		}
		if (flag == false)
			return sum * -1;
		else
			return sum;
	}
	public static void main(String[] args){
		Scanner cin=new Scanner(System.in);
		String str=cin.nextLine();
		System.out.println(myAtoi(str));
		cin.close();
	}

}
