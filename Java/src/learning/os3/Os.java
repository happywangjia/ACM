package learning.os3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Os {
	private static Scanner cin = new Scanner(System.in);
	private static String now = "C:/";
	private static HashMap<String,Directory> map=new HashMap<>();
	private static HashMap<String,File> fileMap=new HashMap<>();
	private static int[] wei=new int[100];

	public static void main(String[] args) {
		initC();
		init();
		while (true) {
			System.out.print(now);
			String str = cin.nextLine().trim();
			System.out.println(str);
			String[] strArr=str.split(" ");
			switch (strArr[0].toLowerCase()) {
			case "md":
				md(strArr[1]); break;
			case "cd":
				cd(strArr[1]);  break;
			case "rd":
				rd(strArr[1]);  break;
			case "mk":
				mk(strArr[1]);
				break;
			case "del":
				del(strArr[1]); break;
			case "dir":
				dir(); break;
			case "dis":
				dis(); break;
			case "back":
				back(); break;
			default:
				break;
			}
		}
	}
	private static void initC(){
		map.clear();
		Arrays.fill(wei, 0);
		wei[0]=1;
		String name="C:";
		Directory dir=new Directory(name);
		dir.setParent(null);
		dir.setBit(0);
		ArrayList<String> dirs=new ArrayList<>();
		dir.setDirs(dirs);
		ArrayList<String> files=new ArrayList<>();
		dir.setFiles(files);
		map.put(name, dir);
	}
	
	private static void init() {
		System.out.println("-------");
		System.out.println("MD创建文件夹");
		System.out.println("CD进入文件夹");
		System.out.println("BACK退出文件夹");
		System.out.println("RD删除文件夹");
		System.out.println("MK删除文件夹");
		System.out.println("DEL删除文件");
		System.out.println("DIR显示当前路径文件和文件夹");
		System.out.println("DIS显示位矢图");
		System.out.println("----------");
	}

	private static void md(String name) {
		if(map.containsKey(name)) return;
		String[] arr=now.split("/");
		int len=arr.length;
		String father=arr[len-1];
		Directory dir=new Directory(name);
		dir.setParent(father);
		int i;
		for(i=0;i<100;i++){
			if(wei[i]==0){
				wei[i]=1;
				dir.setBit(i);
				break;
			}
		}
		if(i==100){
			System.out.println("已满！");
			return;
		}
		ArrayList<String> dirs=new ArrayList<>();
		dir.setDirs(dirs);
		ArrayList<String> files=new ArrayList<>();
		dir.setFiles(files);
		map.put(name, dir);
		Directory fathDir=map.get(father);
		fathDir.getDirs().add(name);
	}

	private static void cd(String name) {
		String[] arr=now.split("/");
		int len=arr.length;
		String father=arr[len-1];
		Directory fathDir=map.get(father);
		ArrayList<String> sonDir=fathDir.getDirs();
		if(!sonDir.contains(name)){
			return;
		}
		now+=name+"/";
		return;
	}
	private static void back(){
		String[] arr=now.split("/");
		int len=arr.length;
		if(arr[len-1]=="C:")
			return;
		now="";
		for(int i=0;i<len-1;i++){
			now+=arr[i]+"/";
		}
	}

	private static void rd(String name) {
		String[] arr=now.split("/");
		int len=arr.length;
		String father=arr[len-1];
		Directory fathDir=map.get(father);
		ArrayList<String> sonDir=fathDir.getDirs();
		if(!sonDir.contains(name)){
			return;
		}
		sonDir.remove(name);
		Directory dir=map.get(name);
		removeDis(dir);
		map.remove(name);
	}
	private static void removeDis(Directory dir){
		wei[dir.getBit()]=0;
		ArrayList<String> sonFiles=dir.getFiles();
		for(int i=0;i<sonFiles.size();i++){
			File file=fileMap.get(sonFiles.get(i));
			ArrayList<Integer> bits=file.getBit();
			for(int j=0;j<bits.size();j++){
				wei[bits.get(j)]=0;
			}
			fileMap.remove(sonFiles.get(i));
		}
		ArrayList<String> sonDir=dir.getDirs();
		for(int i=0;i<sonDir.size();i++){
			removeDis(map.get(sonDir.get(i)));
			map.remove(sonDir.get(i));
		}
	}

	private static void mk(String name) {
		if(fileMap.containsKey(name)) return;
		String[] arr=now.split("/");
		int len=arr.length;
		String father=arr[len-1];
		Directory fathDir=map.get(father);
		File file=new File(name);
		int rand=new Random().nextInt(3);
		ArrayList<Integer> bit=new ArrayList<>();
		int i;
		//System.out.println(rand);
		for(i=0;rand>0&&i<100;i++){
			if(wei[i]==0){
				wei[i]=1;
				bit.add(i);
				rand--;
			}
		}
		if(i==100){
			return;
		}
		file.setBit(bit);
		fathDir.getFiles().add(name);
		fileMap.put(name, file);
	}

	private static void del(String name) {
		String[] arr=now.split("/");
		int len=arr.length;
		String father=arr[len-1];
		Directory fathDir=map.get(father);
		ArrayList<String> sonFile=fathDir.getFiles();
		if(!sonFile.contains(name)){
			return;
		}
		sonFile.remove(name);
		File file=fileMap.get(name);
		ArrayList<Integer> bit=file.getBit();
		for(int i=0;i<bit.size();i++){
			wei[bit.get(i)]=0;
		}
		fileMap.remove(name);
		
	}

	private static void dir() {
		String[] arr=now.split("/");
		int len=arr.length;
		String father=arr[len-1];
		Directory fathDir=map.get(father);
		ArrayList<String> sonDir=fathDir.getDirs();
		ArrayList<String> sonFile=fathDir.getFiles();
		for(int i=0;i<sonDir.size();i++){
			System.out.println(sonDir.get(i)+"		"+"Directory");
		}
		for(int i=0;i<sonFile.size();i++){
			System.out.println(sonFile.get(i)+"		"+"File");
		}
	}

	private static void dis() {
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				System.out.print(wei[i*10+j]+" ");
			}
			System.out.println();
		}
	}
}
