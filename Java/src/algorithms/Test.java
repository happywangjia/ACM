package algorithms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Test {
	public static void main(String[] args){
		String s="停留在？那年";
		String result=base64Encode(s);
		System.out.println(result);
	}
	public static String base64Encode(String s){
		ByteArrayOutputStream bOut=new ByteArrayOutputStream();
		Base64Util out=new Base64Util(bOut);
		try{
			out.write(s.getBytes());
			out.flush();
			out.close();
		}catch(Exception e){
			try {
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return bOut.toString();
	}
}
