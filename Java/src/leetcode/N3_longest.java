package leetcode;

import java.util.Hashtable;

public class N3_longest {
	public int lengthOfLongestSubstring(String s) {
		int len = s.length();
		Hashtable<Character, Integer> hashtable = new Hashtable<Character, Integer>();
		int max = 0;
		int k = 0, q = 0;
		for (int i = 0; i < len; i++) {
			Integer n = hashtable.get(s.charAt(i));
			if (n != null) {
				if (k > max)
					max = k;
				for (int j = q; j <= n; j++) {
					hashtable.remove(s.charAt(j));
					k--;
				}
				q = n + 1;
			}
			k++;
			hashtable.put(s.charAt(i), i);
		}
		if (k > max)
			max = k;
		return max;
	}
}
