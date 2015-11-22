package core_java.mysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class CacheRowSetTest {
	private static String driver;
	private static String user;
	private static String url;
	private static String pass;
	public static void initParam(String paramFile) throws FileNotFoundException, IOException{
		Properties prop=new Properties();
		prop.load(new FileInputStream(paramFile));
		driver=prop.getProperty("driver");
		url=prop.getProperty("url");
		user=prop.getProperty("user");
		pass=prop.getProperty("pass");
	}
	public CachedRowSet query(String sql)throws Exception{
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(url,user,pass);
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		RowSetFactory rf=RowSetProvider.newFactory();
		CachedRowSet cr=rf.createCachedRowSet();
		cr.populate(rs);
		rs.close();
		stmt.close();
		conn.close();
		return cr;
	}
	public static void main(String[] args) throws Exception{
		String path=System.getProperty("user.dir")+"/src/core_java/mysql/mysql.ini";
		CacheRowSetTest cst=new CacheRowSetTest();
		cst.initParam(path);
		String sql="select * from users";
		CachedRowSet crs=cst.query(sql);
		crs.afterLast();
		while(crs.previous()){
			System.out.println(crs.getString(1)+"\t"+crs.getString(2)+"\t"+crs.getString(3)+"\t");
			if(crs.getInt(1)==2222){
				crs.updateString("name", "wangjia");
				crs.updateRow();
			}
		}
		Connection conn=DriverManager.getConnection(url,user,pass);
		conn.setAutoCommit(false);
		crs.acceptChanges(conn);
	}
}
