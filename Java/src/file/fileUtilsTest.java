package file;

import java.io.File;

/**
 * 
 * @tag:
 * @link:
 * @Num:fileUtilsTest.java
 * @author hijj
 * Create at: 2015年9月20日 上午12:56:07
 * 
 * 列出指定目录下（包括其子目录）的所有文件
 */
public class fileUtilsTest {
	private static final String path="C:/Users/Administrator/Desktop/java/test";
	
	private static void listDirectory(File dir){
		if(!dir.exists()){
			throw new IllegalArgumentException("目录"+"不存在");
		}
		if(!dir.isDirectory()){
			throw new IllegalArgumentException("不是目录");
		}
		String[] filenames=dir.list();
		for(String name:filenames){
			System.out.println(name);
		}
		File[] files=dir.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				listDirectory(file);
			}else{
				System.out.println(file);
			}
		}
	}
	public static void main(String[] args){
		listDirectory(new File(path));
	}
}
