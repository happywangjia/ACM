package learning.rzh1;

public class rzhi1_2 {
	private static WJ qian;
	private static WJ hou;
	public static void main(String[] args){
		init();
		System.out.println("前岸：传教士 "+qian.chuan+"野人 "+qian.ye);
		System.out.println("后岸： 传教士 "+hou.chuan+"野人 "+hou.ye);
		System.out.println();
		qian.ye--;
		while(hou.chuan<3){
			if(hou.ye==hou.chuan){
				qian.chuan--;
				System.out.println("  去：野人 传教士");
				print();
				hou.chuan++;
				if(hou.chuan==3){
					hou.ye++;
					System.out.println();
					print();
					System.out.println("完成");
					return;
				}
				System.out.println("  回： 野人");
				print();
			}else{
				qian.ye--;
				System.out.println("  去：野人  野人");
				print();
				hou.ye++;
				System.out.println("  回： 野人");
				print();
			}
			System.out.println();
		}
	}
	private static void print(){
		System.out.println("前岸：传教士 "+qian.chuan+"野人 "+qian.ye);
		System.out.println("后岸： 传教士 "+hou.chuan+"野人 "+hou.ye);
	}
	private static void init(){
		qian=new WJ(3,3);
		hou=new WJ();
	}
}
class WJ{
	int ye;
	int chuan;
	public WJ(){
		this.ye=0;
		this.chuan=0;
	}
	public WJ(int ye,int chuan){
		this.ye=ye;
		this.chuan=chuan;
	}
}