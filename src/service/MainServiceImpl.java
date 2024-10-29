package service;

import model.Car;
import model.Role;
import model.User;
import repository.CarRepository;
import repository.UserRepository;
import utils.MyArrayList;
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

    // === CREATE ===

    // Добавляет автомобиль в список
    @Override
    public void addCar(String model, int year, double price) {
        repositoryCar.addCar(model, year, price);
    }

    // Регистрирует пользователя на основании переданных адреса электронной почты и пароля и возвращает экземпляр класса
    @Override
    public User registerUser(String email, String password) {
        // Добавить валидацию email и password
        // Если не прошли валидацию - закончить работу метода, вернуть null

        if(email == null || password == null) return null; // исключение передачи null

        if(repositoryUser.isEmailExists(email)) {
            System.out.println("Email already exists");
            return null;
        }

        User user = repositoryUser.addUser(email, password);

        return user;
    }

    // === READ ===

    // Возвращает текущий список всех автомобилей
    @Override
    public MyList<Car> getAllCars() {
        return repositoryCar.getAllCars();
    }

    // Возвращает автомобиль по ID
    public Car getByID (int id) {
        if (id < 0) return null; // исключаем передачу некорректных данных
        return repositoryCar.getByID(id);
    }

    // Возвращает список автомобилей по модели
    @Override
    public MyList<Car> getCarsByModel(String model) {
        return null;
    }

    // Возвращает список свободных автомобилей
    @Override
    public MyList<Car> getFreeCars() {
        return repositoryCar.getFreeCars();
    }

    // Возвращает объект пользователя по адресу электронной почты
    public User getUserByEmail(String email) {
        return repositoryUser.getUserByEmail(email);
    }

    // Возвращает активного пользователя
    @Override
    public User getActiveUser() {
        return activeUser;
    }

    // Возвращает список пользователей по заданным ролям
    @Override
    public MyList<User> getUsersByRole(Role... roles) {
        return repositoryUser.getUsersByRole(roles);
    }

    // === UPDATE ===

    // Обновляет цену автомобиля и возвращает статус успеха операции
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
        if (id < 0) return false; // исключаем передачу некорректных данных
        Car car = repositoryCar.getByID(id);
        /*
        1. Проверить, что автомобиль найден и свободен. Если нет - закончить метод;
        2. Если да: пометить автомобиль как занятый;
        3. Добавить автомобиль в список текущего пользователя
         */
        if (car == null || car.isBusy()) return false;
        car.setBusy(true);
        activeUser.addCarToUserCars(car);
        return true;
    }

    // Осуществляет возврат автомобиля от арендатора и возвращает статус успеха операции
    @Override
    public boolean returnCar(int id) {
        if (id < 0) return false; // исключаем передачу некорректных данных
        if (activeUser.getUserCars().isEmpty()) return false;
        if (!activeUser.getUserCars().contains(repositoryCar.getByID(id))) return false;

        activeUser.removeCarFromUserCars(repositoryCar.getByID(id));
        repositoryCar.getByID(id).setBusy(false);
        return true;
    }

    // Осуществляет вход пользователя в систему и возвращает статус успеха операции
    @Override
    public boolean loginUser(String email, String password) {

        /*
        1. Нужно найти в БД пользователя с таким email
        2. Если пользователя не существует - отказать
        3. Проверить, совпадает ли пароль пользователя в БД с паролем, который пришёл в методе
        4. Если не совпадает, отказать;
        5. Залогинить пользователя - пометить как активного пользователя системы
         */

        if (email == null || password == null) return false; // исключение передачи null

        User user = repositoryUser.getUserByEmail(email);

        if (user == null) {
            System.out.println("Адрес электронной почты введён неверно. Повторите ввод.");
            return false;
        }

        if(!user.getPassword().equals(password)) {
            System.out.println("Пароль введён неверно. Повторите ввод.");
            return false;
        }

        if (user.getRole() == Role.BLOCKED) {
            System.out.println("\nВход в систему невозможен. Ваша учётная запись заблокирована.");
            return false;
        }

        activeUser = user;
        return true;
    }

    // Осуществляет выход пользователя из системы
    @Override
    public void logout() {
        activeUser = null;
    }

    // === DELETE ===

    // Удаляет автомобиль из хранилища
    @Override
    public Car deleteCar(int id) {
        Car car = repositoryCar.getByID(id);
        if (car == null) return null;

        repositoryCar.deleteCar(car);
        return car;
    }

}