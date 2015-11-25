package core_java.singleton;

/**
 * 
 * @tag:懒汉式单例
 * @desc:延迟加载，效率相对较低，线程安全
 * @Num:SingletonDemo2.java
 * @author hijj
 * Create at: 2015年11月23日 下午1:27:56
 */
public class SingletonDemo2 {
	private static SingletonDemo2 instance=null;
	private SingletonDemo2(){};
	public static synchronized SingletonDemo2 getInstance(){
		if(instance==null){
			instance=new SingletonDemo2();
		}
		return instance;
	}
}
