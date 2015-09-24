package leetcode;

public class N5_longest {
	public static String longestPalindrome(String s) {
		int len=s.length();
		int max=0,maxMiddle=0,j,i;
		for(i=1;i<len;i++){
			for(j=1;j<=i&&i+j<len;j++){
				if(s.charAt(i-j)!=s.charAt(i+j))
					break;
			}
			if(max<j-1){
				max=j-1;
				maxMiddle=i;
			}
		}
		int max2=0,kk=0;;
		for(i=0;i<len;i++){
			for(j=0;j<=i&&j+i+1<len;j++){
				if(s.charAt(i-j)!=s.charAt(i+1+j))
					break;
			}
			if(max2<j){
				kk=i;
				max2=j;
			}
		}
		if(max*2+1>max2*2)
			return s.substring(maxMiddle-max,maxMiddle+max+1);
		else
			return s.substring(kk-max2+1,kk+max2+1);

	}
	public static void main(String[] args){
		String s="bb";
		System.out.println(longestPalindrome(s));
		
	}
}
