package learning.rzh2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * f(x)=3*x^5-17*x^4 [0,100] max min 输入交叉率 变异率 种群规模 和最大代数等参数
 * 
 * @author ACER
 * 
 */
public class RZH2 {
	private static Scanner cin = new Scanner(System.in);
	private static int[] arr;
	private static int size;
	private static long Min;
	private static long Max;
	private static long shiMin;
	private static long shiMax;
	private static double jl;
	private static double bl;
	private static int count;
	private static boolean flagMin=false;
	private static boolean flagMax=false;
	public static void main(String[] args) {
		scan();
		// 初始化染色体
		init();
		setMaxMin();
		int kk = 1;
		printArr(kk);
		while (kk++ < count) {
			if(flagMax==true&&flagMin==true){
				return;
			}
			chooseCopy();
			jCha();
			bYi();
			setMaxMin();
			printArr(kk);
		}
		System.out.println("代数："+(kk-1));
		System.out.println("最大值为："+Max);
		System.out.println("最小值为："+Min);
	}
	/**
	 *　随机给Arr[]赋值
	 *	设置Min为long最大值，Max为long最小值
	 */
	private static void init(){
		arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = new Random().nextInt(100);
		}

		Min = Long.MAX_VALUE;
		Max = Long.MIN_VALUE;

		shiMax = getShi(100);
		shiMin = getShi(4);
	}
	/**
	 * 输入数据
	 */
	private static void scan(){
		System.out.println("输入交叉率：");
		jl = cin.nextDouble();
		System.out.println("输入变异率：");
		bl = cin.nextDouble();
		System.out.println("输入种群规模：");
		size = cin.nextInt();
		System.out.println("输入最大代数：");
		count = cin.nextInt();
	}
	/**
	 * 选择－复制
	 * 如果适应度小于０，设置为０
	 * 在求最小适应度是在该设置之前，所以有存在适应度最小值小于０的情况
	 */
	private static void chooseCopy() {
		long[] shi = new long[size];
		long sum = 0;
		for (int i = 0; i < size; i++) {
			shi[i] = getShi(arr[i]);
			if(shi[i]<0) shi[i]=0;
			sum += shi[i];
		}
		double[] xl = new double[size];
		for (int i = 0; i < size; i++) {
			xl[i] = (double) shi[i] / sum;
		}
		double[] jll = new double[size];
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				jll[i] = xl[i];
			} else {
				jll[i] = jll[i - 1] + xl[i];
			}
		}
		int[] zhong = new int[size];
		Arrays.fill(zhong, 0);
		for (int i = 0; i < size; i++) {
			double suiji = new Random().nextDouble();
			for(int yy=0;yy<size;yy++){
				if(suiji<jll[yy]){
					zhong[yy]++;
					break;
				}
			}
		}
		int[] arr1 = new int[size];
		Arrays.fill(arr1, 0);
		int mm = 0;
		for (int i = 0; i < size; i++) {
			if (zhong[i] != 0) {
				for (int k = 0; k < zhong[i]; k++) {
					arr1[mm++] = arr[i];
				}
			}
		}
		arr = Arrays.copyOf(arr1, arr1.length);
	}
	/**
	 * 交叉
	 * 随机取若干位替换，排除0
	 */
	private static void jCha() {
		int k = (int) (size * jl);
		int brand = new Random().nextInt(127-1)+1;
		while (k > 0) {
			int wj1 = new Random().nextInt(size);
			int wj2 = new Random().nextInt(size);
			while(wj2==wj1){
				wj2=new Random().nextInt(size);
			}
			//获取需要替换位数的数的和
			int temp = arr[wj1] & brand;
			int temp2 = arr[wj2] & brand;
			
			
			int wtemp=brand^127;
			int wtemp2=brand^127;
			
			//获取不需要替换位的数的和
			arr[wj1]=arr[wj1]&wtemp;
			arr[wj2]=arr[wj2]&wtemp2;
			
			if ((arr[wj1] | temp2) > 100 || (arr[wj2] | temp) > 100) {
				continue;
			}
			
			//交换低四位
			arr[wj1] = arr[wj1] | temp2;
			arr[wj2] = arr[wj2] | temp;
			k--;
		}
	}

	/**
	 * 变异
	 * 在size*7范围内选随机数，然后计算变异的位数并更改该位值
	 */
	private static void bYi() {
		int bb = (int) (bl * size * 7);
		while (bb > 0) {
			int rand = new Random().nextInt(size * 7);
			int zh = rand / 7;
			int xi = rand % 7;
			int temp = arr[zh] & (int) (Math.pow(2, xi)+0.5);
			if (temp == 0) {
				if ((arr[zh] | (int) (Math.pow(2, xi)+0.5)) > 100) {
					continue;
				}
				arr[zh] = arr[zh] | (int) (Math.pow(2, xi)+0.5);
			} else {
				arr[zh] = arr[zh] - temp;
			}
			bb--;
		}
	}
	
	/**
	 * 输出Arr数据
	 */
	private static void printArr(int kk) {
		System.out.printf("第%d代：", kk);
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + "   ");
		}
		System.out.println();
		System.out.println();
	}
	private static long getShi(int shu) {
		return 3 * (long) (Math.pow(shu, 5) + 0.5) - 17 * (long) (Math.pow(shu, 4) + 0.5);
	}
	/**
	 * 设置最大最小，并判断设置flagMin,flagMax
	 */
	private static void setMaxMin(){
		for(int i=0;i<size;i++){
			long shi=getShi(arr[i]);
			if(Max<shi)
				Max=shi;
			if(Min>shi)
				Min=shi;
			if(Max==shiMax)
				flagMax=true;
			if(Min==shiMin)
				flagMin=true;
		}
		
	}
}