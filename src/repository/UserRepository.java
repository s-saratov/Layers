package repository;

import model.User;

/**
 * @author Sergey Bugaenko
 * {@code @date} 25.10.2024
 */

public interface UserRepository {

    User addUser(String email, String password);

    boolean isEmailExists(String email);

    User getUserByEmail(String email);
}
