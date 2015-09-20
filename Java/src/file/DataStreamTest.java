package file;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @tag:
 * @link:
 * @Num:DataStreamTest.java
 * @author hijj Create at: 2015年9月20日 上午12:32:23
 * 
 *         DataOutputStream/DataInputStream 对"流"功能的扩展，可以更加方便的读取int,long,字符等类型数据
 *         DataOutputStream writeInt()/writeDouble()/WriteUTF()
 */
public class DataStreamTest {
	private static final String srcPath = "C:/Users/Administrator/Desktop/java/test/jj";

	// private static final String destPath =
	// "C:/Users/Administrator/Desktop/java/test/";

	private static void writeDemo() throws IOException {
		String file = srcPath;
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
		dos.writeInt(97);
		dos.writeInt(-98);
		dos.writeLong(10L);
		dos.writeDouble(10.5);
		dos.writeUTF("中国");
		dos.close();

	}

	private static void readDemo() throws IOException {
		String file = srcPath;
		DataInputStream dis = new DataInputStream(new FileInputStream(file));
		int i = dis.readInt();
		System.out.println(i);
		i = dis.readInt();
		System.out.println(i);
		long l = dis.readLong();
		System.out.println(l);
		double d = dis.readDouble();
		System.out.println(d);
		String s = dis.readUTF();
		System.out.println(s);
		dis.close();
	}
	public static void main(String[] args) throws IOException{
		writeDemo();
		readDemo();
	}
}
