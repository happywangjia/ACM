package algorithms;

public class N_006 {
	private static int maxn=100;
	private static int[] ff=new int[maxn];
	public static void main(String[] args){
		String t="afadfesdafadfdadfadfadadds";
		String p="adf";
		find(t,p);
	}
	
	public static void getFail(String p){
		int len=p.length();
		ff[0]=0;
		ff[1]=0;
		for(int i=1;i<len;i++){
			int j=ff[i];
			while(j!=0&&p.charAt(i)!=p.charAt(j)){
				j=ff[j];
			}
			ff[i+1]=p.charAt(i)==p.charAt(j)?j+1:0;
		}
	}
	public static void find(String t,String p){
		getFail(p);
		int tLen=t.length();
		int pLen=p.length();
		int j=0;
		for(int i=0;i<tLen;i++){
			while(j!=0&&p.charAt(j)!=t.charAt(i)){
				j=ff[j];
			}
			if(p.charAt(j)==t.charAt(i))
				j++;
			if(j==pLen){
				System.out.println(i-pLen+1);
			}
		}
	}
	
}
