package core_java.singleton;

/**
 * 
 * @tag:饿汉式单例模式
 * @desc:没有延时加载，效率高，加载时，线程安全
 * @Num:SingletonDemo1.java
 * @author hijj
 * Create at: 2015年11月23日 下午1:20:08
 */
public class SingletonDemo1 {
	private static SingletonDemo1 instance=new SingletonDemo1();
	private SingletonDemo1(){}
	public static SingletonDemo1 getInstance(){
		return instance;
	}
}
