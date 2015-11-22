package core_java.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnMysql {
	public static void main(String[] args) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		String selSql="select * from ahu_uinfo";
		String wjSql="select * from ahu_uinfo where learn_num='wj'";
		String delSql="delete from ahu_uinfo where learn_num='wj'";
		String insSql="insert into ahu_uinfo (learn_num,name,password,class) values ('wj','wangjia','"
				+MD5Util.MD5("123456")+"','软件工程');";
		
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ahu","ahuname","password");
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(selSql);
			//System.out.println(rs.getRow());
			while(rs.next()){
				System.out.println(rs.getString("class"));
			}
			rs=stmt.executeQuery(wjSql);
			if(rs.next()){
				stmt.execute(delSql);
			}else{
				stmt.execute(insSql);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
