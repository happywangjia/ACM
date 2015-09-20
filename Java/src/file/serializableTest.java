package file;

import java.io.Serializable;

/**
 * 
 * @tag:
 * @link:
 * @Num:serializableTest.java
 * @author hijj Create at: 2015年9月20日 上午10:30:05
 * 
 *         对象序列化、反序列化 1）对象序列化就是将Object转化成byte序列，反之叫做反序列化
 *         2）序列化流（ObjectOutputStream),是过滤流--writeObject
 *         反序列化流（ObjectInputStream)-- readObject 3）序列化接口(Serializable)
 *         对象必须实现序列化接口，才能进行序列化，否则出现异常 这个接口没有任何关系，只是一个标准
 * 
 *         private void writeObject(java.io.ObjectOutputStream s) throws
 *         java.io.IOException
 * 
 *         private void readObject(java.io.ObjectInputStream s) throws
 *         java.io.IOException
 * 
 *         5）序列化子类和父类构造函数的调用问题
 *         
 *         private transient int stuage;
 *         对stuage不进行序列化
 * 
 */
public class serializableTest implements Serializable {

	private static final long serialVersionUID = 3449053697160276157L;
	private String stuno;
	private String stuname;
	// 该元素不会进行jvm默认的序列化，也可以自己完成这个元素的序列化
	private transient int stuage;

	public serializableTest() {
	}

	public serializableTest(String stuno, String stuname, int stuage) {
		super();
		this.stuno = stuno;
		this.stuname = stuname;
		this.stuage = stuage;
	}

	public String getStuno() {
		return stuno;
	}

	public void setStuno(String stuno) {
		this.stuno = stuno;
	}

	public String toString() {
		return "[stuno=" + this.stuno + ",stuname=" + this.stuname + ",stuage="
				+ this.stuage + "]";
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public int getStuage() {
		return stuage;
	}

	public void setStuage(int stuage) {
		this.stuage = stuage;
	}

}
