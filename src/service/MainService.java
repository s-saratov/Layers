package service;

import model.Car;
import model.Role;
import model.User;
import utils.MyList;

public interface MainService {

    // Методы

    // === CREATE (add) ===

    // Добавляет автомобиль в список
    void addCar(String model, int year, double price);

    // Регистрирует пользователя на основании переданных адреса электронной почты и пароля и возвращает экземпляр класса
    User registerUser (String email, String password);

    // === READ ===

    // Возвращает текущий список всех автомобилей
    MyList<Car> getAllCars();

    // Возвращает автомобиль по ID
    Car getByID(int id);

    // Возвращает список автомобилей по модели
    MyList<Car> getCarsByModel(String model);

    // Возвращает список свободных автомобилей
    MyList<Car> getFreeCars();

    // Возвращает объект пользователя по адресу электронной почты
    public User getUserByEmail(String email);

    // Возвращает активного пользователя
    public User getActiveUser();

    // Возвращает список пользователей по заданным ролям
    public MyList<User> getUsersByRole(Role... roles);

    // === UPDATE ===

    // Обновляет цену автомобиля и возвращает статус успеха операции
    boolean updateCarPrice(int id, double price);

    // Осуществляет взятие автомобиля в аренду и возвращает статус успеха операции
    boolean takeCar(int id);

    // Осуществляет возврат автомобиля от арендатора и возвращает статус успеха операции
    boolean returnCar (int id);

    // Осуществляет вход пользователя в систему и возвращает статус успеха операции
    boolean loginUser(String email, String password);

    // Осуществляет выход пользователя из системы
    void logout();

    // === DELETE ===

    // Удаляет автомобиль по id
    Car deleteCar(int id);

}