import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "/Users/h/IdeaProjects/lab2_pp/input.txt";
        String outputFileName = "output.txt";

        //try (BufferedWriter bw = new BufferedWriter(new FileWriter(inputFileName))) {
            //bw.write("1A-2F-09 12-30-45  01-02-03 02-02-02 ijflkefknfnk -extra -small short-text\n");
            //bw.write("- \n");
        //} catch (IOException e) {
          //  System.err.println("Ошибка создания файла: " + e.getMessage());
        //}

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {

            String firstLine = br.readLine();
            String secondLine = br.readLine();

            if (firstLine == null || secondLine == null) return;
            Integer[] hexNumbers = TextProcessor.findHexNumbers(firstLine, secondLine);

            String[] allTimes = TextProcessor.findAllTimes(firstLine);

            String[] sortedTokens = TextProcessor.sortOtherTokens(firstLine, secondLine);

            String processedResult = TextProcessor.processString(firstLine, secondLine);

            String outHex = "Найденные 16-ричные числа: " + Arrays.toString(hexNumbers);
            String outTimes = "Все найденные метки времени: " + Arrays.toString(allTimes);
            String outSorted = "Отсортированные лексемы: " + Arrays.toString(sortedTokens);
            String outText = "Результат обработки строки: " + processedResult;

            System.out.println(outHex);
            System.out.println(outTimes);
            System.out.println(outSorted);
            System.out.println(outText);

            bw.write(outHex); bw.newLine();
            bw.write(outTimes); bw.newLine();
            bw.write(outSorted); bw.newLine();
            bw.write(outText); bw.newLine();

        } catch (IOException e) {
            System.err.println("Ошибка работы с файлами: " + e.getMessage());
        }
    }
}
