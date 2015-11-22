package core_java.mysql;

import java.io.FileInputStream;
import java.io.ObjectInputStream.GetField;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * @tag:
 * @link:
 * @Num:ExecuteDDL.java
 * @author hijj
 * Create at: 2015年11月20日 下午10:55:31
 * 修改mysql.ini即可
 */
public class ExecuteDDL {
	private String driver;
	private String url;
	private String user;
	private String pass;
	public void initParam(String paramFile) throws Exception{
		Properties props=new Properties();
		props.load(new FileInputStream(paramFile));
		driver=props.getProperty("driver");
		url=props.getProperty("url");
		user=props.getProperty("user");
		pass=props.getProperty("pass");
	}
	public void createTable(String sql) throws ClassNotFoundException{
		Class.forName(driver);
		try(
			Connection conn=DriverManager.getConnection(url,user,pass);
			Statement stmt=conn.createStatement()){
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception{
		String path=System.getProperty("user.dir")+"/src/core_java/mysql/mysql.ini";
//		System.out.println(path);
		ExecuteDDL ed=new ExecuteDDL();
		ed.initParam(path);
		ed.createTable("create table jdbc_test"
				+"( jdbc_id int auto_increment primary key,"
				+ "jdbc_name varchar(255),"
				+ "jdbc_desc text);"
				);
	}
}
