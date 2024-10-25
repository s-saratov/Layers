package model;

import java.util.Objects;

public class Car {

    // Поля

    private final int id;
    private String model;
    private final int year;
    private double price;
    private boolean isBusy; // значение по умолчанию false

    // Конструктор

    public Car(int id, String model, int year, double price) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    // Геттеры и сеттеры

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    // Метод, возвращающий строковое представление экземпляра класса

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", isBusy=" + isBusy +
                '}';
    }

    // Метод, проверяющий равенство двух автомобилей

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

// TODO: спросить на консультации, почему методы equals и hashCode идут в паре