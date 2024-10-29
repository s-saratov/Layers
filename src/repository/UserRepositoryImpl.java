package repository;

import model.Car;
import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;

public class UserRepositoryImpl implements UserRepository {

    // Список пользователей
    private final MyList<User> users;

    // Конструктор
    public UserRepositoryImpl() {
        users = new MyArrayList<>();
        users.add(new User("admin@mietwagen.de", "A*,5QReA-J1CDo["));   // добавляем администратора по умолчанию
        users.get(0).setRole(Role.ADMIN);
        users.add(new User("test@mietwagen.de", "!2345Qwerty"));    // добавляем пользователя по умолчанию
        users.add(new User("sm@sergey-mavrodi.com", "MmM-4EVER!"));    // добавляем пользователя по умолчанию
        users.get(2).setRole(Role.BLOCKED);
    }

    // Методы

    // === CREATE ===

    // Добавляет пользователя в список
    @Override
    public User addUser(String email, String password) {

        if(email == null || password == null) return null; // исключение передачи null
        User user = new User(email, password);
        users.add(user);
        return user;
    }

    // === READ ===

    // Проверяет, существует ли данный адрес электронной почты
    @Override
    public boolean isEmailExists(String email) {
        if (email == null) return false; // исключение передачи null
        for (User user : users) {
            if (user.getEmail().equals(email)) return true;
        }
        return false;
    }

    // Возвращает объект пользователя по адресу электронной почты
    @Override
    public User getUserByEmail(String email) {
        if (email == null) return null; // исключение передачи null
        for (User user : users) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }

    // Возвращает список пользователей по заданным ролям
    @Override
    public MyList<User> getUsersByRole(Role... roles) {
        MyList<User> result = new MyArrayList<>();
        for (Role role : roles) {
            for (User user : users) {
                if (user.getRole().equals(role)) result.add(user);
            }
        }
        return result;
    }

}