package learning.ks5;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ks5 {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws CloneNotSupportedException{
		MyString str=new MyString();
		Scanner cin=new Scanner(System.in);
		int n;
		while(true){
			System.out.println("请选择将要进行的操作：");
			System.out.println("1、赋值");
			System.out.println("2、求长度");
			System.out.println("3、求子串");
			System.out.println("4、子串定位");
			System.out.println("5、替换");
			System.out.println("6、判相等");
			System.out.println("7、退出");
			n=cin.nextInt();
			
			switch (n) {
			case 1:
				char[] ch=str.strAssign();
				System.out.println(ch);
				break;
			case 2:
				int len=str.strLength();
				System.out.println(len);
				break;
			case 3:
				System.out.println("输入起始位置：");
				int pos = cin.nextInt();
				System.out.println("输入子串长度：");
				int len2 = cin.nextInt();
				MyString sub=str.subString(pos, len2);
				sub.print();
				break;
			case 4:
				MyString substr = new MyString();
				System.out.println("输入子串");
				substr.strAssign();
				List<Integer> list=str.index(substr);
				if(list!=null){
					Iterator<Integer> iterator=list.iterator();
					while(iterator.hasNext()){
						System.out.print(iterator.next());
					}
					System.out.println();
				}
				break;
			case 5:
				MyString sub1 = new MyString();
				MyString rep = new MyString();
				System.out.println("输入子串");
				sub1.strAssign();
				System.out.println("输入替换串");
				rep.strAssign();
				MyString now=str.replace(sub1, rep);
				now.print();
				break;
			case 6:
				MyString ob = new MyString();
				System.out.println("输入比较串");
				ob.strAssign();
				int flag=str.compare(ob);
				str.print();
				if(flag==-1) System.out.println("<");
				else if(flag==1) System.out.println(">");
				else System.out.println("=");
				ob.print();
				break;
			default:
				return;
			}	
		}
	}
}
