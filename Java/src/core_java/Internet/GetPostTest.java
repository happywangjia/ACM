package core_java.Internet;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class GetPostTest {
	public static String sendGet(String url,String param){
		String result="";
		String urlName=url+"?"+param;
		try{
			URL realUrl=new URL(urlName);
			URLConnection conn=realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection","keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/5.0 (X11; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0");
			conn.connect();
			Map<String,List<String>> map=conn.getHeaderFields();
			for(String key:map.keySet()){
				System.out.println(key+"-->"+map.get(key));
			}
			try(
					BufferedReader in=new BufferedReader(
							new InputStreamReader(conn.getInputStream(),"utf-8"));
					){
				String line;
				while((line=in.readLine())!=null){
					result+="\n"+line;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static String sendPost(String url,String param){
		String result="";
		try{
			URL realUrl=new URL(url);
			URLConnection conn=realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/5.0 (X11; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			try(
					PrintWriter out=new PrintWriter(conn.getOutputStream());
					){
				out.print(param);
				out.flush();
			}
			try(
					BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"))
					){
				String line;
				while((line=in.readLine())!=null){
					result+="\n"+line;
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args){
		
	}
	
}
