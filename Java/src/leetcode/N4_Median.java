package leetcode;

public class N4_Median {
	public static void main(String[] args){
		int[] nums1 = new int[0];
		int[] nums2=new int[1];
		nums2[0]=1;
		System.out.println(findMedianSortedArrays(nums1, nums2));
		
	}
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len1=nums1.length;
		int len2=nums2.length;
		int len=len1+len2;
		int[] nums=new int[len];
		int a=0,b=0,d=0;
		while(a<len1&&b<len2){
			if(nums1[a]<nums2[b]){
				nums[d++]=nums1[a];
				a++;
			}else{
				nums[d++]=nums2[b];
				b++;
			}
		}
		if(a<len1){
			while(a<len1){
				nums[d++]=nums1[a];
				a++;
			}
		}
		if(b<len2){
			while(b<len2){
				nums[d++]=nums2[b];
				b++;
			}
		}
		if(len%2==0){
			return (double)(nums[len/2]+nums[len/2-1])/2;
		}else{
			return (double)nums[len/2];
		}
	}
}
