package usedBookMarketplace;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		User user = new User();
		Scanner sc = new Scanner(System.in);
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password: ");
		String password = sc.nextLine();
		user.Login(id, password);
	  
  /*    Connection conn = null;
    
   try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String url = "jdbc:mysql://localhost/usedbookmarketplace?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
      conn = DriverManager.getConnection(url,"root","rlaas7490!");
      System.out.println("연결 성공");
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
    }		*/
    
  }
}
