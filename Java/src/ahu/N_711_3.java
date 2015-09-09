package ahu;

import java.util.Scanner;

public class N_711_3 {
	public static void main(String[] args){
		final int MOD=1000000007;
		int n;
		Scanner cin=new Scanner(System.in);
		while(cin.hasNext()){
			n=cin.nextInt();
			int k=2*n-1;
			Long sum=(long) 1;
			while(k>1){
				sum=sum*k%MOD;
				k-=2;
			}
			System.out.println(sum);
		}
	}
}
