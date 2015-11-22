package core_java.mysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Properties;

public class CallableStatementTest {
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
	public void callProcedure() throws Exception{
		Class.forName(driver);
		try(
			Connection conn=DriverManager.getConnection(url,user,pass);
				CallableStatement cstmt=conn.prepareCall("{call add_pro(?,?,?)}")){
			cstmt.setInt(1, 4);
			cstmt.setInt(2, 5);
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.execute();
			System.out.println("执行结果是："+cstmt.getInt(3));
		}
	}
	public static void main(String[] args) throws Exception{
		String path=System.getProperty("user.dir")+"/src/core_java/mysql/mysql.ini";
		CallableStatementTest ct=new CallableStatementTest();
		ct.initParam(path);
		ct.callProcedure();
	}
}
/*
delimiter //
create procedure add_pro(a int,b int,out sum int)
begin
set sum=a+b;
end;
//
*/