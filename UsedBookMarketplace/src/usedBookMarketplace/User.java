package usedBookMarketplace;

import java.sql.*;
import java.util.Scanner;

public class User implements AdminAndUser {
	static Scanner sc = new Scanner(System.in);
//	public static String sql;

 	
	
	public void Login(String id, String password) {	// �α��� �� ù ȭ��
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
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
	     		System.out.println("1.����� ���� ��� 2.�����˻�");
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
	public void UserRegistration(String id) {	// ����� ���� ���
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.println("����");
	     	System.out.println("1.�̸����� 2.��ȭ��ȣ ��� 3.�̸��� ���");
	     	int choose = sc.nextInt();
	     	String sql = UserRegistrationSql(choose, id);
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
	
	public String UserRegistrationSql(int choose, String id) {		// ��Ͻ� ���� ������ �ۼ�
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

	public void Search(String id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	     	conn = DriverManager.getConnection(url, dbId, dbPassword);
	     	System.out.println("�˻� ��� ����");
	     	System.out.println("1.���� ���� 2.ISBN��ȣ 3.���� 4.���ǻ� 5.���ǳ⵵ 6.�Ǹ��� id");
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
	public static String BookSearchSql(int choose, String id) {
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
     		sql = "select * from book where substr(publicationYear, 1, 4) ="+ "\"" + year + "\"";
			return sql;
			
		case 6:
			System.out.print("�Ǹ��� �Է�: ");
     		String user = sc.next();
     		sql = "select * from book where user_id ="+ "\"" + user + "\"";
			return sql;
		}
		return sql;
	}
}

