package util;

import model.Transport;
import storage.Storage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FileManager {

    public static void loadFromFile(String filename, Storage storage) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Файл данных не найден. Будет создан новый при сохранении.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                try {
                    Transport t = parseTransport(line);
                    storage.add(t);
                } catch (Exception e) {
                    System.err.println("[ОШИБКА ВАЛИДАЦИИ СТРОКИ]: " + e.getMessage() + " -> Строка: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public static void saveToFile(String filename, Storage storage) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Transport t : storage.getAllAsList()) {
                bw.write(t.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    private static Transport parseTransport(String line) throws Exception {
        String[] parts = line.split(";");
        if (parts.length < 7) {
            throw new Exception("Недостаточно полей данных (требуется 7)");
        }

        int id = Integer.parseInt(parts[0].trim());
        String type = parts[1].trim();
        if (!type.equalsIgnoreCase("Суша") && !type.equalsIgnoreCase("Вода") && !type.equalsIgnoreCase("Воздух")) {
            throw new Exception("Неверный тип перевозки (допустимы: Суша, Вода, Воздух)");
        }

        String model = parts[2].trim();
        int power = Integer.parseInt(parts[3].trim());
        double maxSpeed = Double.parseDouble(parts[4].trim());

        LocalDate date;
        try {
            date = LocalDate.parse(parts[5].trim());
        } catch (DateTimeParseException e) {
            throw new Exception("Неверный формат даты. Используйте ГГГГ-ММ-ДД");
        }

        double price = Double.parseDouble(parts[6].trim());

        if (power < 0 || maxSpeed < 0 || price < 0) {
            throw new Exception("Числовые значения не могут быть отрицательными");
        }

        return new Transport(id, type, model, power, maxSpeed, date, price);
    }
}
