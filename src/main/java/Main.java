public class Main {
    public static void main(String[] args) {
        User user1 = new User("Alex", 22);
        User user2 = new User("Ben", 23);
        User user3 = new User("John", 24);
        User user4 = new User("Carl", 25);
        FileStorage storage = new FileStorage("Test");
        storage.addUser(user1);
        storage.addUser(user2);
        storage.addUser(user3);
        storage.addUser(user4);
        storage.removeUserByName("Ben");
        System.out.println(storage.getAllUsers());
    }
}
