package repository;

import model.User;
import utils.MyArrayList;
import utils.MyList;

public class UserRepositoryImpl implements UserRepository {

    // Список пользователей
    private final MyList<User> users;

    // Конструктор
    public UserRepositoryImpl() {
        users = new MyArrayList<>();
    }

    // Методы

    // Добавляет пользователя в список
    @Override
    public User addUser(String email, String password) {
        User user = new User(email, password);
        users.add(user);
        return user;
    }

    // Проверяет, существует ли данный адрес электронной почты
    @Override
    public boolean isEmailExists(String email) {
        return false;
    }

    // Возвращает объект пользователя по адресу электронной почты
    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
