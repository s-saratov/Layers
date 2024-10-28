package service;

import model.Car;
import model.Role;
import model.User;
import utils.MyList;

public interface MainService {

    // Методы

    // === Create (add) ===

    void addCar(String model, int year, double price);

    // === Read ===

    // Получить список всех автомобилей
    MyList<Car> getAllCars();

    // Получить автомобиль по ID
    Car getByID(int id);

    // Получить список автомобилей по модели
    MyList<Car> getCarsByModel(String model);

    // Получить список свободных автомобилей
    MyList<Car> getFreeCars();

    // Получить объект пользователя по адресу электронной почты
    public User getUserByEmail(String email);

    // Получить список пользователей по заданным ролям
    public MyList<User> getUsersByRole(Role... roles);

    // === Update ===

    boolean updateCarPrice(int id, double price);

    boolean takeCar(int id);

    boolean returnCar (int id);

    // === Delete ===

    Car deleteCar(int id);

    // Методы для работы с пользователем

    // Возвращает активного пользователя
    public User getActiveUser();

    User registerUser (String email, String password);

    boolean loginUser(String email, String password);

    void logout();

}