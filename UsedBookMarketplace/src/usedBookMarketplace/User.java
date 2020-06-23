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
	     	System.out.println("연결");
	     	String sql = "select * from user where id =" +"\"" + id + "\"";
	     	stmt = conn.createStatement();
	     	rs = stmt.executeQuery(sql);
	     	rs.next();
	     	if(rs.getString("id").compareTo(id)!=0 || rs.getString("password").compareTo(password)!=0) {
	     		System.out.println("잘못입력하였습니다.");
	     	} else {
	     		System.out.println("로그인 성공");
	     	}
	    } catch(ClassNotFoundException e) {
	    	System.out.println("드라이버 로딩 실패");
	    } catch(SQLException e) {
	   		System.out.println("에러: " + e);
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

