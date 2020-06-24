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
	}
}
