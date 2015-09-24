package learning;

import java.util.Arrays;
import java.util.Comparator;

public class learn_3 {
	private static final int maxn = 100;

	public static void main(String[] args) {
		// int[] nums = new int[maxn];
		// for (int i = 0; i < maxn; i++) {
		// nums[i] = (int) Math.floor(Math.random() * 100);
		// }
		// Arrays.sort(nums, 0, maxn);
		// for (int i = 0; i < maxn; i++) {
		// System.out.print(nums[i] + " ");
		// }
		// System.out.println();

		wj[] num = new wj[maxn];
		for (int i = 0; i < maxn; i++) {
			num[i] = new wj();
			num[i].a = (int) Math.floor(Math.random() * 100);
			num[i].b = (int) Math.floor(Math.random() * 100);
		}
		Comparator<wj> ss = new Comparator<wj>() {

			@Override
			public int compare(wj o1, wj o2) {
				if (o1.a > o2.a)
					return 1;
				else if (o1.a < o2.a)
					return -1;
				else if (o1.b > o2.b)
					return 1;
				else
					return -1;
			}
		};
		Arrays.sort(num, 0, maxn, ss);
		for (int i = 0; i < maxn; i++) {
			System.out.println(num[i].a + "	" + num[i].b);
		}

	}
}

class wj {
	public int a;
	public int b;
}
