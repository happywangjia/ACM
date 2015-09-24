package file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 
 * @tag:
 * @link:
 * @Num:RandomAccessFileTest.java
 * @author hijj
 * Create at: 2015年9月20日 上午1:28:47
 * 
 * java.io.file类用于表示文件（目录），file类只用于表示文件（目录）的信息，不能用于文件内容的访问
 * RandomAccessFile java提供的对文件内容的访问，既可以读文件，也可以写文件，支持随机访问文件，可以访问文件的任意位置
 * 
 * 1) java文件模型
 * 在硬盘上的文件是byte byte byte存储的，是数据的集合
 * 
 * 2) 打开方式
 * 有两种模式 "rw"(读写) "r" (只读）
 * RandomAccessFile raf=new RandomAccessFile(File,"rw");
 * 文件指针，打开文件时指针在开头pointer=0;
 * 
 * 3) 写方法
 * raf.write(int)-->只写一个字节（后8位），同时指针指向一个位置，准备再次写入
 * 
 * 4) 读方法
 * int b=raf.read() -->读一个字节
 * 
 * 5) 文件读写完成以后一定要关闭
 * 
 */
public class RandomAccessFileTest {
	private static final String srcPath = "C:/Users/Administrator/Desktop/java/test/lll";
//	private static final String destPath = "C:/Users/Administrator/Desktop/java/test/www";

	
	public static void main(String[] args) throws IOException{
		File demo=new File(srcPath);
		if(!demo.exists()){
			demo.mkdir();
		}
		File file=new File(demo,"jjj");
		if(!file.exists()){
			file.createNewFile();
		}
		RandomAccessFile raf=new RandomAccessFile(file, "rw");
		System.out.println(raf.getFilePointer());
		raf.write('A');
		System.out.println(raf.getFilePointer());
		int i=0x7fffffff;
		raf.write(i>>>24);
		raf.write(i>>>16);
		raf.write(i>>>8);
		raf.write(i);
		
		raf.writeInt(i);
		
		System.out.println(raf.getFilePointer());
		String s="中";
		byte[] gbk=s.getBytes("utf-8");
		raf.write(gbk);
		System.out.println(raf.length());
		
		
		raf.seek(0);
		byte[] buf=new byte[(int) raf.length()];
		raf.read(buf);
		
		System.out.println(Arrays.toString(buf));
		String s1=new String(buf,"Unicode");
		System.out.println(s1);
		for(byte b:buf){
			System.out.print(Integer.toHexString(b&0xff)+" ");
		}
		raf.close();
		
		
	}
}
