package algorithms;

public class N_003 {
	private static final int maxn=200;
	public static void main(String[] args){
		int[] nums=new int[maxn+1];
		for(int i=1;i<=maxn;i++){
			nums[i]=(int) Math.floor(Math.random()*100);
		}
		quickSort(nums,1,maxn);
		for(int i=1;i<=maxn;i++){
			if(i%10==0)
				System.out.println("  "+nums[i]);
			else
				System.out.print("  "+nums[i]);
		}
	}
	private static void quickSort(int[] arr,int start,int end){
		if(start<end){
			int q=find(arr,start,end);
			quickSort(arr,start,q-1);
			quickSort(arr,q+1,end);
		}
	}
	private static int find(int[] arr,int start,int end){
		if(start==end) return start;
		int i=start,temp;
		for(;i<end;i++){
			if(arr[i]<arr[end]){
				temp=arr[i];
				arr[i]=arr[start];
				arr[start]=temp;
				start++;
			}
		}
		temp=arr[start];
		arr[start]=arr[end];
		arr[end]=temp;
		return start;
	}

}
