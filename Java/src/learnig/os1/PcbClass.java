package learnig.os1;

public class PcbClass {
	private String process;
	private int priority;
	private float arriveTime;
	private float needTime;
	private float CpuTime;
	private Statue proStatue;
	public int index;
	public PcbClass(String process,float arriveTime,float needTime){
		this.process=process;
		this.priority=0;
		this.arriveTime=arriveTime;
		this.needTime=needTime;
		this.CpuTime=0;
		this.proStatue=Statue.BLOCK;
		this.index=0;
	}
	public PcbClass(String process,int priority,float arriveTime,float needTime){
		this(process,arriveTime,needTime);
		this.priority=priority;
	}
	
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public float getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(float arriveTime) {
		this.arriveTime = arriveTime;
	}
	public float getNeedTime() {
		return needTime;
	}
	public void setNeedTime(float needTime) {
		this.needTime = needTime;
	}
	public float getCpuTime() {
		return CpuTime;
	}
	public void setCpuTime(float cpuTime) {
		CpuTime = cpuTime;
	}
	public Statue getProStatue() {
		return proStatue;
	}
	public void setProStatue(Statue procStatue) {
		this.proStatue=procStatue;
	}
}
enum Statue{
	RUNING,FINISH,BLOCK
}