package learning;

public class Che {
	private String id;
	private String jkTime;
	private String ckTime;
	private String jdTime;
	private String cdTime;
	private boolean hFlag;
	private Che last;
	private Che next;
	
	public Che(){
		this.jkTime="00:00";
		this.ckTime="00.00";
		this.jdTime="00:00";
		this.cdTime="00:00";
		this.hFlag=false;
		this.last=null;
		this.next=null;
	}
	public Che getLast() {
		return last;
	}
	public void setLast(Che last) {
		this.last = last;
	}
	public Che getNext() {
		return next;
	}
	public void setNext(Che next) {
		this.next = next;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJkTime() {
		return jkTime;
	}
	public void setJkTime(String jkTime) {
		this.jkTime = jkTime;
	}
	public String getCkTime() {
		return ckTime;
	}
	public void setCkTime(String ckTime) {
		this.ckTime = ckTime;
	}
	public String getJdTime() {
		return jdTime;
	}
	public void setJdTime(String jdTime) {
		this.jdTime = jdTime;
	}
	public String getCdTime() {
		return cdTime;
	}
	public void setCdTime(String cdTime) {
		this.cdTime = cdTime;
	}
	public boolean ishFlag() {
		return hFlag;
	}
	public void sethFlag(boolean hFlag) {
		this.hFlag = hFlag;
	}
}
