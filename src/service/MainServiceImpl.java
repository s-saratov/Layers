package service;

import model.Car;
import model.Role;
import model.User;
import repository.CarRepository;
import repository.UserRepository;
import utils.MyList;

/**
 * @author Sergey Bugaenko
 * {@code @date} 25.10.2024
 */

public class MainServiceImpl implements MainService {

    private final CarRepository repositoryCar;
    private final UserRepository repositoryUser;
    private User activeUser;

    public MainServiceImpl(CarRepository carRepository, UserRepository repositoryUser) {
        this.repositoryCar = carRepository;
        this.repositoryUser = repositoryUser;
    }


    @Override
    public void addCar(String model, int year, double price) {
        // Проверка роли текущего пользователя
        if (activeUser == null || activeUser.getRole() != Role.ADMIN) {
            System.out.println("Добавление нового автомобиля доступно только Администраторам");
            return;
        }

        // Проверка входящих значений и добавление автомобиля в БД

    }

    @Override
    public MyList<Car> getAllCars() {
        return repositoryCar.getAllCars();
    }

    @Override
    public MyList<Car> getCarsByModel(String model) {
        return null;
    }

    @Override
    public MyList<Car> getFreeCars() {
        return null;
    }

    @Override
    public boolean updateCarPrice(int id, double price) {
        // Добавим проверку роли пользователя
        if (activeUser == null || activeUser.getRole() != Role.ADMIN) {
            System.out.println("Изменять цену может только администратор");
            return false;
        }

        Car car = repositoryCar.getById(id);
        if (car == null || price < 0) {
            return false;
        }

        car.setPrice(price);
        return true;
    }

    @Override
    public boolean takeCar(int id) {
        // Проверка, что пользователь авторизирован в системе
        if (activeUser == null) {
            System.out.println("Выполните вход в систему");
            return false;
        }

        if (activeUser.getRole() == Role.BLOCKED) {
            System.out.println("Ваш аккаунт заблокирован! Обратитесь в службу поддержки");
            return false;
        }

        Car car = repositoryCar.getById(id);
        // Todo
        /*
        1. Проверить, что машина найден и свободна. Если нет - закончить метод
        2. Пометить машину, как занятую
        3. Добавить машину в список машин текущего пользователя
         */

        return false;
    }

    @Override
    public Car deleteCar(int id) {
        // Проверка пользователя
        if (activeUser == null || activeUser.getRole() != Role.ADMIN) {
            System.out.println("Доступно только Администратору");
            return null;
        }
        
        Car car = repositoryCar.getById(id);
        if (car == null) return null;

        repositoryCar.deleteCar(car);
        return car;
    }

    @Override
    public User registerUser(String email, String password) {
        // Добавить валидацию email и password
        // Если не прошли валидацию - закончить работу метода, вернуть null

        if (repositoryUser.isEmailExists(email)) {
            System.out.println("Email already exists");
            return null;
        }

        User user = repositoryUser.addUser(email, password);

        return user;
    }

    @Override
    public boolean loginUser(String email, String password) {
        /*
        1. Нужно пользователя в БД с таким email
        2. Если пользователя не существует - отказать
        3. Проверить, совпадает ли пароль у пользователя в БД с паролем, который пришел в методе
        4. Если не совпадает - отказать
        5. Залогинить пользователя - пометить как активный пользователь системы
         */

        User user = repositoryUser.getUserByEmail(email);
        if (user == null) {
            System.out.println("Invalid email or password");
            return false;
        }

        if (!user.getPassword().equals(password)) {
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
