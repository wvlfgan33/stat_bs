
public class SpecialFunctions {

    /**
     * Calculates the error function erf(x)
     * @param x a number
     * @return erf(x)
     */
    public static double erf(double x) {
        final int p = 10; //precision
        final double scale = 2/Math.sqrt(Math.PI);
        double result = 0;

        for (int i=0; i<=p; i++) {
            result += Math.pow(-1,i)*Math.pow(x,2*i+1)/(factorial(i)*(2*i+1));
        }
        
        return scale*result;
    }
    
    /**
     * Calculates the factorial of n.
     * @param n A nonnegative integer.
     * @return The number n!, n factorial.
     */
    public static int factorial(int n) {
        if (n==0) {
            return 1;
        }
        if (n <= 2) {
            return n;
        }
        return n*factorial(n-1);
    }

}