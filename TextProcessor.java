import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.Arrays;


public class TextProcessor {

    private static final Pattern TIME_PATTERN = Pattern.compile("\\b\\d{2}-\\d{2}-\\d{2}\\b");

    private static SimpleDateFormat getStrictTimeFormatter() {
        SimpleDateFormat sdf = new SimpleDateFormat("mm-HH-ss");
        sdf.setLenient(false);
        return sdf;
    }

    public static Integer[] findHexNumbers(String firstLine, String delimiters) {
        ArrayList<Integer> hexNumbers = new ArrayList<>();

        String regexDelim = "[" + Pattern.quote(delimiters) + "]+";
        String[] tokens = firstLine.split(regexDelim);

        for (String token : tokens) {
            if (token.isEmpty()) continue;
            try {
                int val = Integer.parseInt(token, 16);
                hexNumbers.add(val);
            } catch (NumberFormatException e) {
            }
        }
        return hexNumbers.toArray(new Integer[0]);
    }

    public static String[] findAllTimes(String firstLine) {
        ArrayList<String> foundTimes = new ArrayList<>();
        Matcher matcher = TIME_PATTERN.matcher(firstLine);
        SimpleDateFormat sdf = getStrictTimeFormatter();

        while (matcher.find()) {
            String timeCandidate = matcher.group();
            try {
                sdf.parse(timeCandidate);
                foundTimes.add(timeCandidate);
            } catch (ParseException ignored) {
            }
        }
        return foundTimes.toArray(new String[0]);
    }

    public static String[] sortOtherTokens(String firstLine, String delimiters) {
        ArrayList<String> otherTokensList = new ArrayList<>();
        String regexDelim = "[" + Pattern.quote(delimiters) + "]+";
        String[] tokens = firstLine.split(regexDelim);

        Pattern timePattern = Pattern.compile("\\b\\d{2}-\\d{2}-\\d{2}\\b");
        SimpleDateFormat sdf = new SimpleDateFormat("mm-HH-ss");
        sdf.setLenient(false);

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            try {
                Integer.parseInt(token, 16);
                continue;
            } catch (NumberFormatException ignored) {}

            Matcher matcher = timePattern.matcher(token);
            if (matcher.matches()) {
                try {
                    sdf.parse(token);
                    continue; // Если валидное время, пропускаем
                } catch (ParseException ignored) {}
            }

            otherTokensList.add(token);
        }

        String[] otherTokensArray = otherTokensList.toArray(new String[0]);


        Comparator<String> stringLengthComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int lengthCompare = Integer.compare(s1.length(), s2.length());

                if (lengthCompare != 0) {
                    return lengthCompare;
                }
                return s1.compareTo(s2);
            }
        };

        Arrays.sort(otherTokensArray, stringLengthComparator);

        return otherTokensArray;
    }

    public static String processString(String firstLine, String delimiters) {
        StringBuilder sb = new StringBuilder(firstLine);
        SimpleDateFormat sdf = getStrictTimeFormatter();

        String timeToken = null;
        int timeTokenIndexInOriginal = -1;

        Matcher matcher = TIME_PATTERN.matcher(firstLine);
        while (matcher.find()) {
            String token = matcher.group();
            try {
                sdf.parse(token);
                timeToken = token;
                timeTokenIndexInOriginal = matcher.start();
                break;
            } catch (ParseException ignored) {}
        }

        Random rand = new Random();
        int randomNumber = rand.nextInt(100);
        String insertStr = String.valueOf(randomNumber);

        if (timeToken != null) {
            int insertIndex = timeTokenIndexInOriginal + timeToken.length();
            sb.insert(insertIndex, insertStr);
        } else {
            sb.insert(sb.length() / 2, insertStr);
        }

        String currentStr = sb.toString();
        int minLength = Integer.MAX_VALUE;
        int targetStart = -1;
        int targetEnd = -1;

        for (int i = 0; i < currentStr.length(); i++) {
            if (currentStr.charAt(i) == '-') {
                for (int j = i + 1; j <= currentStr.length(); j++) {
                    int len = j - i;
                    if (len < minLength) {
                        minLength = len;
                        targetStart = i;
                        targetEnd = j;
                    }
                }
            }
        }

        if (targetStart != -1) {
            sb.delete(targetStart, targetEnd);
        }

        return sb.toString();
    }
}
