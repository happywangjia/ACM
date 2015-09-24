package leetcode;

import java.util.Scanner;

public class N9_palindrome {
	public static boolean isPalindrome(int x) {
		if(x<0) return false;
		String str="";
		str+=x;
		int len=str.length();
		for(int i=len/2;i>=0;i--){
			if(str.charAt(i)!=str.charAt(len-1-i))
				return false;
		}
		return true;
	}
	public static void main(String[] args){
		Scanner cin=new Scanner(System.in);
		int x=cin.nextInt();
		System.out.println(isPalindrome(x));
		cin.close();
	}
}
