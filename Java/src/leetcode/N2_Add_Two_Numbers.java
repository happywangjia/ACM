package leetcode;

/**
 * 
 * @tag:Add Two Numbers
 * @link:https://leetcode.com/problems/add-two-numbers/
 * @Num:N2_Add_Two_Numbers.java
 * @author hijj Create at: 2015年9月10日 上午1:48:26
 */
public class N2_Add_Two_Numbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int[] ss1 = new int[100];
		int[] ss2 = new int[100];
		int k1 = 0;
		ss1[k1++] = l1.val;
		while (l1.next != null) {
			l1 = l1.next;
			ss1[k1++] = l1.val;
		}
		int k2 = 0;
		ss2[k2++] = l2.val;
		while (l2.next != null) {
			l2 = l2.next;
			ss2[k2++] = l2.val;
		}
		int k;
		if (k1 > k2)
			k = k1;
		else
			k = k2;
		for (int i = k1; i < k; i++)
			ss1[i] = 0;
		for (int i = k2; i < k; i++)
			ss2[i] = 0;
		int cnt = 0;
		int val = 0;
		val = ss1[0] + ss2[0];
		if (val >= 10)
			cnt = 1;
		ListNode l = new ListNode(val % 10);
		val = 0;
		val = ss1[1] + ss2[1] + cnt;
		if (k <= 1 && val == 0)
			return l;
		if (val >= 10)
			cnt = 1;
		else
			cnt = 0;
		ListNode p = new ListNode(val % 10);
		val = 0;
		l.next = p;

		for (int i = 2; i <= k; i++) {
			if (i == k && cnt == 1) {
				p.next = new ListNode(cnt);
				break;
			} else if (i == k)
				break;

			val = cnt + ss1[i] + ss2[i];
			if (val >= 10)
				cnt = 1;
			else
				cnt = 0;
			p.next = new ListNode(val % 10);
			val = 0;
			p = p.next;
		}
		return l;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
