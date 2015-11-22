package core_java.mysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PrepareStatemenTest {
	private String driver;
	private String url;
	private String user;
	private String pass;
	public void initParam(String paramFile) throws FileNotFoundException, IOException{
		Properties prop=new Properties();
		prop.load(new FileInputStream(paramFile));
		driver=prop.getProperty("driver");
		url=prop.getProperty("url");
		user=prop.getProperty("user");
		pass=prop.getProperty("pass");
	}
	public void insertUserStatement() throws ClassNotFoundException{
		long start=System.currentTimeMillis();
		Class.forName(driver);
		try(
				Connection conn=DriverManager.getConnection(url,user,pass);
				Statement stmt=conn.createStatement()){
			for(int i=0;i<100;i++){
				stmt.executeUpdate("insert into jdbc_test values("
						+"null,'姓名"+i+"',1)");
			}
			System.out.println("使用Statement费时："+(System.currentTimeMillis()-start));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertUsePrepare() throws ClassNotFoundException{
		long start=System.currentTimeMillis();
		Class.forName(driver);
		try(
				Connection conn=DriverManager.getConnection(url,user,pass);
				PreparedStatement pstmt=conn.prepareStatement("insert into jdbc_test values(null,?,1)");
				){
			for(int i=0;i<100;i++){
				pstmt.setString(1, "姓名"+i);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()-start);
	}
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
		String path=System.getProperty("user.dir")+"/src/core_java/mysql/mysql.ini";
		PrepareStatemenTest pt=new PrepareStatemenTest();
		pt.initParam(path);
		pt.insertUsePrepare();
		pt.insertUsePrepare();
	}
	
}
