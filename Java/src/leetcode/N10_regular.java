package leetcode;

import java.util.Scanner;
/**
 * 
 * @tag:正则匹配
 * @link:https://leetcode.com/problems/regular-expression-matching/
 * @Num:N3_longest.java
 * @author hijj
 * Create at: 2015年10月8日 上午12:01:42
 * 代码参照网上的，真的很妙
 * 
 */
public class N10_regular {
	public static final Scanner cin = new Scanner(System.in);
	public static boolean isMatch(String s, String p) {
		if(p.length()==0){
			return s.length()==0;
		}
		if(p.length()==1||p.charAt(1)!='*'){
			if(s.length()<1||(p.charAt(0)!=s.charAt(0)&&p.charAt(0)!='.')){
				return false;
			}
			return isMatch(s.substring(1), p.substring(1));
		}else{
			int len=s.length();
			int i=-1;
			while(i<len&&(i<0||p.charAt(0)=='.'||p.charAt(0)==s.charAt(i))){
				if(isMatch(s.substring(i+1), p.substring(2)))
						return true;
				i++;
			}
			return false;
		}
	}

	public static void main(String[] args) {
		while (true) {
			String str1 = cin.next();
			String str2 = cin.next();
			boolean flag = isMatch(str1, str2);
			System.out.println(flag);
		}
	}
}
