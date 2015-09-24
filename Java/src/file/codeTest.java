package file;

import java.io.UnsupportedEncodingException;

/**
 * 
 * @tag:
 * @link:
 * @Num:codeTest.java
 * @author hijj
 * Create at: 2015年9月20日 上午1:19:19
 * 
 * 文本文件就是字节序列
 * 可以使任意编码的字节文件
 * 如果我们在中文机器上直接创建文本文件，那么该文本文件只认识ansi编码方式
 */
public class codeTest {
	public static void main(String[] args) throws UnsupportedEncodingException{
		String s="疯狂Java教程";
		byte[] bytes1=s.getBytes();
		for(byte b:bytes1){
			System.out.print(Integer.toHexString(b&0xff)+"	");
		}
		System.out.println();
		/**
		 * 显示指定为gbk编码
		 */
		byte[] bytes2=s.getBytes("gbk");
		for(byte b:bytes2){
			System.out.print(Integer.toHexString(b&0xff)+"	");
		}
		System.out.println();
		/**
		 * 显示指定utf-8编码
		 * utf-8中文占三个字符，英文占一个字符
		 */
		byte[] bytes3=s.getBytes("utf-8");
		for(byte b:bytes3){
			System.out.print(Integer.toHexString(b&0xff)+"	");
		}
		System.out.println();
		/**
		 * 显示指定utf-16be编码
		 */
		byte[] bytes4=s.getBytes("utf-16be");
		for(byte b:bytes4){
			System.out.print(Integer.toHexString(b&0xff)+"	");
		}
		System.out.println();
		/**
		 * 当字节序列是某种编码时，想把字节序列变成字符串，也需要用这个编码方式，否则会出现乱码
		 */
		String str1=new String(bytes4);
		System.out.println(str1);
		String str2=new String(bytes4,"utf-16be");
		System.out.println(str2);
	}
}
