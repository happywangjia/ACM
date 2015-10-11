package learning.ks6;

import java.util.Scanner;

public class MatrixTest {
	public static Scanner cin = new Scanner(System.in);

	public static void main(String[] args) {
		int n;
		TSMatrix aaa = null;
		TSMatrix bbb = null;
		TSMatrix ccc = null;
		while (true) {
			System.out.println("0:创建矩阵：");
			System.out.println("1:A+B");
			System.out.println("2:A-B");
			System.out.println("3:A*B");
			System.out.println("4:退出");
			n = cin.nextInt();
			switch (n) {
			case 0:
				aaa = createMatrix();
				bbb = createMatrix();
				break;
			case 1:
				ccc = aAddB(aaa, bbb);
				ccc.printMatrix();
				break;
			case 2:
				ccc = aJianB(aaa, bbb);
				ccc.printMatrix();
				break;
			case 3:
				ccc = aChenB(aaa, bbb);
				ccc.printMatrix();
				break;
			default:
				return;
			}
		}
	}

	private static TSMatrix aChenB(TSMatrix aaa, TSMatrix bbb) {
		if (aaa.n != bbb.m) {
			System.out.println("输入错误：");
		}
		TSMatrix ccc = new TSMatrix(aaa.m, bbb.n);
		for (int i = 0; i < aaa.len; i++) {
			for (int j = 0; j < bbb.len; j++) {
				if (aaa.data[i].col == bbb.data[j].row) {
					int kk = ccc.contain(aaa.data[i].row, bbb.data[j].col);
					if (kk == -1) {
						ccc.add(aaa.data[i].row, bbb.data[j].col, aaa.data[i].e * bbb.data[i].e);
					} else {
						ccc.data[kk].e += aaa.data[i].e * bbb.data[i].e;
					}
				}
			}
		}

		return ccc;
	}

	private static TSMatrix aJianB(TSMatrix aaa, TSMatrix bbb) {
		if (aaa.m != bbb.m || aaa.n != bbb.n) {
			System.out.println("矩阵错误");
			return null;
		}

		TSMatrix ccc = new TSMatrix(aaa.m, aaa.n);
		for (int i = 0; i < aaa.len; i++) {
			ccc.add(aaa.data[i].row, aaa.data[i].col, aaa.data[i].e);
		}
		for (int i = 0; i < bbb.len; i++) {
			int kk = ccc.contain(bbb.data[i].row, bbb.data[i].col);
			if (kk == -1) {
				ccc.add(bbb.data[i].row, bbb.data[i].col, bbb.data[i].e * -1);
			} else {
				ccc.data[i].e -= bbb.data[i].e;
			}

		}
		return ccc;

	}

	private static TSMatrix aAddB(TSMatrix aaa, TSMatrix bbb) {
		if (aaa.m != bbb.m || aaa.n != bbb.n) {
			System.out.println("矩阵错误");
			return null;
		}

		TSMatrix ccc = new TSMatrix(aaa.m, aaa.n);
		for (int i = 0; i < aaa.len; i++) {
			ccc.add(aaa.data[i].row, aaa.data[i].col, aaa.data[i].e);
		}
		for (int i = 0; i < bbb.len; i++) {
			int kk = ccc.contain(bbb.data[i].row, bbb.data[i].col);
			if (kk == -1) {
				ccc.add(bbb.data[i].row, bbb.data[i].col, bbb.data[i].e);
			} else {
				ccc.data[i].e += bbb.data[i].e;
			}

		}
		return ccc;
	}

	private static TSMatrix createMatrix() {
		System.out.println("输出行数m 列数n:");
		int m, n;
		m = cin.nextInt();
		n = cin.nextInt();
		TSMatrix matrix = new TSMatrix(m, n);
		int[][] arr = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				arr[i][j] = cin.nextInt();
				if (arr[i][j] != 0) {
					matrix.add(i, j, arr[i][j]);
				}
			}
		}
		return matrix;

	}
}