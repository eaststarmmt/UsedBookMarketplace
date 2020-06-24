package usedBookMarketplace;

import java.sql.*;
import java.util.Scanner;

public class User implements AdminAndUser {
	Scanner sc = new Scanner(System.in);
//	public static String sql;

 	
	
	public void Login(String id, String password) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
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
	     		System.out.println("1.사용자 정보 등록");
	     		int choose = sc.nextInt();
	     		switch(choose) {
	     		case 1:
	     			UserRegistration(id);
	     			stmt.close();
	     		}
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
	public void UserRegistration(String id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.println("연결");
	     	System.out.println("1.이름변경 2.전화번호 등록 3.이메일 등록");
	     	int choose = sc.nextInt();
	     	String sql = UserRegistrationSql(choose, id);
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	System.out.println("변경완료");
	     	stmt.close();
	    
		} catch(ClassNotFoundException e) {
	    	System.out.println("드라이버 로딩 실패");
	    } catch(SQLException e) {
	   		System.out.println("에러: "+ e);
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
	
	public String UserRegistrationSql(int choose, String id) {
		String sql = null;
		switch (choose) {
		case 1:
			System.out.print("이름 입력: ");
     		String n = sc.next();
			sql = "update user set name = "+ "\"" + n + "\" where id = " + "\"" + id + "\"";
			return sql;
		
		case 2:
			System.out.print("전화번호 입력(ex 010-1234-1234): ");
     		String phone = sc.next();
			sql = "update user set phone = "+ "\"" + phone + "\" where id = " + "\"" + id + "\"";
			return sql;
			
		case 3:
			System.out.print("이메일 입력: ");
     		String email = sc.next();
			sql = "update user set email = "+ "\"" + email + "\" where id = " + "\"" + id + "\"";
			return sql;
		}
		return sql;
	}

	public void Search(String keyword) {
	}
}

