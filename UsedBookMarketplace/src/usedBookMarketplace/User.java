package usedBookMarketplace;

import java.sql.*;

public class User implements AdminAndUser {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	public void Login(String id, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.println("����");
	     	String sql = "select * from user where id =" +"\"" + id + "\"";
	     	stmt = conn.createStatement();
	     	rs = stmt.executeQuery(sql);
	     	rs.next();
	     	if(rs.getString("id").compareTo(id)!=0 || rs.getString("password").compareTo(password)!=0) {
	     		System.out.println("�߸��Է��Ͽ����ϴ�.");
	     	} else {
	     		System.out.println("�α��� ����");
	     	}
	    } catch(ClassNotFoundException e) {
	    	System.out.println("����̹� �ε� ����");
	    } catch(SQLException e) {
	   		System.out.println("����: " + e);
	   	} finally {
	    	try {
	    		if(conn != null && !conn.isClosed()) {
	    			conn.close();
	    		}
	    	} catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    }
	}
	public void Search(String keyword) {
	}
}

