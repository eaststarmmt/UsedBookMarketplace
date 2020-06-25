package usedBookMarketplace;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		User user = new User();
		Admin admin = new Admin();
		Scanner sc = new Scanner(System.in);
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password: ");
		String password = sc.nextLine();
		
		if(id.compareTo("admin")==0 && password.compareTo("nayana")==0) {
			admin.Login(id, password);
		}
		user.Login(id, password);
	}
}
