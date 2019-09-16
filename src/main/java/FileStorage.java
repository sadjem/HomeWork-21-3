import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class FileStorage implements Storage {
    private String file;
    private int id;
    private Map<Integer, User> usersStorage = new HashMap<>();
    Gson gson = new Gson();

    public FileStorage(String file) {
        this.file = file;
        usersToJson();
    }

    private void usersToJson() {
        saveToFile(gson.toJson(usersStorage));

    }

    private void saveToFile(String output) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<Integer, User> fromJson() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Type type = new TypeToken<Map<Integer, User>>() {
            }.getType();
            return gson.fromJson(br, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAll() {
        fromJson();
        usersStorage.clear();
        usersToJson();
    }

    @Override
    public void removeUser(int id) {
        fromJson();
        usersStorage.remove(id);
        usersToJson();
    }

    @Override
    public void removeUserByName(String name) {
        fromJson();
        List<User> users = new ArrayList<>(usersStorage.values());
        for (User user : users) {
            if (user.name.equals(name)) {
                usersStorage.remove(user.id);
            }
        }
        usersToJson();
    }

    @Override
    public void addUser(User user) {
        fromJson();
        user.id = id++;
        usersStorage.put(user.id, user);
        usersToJson();
    }

    @Override
    public void updateUser(User user) {
        fromJson();
        usersStorage.put(user.id, user);
        usersToJson();
    }

    @Override
    public User getUser(int id) {
        fromJson();
        return usersStorage.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        fromJson();
        return new LinkedList<>(usersStorage.values());
    }
}
