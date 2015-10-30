package learning.sk9;

public class SortTest {
	private static int yi = 0;
	private static int cm = 0;

	public static void maopao(int[] mpao) {
		int len = mpao.length;
		yi = 0;
		cm = 0;
		int temp;
		for (int i = 1; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				if (mpao[i] > mpao[j]) {
					temp = mpao[i];
					mpao[i] = mpao[j];
					mpao[j] = temp;
					yi += 3;
				}
				cm++;
			}
		}
		System.out.println("冒泡排序：");
		System.out.printf("	主关键字移动了%d次,比较了%d次\n",yi,cm);
	}

	public static void charu(int[] cru) {
		int len = cru.length;
		yi = 0;
		cm = 0;
		int key = 0, j = 0;
		for (int i = 2; i < len; i++) {
			key = cru[i];
			j = i - 1;
			while (j > 0 && cru[j] > key) {
				cru[j + 1] = cru[j];
				yi++;
				cm++;
				j--;
			}
			cm++;
			cru[j + 1] = key;
		}
		System.out.println("直接插入排序：");
		System.out.printf("	主关键字移动了%d次,比较了%d次\n",yi,cm);
	}

	public static void xuanze(int[] xze) {
		int len = xze.length;
		yi = 0;
		cm = 0;
		for (int i = 1; i < len; i++) {
			int j = selectMinkey(xze, i);
			if (i != j) {
				int temp = xze[i];
				xze[i] = xze[j];
				xze[j] = temp;
				yi += 3;
			}
		}
		System.out.println("简单选择排序：");
		System.out.printf("	主关键字移动了%d次,比较了%d次\n",yi,cm);
	}

	private static int selectMinkey(int[] xze, int i) {
		int len = xze.length;
		int Min = xze[i];
		int index = i;
		for (int j = i; j < len; j++) {
			if (Min > xze[j]) {
				Min = xze[j];
				index = j;
				yi++;
			}
			cm++;
		}
		return index;
	}

	public static void qSort(int[] qsort) {
		yi = 0;
		cm = 0;
		int len = qsort.length;
		quickSort(qsort, 1, len - 1);
		System.out.println("快速排序：");
		System.out.printf("	主关键字移动了%d次,比较了%d次\n",yi,cm);
	}

	private static void quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int q = find(arr, start, end);
			quickSort(arr, start, q - 1);
			quickSort(arr, q + 1, end);
		}
	}

	private static int find(int[] arr, int start, int end) {
		if (start == end)
			return start;
		int i = start, temp;
		for (; i < end; i++) {
			if (arr[i] < arr[end]) {
				temp = arr[i];
				arr[i] = arr[start];
				arr[start] = temp;
				start++;
				yi += 3;
			}
			cm++;
		}
		temp = arr[start];
		arr[start] = arr[end];
		arr[end] = temp;
		yi += 3;
		return start;
	}

	public static void shellSort(int[] shell) {
		yi = 0;
		cm = 0;
		int len = shell.length;
		int[] dlta = new int[len];
		int d = 0;
		int yy = len;
		while (yy/2!=0) {
			yy/=2;
			dlta[d++] = yy;
		}
		shellsort(shell, d, dlta);
	}

	private static void shellsort(int[] shu, int t, int[] dlta) {
		for (int i = 0; i < t; i++) {
			shellInsert(shu, dlta[i]);
		}
		System.out.println("希尔排序：");
		System.out.printf("	主关键字移动了%d次,比较了%d次\n",yi,cm);
	}

	private static void shellInsert(int[] shu, int dk) {
		int temp;
		for (int i = dk + 1; i < shu.length; i++) {
			if (shu[i] < shu[i - dk]) {
				temp = shu[i];
				yi++;
				int j;
				for (j = i - dk; j >=1 && temp < shu[j]; j -= dk) {
					cm++;
					shu[j + dk] = shu[j];
					yi++;
				}
				cm++;
				shu[j + dk] = temp;
				yi++;
			}
			cm++;
		}
	}

	public static void heapSort(int[] hsort) {
		int len = hsort.length;
		heapSort(hsort, len - 1);
		for (int i = len - 1; i >= 2; i--) {
			int temp = hsort[i];
			hsort[i] = hsort[1];
			hsort[1] = temp;
			maxHeap(hsort, 1, i - 1);
		}
		System.out.println("堆排序：");
		System.out.printf("	主关键字移动了%d次，比较了%d次\n",yi,cm);
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
		cm++;
		if (right <= len && nums[right] > nums[large]) {
			large = right;
		}
		cm++;
		if (large != middle) {
			int temp = nums[large];
			nums[large] = nums[middle];
			nums[middle] = temp;
			yi += 3;
			maxHeap(nums, large, len);
		}
	}
}
