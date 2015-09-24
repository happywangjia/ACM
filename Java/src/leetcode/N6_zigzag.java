package leetcode;

public class N6_zigzag {
	public static String convert(String s, int numRows) {
		int n=numRows*2-2;
		int len=s.length();
		if(numRows==1) return s;
		char[] cn=new char[len+numRows*2-1];
		for(int i=0;i<cn.length;i++){
			cn[i]=' ';
		}
		int k,mod,z=(len+n-1)/n;
		for(int i=0;i<len;i++){
			mod=i%n;
			k=i/n;
			if(mod==0){
				cn[k]=s.charAt(i);
				continue;
			}
			if(mod==numRows-1){
				cn[z+2*(numRows-2)*z+k]=s.charAt(i);
				continue;
			}
			if(mod<numRows-1){
				cn[z+2*(mod-1)*z+2*k]=s.charAt(i);
			}else{
				cn[z+2*(n-mod-1)*z+2*k+1]=s.charAt(i);
			}
		}
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<len+numRows*2-1;i++){
			if(cn[i]!=' ')
				sb.append(cn[i]);
		}
		return sb.toString();	
	}
	public static void main(String[] args){
		System.out.println("PAYPALISHIRING");
		System.out.println(convert("PAYPALISHIRING", 3));
	}
	
}
