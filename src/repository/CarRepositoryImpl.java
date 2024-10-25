package repository;

import model.Car;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

/*
CRUD-операции:
    - Create (создание) - добавление новых данных;
    - Read (чтение) - получение или чтение данных;
    - Update (обновление) - изменение существующих данных;
    - Delete (удаление) - удаление данных.
 */

public class CarRepositoryImpl implements CarRepository{

    // Здесь будут храниться все автомобили. Имитация базы данных
    private final MyList<Car> cars;

    // Объект, отвечающий за генерацию уникальных ID
    private final AtomicInteger currentID = new AtomicInteger(1);

    // Конструктор
        public CarRepositoryImpl() {
        this.cars = new MyArrayList<>();
    }

    // Методы

    // Добавляет автомобиль в "хранилище"
        @Override
    public void addCar(String model, int year, double price) {
        // currentID.getAndIncrement() - аналог currentID++; => получение текущего ID и затем увеличение его на 1
        Car car = new Car(currentID.getAndIncrement(), model, year, price);
        cars.add(car); // сохранение в "хранилище"
    }

    // Возвращает текущий список автомобилей
        @Override
    public MyList<Car> getAllCars() {
        return cars;
    }

    // Возвращает автомобиль по ID
    @Override
    public Car getByID(int id) {
        for (Car car : cars) {
            if (car.getId() == id) return car;
        }
        return null;
    }

    // Возвращает список автомобилей по модели
    @Override
    public MyList<Car> getCarsByModel(String model) {
        MyList<Car> result = new MyArrayList<>();
        for (Car car : cars) {
            if (car.getModel().equals(model)) result.add(car);
        }
        return result;
    }

    // Возвращает список свободных автомобилей (т.е. тех, у которых isBusy == false)
    @Override
    public MyList<Car> getFreeCars() {
        MyList<Car> result = new MyArrayList<>();
        for (Car car : cars) {
            if (!car.isBusy()) result.add(car);
        }
        return result;
    }

    // Возвращает список занятых автомобилей
    public MyList<Car> getAllBusyCars() {
        MyList<Car> result = new MyArrayList<>();
        for (Car car : cars) {
            if (car.isBusy()) result.add(car);
        }
        return result;
    }

    // Обновляет цену автомобиля
    //TODO: перенести в сервис
    public boolean updateCarPrice(int id, double price) {
        Car car = getByID(id);
        if (car == null) return false; // метод getByID не нашёл авто с таким ID (вернул null)
        car.setPrice(price);
        return true;
    }

    // Удаляет автомобиль из "хранилища"
    @Override
    public void deleteCar(Car car) {
        cars.remove(car);
    }
}