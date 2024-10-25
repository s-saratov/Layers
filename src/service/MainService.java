package service;

import model.Car;
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
    // Car getByID(int id);

    // Получить список автомобилей по модели
    MyList<Car> getCarsByModel(String model);

    // Получить список свободных автомобилей
    MyList<Car> getFreeCars();

    // === Update ===

    boolean updateCarPrice(int id, double price);

    boolean takeCar(int id);

    // === Delete ===

    Car deleteCar(int id);

    // Методы для работы с пользователем

    User registerUser (String email, String password);

    boolean loginUser(String email, String password);

    void logout();

}