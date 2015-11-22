package core_java.mysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.ResultSetMetaData;

public class ExecuteSQL {
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
	public void executeSql(String sql)throws Exception{
		Class.forName(driver);
		try(
			Connection conn=DriverManager.getConnection(url,user,pass);
				Statement stmt=conn.createStatement()){
			boolean hasResultSet=stmt.execute(sql);
			if(hasResultSet){
				try(
						ResultSet rs=stmt.getResultSet()){
					java.sql.ResultSetMetaData rsmd=rs.getMetaData();
					int columnCount=rsmd.getColumnCount();
					while(rs.next()){
						for(int i=0;i<columnCount;i++){
							System.out.println(rs.getString(i+1)+"\t");
						}
						System.out.println("\n");
					}
				}
			}else{
				System.out.println("影响记录有："+stmt.getUpdateCount()+"条");
			}
		}
	}
}
