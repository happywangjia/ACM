package learning;

import java.util.HashSet;
import java.util.Scanner;

public class complie1 {
	private static final char VN[]={
		'S','A','B','C','D'
	};
	private static HashSet<Character> setN=new HashSet<Character>();
	private static HashSet<Character> setT=new HashSet<Character>();
	private static final char VT[]={
		'a','b','c','d','@'
	};
	private static final char ST='S';
	private static Scanner cin=new Scanner(System.in);
	
	public static void main(String[] args){
		for(int i=0;i<VN.length;i++){
			setN.add(VN[i]);
		}
		for(int i=0;i<VT.length;i++){
			setT.add(VT[i]);
		}
		String str=null;
		int k=3;
		while(cin.hasNext()){
			str=cin.next();
			if(str.equals("#")) break;
			String[] st=str.split("-");
			int len1=st[0].length();
			int len2=st[1].length();
			if(k==3){
				if(len1==1&&setN.contains(st[0].charAt(0))){
					boolean flag1=setT.contains(st[1].charAt(0))&&setN.contains(st[1].charAt(1));
					boolean flag2=setT.contains(st[1].charAt(1))&&setN.contains(st[1].charAt(0));
					if(len2==2&&(flag1||flag2)){
						continue;
					}
				}
				k=2;
			}
			if(k>=2){
				if(len1==1&&setN.contains(st[0].charAt(0))){
					continue;
				}
				k=1;
			}
			if(k>=1){
				if(len1==1&&len2==1&&setN.contains(st[0].charAt(0))&&st[1].charAt(0)=='@'){
					continue;
				}
				if(len1<=len2) continue;
				k=0;
			}
		}
		System.out.println(k);
	}
}
