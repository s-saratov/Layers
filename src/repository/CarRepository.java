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

    // === CREATE (add) ===

    // Добавляет автомобиль в "хранилище"
    void addCar(String model, int year, double price);

    // === READ ===

    // Возвращает список всех автомобилей
    MyList<Car> getAllCars();

    // Возвращает автомобиль по ID
    Car getByID(int id);

    // Возвращает список автомобилей по модели
    MyList<Car> getCarsByModel(String model);

    // Возвращает список свободных автомобилей
    MyList<Car> getFreeCars();

    // === DELETE ===

    // Удаляет автомобиль
    void deleteCar(Car car);

}