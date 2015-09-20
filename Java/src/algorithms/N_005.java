package algorithms;

/**
 * 
 * @tag:堆排序
 * @link:
 * @Num:N_005.java
 * @author hijj Create at: 2015年9月18日 下午12:07:32
 *         注：本例是从1开始，当有其他需求的时候可以通过start设置，步骤相对复杂
 */
public class N_005 {
	private static final int maxn = 20;

	public static void main(String[] args) {
		int[] nums = new int[maxn + 1];
		for (int i = 1; i <= maxn; i++)
			nums[i] = (int) Math.floor(Math.random() * 100);
		for (int i = 1; i <= maxn; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
		int len = nums.length - 1;
		heapSort(nums, nums.length - 1);
		for (int i = len; i >= 2; i--) {
			int temp = nums[i];
			nums[i] = nums[1];
			nums[1] = temp;
			maxHeap(nums, 1, i - 1);
		}
		for (int i = 1; i <= maxn; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	private static void heapSort(int[] nums, int len) {
		int middle = len / 2;
		for (int i = middle; i >= 1; i--) {
			maxHeap(nums, i, len);
		}
	}

	private static void maxHeap(int[] nums, int middle, int len) {
		int left = middle * 2;
		int right = middle * 2 + 1;
		int large;
		if (left <= len && nums[left] > nums[middle]) {
			large = left;
		} else {
			large = middle;
		}
		if (right <= len && nums[right] > nums[large]) {
			large = right;
		}
		if (large != middle) {
			int temp = nums[large];
			nums[large] = nums[middle];
			nums[middle] = temp;
			maxHeap(nums, large, len);
		}

	}
}
