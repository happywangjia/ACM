package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @tag:
 * @link:
 * @Num:ObjectSeriaDemo2.java
 * @author hijj
 * Create at: 2015年9月20日 上午11:12:21
 * 
 * 一个类实现一个序列化接口，那么其子类都可以进行序列化
 * 序列化递归能够调用构造函数
 * 
 * 对子类对象进行反序列操作时，如果其父类没有实现序列化接口，那么其父类的构造函数会被调用
 * 
 * 
 */
public class ObjectSeriaDemo2 {
	private static final String srcPath="C:/Users/Administrator/Desktop/java/test/lll/jj";
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
		boolean flag=false;
	//	flag=true;
		if(flag==true){
			Out();
		}else{
			In();
		}
	}
	
	//序列化操作
	public static void Out() throws FileNotFoundException, IOException{
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(srcPath));
		Foo2 foo2=new Foo2();
		oos.writeObject(foo2);
		oos.flush();
		oos.close();
	}
	//反序列化操作
	public static void In() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(srcPath));
		Foo2 foo2=(Foo2) ois.readObject();
		System.out.println(foo2);
		ois.close();
	}
}

class Foo implements Serializable{
	public Foo(){
		System.out.println("foo...");
	}
}
class Foo1 extends Foo{
	public Foo1(){
		System.out.println("foo1...");
	}
}
class Foo2 extends Foo1{
	public Foo2(){
		System.out.println("foo2...");
	}
	public String toString(){
		return "wangjia";
	}
}

class Bar{
	public Bar(){
		System.out.println("bar...");
	}
}
class Bar1 extends Bar implements Serializable{
	public Bar1(){
		System.out.println("bar1...");
	}
}
class Bar2 extends Bar1{
	public Bar2(){
		System.out.println("bar2");
	}
}