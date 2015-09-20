package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class objectSeriaDemo1 {
	private static final String destPath="C:/Users/Administrator/Desktop/java/test/lll/jj";
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
	//	ObjectOut();
		ObjectIn();
	}
	public static void ObjectOut() throws FileNotFoundException, IOException{
		String file=destPath;
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
		serializableTest stu=new serializableTest("10000","张三",20);
		oos.writeObject(stu);
		oos.flush();
		oos.close();
	}
	public static void ObjectIn() throws ClassNotFoundException, IOException{
		String file=destPath;
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
		serializableTest stu=(serializableTest) ois.readObject();
		System.out.println(stu);
		ois.close();
	}
	
}
