package learning.os3;

import java.util.ArrayList;

public class Directory {
	private String name;
	private int bit;
	private String parent=null;
	private ArrayList<String> dirs=new ArrayList<>();
	private ArrayList<String> files=new ArrayList<>();
	public Directory(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBit() {
		return bit;
	}
	public void setBit(int bit) {
		this.bit = bit;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public ArrayList<String> getDirs() {
		return dirs;
	}
	public void setDirs(ArrayList<String> dirs) {
		this.dirs = dirs;
	}
	public ArrayList<String> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<String> files) {
		this.files = files;
	}
	

}
