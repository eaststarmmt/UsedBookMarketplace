package usedBookMarketplace;

import java.sql.*;
import java.util.Scanner;

public class User implements AdminAndUser {
	static Scanner sc = new Scanner(System.in);
	
	public void Login(String id, String password) {	// �α��� �� ù ȭ��
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
	     		System.out.println("�߸��Է��Ͽ����ϴ�.");
	     	} else {
	     		System.out.println("�α��� ����");
	     		System.out.print("1.����� ���� ��� 2.�����˻� 3.���� ��� 4.����� ����Ʈ ���� 5.���� > ");
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
	
	protected void UserRegistration(String id, String password) {	// ����� ���� ���
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.print("1.�̸����� 2.��ȭ��ȣ ��� 3.�̸��� ���  > ");
	     	int choose = sc.nextInt();
	     	String sql = UserRegistrationSql(choose, id);
	     	System.out.println(sql);
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
	
	private String UserRegistrationSql(int choose, String id) {		// ��Ͻ� ���� ������ �ۼ�
		String sql = null;
		switch (choose) {
		case 1:
			System.out.print("�̸� �Է�: ");
     		String n = sc.next();
			sql = "update user set name = "+ "\"" + n + "\" where id = " + "\"" + id + "\"";
			return sql;
		
		case 2:
			System.out.print("��ȭ��ȣ �Է�(ex 010-1234-1234): ");
     		String phone = sc.next();
			sql = "update user set phone = "+ "\"" + phone + "\" where id = " + "\"" + id + "\"";
			return sql;
			
		case 3:
			System.out.print("�̸��� �Է�: ");
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
	
	protected void RegisterBook(String id, String password) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.println("����");
	     	String sql = RegisterBookSql(id);
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	System.out.println("��ϿϷ�");
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
	
	private static String RegisterBookSql(String id) {
		String sql;
		System.out.print("å����: ");
		String name = sc.next();
		System.out.print("ISBN: ");
		String isbn = sc.next();
		System.out.print("����: ");
		String writer = sc.next();
		System.out.print("���ǻ�: ");
		String publisher = sc.next();
		System.out.print("���ǳ⵵: ");
		String year = sc.next();
		System.out.print("����: ");
		String price = sc. next();
		System.out.print("å���� (Excellent, Good, Fair): ");
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
	     	System.out.print("1.���� 2.����  >");
	     	int choose = sc.nextInt();
	     	switch (choose) {
	     	case 1:
	     		Modify(id);
	     		
	     	case 2:
	     		Delete(id);
	     	}
	    
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
	
	private static void Modify(String id) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.print("������ å ����: ");
	     	String name = sc.next();
	     	String sql = ModifyBookSql(name, id);
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	System.out.println("����Ϸ�");
	     	stmt.close();
	    
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
	
	private static String ModifyBookSql(String name, String id) {
		String sql = null;
		System.out.println("������ �׸�");
		System.out.print("1.ISBN��ȣ 2.���� 3.���ǻ� 4.���ǳ⵵ 5.���� 6.����  > ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			System.out.print("ISBN��ȣ: ");
     		String isbn = sc.next();
			sql = "update book set isbn = "+ "\"" + isbn + "\" where name = " + "\"" + name + "\" and user_id = \"" + id + "\"";
			return sql;
		
		case 2:
			System.out.print("����: ");
     		String writer = sc.next();
			sql = "update book set writer = "+ "\"" + writer + "\" where name = " + "\"" + name + "\" and user_id = \"" + id + "\"";
			return sql;
			
		case 3:
			System.out.print("���ǻ�: ");
     		String publisher = sc.next();
			sql = "update book set publisher = "+ "\"" + publisher + "\" where name = " + "\"" + name + "\" and user_id = \"" + id + "\"";
			return sql;
			
		case 4:
			System.out.print("���ǳ⵵: ");
     		String year = sc.next();
			sql = "update book set publicationYear = "+ "\"" + year + "\" where name = " + "\"" + name + "\" and user_id = \"" + id + "\"";
			return sql;
			
		case 5:
			System.out.print("����: ");
     		String price = sc.next();
			sql = "update book set price = "+ "\"" + price + "\" where name = " + "\"" + name + "\" and user_id = \"" + id + "\"";
			return sql;
			
		case 6:
			System.out.print("���ǳ⵵: ");
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
	     	System.out.print("������ å ����: ");
	     	String name = sc.next();
	     	String sql = "delete from book where name = \"" + name + "\"and user_id = \"" + id + "\"";
	     	System.out.println(sql);
	     	stmt = conn.createStatement();
	     	int result = stmt.executeUpdate(sql);
	     	System.out.println("�����Ϸ�");
	     	stmt.close();
	    
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

