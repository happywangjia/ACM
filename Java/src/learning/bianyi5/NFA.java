package learning.bianyi5;

public class NFA {
	private int from;
	private int to;
	private String varch;
	public NFA(int from,int to,String varch){
		this.from=from;
		this.to=to;
		this.varch=varch;
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
	public String getVarch() {
		return varch;
	}
	public void setVarch(String varch) {
		this.varch = varch;
	}
	
}
