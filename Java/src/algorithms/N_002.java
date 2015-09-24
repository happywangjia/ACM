package algorithms;

/**
 * 
 * @tag:归并排序
 * @link:
 * @Num:N_002.java
 * @author hijj
 * Create at: 2015年9月11日 上午2:12:28
 */
public class N_002 {
	private static final int maxn=200;
	private static void mergeSort(int[] nums,int start,int end){
		if(start<end){
			int mid=(start+end)/2;
			mergeSort(nums,start,mid);
			mergeSort(nums,mid+1,end);
			merge_num(nums,start,mid,end);
		}
 	}
	
	private static void merge_num(int[] nums, int start, int mid, int end) {
		int num1=mid-start+1;
		int num2=end-mid;
		int[] arr1=new int[num1+1];
		int[] arr2=new int[num2+1];
		int d=start,i;
		for(i=0;i<num1;i++){
			arr1[i]=nums[d++];
		}
		arr1[i]=(int) Double.POSITIVE_INFINITY;
		for(i=0;i<num2;i++){
			arr2[i]=nums[d++];
		}
		arr2[i]=(int) Double.POSITIVE_INFINITY;
		int k=end-start+1;
		int x=0,y=0,z=start;
		while(--k>=0){
			if(arr1[x]>arr2[y]){
				nums[z++]=arr2[y];
				y++;
			}else{
				nums[z++]=arr1[x];
				x++;
			}
		}
	}

	public static void main(String[] args){
		int[] nums=new int[maxn+1];
		for(int i=1;i<=maxn;i++){
			nums[i]=(int) Math.floor(Math.random()*100);
		}
		mergeSort(nums,1,maxn);
		for(int i=1;i<=maxn;i++){
			if(i%10==0)
				System.out.println("  "+nums[i]);
			else
				System.out.print("  "+nums[i]);
		}
		
	}
}
