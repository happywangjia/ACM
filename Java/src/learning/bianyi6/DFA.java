//package learning.bianyi6;

public class DFA {
	private char var;
	private int from;
	private int to;

	public DFA(int from, char var, int to) {
		this.from = from;
		this.var = var;
		this.to = to;
	}

	public char getVar() {
		return var;
	}
	
	public String toString(){
		return from+" "+var+" "+to;
	}
	
	public void setVar(char var) {
		this.var = var;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

}
