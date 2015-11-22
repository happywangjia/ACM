package learning.bianyi5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class NTDFA {
	private static List<NFA> list=new ArrayList<>();
	private static Map<Character,Set<Integer>> map=new HashMap<>();
	private static Stack<Character> stack=new Stack<>();
	private static Set<Wj> set=new HashSet<>();
	private static int kk;
	
	public static void main(String[] args){
		init();
		NTD();
		Iterator<Wj> it=set.iterator();
		while(it.hasNext()){
			Wj wj=it.next();
			System.out.println(""+wj.getFrom()+"  "+wj.getTo()+"  "+wj.getVar());
		}
		Set<Character> set2=map.keySet();
		Iterator<Character> it2=set2.iterator();
		while(it2.hasNext()){
			char ccc=it2.next();
			System.out.print(ccc+"   ");
			System.out.println(map.get(ccc));
		}
	}
	private static void NTD(){
		kk=0;
		Chu2("&");
		Chu2("a");
		Chu2("b");
		
		while(stack.empty()==false){
			Character ch=stack.pop();
			Chu(ch,"&");
			Chu(ch,"a");
			Chu(ch,"b");
		}
	}
	private static void Chu2(String str){
		Set<Integer> ll=new HashSet<>();
		ll=getStart(0,str,ll);
		if(str.equals("&"))
			ll.add(0);
		if(ll.size()!=0){
			Chu3(ll);
		}
	}
	
	private static void Chu(char ch,String str){
		Set<Integer> ll4=map.get(ch);
		Set<Integer> ll5=new HashSet<>();
		Iterator<Integer> it=ll4.iterator();
		while(it.hasNext()){
			int m=it.next();
			ll5=getStart(m, str, ll5);
			if(str.equals("&"))
				ll5.add(m);
		}
		if(ll5.size()==0) return;
		if(str.equals("&")==false){
			char ww =Chu3(ll5);
			Wj wj=new Wj(ch,ww,str);
			set.add(wj);
		}
	}
	private static char Chu3(Set<Integer> ll5){
		char ww=' ';
		Set<Character> kSet1=map.keySet();
		Iterator<Character> it2=kSet1.iterator();
		boolean flag=false;
		while(it2.hasNext()){
			char cc=it2.next();
			Set<Integer> set1=map.get(cc);
			if(set1.equals(ll5)){
				ww=cc;
				flag=true;
				break;
			}
		}
		if(flag==false){
			map.put((char) ('A'+(kk++)), ll5);
			stack.add((char) ('A'+(kk-1)));
			ww=(char) ('A'+(kk-1));
		}
		return ww;
	}
	
	private static Set<Integer> getStart(int shu,String str,Set<Integer> ll){
		for(int i=0;i<list.size();i++){
			NFA nfa=list.get(i);
			if(nfa.getFrom()==shu&&nfa.getVarch().equals(str)){
				ll.add(nfa.getTo());
			}
		}
		boolean flag=false;
		while(true){
			flag=false;
			Iterator<Integer> it=ll.iterator();
			while(it.hasNext()){
				int m=it.next();
				for(int i=0;i<list.size();i++){
					NFA nfa=list.get(i);
					if(nfa.getFrom()==m&&nfa.getVarch().equals("&")&&ll.contains(nfa.getTo())==false){
						ll.add(nfa.getTo());
						flag=true;
					}
				}
			}
			if(flag==false) break;
		}
		
		return ll;
	}
	private static List<NFA> init(){
		list.clear();
		list.add(new NFA(0,1,"&"));
		list.add(new NFA(1,1,"a"));
		list.add(new NFA(1,1,"b"));
		list.add(new NFA(1,2,"&"));
		list.add(new NFA(2,3,"a"));
		list.add(new NFA(2,4,"b"));
		list.add(new NFA(3,5,"a"));
		list.add(new NFA(4,5,"b"));
		list.add(new NFA(5,6,"&"));
		list.add(new NFA(6,6,"a"));
		list.add(new NFA(6,6,"b"));
		list.add(new NFA(6,7,"&"));
		return list;
	}
}
