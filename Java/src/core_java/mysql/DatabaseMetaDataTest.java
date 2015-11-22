package core_java.mysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.ResultSetMetaData;

public class DatabaseMetaDataTest {

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
	public void info() throws Exception{
		Class.forName(driver);
		try(
				Connection conn=DriverManager.getConnection(url,user,pass)){
				DatabaseMetaData dbmd=conn.getMetaData();
				ResultSet rs=dbmd.getTableTypes();
				System.out.println("--MySql支持的表类型信息：");
				printResultSet(rs);
				rs=dbmd.getTables(null, null, "%", new String[]{"TABLE"});
				System.out.println("--当前数据库里的数据表信息");
				printResultSet(rs);
				rs=dbmd.getPrimaryKeys(null, null, "users");
				System.out.println("--users的主键信息");
				printResultSet(rs);
				rs=dbmd.getProcedures(null, null, "%");
				System.out.println("--当前数据库的存储过程信息");
				printResultSet(rs);
//				rs=dbmd.getCrossReference(null, null, "users", null, null, "wj");
//				System.out.println("--users表和wj表之间的外键约束");
//				printResultSet(rs);
				rs=dbmd.getColumns(null, null, "users", "%");
				System.out.println("--users表的全部数据列");
				printResultSet(rs);
				
		}
	}
	public void printResultSet(ResultSet rs) throws Exception{
		ResultSetMetaData rsmd=(ResultSetMetaData) rs.getMetaData();
		for(int i=1;i<rsmd.getColumnCount();i++){
			System.out.print(rsmd.getColumnName(i+1)+"\t");
		}
		System.out.print("\n");
		while(rs.next()){
			for(int i=0;i<rsmd.getColumnCount();i++){
				System.out.print(rs.getString(i+1)+"\t");
			}
			System.out.print("\n");
		}
		rs.close();
	}
	public static void main(String[] args) throws Exception{
		String path=System.getProperty("user.dir")+"/src/core_java/mysql/mysql.ini";
		DatabaseMetaDataTest dmdt=new DatabaseMetaDataTest();
		dmdt.initParam(path);
		dmdt.info();
	}
}
