import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SeriesCalculator {

    public static double calculateSimple(double x, int k) {
        double eps = Math.pow(10, -k);
        double sum = 0;
        double term = x;
        int i = 1;

        while (Math.abs(term) >= eps) {
            sum += term;
            i++;
            term = -term * x * (i - 1) / i;
        }
        return sum;
    }

    public static BigDecimal calculateBig(BigDecimal x, int k) {
        MathContext mc = new MathContext(k + 10, RoundingMode.HALF_UP);
        BigDecimal eps = BigDecimal.ONE.divide(BigDecimal.TEN.pow(k), mc);

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal term = x;

        BigDecimal i = BigDecimal.ONE;
        BigDecimal multiplier;

        while (term.abs().compareTo(eps) >= 0) {
            sum = sum.add(term, mc);
            i = i.add(BigDecimal.ONE);

            // term = -term * x * (i - 1) / /
            multiplier = i.subtract(BigDecimal.ONE)
                    .divide(i, mc)
                    .multiply(x, mc)
                    .negate();

            term = term.multiply(multiplier, mc);
        }
        return sum;
    }
}
