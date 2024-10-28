package repository;

import model.Car;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sergey Bugaenko
 * {@code @date} 24.10.2024
 */

/*
CRUD - операции
- Create (Создание) - добавление новых данных
- Read (Чтение) - получение или чтение данных
- Update (Обновление) - изменение существующих данных
- Delete (Удаление) - удаление данных
 */


public class CarRepositoryImpl implements CarRepository {

    // тут будут хранится все наши машинки. Имитация БД
    private final MyList<Car> cars;

    // объект, отвечающий за генерацию уникальных id
    private final AtomicInteger currentId = new AtomicInteger(1);

    public CarRepositoryImpl() {
        this.cars = new MyArrayList<>();
        addCars();
    }

    private void addCars() {
        cars.addAll(
                new Car(currentId.getAndIncrement(), "VW Golf", 2021, 20000.00),
                new Car(currentId.getAndIncrement(), "VW Golf", 2019, 17500.00),
                new Car(currentId.getAndIncrement(), "VW Passat", 2022, 30000.00),
                new Car(currentId.getAndIncrement(), "VW Passat", 2020, 24300.00),
                new Car(currentId.getAndIncrement(), "VW Tiguan", 2021, 28000.00),
                new Car(currentId.getAndIncrement(), "VW Tiguan", 2023, 34000.00)
        );
    }


    @Override
    public void addCar(String model, int year, double price) {
        //currentId.getAndIncrement() -> аналог currentId++; -> получение текущего id и затем увеличение его на +1
        Car car = new Car(currentId.getAndIncrement(), model, year, price);
        cars.add(car); // сохранение в "хранилище"
    }

    @Override
    public MyList<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getById(int id) {
        for (Car car : cars) {
            if (car.getId() == id) return car;
        }
        return null;
    }

    @Override
    public MyList<Car> getCarsByModel(String model) {
        MyList<Car> result = new MyArrayList<>();
        for (Car car : cars) {
            if (car.getModel().equals(model)) result.add(car);
        }
        return result;
    }

    // Метод должен вернуть список всех свободных машин.
    // Т.е. список машин, у которых isBusy = false
    @Override
    public MyList<Car> getFreeCars() {
        MyList<Car> result = new MyArrayList<>();

        // Занятая - isBusy = true
        // Свободная - не занятая - isBusy = false

        for (Car car : cars) {
            // false | true
            // Если машина НЕ занята!
            if (!car.isBusy()) {
                result.add(car);
            }

            // Тоже самое
//            if (car.isBusy()) {
//                // Машина занята. Лействий в данном блоке не требуется
//                // Т.к. я ищу НЕ занятый машины
//            } else {
//                // Машина не занята
//                result.add(car);
//            }
        }
        return result;
    }

    /*
    Проверка на занятость машины
        if (car.isBusy()) {
            // Действие с занятой машиной.
       }
     */

    // Метод, возвращающий список всех занятых машин
    public MyList<Car> getAllBusyCars() {
        MyList<Car> result = new MyArrayList<>();

        for (Car car : cars) {
            if (car.isBusy()) result.add(car);
        }
        return result;
    }




    @Override
    public void deleteCar(Car car) {
        cars.remove(car);
    }
}
