package algorithms.tree;

/**
 * 
 * @tag:最优二叉搜索树
 * @link:
 * @Num:OptimalBST.java
 * @author hijj
 * Create at: 2015年10月4日 上午6:10:26
 */
public class OptimalBST {
	private static final int n = 5;
	private static final double[] p = { -1, 0.15, 0.1, 0.05, 0.1, 0.2 };
	private static final double[] q = { 0.05, 0.1, 0.05, 0.05, 0.05, 0.1 };
	private static int root[][] = new int[n + 1][n + 1];
	private static double[][] w = new double[n + 2][n + 2];
	private static double[][] e = new double[n + 2][n + 2];
	
	public static void main(String[] args){
		optimalBST();
		printBST(1, n, -1);
	}
	private static void optimalBST(){
		for(int i=1;i<=n+1;i++){
			w[i][i-1]=q[i-1];
			e[i][i-1]=q[i-1];
		}
		for(int len=1;len<=n;len++){
			for(int i=1;i<=n-len+1;i++){
				int j=i+len-1;
				e[i][j]=Double.MAX_VALUE;
				w[i][j]=w[i][j-1]+p[j]+q[j];
				for(int k=i;k<=j;k++){
					double temp=e[i][k-1]+e[k+1][j]+w[i][j];
					if(temp<e[i][j]){
						e[i][j]=temp;
						root[i][j]=k;
					}
				}	
			}
		}
	}
	private static void printBST(int l,int r,int rt){
		int childRoot=Integer.MAX_VALUE;
		if(l<n+1&&r<n+1)
			childRoot=root[l][r];
		if(childRoot==root[1][n]){
			System.out.println("k"+childRoot+" is root");
			printBST(l,childRoot-1,childRoot);
			printBST(childRoot+1,r,childRoot);
			return;
		}
		if(r<l-1) return;
		if(r==l-1){
			if(r<rt){
				System.out.println("d"+r+"是k"+rt+"的左孩子");
			}else{
				System.out.println("d"+r+"是k"+rt+"的右孩子");
			}
		}else{
			if(childRoot<rt){
				System.out.println("k"+childRoot+"是k"+rt+"的左孩子");
			}else{
				System.out.println("k"+childRoot+"是k"+rt+"的右孩子");
			}
			printBST(l, childRoot-1, childRoot);
			printBST(childRoot+1, r, childRoot);
		}
	}
}
