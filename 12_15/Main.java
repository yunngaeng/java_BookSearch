package Book;

public class Main {
    public static void main(String[] args) {
        UserDataStorage storage = new UserDataStorage();
        new LoginFrame(storage);
    }
}