package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @tag:
 * @link:
 * @Num:copyFileByByteTest.java
 * @author hijj Create at: 2015年9月19日 下午8:54:55
 * 
 *         单字节，不要缓冲进行文件拷贝
 */
public class copyFileByByteTest {
	public static void copyFileByByte(File srcFile, File destFile)
			throws FileNotFoundException {
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("文件" + srcFile + "不存在！");
		}
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException("文件" + srcFile + "不是文件！");
		}
		FileInputStream bis = new FileInputStream(srcFile);
		FileOutputStream bos = new FileOutputStream(destFile);
		int c;
		try {
			while ((c = bis.read()) != -1) {
				bos.write(c);
				bos.flush();// 可以不要
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
	}
}
