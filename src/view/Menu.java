package view;

import model.User;
import service.MainService;

import java.util.Scanner;

/**
 * @author Sergey Bugaenko
 * {@code @date} 25.10.2024
 */

public class Menu {

    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

    private final static String COLOR_BLACK = "\u001B[0m";

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_CYAN = "\u001B[36m";

    public static final String COLOR_WHITE = "\u001B[37m";


    public Menu(MainService service) {
        this.service = service;
    }

    public void run() {
        showMenu();
    }

    private void showMenu() {
        while (true) {
            System.out.println(Color.GREEN + "Добро пожаловать в меню" + Color.RESET);
            System.out.println("1. Меню автомобилей");
            System.out.println("2. Меню пользователей");
            System.out.println("3. Меню администратора");
            System.out.println("0. Выход из системы");
            System.out.println(Color.YELLOW + "\nВведите пункт меню:" + Color.RESET);

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("До свидания!");
                // Завершить работу приложения
                System.exit(0);
            }

            showSubMenu(choice);
        }
    }

    private void showSubMenu(int choice) {
        switch (choice) {
            case 1:
                //Todo
                // showCarMenu();
                break;
            case 2:
                showUserMenu();
                break;
            case 3:
                //Todo
                // showAdminMenu();
                break;
            default:
                System.out.println(Color.RED + "Сделайте корректный выбор\n" + Color.RESET);
        }
    }

    private void showUserMenu() {
        while (true) {
            System.out.println(Color.GREEN + "Меню пользователя" + Color.RESET);
            System.out.println("1. Вход в систему");
            System.out.println("2. Регистрация нового пользователя");
            System.out.println("3. Logout");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.println("\nСделайте выбор пункта меню");
            int input = scanner.nextInt();
            scanner.nextLine();
            // прервать текущий цикл
            if (input == 0) break;

            handleUserMenuChoice(input);

        }
    }

    private void handleUserMenuChoice(int input) {
        switch (input) {
            case 1:
                //Авторизацию
                //Todo написать авторизацию
                System.out.println("Метод в разработке. Приходите завтра");
                waitRead();
                break;
            case 2:
                // Регистрацию
                System.out.println("Регистрация нового пользователя");
                System.out.println("Введите email:");
                String email = scanner.nextLine();

                System.out.println("Введите пароль");
                String password = scanner.nextLine();

                User user = service.registerUser(email, password);

                if (user != null) {
                    System.out.println("Вы успешно зарегистрировались в системе");
                } else {
                    System.out.println("Регистрация провалена!");
                }

                waitRead();

                break;
            case 3:
                // logout
                service.logout();
                System.out.println("Вы вышли из системы");
                waitRead();
                break;
            default:
                System.out.println("\nНе верный ввод");
        }
    }

    private void waitRead() {
        System.out.println(Color.YELLOW + "\nДля продолжения нажмите Enter..." + Color.RESET);
        scanner.nextLine();
    }


}
