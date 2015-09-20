package file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @tag:
 * @link:
 * @Num:FileReaderTest.java
 * @author hijj Create at: 2015年9月20日 上午12:18:22
 * 
 *         FileReader/FileWriter
 * 
 */
public class FileReaderTest {
	private static final String srcPath = "C:/Users/Administrator/Desktop/java/test/jj";
	private static final String destPath = "C:/Users/Administrator/Desktop/java/test/students.txt";

	public static void main(String[] args) {
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader(destPath);
			fw = new FileWriter(srcPath);
			int c;
			char[] buffer = new char[1024];
			while ((c = fr.read(buffer,0,buffer.length)) != -1) {
				fw.write(buffer,0,c);
				fw.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
