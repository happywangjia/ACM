package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 
 * @tag:
 * @link:
 * @Num:InputStreamReaderTest.java
 * @author hijj Create at: 2015年9月19日 下午9:57:49
 * 
 *         1)编码问题 2)认识文本和文本文件 java的文本(char)是16位无符号整数，是字符的unicode编码(双字节编码)
 *         文本是byte byte...的数据序列
 *         文本文件是char序列按照某种编程方案(utf-8,utf-16,gbk)序列化为byte的存储结果 3)字符流(Reader
 *         Writer)-->操作文本文件 字符的处理，一次处理一个字符 字符流的基本实现 InputStreamReader
 *         完成byte流解析为char流，按照编码解析 OutputStreamReader 提供char流到byte
 */
public class InputStreamReaderTest {
	private static final String srcPath = "C:/Users/Administrator/Desktop/java/test/jj";
	private static final String destPath = "C:/Users/Administrator/Desktop/java/test/";

	public static void ReadByte(InputStreamReader isr, OutputStreamWriter osw) {
		int c;
		try {
			while ((c = isr.read()) != -1) {
				System.out.println(c);
				osw.write(c);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void ReadBuffer(InputStreamReader isr, OutputStreamWriter osw)
			throws IOException {
		char[] buffer = new char[8 * 1024];
		int c;
		while ((c = isr.read(buffer, 0, buffer.length)) != -1) {
			String s = new String(buffer, 0, c);
			System.out.println(s);
			osw.write(buffer, 0, c);
			osw.flush();
		}
	}

	public static void main(String[] args) {
		try (FileInputStream in = new FileInputStream(destPath + "students.txt");
				InputStreamReader isr = new InputStreamReader(in);
				FileOutputStream out = new FileOutputStream(srcPath);
				OutputStreamWriter osw = new OutputStreamWriter(out)) {
			// ReadBuffer(isr, osw);
			ReadByte(isr, osw);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
