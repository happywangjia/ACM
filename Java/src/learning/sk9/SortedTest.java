package learning.sk9;
import java.util.Arrays;
import java.util.Random;

import javax.transaction.xa.Xid;


public class SortedTest {
	private static final int maxn=200;
	private static int[]shu=new int[maxn+1];
	private static int[] mpao;
	private static int[] cru;
	private static int[] xze;
	private static int[] qsort;
	private static int[] shell;
	private static int[] hsort;
	
	public static void main(String[] args){
		for(int i=1;i<maxn;i++){
			shu[i]=new Random().nextInt();
		}
		mpao=Arrays.copyOf(shu, shu.length);
		cru=Arrays.copyOf(shu, shu.length);
		xze=Arrays.copyOf(shu, shu.length);
		qsort=Arrays.copyOf(shu, shu.length);
		shell=Arrays.copyOf(shu, shu.length);
		hsort=Arrays.copyOf(shu,shu.length);
		SortTest.maopao(mpao);
		SortTest.charu(cru);
		SortTest.xuanze(xze);
		SortTest.qSort(qsort);
		SortTest.shellSort(shell);
		SortTest.heapSort(hsort);
		boolean flag1=Arrays.equals(mpao,cru);
		boolean flag2=Arrays.equals(mpao, xze);
		boolean flag3=Arrays.equals(mpao, qsort);
		boolean flag4=Arrays.equals(mpao, shell);
		boolean flag5=Arrays.equals(mpao, hsort);
		System.out.println(flag1+"  "+flag2+"  "+flag3+"  "+flag4+"  "+flag5);
		for(int i=1;i<maxn;i++){
			System.out.print(mpao[i]+"  ");
			if(i%10==0)
				System.out.println();
		}
	}
	
}
