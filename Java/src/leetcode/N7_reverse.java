package leetcode;

public class N7_reverse {
	public static int reverse(int x) {
		boolean fu=true;
		boolean flag=false;
		if(x<0){
			if(x==Integer.MIN_VALUE) return 0;
			fu=false;
			x*=-1;
		}
		long sum=0;
		while(x/10>0){
			if(flag)
				sum=sum*10+x%10;
			if(flag==false&&x/10>0){
				flag=true;
				sum=sum*10+x%10;
			}
			x/=10;
		}
		sum=sum*10+x;
		if(sum==0) return (int) sum;
		if(fu==false){
			sum*=-1;
			if(sum<Integer.MIN_VALUE) return 0;
			return (int) sum;
		}
		if(sum>Integer.MAX_VALUE){
			return 0;
		}
		return (int) sum;
	}
	public static void main(String[] args){
		System.out.println(reverse(-2147483648));
	}
}
