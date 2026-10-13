package main;

import model.Transport;
import storage.ListStorage;
import storage.MapStorage;
import storage.Storage;
import util.FileManager;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "/Users/h/IdeaProjects/lab3_pp/src/transports.txt";
    private static final Storage storage = new ListStorage();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FileManager.loadFromFile(FILE_NAME, storage);
        runMenu();
    }

    private static void runMenu() {
        while (true) {
            System.out.println("\n--- МЕНЮ КОМПАНИИ ПЕРЕВОЗОК ---");
            System.out.println("1. Вывести весь транспорт");
            System.out.println("2. Добавить новый транспорт");
            System.out.println("3. Редактировать транспорт");
            System.out.println("4. Удалить транспорт");
            System.out.println("5. Сортировка по Модели");
            System.out.println("6. Сортировка по Цене");
            System.out.println("7. Сохранить изменения в файл");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> storage.printAll();
                case "2" -> handleCreate();
                case "3" -> handleUpdate();
                case "4" -> handleDelete();
                case "5" -> {
                    // Компаратор через лямбду по модели (п. 11)
                    storage.sort((t1, t2) -> t1.getModel().compareToIgnoreCase(t2.getModel()));
                    System.out.println("Данные отсортированы по модели.");
                    storage.printAll();
                }
                case "6" -> {
                    // Компаратор через лямбду по цене (п. 11)
                    storage.sort((t1, t2) -> Double.compare(t1.getPrice(), t2.getPrice()));
                    System.out.println("Данные отсортированы по цене.");
                    storage.printAll();
                }
                case "7" -> {
                    FileManager.saveToFile(FILE_NAME, storage);
                    System.out.println("Данные успешно сохранены в " + FILE_NAME);
                }
                case "0" -> {
                    System.out.println("Завершение работы.");
                    return;
                }
                default -> System.out.println("Неверный пункт меню!");
            }
        }
    }

    // Консольный ввод CUD операций (п. 9)
    private static void handleCreate() {
        try {
            System.out.print("Введите ID (число): ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Введите тип (Суша/Вода/Воздух): ");
            String type = scanner.nextLine();
            System.out.print("Введите модель: ");
            String model = scanner.nextLine();
            System.out.print("Введите мощность (л.с.): ");
            int power = Integer.parseInt(scanner.nextLine());
            System.out.print("Введите макс. скорость: ");
            double speed = Double.parseDouble(scanner.nextLine());
            System.out.print("Введите дату выпуска (ГГГГ-ММ-ДД): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Введите цену: ");
            double price = Double.parseDouble(scanner.nextLine());

            Transport transport = new Transport(id, type, model, power, speed, date, price);
            storage.add(transport);
            System.out.println("Транспорт успешно добавлен!");
        } catch (Exception e) {
            System.out.println("Ошибка ввода данных! Операция отменена. " + e.getMessage());
        }
    }

    private static void handleUpdate() {
        try {
            System.out.print("Введите ID транспорта для редактирования: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Введите новый тип (Суша/Вода/Воздух): ");
            String type = scanner.nextLine();
            System.out.print("Введите новую модель: ");
            String model = scanner.nextLine();
            System.out.print("Введите новую мощность: ");
            int power = Integer.parseInt(scanner.nextLine());
            System.out.print("Введите новую макс. скорость: ");
            double speed = Double.parseDouble(scanner.nextLine());
            System.out.print("Введите новую дату выпуска (ГГГГ-ММ-ДД): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Введите новую цену: ");
            double price = Double.parseDouble(scanner.nextLine());

            Transport transport = new Transport(id, type, model, power, speed, date, price);
            storage.update(transport);
            System.out.println("Данные транспорта обновлены!");
        } catch (Exception e) {
            System.out.println("Ошибка изменения данных! " + e.getMessage());
        }
    }

    private static void handleDelete() {
        try {
            System.out.print("Введите ID для удаления: ");
            int id = Integer.parseInt(scanner.nextLine());
            storage.delete(id);
            System.out.println("Транспорт с ID " + id + " удален из оперативной памяти.");
        } catch (Exception e) {
            System.out.println("Некорректный ID.");
        }
    }
}
