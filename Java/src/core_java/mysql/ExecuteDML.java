package core_java.mysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class ExecuteDML {
	private String driver;
	private String user;
	private String url;
	private String pass;
	public void initParam(String paramFile) throws FileNotFoundException, IOException{
		Properties prop=new Properties();
		prop.load(new FileInputStream(paramFile));
		driver=prop.getProperty("driver");
		url=prop.getProperty("url");
		user=prop.getProperty("user");
		pass=prop.getProperty("pass");
	}
	public int insertData(String sql)throws Exception{
		Class.forName(driver);
		try(
			Connection conn=DriverManager.getConnection(url,user,pass);
				Statement stmt=conn.createStatement()){
			return stmt.executeUpdate(sql);
		}
	}
	public static void main(String[] args) throws Exception{
		String path=System.getProperty("user.dir")+"/src/core_java/mysql/mysql.ini";
		ExecuteDML ed=new ExecuteDML();
		ed.initParam(path);
//		int result=ed.insertData("insert into jdbc_test(jdbc_name,jdbc_dest) values('wj','wj');");
	}

}
