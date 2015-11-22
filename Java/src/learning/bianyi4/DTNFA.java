package learning.bianyi4;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class DTNFA {
	private static HashMap<String, String> map = new HashMap<String, String>();
	private static String[] arr = { "a", "*", ".", "|", "(", ")", "#" };
	private static Stack<String> stack = new Stack<String>();
	private static Scanner cin = new Scanner(System.in);
	private static List<NFA> list = new LinkedList<NFA>();
	private static String priority = "err,>,>,>,err,>,>,<,err,>,>,<,>,>,<,<,>,>,<,>,>,<,"
			+ "<,<,>,<,>,>,<,<,<,<,<,=,err,err,>,>,>,>,err,>,<,<,<,<,<,err,=";
	private static Stack<Wj> nowStack = new Stack<Wj>();

	public static void main(String[] args) {
		init();
		String str = cin.next();
		stack.clear();
		stack.push("#");
		int kk = 0;
		list.clear();
		int len = str.length();
		int haha = 1;
		nowStack.clear();
		while (kk < len) {
			String st = stack.lastElement();
			String rr = str.charAt(kk) + "";
			String st1 = st;
			String rr1 = rr;
			if (!conta(st1)) {
				st1 = "a";
			}
			if (!conta(rr)) {
				rr1 = "a";
			}
			String jiao = map.get(st1 + rr1);
			System.out.println(stack);
			if (jiao.equals("err")) {
				System.out.println("error");
				return;
			} else if (jiao.equals("<")) {
				stack.push(rr);
				kk++;
				continue;
			} else if (jiao.equals(">")) {
				if (st1.equals("a")) {
					NFA nfa = new NFA(haha++, haha++, st);
					list.add(nfa);
					Wj wj = new Wj(haha - 2, haha - 1);
					nowStack.add(wj);
					stack.pop();
					continue;
				} else if (st1.equals("*")) {
					Wj wj = nowStack.pop();
					int from = wj.getFrom();
					int to = wj.getTo();
					NFA nfa1 = new NFA(to, from, "&");
					NFA nfa2 = new NFA(haha++, from, "&");
					NFA nfa3 = new NFA(to, haha++, "&");
					NFA nfa4 = new NFA(haha - 2, haha - 1, "&");
					list.add(nfa1);
					list.add(nfa2);
					list.add(nfa3);
					list.add(nfa4);
					Wj wj2 = new Wj(haha - 2, haha - 1);
					nowStack.add(wj2);
					stack.pop();
					continue;
				} else if (st1.equals(".")) {
					stack.pop();
					Wj wj = nowStack.pop();
					int from = wj.getFrom();
					int to = wj.getTo();
					Wj wj2 = nowStack.pop();
					int from2 = wj2.getFrom();
					int to2 = wj2.getTo();
					int lenl = list.size();
					for (int jj = lenl - 1; jj >= 0; jj--) {
						NFA ff = list.get(jj);
						if (ff.getFrom() == from) {
							ff.setFrom(to2);
						}
						if (ff.getTo() == from) {
							ff.setTo(to2);
						}
					}
					Wj wj3 = new Wj(from2, to);
					nowStack.add(wj3);
					continue;
				} else if (st1.equals("|")) {
					stack.pop();
					Wj wj = nowStack.pop();
					int from = wj.getFrom();
					int to = wj.getTo();
					Wj wj2 = nowStack.pop();
					int from2 = wj2.getFrom();
					int to2 = wj2.getTo();
					boolean flag1 = pan(from, to);
					boolean flag2 = pan(from2, to2);
					if (flag1 == false && flag2 == false) {
						NFA nfa2 = new NFA(haha++, from2, "&");
						NFA nfa1 = new NFA(haha - 1, from, "&");
						NFA nfa3 = new NFA(to2, haha++, "&");
						NFA nfa4 = new NFA(to, haha - 1, "&");
						list.add(nfa1);
						list.add(nfa2);
						list.add(nfa3);
						list.add(nfa4);
						Wj wj3 = new Wj(haha - 2, haha - 1);
						nowStack.add(wj3);
						continue;
					}
					if (flag1 == true && flag2 == false) {
						NFA nfa1 = new NFA(from, from2, "&");
						NFA nfa2 = new NFA(to2, to, "&");
						list.add(nfa1);
						list.add(nfa2);
						Wj wj3 = new Wj(from, to);
						nowStack.add(wj3);
						continue;
					}
					if (flag1 == false && flag2 == true) {
						NFA nfa1 = new NFA(from2, from, "&");
						NFA nfa2 = new NFA(to, to2, "&");
						list.add(nfa1);
						list.add(nfa2);
						Wj wj3 = new Wj(from2, to2);
						nowStack.add(wj3);
						continue;
					}
					if (flag1 == true && flag2 == true) {
						int size = list.size();
						for (int i = 0; i < size; i++) {
							NFA nfa = list.get(i);
							if (nfa.getFrom() == from2) {
								nfa.setFrom(from);
							}
							if (nfa.getFrom() == to2) {
								nfa.setTo(to);
							}
						}
						Wj wj3 = new Wj(from, to);
						nowStack.add(wj3);
						continue;
					}
				} else if (st1.equals(")")) {
					stack.pop();
				}

			} else if (jiao.equals("=")) {
				if (st.equals("#")) {
					System.out.println("finish!");
					Wj wj=nowStack.pop();
					System.out.println(" "+wj.getFrom()+" "+wj.getTo());
				}
				stack.pop();
				kk++;
				continue;
			}
		}
		int m = 0;
		while (m < list.size()) {
			NFA nfa = list.get(m);
			System.out.println(nfa.getFrom() + "  " + nfa.getTo() + "  " + nfa.getVarch());
			m++;
		}
	}

	private static boolean pan(int from, int to) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			NFA nfa = list.get(i);
			if (nfa.getFrom() == from) {
				if (nfa.getVarch().equals("&") == false) {
					return false;
				}
				if (nfa.getTo() == to) {
					if (nfa.getVarch().equals("&") == false) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static boolean conta(String str) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(str)) {
				return true;
			}
		}
		return false;
	}

	private static void init() {
		String[] pri = priority.split(",");
		int d = 0;
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				String stack = arr[i];
				String r = arr[j];
				map.put(stack + r, pri[d++]);
			}
		}
	}
}
// *(a|b).(a.b|b.b).*(a|b)#
// *(a|b).(a.b|b.b).(a|b)#
//*(a|b).(a.a|b.b).*(a|b)#