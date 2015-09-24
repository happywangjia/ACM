package learning;

import java.io.IOException;

import algorithms.N_004;

public class learn_2 {
	private final static char[] ch = { '+', '-', '*', '/', '^', '(', ')', '#' };
	private final static int[] f1 = { 4, 4, 6, 6, 7, 2, 9, 1 };
	private final static int[] f2 = { 3, 3, 5, 5, 8, 9, 2, 1 };

	public static void main(String[] args) throws IOException {
		System.out.println("请输入表达式，以'#'结束：");
		int result=EvalluateExpression();
		System.out.println(result);
	}

	private static int cton(char c) {
		switch (c) {
		case '+':
			return 0;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 3;
		case '^':
			return 4;
		case '(':
			return 5;
		case ')':
			return 6;
		case '#':
			return 7;
		}
		return -1;
	}

	private static Boolean isCh(char c) {
		for (int i = 0; i < 8; i++)
			if (ch[i] == c)
				return true;
		return false;
	}

	private static char Compare(char c1, char c2) {
		int i1 = cton(c1);
		int i2 = cton(c2);
		if (f1[i1] > f2[i2])
			return '>';
		else if (f1[i1] == f2[i2])
			return '=';
		else
			return '<';
	}

	private static int pow(int a, int b) {
		int sum = 1;
		while (b-- > 0) {
			sum *= a;
		}
		return sum;
	}

	private static int Opreate(int a, int t, int b) {
		int sum;
		switch (t) {
		case 0:
			sum = a + b;
			break;
		case 1:
			sum = a - b;
			break;
		case 2:
			sum = a * b;
			break;
		case 3:
			sum = a / b;
			break;
		default:
			sum = pow(a, b);
		}
		return sum;
	}

	private static boolean isDigit(char ch) {
		if (ch <= '9' && ch >= '0')
			return true;
		return false;
	}

	private static int EvalluateExpression() throws IOException {
		char c;
		Boolean k = false;
		int sum = 0;
		int t, a, b;
		N_004<Integer> OPTR = new N_004<Integer>();
		N_004<Integer> OPND = new N_004<Integer>();
		OPTR.push(cton('#'));
		c = (char) System.in.read();
		while (c != '#' || ch[OPTR.top()] != '#') {
			if (isDigit(c)) {
				k = true;
				sum = 0;
				while (isDigit(c)) {
					sum = sum * 10 + c - '0';
					c = (char) System.in.read();
				}
				OPND.push(sum);
			} else if (k && isCh(c)) {
				k = false;
				switch (Compare(ch[OPTR.top()], c)){
				case '<':
					OPTR.push(cton(c));
					c=(char) System.in.read();
					break;
				case '>':
					k=true;
					t=OPTR.top();
					OPTR.pop();
					b=OPND.top();
					OPND.pop();
					a=OPND.top();
					OPND.pop();
					OPND.push(Opreate(a, t, b));
				default:
					System.out.println("错误！");
					return -1;
				}
			}else if(c=='#'){
				return OPND.top();
			}
			else{
				System.out.println("输入错误！");
				return -1;
			}
		}
		return -1;
	}
}
