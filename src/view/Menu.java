package view;

import model.Car;
import model.Role;
import model.User;
import service.MainService;
import utils.Utils;

import java.util.Scanner;

public class Menu {

    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(MainService service) {
        this.service = service;
    }

    public void run() {
        showWelcomeMenu();
    }

    private void showWelcomeMenu() {
        System.out.println("\nДобро пожаловать в систему аренды автомобилей!");
        System.out.println("У Вас уже есть учётная запись? (1 - да, 0 - нет)");

        int choice = getSelection();

        while (true) {
            switch (choice) {
                case 0:
                    showAccountCreationMenu();
                    break;
                case 1:
                    showLoginMenu();
                    break;
                default:
                    System.out.println("Введённое Вами число некорректно. Повторите ввод:");
                    choice = getSelection();
                    break;
            }
        }
    }

    private void showAccountCreationMenu() {

        String email = "test@test.com";
        String password = "Qwert123!";

        System.out.println("\nРегистрация нового пользователя.");
        System.out.print("Введите адрес электронной почты: ");
        String string = scanner.nextLine();
        if (Utils.isEmailValid(string)) email = string;
        else {
            while (!Utils.isEmailValid(string)) {
                System.out.printf("Введённый Вами адрес не соответствует требованиям. Повторите ввод: ");
                string = scanner.nextLine();
            }
        }

        System.out.print("Введите пароль: ");
        string = scanner.nextLine();
        if (Utils.isPasswordValid(string)) password = string;
        else {
            while (!Utils.isPasswordValid(string)) {
                System.out.println("Введённый Вами пароль не соответствует требованиям. Повторите ввод:");
                string = scanner.nextLine();
            }
        }

        User user = service.registerUser(email, password);
        if (user == null) {
            System.out.println("Регистрация не пройдена. Повторите попытку.");
            showAccountCreationMenu();
        } else {
            System.out.println("Регистрация успешно завершена. Теперь Вы можете войти в систему.");
            showLoginMenu();
        }

    }

    private void showLoginMenu() {

        String email = "test@test.com";
        String password = "Qwert123!";

        System.out.println("\nВход в систему.");
        System.out.print("Введите адрес электронной почты: ");
        String string = scanner.nextLine();
        if (Utils.isEmailValid(string)) email = string;
        else {
            while (!Utils.isEmailValid(string)) {
                System.out.print("Введённый Вами адрес не соответствует требованиям. Повторите ввод: ");
                string = scanner.nextLine();
            }
        }

        System.out.print("Введите пароль: ");
        string = scanner.nextLine();
        if (Utils.isPasswordValid(string)) password = string;
        else {
            while (!Utils.isPasswordValid(string)) {
                System.out.print("Введённый Вами пароль не соответствует требованиям. Повторите ввод: ");
                string = scanner.nextLine();
            }
        }

        if (service.loginUser(email, password)) {
            showMainMenu();
        } else showLoginMenu();

    }

    private void showMainMenu() {
        System.out.println("\nДобро пожаловать в главное меню!");
        System.out.println("1. Меню автомобилей");
        System.out.println("2. Меню пользователя");
        System.out.println("3. Меню администратора");
        System.out.println("0. Выход из системы");

        int choice = getSelection();

        while (true) {
            switch (choice) {
                case 0:
                    System.out.println("До свидания!");
                    // Завершить работу приложения
                    System.exit(0);
                case 1:
                    showCarMenu();
                    break;
                case 2:
                    showUserMenu();
                    break;
                case 3:
                    showAdminMenu();
                    break;
                default:
                    System.out.println("Введённое Вами число некорректно. Повторите ввод:");
                    choice = getSelection();
                    break;
            }
        }

    }

    private void showCarMenu() {

        System.out.println("\nМеню автомобилей");
        System.out.println("1. Показать список всех автомобилей");
        System.out.println("2. Показать список автомобилей, доступных для аренды");
        System.out.println("3. Взять автомобиль в аренду");
        System.out.println("4. Вернуть автомобиль из аренды");
        System.out.println("0. Вернуться в предыдущее меню");

        int choice = getSelection();

        while (true) {
            switch (choice) {
                case 0:
                    showMainMenu();
                    break;
                case 1:
                    System.out.println("\n" + service.getAllCars().toString());
                    waitRead();
                    showCarMenu();
                    break;
                case 2:
                    System.out.println("\n" + service.getFreeCars().toString());
                    waitRead();
                    showCarMenu();
                    break;
                case 3:
                    System.out.println("\nУкажите ID автомобиля для аренды:");
                    System.out.println(service.getFreeCars().toString());
                    int id = getSelection();
                    while (!service.takeCar(id)) {
                        System.out.print("Введённый Вами ID некорректен. ");
                        id = getSelection();
                    }
                    System.out.printf("\nАвтомобиль %s успешно взят Вами в аренду. Стоимость аренды составляет " +
                            "%.2f евро в день.", service.getByID(id).getModel(), service.getByID(id).getPrice());
                    waitRead();
                    showCarMenu();
                    break;
                case 4:
                    if (service.getActiveUser().getUserCars().isEmpty()) {
                        System.out.println("\nВ настоящее время у Вас не имеется автомобилей в аренде.");
                        waitRead();
                        showCarMenu();
                        break;
                    }
                    System.out.println("\nУкажите ID автомобиля, который Вы хотите вернуть из аренды:");
                    System.out.println(service.getActiveUser().getUserCars().toString());
                    id = getSelection();
                    while (!service.returnCar(id)) {
                        System.out.print("Введённый Вами ID некорректен. ");
                        id = getSelection();
                    }
                    System.out.printf("\nАвтомобиль %s успешно возвращён Вами из аренды.",
                            service.getByID(id).getModel());
                    waitRead();
                    showCarMenu();
                    break;

                default:
                    System.out.print("Введённое Вами число некорректно. ");
                    choice = getSelection();
                    break;
            }
        }

    }

    private void showUserMenu() {
        System.out.println("Меню пользователя");
        System.out.println("1. Вход в систему");
        System.out.println("2. Регистрация нового пользователя");
        System.out.println("3. Выход из системы");
        System.out.println("4. Показать список автомобилей в аренде");
        System.out.println("5. Показать совокупную арендную плату");
        System.out.println("0. Вернуться в предыдущее меню");

        int choice = getSelection();

        while (true) {
            switch (choice) {
                case 0:
                    showMainMenu();
                    break;
                case 1:
                    service.logout();
                    showLoginMenu();
                    break;
                case 2:
                    service.logout();
                    showAccountCreationMenu();
                    break;
                case 3:
                    service.logout();
                    showWelcomeMenu();
                    break;
                case 4:
                    System.out.println("\nСписок автомобилей в аренде:");
                    System.out.println(service.getActiveUser().getUserCars().toString());
                    waitRead();
                    showUserMenu();
                    break;
                case 5:
                    double totalPrice = 0.0;
                    for (Car car : service.getActiveUser().getUserCars()) {
                        totalPrice += car.getPrice();
                    }
                    System.out.printf("\nСовокупная арендная плата по автомобилям у Вас в аренде составляет %.2f eвро.",
                            totalPrice);
                    waitRead();
                    showUserMenu();
                    break;
                default:
                    System.out.print("Введённое Вами число некорректно. ");
                    choice = getSelection();
                    break;
            }
        }
    }

    private void showAdminMenu() {
        if (service.getActiveUser().getRole() != Role.ADMIN) {
            System.out.print("Доступ запрещён. Вы не являетесь администратором системы.");
            waitRead();
            showMainMenu();
        }
        System.out.println("\nМеню администратора");
        System.out.println("1. Заблокировать пользователя");
        System.out.println("2. Разблокировать пользователя");
        System.out.println("3. Добавить новый автомобиль");
        System.out.println("4. Изменить стоимость аренды автомобиля");
        System.out.println("0. Вернуться в предыдущее меню");

        int choice = getSelection();

        while (true) {
            switch (choice) {
                case 0:
                    showMainMenu();
                    break;
                case 1:
                    System.out.println("\nВыберете пользователя для блокировки:");
                    System.out.println(service.getUsersByRole(Role.ADMIN, Role.USER).toString());

                    System.out.print("Введите электронную почту блокируемого пользователя: ");
                    String email = scanner.nextLine();

                    while (service.getUserByEmail(email) == null) {
                        System.out.print("Введённый Вами адрес некорректен. Повторите ввод: ");
                        email = scanner.nextLine();
                    }
                    service.getUserByEmail(email).setRole(Role.BLOCKED);
                    System.out.printf("\nПользователь %s заблокирован.", email);
                    waitRead();
                    showAdminMenu();
                    break;
                case 2:
                    System.out.println("\nВыберете пользователя для разблокирования:");
                    System.out.println(service.getUsersByRole(Role.BLOCKED).toString());

                    System.out.print("Введите электронную почту разблокируемого пользователя: ");
                    email = scanner.nextLine();

                    while (service.getUserByEmail(email) == null) {
                        System.out.print("Введённый Вами адрес некорректен. Повторите ввод: ");
                        email = scanner.nextLine();
                    }
                    service.getUserByEmail(email).setRole(Role.USER);
                    System.out.printf("\nПользователь %s разблокирован.", email);
                    waitRead();
                    showAdminMenu();
                    break;
                case 3:
                    System.out.println("\nДобавление нового автомобиля");
                    System.out.print("Введите модель: ");
                    String model = scanner.nextLine();
                    int year;
                    double price;

                    while (true) {
                        System.out.print("Введите год выпуска: ");
                        if (!scanner.hasNextInt()) {
                            System.out.println("Вы ввели не год. Повторите ввод.");
                            scanner.nextLine();
                        } else {
                            year = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        }
                    }
                    while (true) {
                        System.out.print("Введите стоимость аренды (в евро): ");
                        if (!scanner.hasNextDouble()) {
                            System.out.println("Стоимость некорректна. Повторите ввод.");
                            scanner.nextLine();
                        } else {
                            price = scanner.nextDouble();
                            scanner.nextLine();
                            break;
                        }
                    }
                    service.addCar(model, year, price);
                    System.out.printf("\nАвтомобиль %s %d г.в. успешно добавлен.", model, year);
                    waitRead();
                    showAdminMenu();
                    break;
                case 4:
                    System.out.println("\nУкажите ID автомобиля для изменения стоимости аренды:");
                    System.out.println(service.getFreeCars().toString());
                    int id = getSelection();
                    while (service.getByID(id) == null) {
                        System.out.print("Введённый Вами ID некорректен. ");
                        id = getSelection();
                    }
                    while (true) {
                        System.out.print("Введите новую стоимость аренды (в евро): ");
                        if (!scanner.hasNextDouble()) {
                            System.out.println("Стоимость некорректна. Повторите ввод.");
                            scanner.nextLine();
                        } else {
                            price = scanner.nextDouble();
                            scanner.nextLine();
                            break;
                        }
                    }
                    service.updateCarPrice(id, price);
                    System.out.printf("\nСтоимость аренды автомобиля %s %d г.в. установлена в сумме %.2f евро в день.",
                            service.getByID(id).getModel(), service.getByID(id).getYear(), price);
                    waitRead();
                    showAdminMenu();
                    break;
                default:
                    System.out.print("Введённое Вами число некорректно. ");
                    choice = getSelection();
                    break;
            }
        }
    }

    private void waitRead() {
        System.out.print("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }

    // Возвращает только введённое число (предотвращая ошибку в случае ввода символов)

    private int getSelection() {
        int selection;
        while (true) {
            System.out.printf("Введите Ваш выбор: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Вы ввели не число. Повторите ввод.");
                scanner.nextLine();
            } else {
                selection = scanner.nextInt();
                scanner.nextLine();
                return selection;
            }
        }
    }

}