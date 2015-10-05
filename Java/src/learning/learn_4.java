package learning;

import java.util.Comparator;

public class learn_4 {
//	private static final int maxn=20;
	
	public static void main(String[] args) {
		String str="wangjia";
		str="wwww";
		System.out.println(str);
	}
}
class cmp implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		if(o1>o2) return 1;
		else return -1;
	}
	
}