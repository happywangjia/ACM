package leetcode;

import java.util.Hashtable;
/**\
 * 
 * @tag:Two Sum
 * @link:https://leetcode.com/problems/two-sum/
 * @Num:N1_Two_Sum.java
 * @author hijj
 * Create at: 2015年9月10日 上午1:30:41
 */
public class N1_Two_Sum {
	public static int[] twoSum(int[] numbers,int target){
		int[] a=new int[2];
		Hashtable<Integer, Integer> hashtable=new Hashtable<Integer,Integer>();
		int len=numbers.length;
		for(int i=0;i<len;i++){
			Integer n=hashtable.get(numbers[i]);
			if(n==null){
				hashtable.put(numbers[i], i);
			}
			n=hashtable.get(target-numbers[i]);
			if(n!=null&&n<i){
				a[0]=n+1;
				a[1]=i+1;
				break;
			}
		}
		return a;
	}
	public static void main(String[] args){
		int[] numbers={2,7,11,15};
		int target=9;
		int[] arr=twoSum(numbers,target);
		System.out.println(arr[0]+"	"+arr[1]);
		
	}

}
