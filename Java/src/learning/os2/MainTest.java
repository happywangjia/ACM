package learning.os2;

import java.util.Scanner;

public class MainTest {
	private static Scanner cin=new Scanner(System.in);
	private static int[] Avaliable;
	private static int[][] Max;
	private static int[][] Allocation;
	private static int[][] Need;
	private static boolean[] flag;
	
	public static void main(String[] args){
		System.out.println("请输入进程数：");
		int n=cin.nextInt();
		System.out.println("请输入类数：");
		int m=cin.nextInt();
		Avaliable=new int[m];
		Max=new int[n][m];
		Allocation=new int[n][m];
		Need=new int[n][m];
		flag=new boolean[n];
		for(int i=0;i<n;i++){
			flag[i]=false;
		}
		System.out.println("输入Max:");
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				Max[i][j]=cin.nextInt();
			}
		}
		System.out.println("请输入Need:");
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				Need[i][j]=cin.nextInt();
			}
		}
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				Allocation[i][j]=Max[i][j]-Need[i][j];
			}
		}
		System.out.println("输入Avalible:");
		for(int i=0;i<m;i++){
			Avaliable[i]=cin.nextInt();	
		}
		int k=0;
		while(k<n){
			boolean fff;
			for(int i=0;i<n;i++){
				fff=true;
				if(flag[i]==true) continue;
				for(int j=0;j<m;j++){
					if(Avaliable[j]<Need[i][j]){
						fff=false;
						break;
					}
				}
				if(fff==true){
					System.out.print("p"+i+"  ");
					for(int j=0;j<m;j++){
						System.out.print(Avaliable[j]+"  ");
					}
					for(int j=0;j<m;j++){
						System.out.print(Need[i][j]+"  ");
					}
					for(int j=0;j<m;j++){
						System.out.print(Allocation[i][j]+"   ");
					}
					for(int j=0;j<m;j++){
						Avaliable[j]=Allocation[i][j]+Avaliable[j];
					}
					for(int j=0;j<m;j++){
						System.out.print(Avaliable[j]+"   ");
					}
					flag[i]=true;
					System.out.println(flag[i]);
					k++;
					break;
				}
			}
			if(fff=false){
				System.out.println("失败:");
				break;
			}
		}
		
	}
}
/*
 * 5
 * 3
 * 
 *7 5 3
 *3 2 2
 *9 0 2
 *2 2 2
 *4 3 3
 *
 * 7 4 3
 * 1 2 2
 * 6 0 0
 * 0 1 1
 * 4 3 1
 * 
 * 3 3 2
 */

