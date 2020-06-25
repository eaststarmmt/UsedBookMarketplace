package usedBookMarketplace;

import java.sql.Connection;

public interface AdminAndUser {
	public static final String url = "jdbc:mysql://localhost/usedbookmarketplace?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String dbId = "root";
	public static final String dbPassword = "rlaas7490!";
	void Login(String id, String password);
	void Search(String id, String password);
}
