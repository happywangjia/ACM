package learnig.os1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class OsFirst {
	private static Scanner cin = new Scanner(System.in);
	private static List<PcbClass> list=new LinkedList<>();
	private static List<PcbClass> list2=new LinkedList<>();
	private static Queue<PcbClass> queue=new LinkedList<>();

	public static void main(String[] args) {
		int n;
		System.out.println("1:先来先服务");
		System.out.println("2:最高优先数优先");
		n = cin.nextInt();
		switch (n) {
		case 1:
			FCFS();
			break;
		case 2:
			MostPri();
			break;
		default:
			break;
		}
	}
	public static void FCFS(){
		System.out.println("请输入时间片：");
		float timeSlice=cin.nextFloat();
		System.out.println("请输入进程数：");
		int n=cin.nextInt();
		for(int i=0;i<n;i++){
			System.out.println("进程名　到达时间　需要时间");
			String process=cin.next();
			float arriveTime=cin.nextFloat();
			float needTime=cin.nextFloat();
			PcbClass pcbClass=new PcbClass(process,arriveTime,needTime);
			queue.add(pcbClass);
		}
		int k=0;
		while(k==n){
			while(queue.peek().getProStatue()==Statue.FINISH){
				PcbClass pcbClass=queue.poll();
				queue.add(pcbClass);
			}
			PcbClass pcb=queue.peek();
			boolean flag=false;
			if(pcb.getNeedTime()-pcb.getCpuTime()<=timeSlice){
				pcb.setCpuTime(pcb.getNeedTime());
				flag=true;
			}else{
				pcb.setCpuTime(pcb.getCpuTime()+timeSlice);
			}
			pcb.setProStatue(Statue.RUNING);
			Iterator<PcbClass> it=queue.iterator();
			while(it.hasNext()){
				PcbClass pcbTmp=it.next();
				System.out.println("进程："+pcbTmp.getProcess()+"  到达时间："
						+pcbTmp.getArriveTime()+"  需要时间："+pcbTmp.getNeedTime()+"  CPU时间："
						+pcbTmp.getCpuTime()+"  进程状态："+pcbTmp.getProStatue());
			}
			if(flag){
				pcb.setProStatue(Statue.FINISH);
				k++;
			}else{
				pcb.setProStatue(Statue.BLOCK);
			}
			queue.poll();
			queue.add(pcb);
		}
		
	}
	public static void MostPri(){
		list.clear();
		list2.clear();
		System.out.println("请输入时间片：");
		float timeSlice=cin.nextFloat();
		System.out.println("请输入进程数：");
		int n=cin.nextInt();
		for(int i=0;i<n;i++){
			System.out.println("进程名  优先级　到达时间　需要时间");
			String process=cin.next();
			int priority=cin.nextInt();
			float arriveTime=cin.nextFloat();
			float needTime=cin.nextFloat();
			PcbClass pcbClass=new PcbClass(process,priority,arriveTime,needTime);
			list.add(pcbClass);
		}
		list.sort(new cmp());
		int k=1;
		while(list.isEmpty()==false){
			k++;
			PcbClass pcb=list.get(0);
			pcb.index=k;
			boolean flag=false;
			pcb.setPriority(pcb.getPriority()-1);
			if(pcb.getNeedTime()-pcb.getCpuTime()<=timeSlice){
				pcb.setCpuTime(pcb.getNeedTime());
				flag=true;
			}else{
				pcb.setCpuTime(pcb.getCpuTime()+timeSlice);
			}
			pcb.setProStatue(Statue.RUNING);
			for(int i=0;i<list.size();i++){
				PcbClass pcbTmp=list.get(i);
				System.out.println("进程："+pcbTmp.getProcess()+"  优先级："+pcbTmp.getPriority()+1+"  到达时间："
						+pcbTmp.getArriveTime()+"  需要时间："+pcbTmp.getNeedTime()+"  CPU时间："
						+pcbTmp.getCpuTime()+"  进程状态："+pcbTmp.getProStatue());
			}
			for(int i=0;i<list2.size();i++){
				PcbClass pcbTmp=list2.get(i);
				System.out.println("进程："+pcbTmp.getProcess()+"  优先级："+pcbTmp.getPriority()+1+"  到达时间："
						+pcbTmp.getArriveTime()+"  需要时间："+pcbTmp.getNeedTime()+"  CPU时间："
						+pcbTmp.getCpuTime()+"  进程状态："+pcbTmp.getProStatue());
			}
			System.out.println();
			if(flag==false)
				list.get(0).setProStatue(Statue.BLOCK);
			else{
				list.get(0).setProStatue(Statue.FINISH);
				list2.add(pcb);
				list.remove(0);
			}	
			list.sort(new cmp());
		}
	}
}
class cmp implements Comparator<PcbClass>{
	@Override
	public int compare(PcbClass o1, PcbClass o2) {
		if(o1.getPriority()>o2.getPriority()) return -1;
		if(o1.getPriority()<o2.getPriority()) return 1;
		if(o1.getPriority()==o2.getPriority()&&o1.index>o2.index){
			return 1;
		}
		return -1;
	}
	
}