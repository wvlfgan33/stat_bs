import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

public class Operations {

    private static final int N = 6;

    //ASSUME THAT THE NUMERICAL DATA STARTS AT THE SECOND COLUMN AFTER THE COLUMN HEADERS.
    
    /**
     * Returns the mean of a set of numbers in a column of a CSV file.
     * @param data Data read from a CSV file using the CSVReader class.
     * @return The arithmetic mean (the average) of the data provided.
     */
    public static BigDecimal mean(ArrayList<String> data) {
        BigDecimal sum = BigDecimal.ZERO;
        int n = data.size();
        for (int i=0; i<n; i++) {
            sum = sum.add(BigDecimal.valueOf(Double.parseDouble(data.get(i))));
        }
        return sum.divide(BigDecimal.valueOf(n), N, RoundingMode.HALF_UP);
    }


    /**
     * Gets the covariance of X and Y, where Y is a variable that is suspected to depend on X.
     * @param X Data read from a CSV file representing an independent variable.
     * @param Y Data read from a CSV file that is suspected to depend on X.
     * @return The covariance Cov(X,Y)
     */
    public static BigDecimal covariance(ArrayList<String> X, ArrayList<String> Y) {
        int n = X.size();

        BigDecimal xBar = mean(X);
        BigDecimal yBar = mean(Y);
        BigDecimal numerator = BigDecimal.ZERO;

        for (int i=0; i<n; i++) {
            BigDecimal A = (BigDecimal.valueOf(Double.parseDouble(X.get(i)))).subtract(xBar);
            BigDecimal B = (BigDecimal.valueOf(Double.parseDouble(Y.get(i)))).subtract(yBar);
            numerator = numerator.add(A.multiply(B));
        }

        return numerator.divide(BigDecimal.valueOf(n-1), N, RoundingMode.HALF_UP);
    }


    /**
     * Returns the Pearson correlation coefficient of two variables X, Y, where Y is suspected to depend on X.
     * @param X Data read from a CSV file representing the variable X.
     * @param Y Data read from a CSV file representing the variable Y.
     * @return 
     */
    public static BigDecimal pearsonCorrelation(ArrayList<String> X, ArrayList<String> Y) {
        int n = X.size();

        BigDecimal xBar = mean(X);
        BigDecimal yBar = mean(Y);

        BigDecimal numerator = BigDecimal.ZERO;
        BigDecimal denominator1 = BigDecimal.ZERO;
        BigDecimal denominator2 = BigDecimal.ZERO;

        for (int i=0; i<n; i++) {
            BigDecimal A = (BigDecimal.valueOf(Double.parseDouble(X.get(i)))).subtract(xBar);
            BigDecimal B = (BigDecimal.valueOf(Double.parseDouble(Y.get(i)))).subtract(yBar);
            
            numerator = numerator.add(A.multiply(B));
            denominator1 = denominator1.add(A.pow(2));
            denominator2 = denominator2.add(B.pow(2));
        }

        BigDecimal actualDenominator = (denominator1.multiply(denominator2)).sqrt(MathContext.DECIMAL128);
        return numerator.divide(actualDenominator, N, RoundingMode.HALF_UP);
    }


   /**
    * Provides the coefficients of the equation of the line that approximates the relationship between two variables.
    * @param X Data read from a column of a CSV file representing the independent variable.
    * @param Y Data read from a column of a CSV file representing the probably dependent variable.
    * @return An array of two numbers where the number at index 0 is the X-intercept of the regression line and the number at index 1 is the coefficient of X in the regression line.
    */
   
    public static BigDecimal[] linearRegresssionCoefficients(ArrayList<String> X, ArrayList<String> Y) { 
        int n = X.size();

        BigDecimal sumOfY = BigDecimal.ZERO;
        BigDecimal sumOfX = BigDecimal.ZERO;
        BigDecimal sumOfXSquared = BigDecimal.ZERO;
        BigDecimal sumOfXTimesY = BigDecimal.ZERO;

        for (int j=0; j<n; j++) {
            sumOfY = sumOfY.add(BigDecimal.valueOf(Double.parseDouble(Y.get(j))));
        }

        for (int i=0; i<n; i++) {
            sumOfX = sumOfX.add(BigDecimal.valueOf(Double.parseDouble(X.get(i))));
            sumOfXSquared = sumOfXSquared.add((BigDecimal.valueOf(Double.parseDouble(X.get(i)))).pow(2));
        }

        for (int k=0; k<n; k++) {
            sumOfXTimesY = sumOfXTimesY.add((BigDecimal.valueOf(Double.parseDouble(X.get(k)))).multiply(BigDecimal.valueOf(Double.parseDouble(Y.get(k)))));
        }

        BigDecimal a1 = (sumOfY.multiply(sumOfXSquared)).subtract(sumOfX.multiply(sumOfXTimesY));
        BigDecimal a2 = (BigDecimal.valueOf(n).multiply(sumOfXSquared)).subtract(sumOfX.pow(2));
        BigDecimal a = a1.divide(a2, N, RoundingMode.HALF_UP); //x-intercept

        BigDecimal b1 = (BigDecimal.valueOf(n).multiply(sumOfXTimesY)).subtract(sumOfX.multiply(sumOfY)); 
        BigDecimal b2 = (BigDecimal.valueOf(n).multiply(sumOfXSquared)).subtract(sumOfX.pow(2));
        BigDecimal b = b1.divide(b2, N, RoundingMode.HALF_UP); //coefficient of X

        return new BigDecimal[]{a,b};
    }
    

    /**
     * Gets the coefficient of determination (r-squared) of X and Y, the data of both of which are read from a CSV file.
     * @param X Data read from a column of a CSV file representing the independent variable.
     * @param Y Data read from a column of a CSV file representing the probably dependent variable.
     * @return The coefficient of determination.
     */
    
    public static BigDecimal rSquared(ArrayList<String> X, ArrayList<String> Y) { //Y against X
        int n = X.size();
        
        BigDecimal yBar = mean(Y);
        BigDecimal betaZero = linearRegresssionCoefficients(X,Y)[0];
        BigDecimal betaOne = linearRegresssionCoefficients(X,Y)[1];

        BigDecimal SSres = BigDecimal.ZERO;
        BigDecimal SStot = BigDecimal.ZERO;

        for (int i=0; i<n; i++) {
            BigDecimal yi = BigDecimal.valueOf(Double.parseDouble(Y.get(i)));
            BigDecimal xi = BigDecimal.valueOf(Double.parseDouble(X.get(i)));

            SStot = SStot.add(yi.subtract(yBar).pow(2));
            SSres = SSres.add((yi.subtract(betaZero.add(xi.multiply(betaOne))).pow(2)));
        }

        return BigDecimal.ONE.subtract(SSres.divide(SStot, N, RoundingMode.HALF_UP));
    }
    

    /**
     * This returns the median of a given set of data read from a column of a CSV file.
     * @param data The data of which the median is to be found.
     * @return The median of the given set of data.
     */
    
    public static BigDecimal median(ArrayList<String> data) {
        ArrayList<String> dataToBeSorted = data;
        Collections.sort(dataToBeSorted);

        double result = 0;
        
        if (data.size()%2 == 1) {
            result = Double.parseDouble(dataToBeSorted.get((data.size()+1)/2));
        } else {
            result = (1/2)*(Double.parseDouble(dataToBeSorted.get(data.size()/2)) + Double.parseDouble(dataToBeSorted.get(1 + data.size()/2)));
        }

        return BigDecimal.valueOf(result);
    }
}