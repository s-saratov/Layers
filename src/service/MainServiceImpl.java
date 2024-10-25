package service;

import model.Car;
import model.User;
import repository.CarRepository;
import repository.UserRepository;
import utils.MyList;

public class MainServiceImpl implements MainService{

    // Поля

    private final CarRepository repositoryCar;
    private final UserRepository repositoryUser;
    private User activeUser;

    // Конструктор

    public MainServiceImpl(CarRepository repositoryCar, UserRepository repositoryUser) {
        this.repositoryCar = repositoryCar;
        this.repositoryUser = repositoryUser;
    }

    // Методы

    // Добавляет автомобиль в список
    @Override
    public void addCar(String model, int year, double price) {

    }

    // Возвращает текущий список автомобилей
    @Override
    public MyList<Car> getAllCars() {
        return repositoryCar.getAllCars();
    }

    // Возвращает список автомобилей по модели
    @Override
    public MyList<Car> getCarsByModel(String model) {
        return null;
    }

    // Возвращает список свободных автомобилей
    @Override
    public MyList<Car> getFreeCars() {
        return null;
    }

    // Обновляет цену автомобиля
    @Override
    public boolean updateCarPrice(int id, double price) {
        Car car = repositoryCar.getByID(id);
        if (car == null || price < 0) {
            return false;
        }
        car.setPrice(price);
        return true;
    }

    // Осуществляет взятие автомобиля в аренду и возвращает статус успеха операции
    @Override
    public boolean takeCar(int id) {
        Car car = repositoryCar.getByID(id);
        /*
        1. Проверить, что автомобиль найден и свободен. Если нет - закончить метод;
        2. Если да: пометить автомобиль как занятый;
        3. Добавить автомобиль в список текущего пользователя
         */
        return false;
    }

    // Удаляет автомобиль из хранилища
    @Override
    public Car deleteCar(int id) {
        Car car = repositoryCar.getByID(id);
        if (car == null) return null;

        repositoryCar.deleteCar(car);
        return car;
    }

    @Override
    public User registerUser(String email, String password) {
        // Добавить валидацию email и password
        // Если не прошли валидацию - закончить работу метода, вернуть null

        if(repositoryUser.isEmailExists(email)) {
            System.out.println("Email already exists");
            return null;
        }

        User user = repositoryUser.addUser(email, password);

        return user;
    }

    @Override
    public boolean loginUser(String email, String password) {

        /*
        1. Нужно найти в БД пользователя с таким email
        2. Если пользователя не существует - отказать
        3. Проверить, совпадает ли пароль пользователя в БД с паролем, который пришёл в методе
        4. Если не совпадает, отказать;
        5. Залогинить пользователя - пометить как активного пользователя системы
         */

        User user = repositoryUser.getUserByEmail(email);
        if (user == null) {
            System.out.println("Invalid email or password");
            return false;
        }

        if(!user.getPassword().equals(password)) {
            System.out.println("Invalid email or password");
            return false;
        }

        activeUser = user;
        return true;
    }

    @Override
    public void logout() {
        activeUser = null;
    }

    public User getActiveUser() {
        return activeUser;
    }
}