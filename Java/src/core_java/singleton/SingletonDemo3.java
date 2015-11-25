package core_java.singleton;

/**
 * 
 * @tag:双重检测锁
 * @link:由于编译优化和JVM底层内部模型原因，偶尔会出问题，不建议使用
 * @Num:SingletonDemo3.java
 * @author hijj
 * Create at: 2015年11月23日 下午1:30:00
 */
public class SingletonDemo3 {
	
	private static SingletonDemo3 instance=null;
	private SingletonDemo3(){}
	public static SingletonDemo3 getInstance(){
		if(instance==null){
			SingletonDemo3 sc;
			synchronized(SingletonDemo3.class){
				sc=instance;
				if(sc==null){
					synchronized (SingletonDemo3.class) {
						if(sc==null){
							sc=new SingletonDemo3();
						}
					}
					instance=sc;
				}
			}
		}
		return instance;
	}
}
