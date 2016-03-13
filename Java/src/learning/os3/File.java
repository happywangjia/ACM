package learning.os3;

import java.util.ArrayList;

public class File {
	private ArrayList<Integer> bit=null;
	private String name;
	public File(String name){
		this.name=name;
	}
	public ArrayList<Integer> getBit() {
		return bit;
	}
	public void setBit(ArrayList<Integer> bit) {
		this.bit = bit;
	}
}
