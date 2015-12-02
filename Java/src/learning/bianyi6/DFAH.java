//package learning.bianyi6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class DFAH {
	private static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
	private static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	private static ArrayList<DFA> dList = new ArrayList<>();
	private static int maxn = 100;
	private static boolean[] zhong = new boolean[maxn];
	private static ArrayList<Integer> haveList = new ArrayList<>();
	private static ArrayList<Integer> resList = new ArrayList<>();
	private static final String dPath = "/media/hijj/娱乐/github/repositories/ACM/Java/src/learning/bianyi6/wj.txt";
	private static final String zPath = "/media/hijj/娱乐/github/repositories/ACM/Java/src/learning/bianyi6/zhong.txt";

	public static void main(String[] args) throws IOException {
		init();
		first();
		System.out.println("去除无用状态并将终态和非终态分组：" + list);
		second();
		for (int i = 0; i < list.size(); i++) {
			resList.add(list.get(i).get(0));
		}
		getResult();
		print();
	}

	@SuppressWarnings("unchecked")
	private static void second() {
		list = secondZhong();
		System.out.println("输出弧条数不同分组：" + list);
		ArrayList<ArrayList<Integer>> tmpList;
		do {
			tmpList = (ArrayList<ArrayList<Integer>>) list.clone();
			list = secondZhiFen();
		} while (!list.equals(tmpList));
		System.out.println("对应指向不同的分组：" + list);
		secondEq();
		System.out.println("去除输出完全相等：" + list);
	}

	private static void print() {
		for (int i = 0; i < dList.size(); i++) {
			DFA dfa = dList.get(i);
			System.out.println(dfa.getFrom() + " " + dfa.getVar() + " " + dfa.getTo());
		}
	}

	private static void getResult() {
		for (int i = 0; i < list.size(); i++) {
			map.clear();
			ArrayList<Integer> ll = list.get(i);
			// int wj=ll.get(0);
			for (int j = 1; j < ll.size(); j++) {
				int www = ll.get(j);
				for (int zz = 0; zz < dList.size(); zz++) {
					if (dList.get(zz).getFrom() == www) {
						dList.remove(zz);
						zz--;
					}
				}
				for (int zz = 0; zz < dList.size(); zz++) {
					if (dList.get(zz).getTo() == www) {
						dList.get(zz).setTo(resList.get(i));
					}
				}
			}
		}
	}

	private static void secondEq() {
		for (int i = 0; i < list.size(); i++) {
			ArrayList<Integer> ll = list.get(i);
			for (int j = 0; j < ll.size(); j++) {
				for (int k = j + 1; k < ll.size(); k++) {
					int mm1 = ll.get(j);
					int mm2 = ll.get(k);
					int mTo1 = getTo(mm1, 'a');
					int mTo2 = getTo(mm1, 'b');
					int m2To1 = getTo(mm2, 'a');
					int m2To2 = getTo(mm2, 'b');
					if (mTo1 == m2To1 && mTo2 == m2To2) {
						for (int zz = 0; zz < dList.size(); zz++) {
							if (dList.get(zz).getFrom() == mm2) {
								dList.remove(zz);
								zz--;
							}
						}
						for (int zz = 0; zz < dList.size(); zz++) {
							if (dList.get(zz).getTo() == mm2) {
								dList.get(zz).setTo(mm1);
							}
						}
						System.out.println("mm:"+mm2+"  "+k+"  "+j);
						ll.remove((Object)mm2);
						haveList.remove((Object)mm2);
						j--;
						break;
					}
				}
			}
		}

	}

	/**
	 * 将指向不同组的分成不同组
	 */
	private static ArrayList<ArrayList<Integer>> secondZhiFen() {
		ArrayList<ArrayList<Integer>> toList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			map.clear();
			ArrayList<Integer> ll = list.get(i);
			for (int j = 0; j < ll.size(); j++) {
				int mm = ll.get(j);
				int to1 = getTo(mm, 'a');
				int to2 = getTo(mm, 'b');
				if (to1 == -1) {
					to1 = 0;
				} else {
					for (int k = 0; k < list.size(); k++) {
						ArrayList<Integer> llw = list.get(k);
						if (llw.contains(to1))
							to1 = k;
					}
				}
				if (to2 != -1) {
					for (int k = 0; k < list.size(); k++) {
						ArrayList<Integer> llw = list.get(k);
						if (llw.contains(to2))
							to2 = k;
					}
				} else {
					to2 = 0;
				}
				int total = to1 * 10 + to2;
				if (map.containsKey(total)) {
					map.get(total).add(mm);
				} else {
					ArrayList<Integer> mll = new ArrayList<>();
					mll.add(mm);
					map.put(total, mll);
				}
			}
			Set<Integer> set = map.keySet();
			Iterator<Integer> it = set.iterator();
			while (it.hasNext()) {
				toList.add(map.get(it.next()));
			}
		}
		return toList;

	}

	/**
	 * 分出输出个数不同的组
	 */
	private static ArrayList<ArrayList<Integer>> secondZhong() {
		ArrayList<ArrayList<Integer>> toList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			map.clear();
			ArrayList<Integer> ll = list.get(i);
			for (int j = 0; j < ll.size(); j++) {
				int mm = ll.get(j);
				int to1 = getTo(mm, 'a');
				int to2 = getTo(mm, 'b');
				if (to1 == -1)
					to1 = 0;
				else
					to1 = 4;
				if (to2 == -1)
					to2 = 0;
				else
					to2 = 8;
				int total = to1 + to2;
				if (map.containsKey(total)) {
					map.get(total).add(mm);
				} else {
					ArrayList<Integer> mll = new ArrayList<>();
					mll.add(mm);
					map.put(total, mll);
				}
			}
			Set<Integer> set = map.keySet();
			Iterator<Integer> it = set.iterator();
			while (it.hasNext()) {
				toList.add(map.get(it.next()));
			}
		}
		return toList;
	}

	private static void first() {
		ArrayList<Integer> ll1 = new ArrayList<>();
		ArrayList<Integer> ll2 = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		stack.add(1);
		haveList.add(1);
		ll1.add(1);
		while (stack.empty() == false) {
			int k = stack.pop();
			int aa = getTo(k, 'a');
			int bb = getTo(k, 'b');
			if (aa != -1 && haveList.contains(aa) == false) {
				haveList.add(aa);
				if (zhong[aa] == false)
					ll1.add(aa);
				else
					ll2.add(aa);
				stack.push(aa);
			}
			if (bb != -1 && haveList.contains(bb) == false) {
				haveList.add(bb);
				if (zhong[bb] == false)
					ll1.add(bb);
				else
					ll2.add(bb);
				stack.push(bb);
			}
		}
		list.add(ll1);
		list.add(ll2);
		for (int i = 0; i < dList.size(); i++) {
			if (!haveList.contains(dList.get(i).getFrom())) {
				dList.remove(i);
				i--;
			}
		}
	}

	private static int getTo(int from, char var) {
		for (int i = 0; i < dList.size(); i++) {
			DFA dfa = dList.get(i);
			if (dfa.getFrom() == from && dfa.getVar() == var) {
				return dfa.getTo();
			}
		}
		return -1;
	}

	private static void init() throws IOException {
		File dFile = new File(dPath);
		@SuppressWarnings("resource")
		BufferedReader dBf = new BufferedReader(new FileReader(dFile));
		String dStr;
		while ((dStr = dBf.readLine()) != null) {
			String[] str = dStr.split(",");
			int from = Integer.parseInt(str[0]);
			char var = str[1].charAt(0);
			int to = Integer.parseInt(str[2]);
			dList.add(new DFA(from, var, to));
		}
		File zFile = new File(zPath);
		@SuppressWarnings("resource")
		BufferedReader zBf = new BufferedReader(new FileReader(zFile));
		String zStr;
		zhong[0] = true;
		int d = 1;
		while ((zStr = zBf.readLine()) != null) {
			boolean f = Boolean.parseBoolean(zStr);
			zhong[d++] = f;
		}

	}
}
