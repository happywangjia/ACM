package ahu;

import java.util.Scanner;
import java.io.*;
import java.util.*;
/**
 * 
 * @tag:配对
 * @link:http://icpc.ahu.edu.cn/OJ/Problem.aspx?id=711
 * @Num:N_711.java
 * @author hijj
 * Create at: 2015年9月8日 下午12:54:27
 */
public class N_711 {
	public static void main(String[] args){
		final int MOD=(int)(1e9+7+0.5);
		Scanner cin=new Scanner(System.in);
		while(cin.hasNextInt()){
			int n=cin.nextInt();
			Long mod1=(long) 1;
			int z,qq=n;
			int wj=2*n;
			for(int i=n+1;i<=wj;i++){
				z=i;
				while(qq!=0&&z%2==0){
					z>>=2;
					qq--;
				}
				mod1=mod1*z%MOD;
			}
			System.out.println(mod1);
		}
		cin.close();
	}
}
