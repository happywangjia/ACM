package core_java.Internet;

import java.io.IOException;
import java.net.InetAddress;

public class InetAddressTest {
	public static void main(String[] args) throws IOException{
		InetAddress ip=InetAddress.getByName("www.baidu.com");
		System.out.println("可达："+ip.isReachable(5000));
		System.out.println(ip.getHostAddress());
		
		
	}
}
