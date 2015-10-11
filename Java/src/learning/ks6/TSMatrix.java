package learning.ks6;

public class TSMatrix {
	private static final int MAXNSIZE = 50;
	Triple[] data;
	int m, n, len;

	public TSMatrix() {
		data = new Triple[MAXNSIZE];
		this.len = 0;
	}

	public TSMatrix(int m, int n) {
		data = new Triple[MAXNSIZE];
		this.m = m;
		this.n = n;
		this.len = 0;
	}

	public int contain(int row, int col) {
		for (int i = 0; i < len; i++) {
			if (data[i].row == row && data[i].col == col) {
				return i;
			}
		}
		return -1;
	}

	public void add(int row, int col, int e) {
		Triple tr = new Triple(row, col, e);
		data[len++] = tr;
	}

	public void printThree() {
		for (int i = 0; i < len; i++) {
			System.out.println(data[i].row + "	" + data[i].col + "		"
					+ data[i].e);
		}
	}

	public void printMatrix() {
		int[][] matr = new int[m + 1][n + 1];
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				matr[i][j] = 0;
			}
		}
		for (int i = 0; i < len; i++) {
			matr[data[i].row][data[i].col] += data[i].e;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(matr[i][j] + " ");
			}
			System.out.println();
		}

	}

}