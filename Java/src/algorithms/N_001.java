package algorithms;

/**
 * 
 * @tag:插入排序
 * @link:
 * @Num:N_001.java
 * @author hijj
 * Create at: 2015年9月10日 下午11:55:22
 */
public class N_001 {
	private final static int maxn=200;
	private static void insertSort(int[] nums){
		int key=0,j=0;
		for(int i=2;i<=maxn;i++){
			key=nums[i];
			j=i-1;
			while(j>0&&nums[j]>key){
				nums[j+1]=nums[j];
				j--;
			}
			nums[j+1]=key;
		}
	}
	public static void main(String[] args){
		int[] nums=new int[maxn+1];
		for(int i=1;i<=maxn;i++){
			nums[i]=(int) Math.floor(Math.random()*100);
		}
		insertSort(nums);
		for(int i=1;i<=maxn;i++){
			if(i%10==0)
				System.out.println("  "+nums[i]);
			else
				System.out.print("  "+nums[i]);
		}
	}
	
}
 
