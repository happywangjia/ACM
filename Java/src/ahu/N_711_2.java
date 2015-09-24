package ahu;

import java.util.Scanner;

public class N_711_2 {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		final int MOD=1000000007;
		int n;
		Long ans=(long) 1;
		Long[] arr=new Long[200005];
		Scanner cin=new Scanner(System.in);
		for(int i=1;i<2*100000;i+=2){
			ans=ans*i%MOD;
			arr[i]=ans;
		}
		while(cin.hasNext()){
			n=cin.nextInt();
			System.out.println(arr[n*2-1]);
		}
	}

}
