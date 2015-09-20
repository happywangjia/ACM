package file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * BufferedInputStream/BufferedOutputStream
 * 这两个流类IO提供了带缓冲的操作，一般打开文件进行写入或读取操作时，都会加上缓冲，这种 流模式提高了IO的性能
 *
 * 从应用程序中吧输入访问文件相当于把一缸倒入另一缸水 
 * FileOutputStream-->write() 相当一滴一滴地倒
 * DataOutputStream-->writeXxx() 相当一瓢一瓢把水转移过去 
 * BufferedOutputStream-->write() 相当先一瓢一瓢放入桶中，再放入缸中
 *
 */

public class copyFileByBufferTest {
	private static final String srcFile = "C:/Users/Administrator/Desktop/java/test/jj";
	private static final String destFile = "C:/Users/Administrator/Desktop/java/test/";

	/**
	 * 进行文件拷贝，利用带缓冲的字节流
	 */
	public static void copyFileByBuffer(File srcFile, File destFile) {
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("文件" + srcFile + "不存在！");
		}
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException("文件" + srcFile + "不是文件！");
		}
		try (BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(srcFile));
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(destFile))) {
			int c;
			while ((c = bis.read()) != -1) {
				bos.write(c);
				bos.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		copyFileByBuffer(new File(srcFile), new File(destFile + start));

	}

}
