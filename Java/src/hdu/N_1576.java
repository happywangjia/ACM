package hdu;

import java.util.Scanner;

/**
 * 
 * @tag:A/B
 * @link:http://acm.hdu.edu.cn/showproblem.php?pid=1576
 * @Num:N_1576.java
 * @author hijj
 * Create at: 2015年9月10日 上午12:41:05
 * 
 * @add: A/B%c=A*B^(C-2)%c
 * B^(C-1)%C=1
 */
public class N_1576 {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		int t;
		Scanner cin=new Scanner(System.in);
		t=cin.nextInt();
		Long a,b;
		final int maxn=9973;
		while(--t>=0){
			a=cin.nextLong();
			b=cin.nextLong();
			for(int i=1;i<=9973-2;i++){
				a=a*b%maxn;
			}
			System.out.println(a);
		}
	}
}