package learning;

/**
 * 
 * @tag:
 * @link:
 * @Num:learn_6.java
 * @author hijj
 * Create at: 2015年10月10日 下午5:57:58
 * 
 * Java一切皆引用
 */
public class learn_6 {
	public static void main(String[] args){
		Me a=new Me();
		a.x=10;
		a.y=14;
		Me c;
		c=a.clone();
		c.x+=10;
		System.out.println(c.x);
		System.out.println(a.x);	
	}
	
}
class Me{
	int x;
	int y;
	public Me clone(){
		Me m=new Me();
		m.x=x;
		m.y=y;
		return m;
	}
	
}