package repository;

import model.Role;
import model.User;
import utils.MyList;

public interface UserRepository {

    // Методы

    // Добавляет пользователя в список
    User addUser(String email, String password);

    // Проверяет, существует ли данный адрес электронной почты
    boolean isEmailExists(String email);

    // Возвращает список пользователей по заданным ролям
    public MyList<User> getUsersByRole(Role... roles);

    // Возвращает объект пользователя по адресу электронной почты
    public User getUserByEmail(String email);

}