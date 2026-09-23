import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Formatter;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("ВАРИАНТ 1 (Простые типы) ");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите x (-1 < x <= 1): ");
        double xSimple = scanner.nextDouble();
        System.out.print("Введите k (натуральное число): ");
        int kSimple = scanner.nextInt();

        double resultSimple = SeriesCalculator.calculateSimple(xSimple, kSimple);
        double standardSimple = Math.log(1 + xSimple);

        printResults(resultSimple, standardSimple, kSimple);

        System.out.println("\nВАРИАНТ 2 (BigDecimal) ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Введите x (-1 < x <= 1): ");
            BigDecimal xBig = new BigDecimal(reader.readLine());
            System.out.print("Введите k (натуральное число): ");
            int kBig = Integer.parseInt(reader.readLine());

            BigDecimal resultBig = SeriesCalculator.calculateBig(xBig, kBig);
            double standardBig = Math.log(1 + xBig.doubleValue());

            printResults(resultBig.doubleValue(), standardBig, kBig);

        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
        }
    }

    private static void printResults(double result, double standard, int k) {
        Formatter formatter = new Formatter();
        long rounded = Math.round(result);
        int precision = k + 1;

        formatter.format("Округленное целое (8-ричная, флаг #): %#o%n", rounded);
        formatter.format("Округленное целое (16-ричная, флаг #): %#x%n", rounded);

        String format0 = "%+015." + precision + "f";
        String formatMinus = "%-15." + precision + "f";

        formatter.format("Результат ряда (флаги + и 0): " + format0 + "%n", result);
        formatter.format("Стандартное значение (флаг -): [" + formatMinus + "] <-- конец поля%n", standard);
        formatter.format("Погрешность: %e%n", Math.abs(result - standard));

        System.out.print(formatter);
        formatter.close();
    }
}
