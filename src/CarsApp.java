import repository.CarRepository;
import repository.CarRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainService;
import service.MainServiceImpl;
import view.Menu;

public class CarsApp {

    public static void main(String[] args) {

        // Создаём экземпляры классов приложения

        CarRepository carRepository = new CarRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        MainService service = new MainServiceImpl(carRepository, userRepository);
        Menu menu = new Menu(service);

        // Запускаем меню

        menu.run();

    }
}