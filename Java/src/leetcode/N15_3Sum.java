package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N15_3Sum {
	public static List<List<Integer>> threeSum(int[] nums) {
		int len = nums.length;
//		if(len<=2) return null;
		Arrays.sort(nums);
		List<List<Integer>> ww=new ArrayList<>();
		for(int i=len-1;i>=2;i--){
			if(i<len-1&&nums[i]==nums[i+1])
				continue;
			List<List<Integer>> list=twoSum(nums,i-1, -nums[i]);
			for(int j=0;j<list.size();j++){
				list.get(j).add(nums[i]);
			}
			ww.addAll(list);
		}
		return ww;
	}
	public static List<List<Integer>> twoSum(int[] num,int end,int target){
		List<List<Integer>> res=new ArrayList<>();
		if(num==null||num.length<=1)
			return res;
		int l=0,r=end;
		while(l<r){
			if(num[l]+num[r]==target){
				List<Integer> item=new ArrayList<>();
				item.add(num[l]);
				item.add(num[r]);
				res.add(item);
				l++;
				r--;
				while(l<r&&num[l]==num[l-1])
					l++;
				while(l<r&&num[r]==num[r+1])
					r--;
			}else if(num[l]+num[r]>target){
				r--;
			}else{
				l++;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] arrs={-1,0,1,2,-1,-4};
		System.out.println(threeSum(arrs));
	}
}
