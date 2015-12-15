package learning.bianyi7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class ss {
	
	List<String> Word=new ArrayList<String>();
    Set<Character> Notend=new LinkedHashSet<Character>();     //非终结符	
	Set<Character> End=new LinkedHashSet<Character>();   //终结符
    
	Map<Character, LinkedHashSet<Character>>first=new LinkedHashMap<Character, LinkedHashSet<Character>>();  //first
	Map<Character, LinkedHashSet<Character>>follow=new LinkedHashMap<Character, LinkedHashSet<Character>>();  //follow集
	Map<String, LinkedHashSet<Character>>select=new LinkedHashMap<String,LinkedHashSet<Character>>();   //select集
	
	
	public ss() {
		Scanner sc=new Scanner(System.in);
		System.out.println("请依次输入文法，#结束");
		String tmp;
		while(true)
		{
			tmp=sc.nextLine();
			if("#".equals(tmp)) break;
			Word.add(tmp);
			Notend.add(tmp.charAt(0));
			for(int i=3;i<tmp.length();i++){
				if(Small(tmp.charAt(i))) End.add(tmp.charAt(i));
			}
		}
		End.add('#');
	}
    
    private boolean Judege(Character c){    //判断是否含有ε
    	for(String s:Word){
    		if(s.charAt(0)==c&&s.charAt(3)=='$') return true;
    	}
    	return false;
    }
    
    private boolean Small(Character c){
    	  if(c<='Z'&&c>='A') return false;
    	  if(c=='$') return false;
    	  return true;
    }

    private void GetSimpleFirst(Character c){
    	LinkedHashSet<Character> tmpfirst=new LinkedHashSet<Character>();
    	for(String s:Word){
    		if(s.charAt(0)!=c) continue;
    		if(Small(s.charAt(3))||s.charAt(3)=='$') tmpfirst.add(s.charAt(3));
    	}
    	first.put(c, tmpfirst);
    }
    
    public void GetFirst()       //产生first集
    {
    	LinkedHashSet<Character> tmp=new LinkedHashSet<Character>();
    	int k;
    	boolean empty;
    	for(Character c:Notend) GetSimpleFirst(c);     //取得简单first，不考虑终结符情况
    	for(int i=0;i<5;i++){      //防止多层都是非终结符   比如   S->AB   A->C   C->D  D->a  一般来说不会超过五层,此处没想到更好的办法
    	for(String s:Word){
    		empty=false;
    		if((!Small(s.charAt(3)))&&s.charAt(3)!='$')      //右部开始为非终结符
    		{
    			tmp=first.get(s.charAt(0));
    			if(tmp.contains('$')) empty=true;
    			if(!Small(s.charAt(3))) tmp.addAll(first.get(s.charAt(3)));    //将右部终结符加进去
    			
    			k=4;
    			boolean IF=false;
    			while(Judege(s.charAt(k-1))){
    				IF=false;
    				if(k<s.length()&&(!Small(s.charAt(k))))        //碰到可能为空的非终结符判断后面一个字符是否为非终结符
  					tmp.addAll(first.get(s.charAt(k++)));
    				if(k==s.length()){
    					if(!first.get(s.charAt(k-1)).contains('$')) IF=true;   //IF表示形如S->AB S能否消除 $;
    					break;
    				}
    				if(Small(s.charAt(k))) {tmp.add(s.charAt(k));IF=true;break;}
    			}
    			if(IF==true&&empty==false)   tmp.remove('$'); 
    			first.put(s.charAt(0),tmp);
    		}
    	}
    	}
    }
    
    
    public void GetFollow()    //产生follow集
    {
     	LinkedHashSet<Character> tmp=new LinkedHashSet<Character>();
     	tmp.add('#');
     	follow.put(Word.get(0).charAt(0),tmp);   //初始化S  '#'
     	int i,k,specialpos;
     	boolean find;
     	Character special;
     	for(Character c:Notend){
     		k=3;
     		tmp=new LinkedHashSet<>();
     		find=false;   //右部包含指定非终结符
     		for(String s:Word){
     			for(i=k;i<s.length();i++) if(s.charAt(i)==c)  {find=true; break;}
     			if(find==true){     //产生式右部有此非终结符
     				if(i<s.length()-1){            //先处理右部非结尾的情况，因为其他follow还不知道
     					if(Small(s.charAt(i+1))==true)  { tmp.add(s.charAt(i+1));continue;}   //把后面终结符添加，并继续往下搜索
     					
     					/*后面非终结符*/
     					tmp.addAll(first.get(s.charAt(i+1)));
     					special=s.charAt(i+1);
     					specialpos=i+1;
     					while(first.get(special).contains('$')&&specialpos<s.length()){     //形如S->ABC 的 当B含有$，A的follow还要加上C的
     						if(specialpos+1==s.length()){tmp.addAll(follow.get(s.charAt(0))); break;}    //后面找$直到结束，把左部follow给它
     						special=s.charAt(++specialpos);
     						if(Small(special)==true) {tmp.add(special);  break;}
     						tmp.addAll(first.get(special));
     					}
     					if(tmp.contains('$')) tmp.remove('$');    //把后面的first集给follow并排除$
         				}
     			}
     		}
     		if(follow.containsKey(c))   tmp.addAll(follow.get(c));
     		follow.put(c, tmp);        //一个非终结符扫好加入end
     	}
     	
     	for(Character c:Notend){     //寻找非终结符在最后的
      		k=3;
      		tmp=new LinkedHashSet<>();
      		find=false;
      		for(String s:Word){
      			if(s.charAt(s.length()-1)!=c) continue;
      			 tmp.addAll(follow.get(s.charAt(0)));      //把左部follow加进去
      		}
      		tmp.addAll(follow.get(c));
      		follow.put(c, tmp);
     	}
     	
 
    }
    
    
    public void GetSelect(){
         int k;
    	 
         for(String s:Word){    //对于每句
        	 LinkedHashSet<Character> tmp=new LinkedHashSet<>();
        	 k=3;
    	 if(Small(s.charAt(k))) {tmp.add(s.charAt(k)); select.put(s, tmp); continue;}  
    	 if(s.charAt(k)=='$') { tmp.addAll(follow.get(s.charAt(0))); select.put(s, tmp);continue;}
    	 tmp.addAll(first.get(s.charAt(k)));  
    	 k++;
    	 while(k<s.length()){
    		 if(first.get(s.charAt(k-1)).contains('$')){
    			 tmp.addAll(first.get(s.charAt(k)));
    			 k++;
    		 }
    		 else break;
    		 }
    	 if(k==s.length()&&first.get(s.charAt(s.length()-1)).contains('$')) tmp.addAll(follow.get(s.charAt(0)));
    	 if(tmp.contains('$')) tmp.remove('$');
    	 select.put(s, tmp);
    	}
    }
    
    
    public void CreatTable(){
    	System.out.print(" ");
    	System.out.println("              预测分析表");
    	for(Character c:End) System.out.print("\t"+c);
    	System.out.println();
   
    	for(Character c:Notend){
    		System.out.print(c);
    		for(Character p:End)
    		{
    			System.out.print("\t");
    		   for(String s:Word){
    			 if(s.charAt(0)!=c) continue;
    		     if(select.get(s).contains(p)) {System.out.print(s.substring(1)+"  "); break;} 
    		   }
    		   
    	     }
    		System.out.println();
    	}
    }
	
	public static void main(String args[]){
		ss T=new ss();
	    T.GetFirst();
	    System.out.println("first集依次为   "+T.first);
	    T.GetFollow();
	    System.out.println("follow集依次为  "+T.follow);
	    T.GetSelect();
	    System.out.println("select集依次为");
	    for(Map.Entry<String, LinkedHashSet<Character>>e:T.select.entrySet()) System.out.println(e.getKey()+" = "+e.getValue());
	    System.out.println();
	    T.CreatTable();
	}
}
