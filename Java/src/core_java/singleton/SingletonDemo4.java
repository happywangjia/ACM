package core_java.singleton;

/**
 * 
 * @tag:静态内部类（也是一种懒加载方式）
 * @desc:外部类没有static属性，不会像饿汉式立即加载，兼并了并发高效和延迟加载的优势
 * @Num:SingletonDemo4.java
 * @author hijj
 * Create at: 2015年11月23日 下午1:37:30
 */
public class SingletonDemo4 {
	private static class SingletonClassInstance{
		private static final SingletonDemo4 instance=new SingletonDemo4();
	}
	public static SingletonDemo4 getInstance(){
		return SingletonClassInstance.instance;
	}
	private SingletonDemo4(){}

}
