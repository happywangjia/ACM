package learning.ks8;

public class Tree {
	private char data;
	private Tree left;
	private Tree right;
	private Tree parent;
	private int lNum;
	private int rNum;
	public Tree(){
		this.left=null;
		this.right=null;
		this.parent=null;
		this.lNum=0;
		this.rNum=0;
	}
	public Tree(char data){
		this();
		this.data=data;
	}
	public int getlNum() {
		return lNum;
	}
	public void setlNum(int lNum) {
		this.lNum = lNum;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public Tree getParent() {
		return parent;
	}
	public void setParent(Tree parent) {
		this.parent = parent;
	}
	public char getData() {
		return data;
	}
	public void setData(char data) {
		this.data = data;
	}
	public Tree getLeft() {
		return left;
	}
	public void setLeft(Tree left) {
		this.left = left;
	}
	public Tree getRight() {
		return right;
	}
	public void setRight(Tree right) {
		this.right = right;
	}
}
