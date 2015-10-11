package leetcode;

public class N14_Longest {
	public static String longestCommonPrefix(String[] strs) {
		int len=strs.length;
		int k=0;
		boolean flag=true;
		if(len==0) return "";
		while(true){
			if(k>=strs[0].length()){
				flag=false;
				break;
			}
			for(int i=1;i<len;i++){
				if(k>=strs[i].length()||strs[i].charAt(k)!=strs[0].charAt(k)){
					flag=false;
					break;
				}
			}
			if(flag==false) break;
			k++;
		}
		if(k==0) return "";
		return strs[0].substring(0,k);
	}
	public static void main(String[] args){
		String[] strs=new String[3];
		strs[0]="adfsd";
		strs[1]="adfdfd";
		strs[2]="adfdfd";
		System.out.println(longestCommonPrefix(strs));
	}
}
