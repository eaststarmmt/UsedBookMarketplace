package usedBookMarketplace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Admin implements AdminAndUser {
	static Scanner sc = new Scanner(System.in);
	
	public void Login(String id, String password) {
		System.out.println("관리자 모드");
	    System.out.print("1.도서검색 2.도서 리스트 3.책 삭제 4.사용자 리스트 5.사용자 삭제 6.사용자 관리 > ");
	    int choose = sc.nextInt();
	    switch(choose) {
	    case 1:
	     	Search(id, password);
	     	break;
	     			
	    case 2:
	     	LisgtingAdmin(id, password);
	     	break;
	    case 3:
	    	DeleteBook(id, password);
	     	break;
	     	
	    case 4:
	    	UserList(id, password);
	    	break;
	    	
	    case 5:
	    	DeleteUser(id, password);
	    	break;
	    	
	    case 6:
	    	Management(id, password);
	    	break;
	    }
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
	
	protected void LisgtingAdmin(String id, String password) { 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	String sql = "select * from book"; 
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	rs = stmt.executeQuery(sql);
	     	System.out.println("Name	ISBN	Writer		Publisher	year	User-id		price	condition");
	     	System.out.println("---------------------------------------------------------------------------------------------------");
	     	while(rs.next()) {
	     		System.out.println(rs.getString(1)+ "	"+ rs.getString(2) + "	" +rs.getString(3)+ "		"+rs.getString(4)+ "	"+rs.getString(5)+ "	"+rs.getString(6)+ "		"+rs.getString(7)+ "	"+rs.getString(8));
	     	}
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
	
	public void DeleteBook(String id, String password) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.print("삭제할 책 제목: ");
	     	String name = sc.next();
	     	String sql = "delete from book where name = \"" + name + "\"";
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	System.out.println("삭제완료");
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
	
	protected void UserList(String id, String password) { 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	String sql = "select * from user where not id = \"admin\""; 
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	rs = stmt.executeQuery(sql);
	     	System.out.println("ID	password	name	phone		email			statment");
	     	System.out.println("---------------------------------------------------------------------------------------------------");
	     	while(rs.next()) {
	     		System.out.println(rs.getString(1)+ "	"+ rs.getString(2) + "	" +rs.getString(3)+ "	"+rs.getString(4) + "	" + rs.getString(5) + "		" + rs.getString(6));
	     	}
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
	
	public void DeleteUser(String id, String password) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.print("삭제할 사용자: ");
	     	String name = sc.next();
	     	String sql = "delete from user where name = \"" + name + "\" and statement = \"deactivated\"";
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	if (result == 0) {
	     		System.out.println("activated 상태 입니다");
	     	} else {
	     		System.out.println("삭제완료");
	     	}
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
	protected void Management(String id, String password) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.print("변경할 사용자 이름: ");
	     	String name = sc.next();
	     	System.out.print("상태(activated, deactivated): ");
	     	String statement = sc.next();
	     	String sql = "update user set statement = \"" + statement + "\" where name = \"" +  name + "\"";
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
}
