package learning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class learn_1 {
	private static final String srcPath = "C:/Users/Administrator/Desktop/java/test/students.txt";

	public static void main(String[] args) {
		try {
			File srcFile = new File(srcPath);
			if (!srcFile.exists()) {
				throw new IllegalArgumentException("文件" + srcFile + "不存在");
			}
			if (!srcFile.isFile()) {
				throw new IllegalArgumentException(srcFile + "不是文件");
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(srcFile)));

			String line = null;
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(" ");
				int len = arr.length;
				for (int i = 0; i < len - 1; i++) {
					System.out.print(arr[i] + "---");
				}
				System.out.println(arr[len - 1]);
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
