package learning;

import java.util.Scanner;

public class learn_5 {
	private static int kCount, dCount;
	private static Stack ck = new Stack();
	private static Queue bd = new Queue();
	private static Scanner cin = new Scanner(System.in);

	public static void main(String[] args) {
		int order;
		System.out.println("请输入车库容量：");
		kCount = cin.nextInt();
		System.out.println("请输入便道容量：");
		dCount = cin.nextInt();
		while (true) {
			System.out.println("1 进站");
			System.out.println("2 出站");
			order = cin.nextInt();
			switch (order) {
			case 1:
				jin();
				break;
			case 2:
				chu();
				break;
			default:
				break;
			}
		}

	}

	private static void jin() {
		Che che = new Che();
		System.out.println("输入车牌号：");
		che.setId(cin.next());
		System.out.println("在车库有空位时是否进库？是1 否0");
		int k = cin.nextInt();
		if (k == 1)
			che.sethFlag(true);
		if (che.ishFlag() == false) {
			System.out.println("输入进道时间：格式： 00:00");
			che.setJdTime(cin.next());
			bd.push(che, dCount);
		} else {
			if (ck.isFull(kCount)) {
				System.out.println("输入进道时间：格式： 00:00");
				che.setJdTime(cin.next());
				bd.push(che, dCount);
			} else {
				System.out.println("请输入进库时间：格式：00:00");
				che.setJkTime(cin.next());
				ck.push(che, kCount);
			}
		}
	}

	private static void chu() {
		System.out.println("请输入车牌号：");
		String id = cin.next();
		Che che = new Che();
		che = ck.pop(id);
		if (che == null) {
			che = bd.pop(id);
			System.out.println("输入出便道时间；");
			if(che==null){
				System.out.println("error id");
				return;
			}
			che.setCdTime(cin.next());
		} else {
			System.out.println("输入出车库时间：");
			che.setCkTime(cin.next());
		}
		System.out.println(che.getJkTime()+"  "+che.getCkTime()+"  "+che.getJdTime()+"  "+che.getCdTime()+"  "+che.getId());
		int kTime = getTime(che.getJkTime(), che.getCkTime());
		int dTime = getTime(che.getJdTime(), che.getCdTime());
		int money = 0;
		if (kTime != 0 && dTime != 0) {
			money += 20;
		}
		int tmp = getMoney(kTime) / 2;
		tmp = getMoney(dTime + kTime) - tmp;
		money += tmp;
		System.out.println("是否要发票？1 是 0  否");
		if (cin.nextInt() == 1) {
			money *= 1 + 0.15;
		}
		System.out.println("停车费用为：" + money);
		if(!bd.isEmpty())
			huan();
	}

	private static void huan() {
		Che che = bd.getFont();
		while (che != null) {
			if (che.ishFlag()) {
				bd.pop(che.getId());
				ck.push(che, kCount);
				break;
			}
			che = che.getNext();
		}
		if(che==bd.getRear()) return;
		System.out.println("请输入出道和进库时间：");
		String str = cin.next();
		che.setJkTime(str);
		che.setCdTime(str);
	}

	private static int getTime(String str1, String str2) {
		int s1 = Integer.parseInt(str1.substring(0, 2));
		int s2 = Integer.parseInt(str2.substring(0, 2));
		int fen1 = Integer.parseInt(str1.substring(3, 5));
		int fen2 = Integer.parseInt(str2.substring(3, 5));
		int sum = 60 * (s2 - s1) + (fen2 - fen1);
		return sum;
	}

	private static int getMoney(int time) {
		if (time < 10)
			return 0;
		if (time < 30)
			return 5;
		if (time < 60)
			return 8;
		return ((time - 60) / 60 + 1) * 6 + 8;
	}

}