package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 
 * @tag:
 * @link:
 * @Num:BufferedReadTest.java
 * @author hijj Create at: 2015年9月19日 下午9:15:44
 * 
 *         字符流过滤器 BufferedReader-->readline 一次读一行
 *         BufferedWriter/PrintWriter-->写一行
 * 
 */
public class BufferedReaderTest {
//	private static final String srcPath = "C:/Users/Administrator/Desktop/java/test/jj";
	private static final String destPath = "C:/Users/Administrator/Desktop/java/test/";

	public static void BufferedWriteDemo(BufferedReader br) {
		String line;
		File destFile = new File(destPath + "jj");
		if (destFile.exists()) {
			destFile.delete();
		}
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(destFile)))) {
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printWriteDemo(BufferedReader br) {
		File srcFile = new File(destPath + "jj");
		String line = null;
		try (PrintWriter pw = new PrintWriter(srcFile)) {
			while ((line = br.readLine()) != null) {
				pw.write(line);
				pw.println();
				pw.flush();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		File srcFile = new File(destPath + "students.txt");
		if (!srcFile.exists() || !srcFile.isFile()) {
			throw new IllegalArgumentException("文件" + srcFile + "不存在或不是文件！");
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(srcFile)))) {

			// BufferedWriteDemo(br);
			printWriteDemo(br);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
