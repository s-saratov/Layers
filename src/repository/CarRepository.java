package repository;

/*
CRUD-операции:
    - Create (создание) - добавление новых данных;
    - Read (чтение) - получение или чтение данных;
    - Update (обновление) - изменение существующих данных;
    - Delete (удаление) - удаление данных.
 */

import model.Car;
import utils.MyList;

public interface CarRepository {

    // Create - add
    void addCar(String model, int year, double price);

    // Read

    // Получить список всех автомобилей
    MyList<Car> getAllCars();

    // Получить автомобиль по ID
    Car getByID(int id);

    // Получить список автомобилей по модели
    MyList<Car> getCarsByModel(String model);

    // Получить список свободных автомобилей
    MyList<Car> getFreeCars();

    // Delete
    void deleteCar(Car car);

}