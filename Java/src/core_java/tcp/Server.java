package core_java.tcp;

import java.net.*;
import java.util.Scanner;
import java.io.*;
public class Server
{
	private static Scanner cin=new Scanner(System.in);
	public static void main(String[] args)
		throws IOException
	{
		// 创建一个ServerSocket，用于监听客户端Socket的连接请求
		ServerSocket ss = new ServerSocket(30000);
		// 采用循环不断接受来自客户端的请求
		while (true)
		{
			// 每当接受到客户端Socket的请求，服务器端也对应产生一个Socket
			Socket s = ss.accept();
			// 将Socket对应的输出流包装成PrintStream
			PrintStream ps = new PrintStream(s.getOutputStream());
			// 进行普通IO操作
			String kk=cin.next();
			ps.println(kk);
			// 关闭输出流，关闭Socket
			ps.close();
			s.close();
		}
	}
}
