package learning.bianyi7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class LL1 {
	private static Scanner cin = new Scanner(System.in);
	private static HashMap<Character, ArrayList<ArrayList<Character>>> inString = new HashMap<>();
	private static HashMap<Character, ArrayList<ArrayList<Character>>> inString2 = new HashMap<>();
	private static HashSet<Character> notEnd = new HashSet<>();
	private static HashMap<Character, Boolean> flag = new HashMap<>();
	private static HashMap<Character, HashSet<Character>> First = new HashMap<>();
	private static HashMap<Character, HashSet<Character>> Follow = new HashMap<>();
	private static HashMap<String, HashSet<Character>> Select = new HashMap<>();
	private static HashMap<String, String> fen = new HashMap<>();
	private static Stack<Character> stack1 = new Stack<>();
	private static Stack<Character> stack2 = new Stack<>();
	private static char start;

	public static void main(String[] args) {
		init();
		cclone();
		toNull();
		System.out.println("is to null:");
		printFlag();
		first();
		HashSet<Character> sss = new HashSet<>();
		sss.add('#');
		Follow.put(start, sss);
		follow();
		if (hasNotEnd()) {
			gai();
		}
		select();
		System.out.println("first:");
		printFF(First);
		System.out.println("follow:");
		printFF(Follow);
		
		Set<String> sst = Select.keySet();
		Iterator<String> it = sst.iterator();
		System.out.println("Select:");
		while (it.hasNext()) {
			String ccc = it.next();
			System.out.println(ccc + "  " + Select.get(ccc));
		}
		toSelect();
		System.out.println("输入测试串：");
		String line=cin.next();
		ce(line);
	}
	private static void printFF(HashMap<Character, HashSet<Character>> map){
		Set<Character> ssss = map.keySet();
		Iterator<Character> it2 = ssss.iterator();
		while (it2.hasNext()) {
			char c = it2.next();
			System.out.println(c + "  " + map.get(c));
		}
	}

	private static void toSelect() {
		Set<String> set = Select.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String line = it.next();
			String[] str = line.split("->");
			HashSet<Character> set2 = Select.get(line);
			Iterator<Character> it2 = set2.iterator();
			while (it2.hasNext()) {
				char cc = it2.next();
				String ll = str[0].charAt(0) + "" + cc;
				System.out.println("ll " + ll);
				fen.put(ll, str[1]);
			}
		}
	}

	private static void ce(String str) {
		stack1.push('#');
		stack1.push(start);
		stack2.push('#');
		for (int i = str.length() - 1; i >= 0; i--) {
			stack2.push(str.charAt(i));
		}
		System.out.println(stack1 + "\t\t" + stack2);
		while (stack1.lastElement() != '#' && stack2.lastElement() != '#') {
			while (stack1.lastElement() == '&')
				stack1.pop();
			char c1 = stack1.lastElement();
			char c2 = stack2.lastElement();
			if (c1 == c2) {
				stack1.pop();
				stack2.pop();
			} else {
				stack1.pop();
				String strs = fen.get(c1 + "" + c2);
				for (int i = strs.length() - 1; i >= 0; i--) {
					stack1.push(strs.charAt(i));
				}
			}
			System.out.println(stack1 + "\t\t" + stack2);
		}

	}

	private static void select() {
		Iterator<Character> it = notEnd.iterator();
		while (it.hasNext()) {
			char c = it.next();
			ArrayList<ArrayList<Character>> list = inString.get(c);
			ArrayList<ArrayList<Character>> list2 = inString2.get(c);
			for (int i = 0; i < list.size(); i++) {
				ArrayList<Character> ll = list.get(i);
				HashSet<Character> set = new HashSet<>();
				String str = c + "->";
				for (int j = 0; j < ll.size(); j++) 
					str += ll.get(j);
				if (list2.get(i).isEmpty()) {
					int k = 0;
					while (k < ll.size() && isNotEnd(ll.get(k))) {
						set.addAll(First.get(ll.get(k)));
						k++;
					}
					if (k == 0) {
						if (ll.get(k) != '&') 
							set.add(ll.get(k));
					}
					if (set.contains('&'))
						set.remove('&');
					set.addAll(Follow.get(c));
				} else {
					int k = 0;
					while (k < ll.size() && isNotEnd(ll.get(k))) {
						set.addAll(First.get(ll.get(k)));
						k++;
					}
					if (k == 0) 
						set.add(ll.get(k));
					set.remove('&');
				}
				Select.put(str, set);
			}
		}
	}

	private static void gai() {
		while (!isAll(start, Follow)) {
			HashSet<Character> haseSet = Follow.get(start);
			Iterator<Character> it = haseSet.iterator();
			while (it.hasNext()) {
				char cc = it.next();
				if (isNotEnd(cc)) {
					HashSet<Character> set = Follow.get(cc);
					haseSet.addAll(set);
					haseSet.remove(cc);
					break;
				}
			}
			if (haseSet.contains(start))
				haseSet.remove(start);
		}
		follow2();
	}

	private static boolean hasNotEnd() {
		Set<Character> set = Follow.keySet();
		Iterator<Character> it = set.iterator();
		while (it.hasNext()) {
			char c = it.next();
			if (!isAll(c, Follow))
				return true;
		}
		return false;
	}

	private static void follow() {
		Iterator<Character> it = notEnd.iterator();
		while (it.hasNext()) {
			char ch = it.next();
			ArrayList<ArrayList<Character>> list = inString.get(ch);
			for (ArrayList<Character> list2 : list) {
				for (int i = 0; i < list2.size(); i++) {
					int j;
					HashSet<Character> hashSet2;
					if (isNotEnd(list2.get(i))) {
						char csc = list2.get(i);
						if (Follow.containsKey(csc)) 
							hashSet2 = Follow.get(csc);
						else 
							hashSet2 = new HashSet<>();
						j = i + 1;
						while (j < list2.size() && isNotEnd(list2.get(j))) {
							char ccc = list2.get(j);
							HashSet<Character> sss = First.get(ccc);
							Iterator<Character> ttt = sss.iterator();
							while (ttt.hasNext()) {
								char cccc = ttt.next();
								if (cccc != '&') 
									hashSet2.add(cccc);
							}
							j++;
						}
						if (j < list2.size() && j == i + 1)
							hashSet2.add(list2.get(j));
						if (j == list2.size())
							hashSet2.add(ch);
						Follow.put(csc, hashSet2);
					}
				}
			}
		}
		follow2();
	}

	private static void follow2() {
		boolean change = false;
		Set<Character> set = Follow.keySet();
		Iterator<Character> it = set.iterator();
		while (it.hasNext()) {
			char c = it.next();
			if (isAll(c, Follow))	continue;
			HashSet<Character> set2 = Follow.get(c);
			Iterator<Character> it2 = set2.iterator();
			while (it2.hasNext()) {
				Character ch = it2.next();
				if (isNotEnd(ch)) {
					if (isAll(ch, Follow)) {
						set2.addAll(Follow.get(ch));
						set2.remove(ch);
						change = true;
						break;
					}
				}
			}
		}
		if (change) {
			follow2();
		}
	}

	@SuppressWarnings("unchecked")
	private static void cclone() {
		Set<Character> set = inString.keySet();
		Iterator<Character> it = set.iterator();
		while (it.hasNext()) {
			char ch = it.next();
			ArrayList<ArrayList<Character>> ll = new ArrayList<ArrayList<Character>>();
			for (ArrayList<Character> ll2 : inString.get(ch)) {
				ll.add((ArrayList<Character>) ll2.clone());
			}
			inString2.put(ch, ll);
		}
	}

	private static void first() {
		Iterator<Character> it = notEnd.iterator();
		while (it.hasNext()) {
			char ch = it.next();
			HashSet<Character> hashSet;
			if (First.containsKey(ch)) {
				hashSet = First.get(ch);
			} else {
				hashSet = new HashSet<Character>();
				if (flag.get(ch))
					hashSet.add('&');
			}
			ArrayList<ArrayList<Character>> list = inString.get(ch);
			for (ArrayList<Character> list2 : list) {
				if (!isNotEnd(list2.get(0)))
					hashSet.add(list2.get(0));
				else {
					for (Character cc : list2)
						if (isNotEnd(cc))
							hashSet.add(cc);
						else
							break;
				}
			}
			First.put(ch, hashSet);
		}
		first2();
	}

	private static void first2() {
		boolean change = false;
		Set<Character> set = First.keySet();
		Iterator<Character> it = set.iterator();
		while (it.hasNext()) {
			char c = it.next();
			if (isAll(c, First))
				continue;
			HashSet<Character> set2 = First.get(c);
			Iterator<Character> it2 = set2.iterator();
			while (it2.hasNext()) {
				Character ch = it2.next();
				if (isNotEnd(ch) && isAll(ch, First)) {
					HashSet<Character> sss = First.get(ch);
					Iterator<Character> ttt = sss.iterator();
					while (ttt.hasNext()) {
						char cccc = ttt.next();
						if (cccc != '&')
							set2.add(cccc);
					}
					set2.remove(ch);
					change = true;
					break;
				}
			}
		}
		if (change)
			first2();
	}

	private static boolean isAll(char c, HashMap<Character, HashSet<Character>> map) {
		HashSet<Character> set = map.get(c);
		Iterator<Character> it = set.iterator();
		while (it.hasNext()) {
			if (isNotEnd(it.next()))
				return false;
		}
		return true;
	}

	private static void init() {
		System.out.println("输入文法：,以#结束");
		boolean ff = false;
		while (true) {
			String line = cin.next().trim();
			if (line == "")
				continue;
			if (line.charAt(0) == '#')
				break;
			String[] arr = line.split("->");
			char c = arr[0].charAt(0);
			if (!ff) {
				start = c;
				ff = true;
			}
			notEnd.add(c);
			String[] next = arr[1].split("\\|"); // 分割特别的字符要用\\,转义字符用\
			for (String s : next) {
				ArrayList<ArrayList<Character>> list;
				if (inString.containsKey(c)) {
					list = inString.get(c);
				} else {
					list = new ArrayList<ArrayList<Character>>();
				}
				ArrayList<Character> ll = new ArrayList<Character>();
				for (int i = 0; i < s.length(); i++) {
					ll.add(s.charAt(i));
				}
				if (!ll.isEmpty()) {
					list.add(ll);
					inString.put(c, list);
				}
			}
		}
		Iterator<Character> it = notEnd.iterator();
		while (it.hasNext()) {
			char c = (char) it.next();
			flag.put(c, false);
		}
	}

	private static boolean isNotEnd(char c) {
		if (notEnd.contains(c))
			return true;
		return false;
	}

	private static void toNull() {
		boolean change = false;
		Iterator<Character> it = notEnd.iterator();
		while (it.hasNext()) {
			char c = it.next();
			if (flag.get(c))
				break;
			ArrayList<ArrayList<Character>> quList = inString2.get(c);
			for (ArrayList<Character> qqList : quList) {
				for (char cn : qqList) {
					if (isNotEnd(cn) && flag.get(cn)) {
						qqList.set(qqList.indexOf(cn), '&');
						change = true;
					}
				}
				while (qqList.contains('&')) {
					qqList.remove((Character) '&');
				}
				if (qqList.isEmpty())
					flag.put(c, true);
			}
		}
		if (change)
			toNull();
	}

	private static void printFlag() {
		Set<Character> hashSet = flag.keySet();
		Iterator<Character> it = hashSet.iterator();
		while (it.hasNext()) {
			char ch = it.next();
			System.out.println(ch + "  " + flag.get(ch));
		}
	}

}
/*
 * S->AB S->bC A->& A->b B->& B->aD C->AD C->b D->aS D->c
 * 
 * S->aH H->aMd|d M->Ab|& A->aM|e
 * 
 * 
 */