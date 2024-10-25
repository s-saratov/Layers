package repository;

import model.User;

public interface UserRepository {

    // Методы

    // Добавляет пользователя в список
    User addUser(String email, String password);

    // Проверяет, существует ли данный адрес электронной почты
    boolean isEmailExists(String email);

    // Возвращает объект пользователя по адресу электронной почты
    User getUserByEmail(String email);

}