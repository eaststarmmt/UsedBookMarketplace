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
		System.out.println("������ ���");
	    System.out.print("1.�����˻� 2.���� ����Ʈ 3.å ���� 4.����� ����Ʈ 5.����� ���� 6.����� ���� > ");
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
	     	System.out.println("�˻� ��� ����");
	     	System.out.print("1.���� ���� 2.ISBN��ȣ 3.���� 4.���ǻ� 5.���ǳ⵵ 6.�Ǹ��� id  > ");
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
	    	System.out.println("����̹� �ε� ����");
	    } catch(SQLException e) {
	   		System.out.println("����: "+ e);
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
			System.out.print("���� �Է�: ");
     		String name = sc.next();
			sql = "select * from book where name ="+ "\"" + name + "\"";
			return sql;
		
		case 2:
			System.out.print("ISBN��ȣ �Է�: ");
     		String isbn = sc.next();
			sql = "select * from book where isbn ="+ "\"" + isbn + "\"";
			return sql;
			
		case 3:
			System.out.print("���� �Է�: ");
     		String writer = sc.next();
     		sql = "select * from book where writer ="+ "\"" + writer + "\"";
			return sql;
			
		case 4:
			System.out.print("���ǻ� �Է�: ");
     		String publisher = sc.next();
     		sql = "select * from book where publisher ="+ "\"" + publisher + "\"";
			return sql;
		
		case 5:
			System.out.print("���ǳ⵵ �Է�: ");
     		String year = sc.next();
     		sql = "select * from book where publicationYear="+ "\"" + year + "\"";
			return sql;
			
		case 6:
			System.out.print("�Ǹ��� �Է�: ");
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
	    	System.out.println("����̹� �ε� ����");
	    } catch(SQLException e) {
	   		System.out.println("����: "+ e);
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
	     	System.out.print("������ å ����: ");
	     	String name = sc.next();
	     	String sql = "delete from book where name = \"" + name + "\"";
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	System.out.println("�����Ϸ�");
	     	stmt.close();
	     	Login(id, password);
		} catch(ClassNotFoundException e) {
	    	System.out.println("����̹� �ε� ����");
	    } catch(SQLException e) {
	   		System.out.println("����: "+ e);
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
	    	System.out.println("����̹� �ε� ����");
	    } catch(SQLException e) {
	   		System.out.println("����: "+ e);
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
	     	System.out.print("������ �����: ");
	     	String name = sc.next();
	     	String sql = "delete from user where name = \"" + name + "\" and statement = \"deactivated\"";
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	if (result == 0) {
	     		System.out.println("activated ���� �Դϴ�");
	     	} else {
	     		System.out.println("�����Ϸ�");
	     	}
	     	stmt.close();
	     	Login(id, password);
		} catch(ClassNotFoundException e) {
	    	System.out.println("����̹� �ε� ����");
	    } catch(SQLException e) {
	   		System.out.println("����: "+ e);
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
	     	System.out.print("������ ����� �̸�: ");
	     	String name = sc.next();
	     	System.out.print("����(activated, deactivated): ");
	     	String statement = sc.next();
	     	String sql = "update user set statement = \"" + statement + "\" where name = \"" +  name + "\"";
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	System.out.println("����Ϸ�");
	     	stmt.close();
	     	Login(id, password);
	     	
	    
		} catch(ClassNotFoundException e) {
	    	System.out.println("����̹� �ε� ����");
	    } catch(SQLException e) {
	   		System.out.println("����: "+ e);
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
