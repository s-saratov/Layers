package model;

import utils.MyArrayList;
import utils.MyList;

public class User {

    // Поля

    private String email;
    private String password;
    private Role role;
    private final MyList<Car> userCars;

    // Конструктор

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Role.USER;
        this.userCars = new MyArrayList<>();
    }

    // Геттеры и сеттеры

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MyList<Car> getUserCars() {
        return userCars;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Методы

    // Добавляет автомобиль в список пользователя

    public boolean addCarToUserCars(Car car) {
        if (car == null) return false; // исключаем передачу null

        // Проверяем, содержится ли уже автомобиль в списке пользователя
        if (userCars.contains(car)) {
            System.out.println("Данный автомобиль уже взят Вами в аренду.");
            return false;
        }

        userCars.add(car);
        return true;
    }

    // Удаляет автомобиль из списка пользователя

    public boolean removeCarFromUserCars(Car car) {
        if (car == null) return false; // исключаем передачу null

        // Проверяем, содержится ли уже автомобиль в списке пользователя
        if (!userCars.contains(car)) {
            System.out.println("Данный автомобиль не находится у Вас в аренде.");
            return false;
        }

        userCars.remove(car);
        return true;
    }

    // Возвращает строковое представление экземпляра класса
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", userCars=" + userCars +
                '}';
    }
}