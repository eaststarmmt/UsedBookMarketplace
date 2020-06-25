package usedBookMarketplace;

import java.sql.*;
import java.util.Scanner;

public class User implements AdminAndUser {
	static Scanner sc = new Scanner(System.in);
	
	public void Login(String id, String password) {	// 로그인 후 첫 화면
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	String sql = "select * from user where id =" +"\"" + id + "\"";
	     	stmt = conn.createStatement();
	     	rs = stmt.executeQuery(sql);
	     	rs.next();
	     	if(rs.getString("id").compareTo(id)!=0 || rs.getString("password").compareTo(password)!=0) {
	     		System.out.println("잘못입력하였습니다.");
	     	} else {
	     		System.out.println("로그인 성공");
	     		System.out.print("1.사용자 정보 등록 2.도서검색 3.도서 등록 4.등록한 리스트 보기 5.종료 > ");
	     		int choose = sc.nextInt();
	     		switch(choose) {
	     		case 1:
	     			UserRegistration(id, password);
	     			stmt.close();
	     			break;
	     			
	     		case 2:
	     			Search(id, password);
	     			stmt.close();
	     			break;
	     		case 3:
	     			RegisterBook(id, password);
	     			stmt.close();
	     			break;
	     		case 4:
	     			ListingBook(id, password);
	     			stmt.close();
	     			break;
	     		case 5:
	     			break;
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
	
	protected void UserRegistration(String id, String password) {	// 사용자 정보 등록
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.print("1.이름변경 2.전화번호 등록 3.이메일 등록  > ");
	     	int choose = sc.nextInt();
	     	String sql = UserRegistrationSql(choose, id);
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	System.out.println("변경완료");
	     	stmt.close();
	     	Login(id, password);
	     	
	    
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
	
	private String UserRegistrationSql(int choose, String id) {		// 등록시 사용될 쿼리문 작성
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

	public void Search(String id, String password) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.println("검색 방법 선택");
	     	System.out.print("1.도서 제목 2.ISBN번호 3.저자 4.출판사 5.출판년도 6.판매자 id  > ");
	     	int choose = sc.nextInt();
	     	String sql = BookSearchSql(choose, id);
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	rs = stmt.executeQuery(sql);
	     	System.out.println("Name	ISBN	Writer		Publisher	year	User-id		price	condition");
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
	
	private static String BookSearchSql(int choose, String id) {
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
     		sql = "select * from book where publicationYear="+ "\"" + year + "\"";
			return sql;
			
		case 6:
			System.out.print("판매자 입력: ");
     		String user = sc.next();
     		sql = "select * from book where user_id ="+ "\"" + user + "\"";
			return sql;
		}
		return sql;
	}
	
	protected void RegisterBook(String id, String password) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.println("연결");
	     	String sql = RegisterBookSql(id);
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	System.out.println("등록완료");
	     	stmt.close();
	     	Login(id, password);
	    
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
	
	private static String RegisterBookSql(String id) {
		String sql;
		System.out.print("책제목: ");
		String name = sc.next();
		System.out.print("ISBN: ");
		String isbn = sc.next();
		System.out.print("저자: ");
		String writer = sc.next();
		System.out.print("출판사: ");
		String publisher = sc.next();
		System.out.print("출판년도: ");
		String year = sc.next();
		System.out.print("가격: ");
		String price = sc. next();
		System.out.print("책상태 (Excellent, Good, Fair): ");
		String condition = sc.next();
		
		sql = "insert into book values (\'" + name + "\', \'" + isbn + "\', \'" + writer + "\', \'" + publisher + "\', \'" + year + "\', \'" + id + "\', \'" + price + "\', \'" + condition + "\')";
		return sql;
	}
	
	protected static void ListingBook(String id, String password) { 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	String sql = "select * from book where user_id = \"" + id + "\""; 
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	rs = stmt.executeQuery(sql);
	     	System.out.println("Name	ISBN	Writer		Publisher	year	User-id		price	condition");
	     	System.out.println("---------------------------------------------------------------------------------------------------");
	     	while(rs.next()) {
	     		System.out.println(rs.getString(1)+ "	"+ rs.getString(2) + "	" +rs.getString(3)+ "		"+rs.getString(4)+ "	"+rs.getString(5)+ "	"+rs.getString(6)+ "		"+rs.getString(7)+ "	"+rs.getString(8));
	     	}
	     	stmt.close();
	     	System.out.println();
	     	System.out.print("1.수정 2.삭제  >");
	     	int choose = sc.nextInt();
	     	switch (choose) {
	     	case 1:
	     		Modify(id);
	     		
	     	case 2:
	     		Delete(id);
	     	}
	    
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
	
	private static void Modify(String id) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.print("수정할 책 제목: ");
	     	String name = sc.next();
	     	String sql = ModifyBookSql(name, id);
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
	
	private static String ModifyBookSql(String name, String id) {
		String sql = null;
		System.out.println("수정할 항목");
		System.out.print("1.ISBN번호 2.저자 3.출판사 4.출판년도 5.가격 6.상태  > ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			System.out.print("ISBN번호: ");
     		String isbn = sc.next();
			sql = "update book set isbn = "+ "\"" + isbn + "\" where name = " + "\"" + name + "\" and user_id = \"" + id + "\"";
			return sql;
		
		case 2:
			System.out.print("저자: ");
     		String writer = sc.next();
			sql = "update book set writer = "+ "\"" + writer + "\" where name = " + "\"" + name + "\" and user_id = \"" + id + "\"";
			return sql;
			
		case 3:
			System.out.print("출판사: ");
     		String publisher = sc.next();
			sql = "update book set publisher = "+ "\"" + publisher + "\" where name = " + "\"" + name + "\" and user_id = \"" + id + "\"";
			return sql;
			
		case 4:
			System.out.print("출판년도: ");
     		String year = sc.next();
			sql = "update book set publicationYear = "+ "\"" + year + "\" where name = " + "\"" + name + "\" and user_id = \"" + id + "\"";
			return sql;
			
		case 5:
			System.out.print("가격: ");
     		String price = sc.next();
			sql = "update book set price = "+ "\"" + price + "\" where name = " + "\"" + name + "\" and user_id = \"" + id + "\"";
			return sql;
			
		case 6:
			System.out.print("출판년도: ");
     		String condition = sc.next();
			sql = "update book set bookcondition = "+ "\"" + condition + "\" where name = " + "\"" + name + "\" and user_id = \"" + id + "\"";
			return sql;
		}
		return sql;
	}
	
	private static void Delete(String id) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.print("삭제할 책 제목: ");
	     	String name = sc.next();
	     	String sql = "delete from book where name = \"" + name + "\"and user_id = \"" + id + "\"";
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	System.out.println("삭제완료");
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
}

