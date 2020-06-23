package usedBookMarketplace;

public interface AdminAndUser {
  void Login(String id, String password);
  void Search(String keyword);
  void Result();
}
