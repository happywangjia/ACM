package learning.bianyi3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ByThree {
	private static HashMap<String, tokClass> map = new HashMap<String, tokClass>();
	private static HashMap<String,List<Integer>> hav=new HashMap<>();
	private static final String tokFile = "/media/hijj/娱乐/github/repositories/ACM/Java/src/learning/bianyi3/tok.txt";
	private static final String sourceFile="/media/hijj/娱乐/github/repositories/ACM/Java/src/learning/bianyi3/Simple.txt";
	private static final String tt1="/media/hijj/娱乐/github/repositories/ACM/Java/src/learning/bianyi3/sourceToken.txt";
	private static final String tt2="/media/hijj/娱乐/github/repositories/ACM/Java/src/learning/bianyi3/sourceFu.txt";
	public static void main(String[] args){
		init();
		du();
	}
	private static void du(){
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fr1=null;
		BufferedWriter br1=null;
		FileWriter fr2=null;
		BufferedWriter br2=null;
		try {
			File file = new File(sourceFile);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line=null;
			int hanshu=0;
			/**
			 * flag:
			 * 1：整数
			 * 2：字符常数
			 * 3：标识符
			 * 4:关键字
			 * 5：注释
			 * 6：除号
			 * 7：界符
			 * 8:运算符
			 */
			File file1=new File(tt1);
			fr1=new FileWriter(file1);
			br1=new BufferedWriter(fr1);
			File file2=new File(tt2);
			fr2=new FileWriter(file2);
			br2=new BufferedWriter(fr2);
			while((line=br.readLine())!=null){
				hanshu++;
				String[] str=line.split(" ");
				for(int i=0;i<str.length;i++){
					int flag=-1;
					String name="";
					int tokenNum=-1;
					if(str[i].length()==0) continue;
					if(map.containsKey(str[i])){
						tokClass tok=map.get(str[i]);
						tokenNum=tok.getToke();
						if(tok.getLei()==1){
							flag=1;
							name="关键字";
						}else if(tok.getLei()==2){
							flag=2;
							name="运算符";
						}else if(tok.getLei()==3){
							flag=3;
							name="界符";
						}
					}
					if(flag==-1&&isDig(str[i].charAt(0))){
						if(str[i].indexOf('.')!=0){
							flag=4;
							name="实常数";
							tokenNum=53;
						}else{
							flag=5;
							tokenNum=52;
							name="整数";
						}
					}
					if(flag==-1&&str[i].charAt(0)=='\''){
						flag=6;
						tokenNum=54;
						name="字符常数";
					}
					if(flag==-1&&(str[i].equals(true)||str[i].equals(false))){
						flag=7;
						tokenNum=55;
						name="布尔常数";
					}
					if(flag==-1){
						flag=8;
						tokenNum=56;
						name="标识符";
					}
					
					br1.write(hanshu+"   "+str[i]+"   "+tokenNum+"\n");
					br1.flush();
					if(hav.containsKey(str[i])){
						List<Integer> list=hav.get(str[i]);
						if(list.contains(hanshu)==false){
							list.add(hanshu);
						}
					}else{
						List<Integer> list=new LinkedList<>();
						list.add(hanshu);
						hav.put(str[i], list);
						br2.write(hanshu+"  "+str[i]+"  "+str[i].length()+"  "+name+"  ");
						for(int j=0;j<list.size();j++){
							if(list.get(j)!=hanshu)
								br2.write(list.get(j)+" ");
						}
						if(list.size()!=1){
							System.out.println("行引用");
						}
						br2.write("\n");
						br2.flush();
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private static boolean isDig(char ch){
		if(ch<='9'&&ch>='0') return true;
		return false;
	}
	public static void init(){
		FileReader fr = null;
		BufferedReader br = null;
		try {
			File file = new File(tokFile);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			for (int i = 1; i < 57; i++) {
				tokClass tok = new tokClass();
				String str = br.readLine();
				int pri = getPri(i);
				String[] name=str.split("\t");
				tok.setLei(pri);
				tok.setToke(Integer.parseInt(name[1]));
				map.put(name[0], tok);
//				System.out.println(pri+" "+name[0]+" "+name[1]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private static int getPri(int k) {
		if (k <= 29)
			return 1;
		else if (k <= 41)
			return 2;
		else if (k <= 51)
			return 3;
		else if (k <= 55)
			return 4;
		else if (k <= 56)
			return 5;
		else
			return -1;
	}
}
