package usedBookMarketplace;

import java.sql.*;
import java.util.Scanner;

public class User implements AdminAndUser {
	static Scanner sc = new Scanner(System.in);
//	public static String sql;

 	
	
	public void Login(String id, String password) {	// 로그인 후 첫 화면
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
	     		System.out.println("1.사용자 정보 등록 2.도서검색");
	     		int choose = sc.nextInt();
	     		switch(choose) {
	     		case 1:
	     			UserRegistration(id);
	     			stmt.close();
	     			
	     		case 2:
	     			Search(id);
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
	public void UserRegistration(String id) {	// 사용자 정보 등록
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
	
	public String UserRegistrationSql(int choose, String id) {		// 등록시 사용될 쿼리문 작성
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

	public void Search(String id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.println("검색 방법 선택");
	     	System.out.println("1.도서 제목 2.ISBN번호 3.저자 4.출판사 5.출판년도 6.판매자 id");
	     	int choose = sc.nextInt();
	     	String sql = BookSearchSql(choose, id);
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	rs = stmt.executeQuery(sql);
	     	System.out.println("Name	ISBN	Writer		Publisher	year		User-id		price	condition");
	     	System.out.println("---------------------------------------------------------------------------------------------------");
	     	while(rs.next()) {
	     		System.out.println(rs.getString(1)+ "	"+ rs.getString(2) + "	" +rs.getString(3)+ "		"+rs.getString(4)+ "	"+rs.getString(5)+ "	"+rs.getString(6)+ "		"+rs.getString(7)+ "	"+rs.getString(8));
	     	}
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
	public static String BookSearchSql(int choose, String id) {
		String sql = null;
		switch (choose) {
		case 1:
			System.out.print("제목 입력: ");
     		String name = sc.next();
			sql = "select * from book where name ="+ "\"" + name + "\"";
			return sql;
		
		case 2:
			System.out.print("ISBN번호 입력: ");
     		String isbn = sc.next();
			sql = "select * from book where isbn ="+ "\"" + isbn + "\"";
			return sql;
			
		case 3:
			System.out.print("저자 입력: ");
     		String writer = sc.next();
     		sql = "select * from book where writer ="+ "\"" + writer + "\"";
			return sql;
			
		case 4:
			System.out.print("출판사 입력: ");
     		String publisher = sc.next();
     		sql = "select * from book where publisher ="+ "\"" + publisher + "\"";
			return sql;
		
		case 5:
			System.out.print("출판년도 입력: ");
     		String year = sc.next();
     		sql = "select * from book where substr(publicationYear, 1, 4) ="+ "\"" + year + "\"";
			return sql;
			
		case 6:
			System.out.print("판매자 입력: ");
     		String user = sc.next();
     		sql = "select * from book where user_id ="+ "\"" + user + "\"";
			return sql;
		}
		return sql;
	}
}

