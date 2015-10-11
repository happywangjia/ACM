package learning.ks5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyString {
	private static final int maxn=100;
	private char[] ch;
	private int curLen;
	
	MyString(){
		ch=new char[maxn];
		curLen=0;
	}
	@SuppressWarnings("resource")
	public char[] strAssign(){
		curLen=0;
		Scanner cin=new Scanner(System.in);
		String in=cin.next();
		for(int i=0;i<in.length();i++){
			ch[curLen++]=in.charAt(i);
		}
		return ch;
	}
	
	public int strLength(){
		return curLen;
	}
	public MyString subString(int pos,int len){
		MyString sub=new MyString();
		for(int i=pos;i<curLen&&i<pos+len;i++){
			sub.add(ch[i]);
		}
		if(sub.curLen==0) return null;
		return sub;
	}
	public List<Integer> index(MyString sub){
		List<Integer> list=new ArrayList<Integer>();
		int[] ff=new int[100];
		int tLen=curLen;
		int pLen=sub.curLen;
		ff[0]=0;
		ff[1]=0;
		int j=0;
		for(int i=1;i<pLen;i++){
			while(j!=0&&sub.ch[i]!=sub.ch[j]){
				j=ff[j];
			}
			ff[i+1]=sub.ch[j]==sub.ch[i]? j+1:0;
		}
		j=0;
		for(int i=0;i<tLen;i++){
			while(j!=0&&sub.ch[j]!=ch[i]){
				j=ff[j];
			}
			if(sub.ch[j]==ch[i]){
				j++;
			}
			if(j==pLen){
				list.add(i-pLen+1);
				j=0;
			}
		}
		return list;
	}
	public MyString replace(MyString sub,MyString rep) throws CloneNotSupportedException{
		List<Integer> list=index(sub);
		if(list==null) return null;
		int size=list.size();
		int w1=0,w2=0;
		char[] ss=new char[maxn];
		int temp=0,d=0;
		while(temp<size){
			w2=list.get(temp);
			temp++;
			for(int i=w1;i<w2;i++){
				ss[d++]=ch[i];
			}
			for(int i=0;i<rep.curLen;i++){
				ss[d++]=rep.ch[i];
			}
			w1=w2+sub.curLen;
		}
		for(int i=w1;i<curLen;i++){
			ss[d++]=ch[i];
		}
		for(int i=0;i<d;i++){
			ch[i]=ss[i];
		}
		curLen=d;
		return this;
		
	}
	public int compare(MyString x){
		int len=curLen>x.curLen?x.curLen:curLen;
		for(int i=0;i<len;i++){
			if(ch[i]<x.ch[i]){
				return -1;
			}
			if(ch[i]>x.ch[i])
				return 1;
		}
		if(curLen>x.curLen) return 1;
		else if(curLen<x.curLen) return -1;
		return 0;
	}
	
	public void add(char c){
		ch[curLen++]=c;
	}
	public void print(){
		for(int i=0;i<curLen;i++){
			System.out.print(ch[i]);
		}
		System.out.println();
	}

}
