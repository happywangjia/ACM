package algorithms.tree;

import java.util.Scanner;

/**
 * 
 * @tag:线段树－－单点替换|| I Hate It
 * @link:http://acm.hdu.edu.cn/showproblem.php?pid=1754
 * @Num:Hdu1754.java
 * @author hijj
 * Create at: 2015年10月3日 上午2:39:00
 */
public class Hdu1754 {
	private static final int maxn=222222;
	private static int[] MAX=new int[maxn<<2];
	private static Scanner cin=new Scanner(System.in);
	
	public static void main(String[] args){
		int n,m;
		toZero();
		while(cin.hasNext()){
			n=cin.nextInt();
			m=cin.nextInt();
			build(1, n, 1);
			while(m--!=0){
				String op=cin.next();
				int a=cin.nextInt();
				int b=cin.nextInt();
				if(op.charAt(0)=='Q'){
					System.out.println(query(a,b,1,n,1));
				}else if(op.charAt(0)=='U'){
					update(a,b,1,n,1);
				}
			}
		}
	}
	private static void build(int l,int r,int rt){
		if(l==r){
			MAX[rt]=cin.nextInt();
			return;
		}
		int m=(l+r)>>1;
		build(l,m,rt<<1);
		build(m+1,r,rt<<1|1);
		pushUp(rt);
	}
	private static void pushUp(int rt){
		MAX[rt]=Math.max(MAX[rt<<1],MAX[rt<<1|1]);
	}
	private static int query(int L,int R,int l,int r,int rt){
		if(L<=l&&R>=r){
			return MAX[rt];
		}
		int m=(l+r)>>1;
		int ret=0;
		if(L<=m) ret+=Math.max(ret, query(L, R, l, m, rt<<1));
		if(R>m) ret+=Math.max(ret, query(L,R,m+1,r,rt<<1|1));
		return ret;
	}
	private static void update(int p,int sc,int l,int r,int rt){
		if(l==r){
			MAX[rt]=sc;
			return;
		}
		int m=(l+r)>>1;
		if(p<=m) update(p,sc,l,m,rt<<1);
		else update(p,sc,m+1,r,rt<<1|1);
		pushUp(rt);
	}
	private static void toZero(){
		for(int i=0;i<maxn;i++){
			MAX[i]=Integer.MIN_VALUE;
		}
	}
}
