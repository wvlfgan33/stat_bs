import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpecialFunctions {

    private static final int N = 10;

    /**
     * Calculates the error function erf(x)
     * @param x a number
     * @return erf(x)
     */
    public static BigDecimal erf(BigDecimal x) {
        final int p = 1000; //precision--the bigger the better.
        final BigDecimal s = BigDecimal.valueOf(2/Math.sqrt(Math.PI)); //a scaling factor
        BigDecimal result = BigDecimal.valueOf(0);

        for (int i=0; i<=p; i++) {
            BigDecimal numerator = (BigDecimal.valueOf(-1).pow(i)).multiply(x.pow(2*i+1));
            BigDecimal denominator = ((BigDecimal.valueOf(2).multiply(BigDecimal.valueOf(i))).add(BigDecimal.ONE)).multiply(factorial(i));
            result = result.add(numerator.divide(denominator, N, RoundingMode.HALF_UP));
        }
        return result.multiply(s);
    }
    
    /**
     * Calculates the factorial of n.
     * @param n A nonnegative integer.
     * @return The number n!, n factorial. In BigDecimal.
     */
    public static BigDecimal factorial(int n) {
        if (n==0) {
            return BigDecimal.ONE;
        }
        if (n <= 2) {
            return BigDecimal.valueOf(n);
        }
        return (BigDecimal.valueOf(n)).multiply(factorial(n-1));
    }

}