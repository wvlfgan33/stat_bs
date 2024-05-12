import java.util.ArrayList;

public class Operations {
    //ASSUME THAT THE NUMERICAL DATA STARTS AT THE SECOND COLUMN AFTER THE COLUMN HEADERS.
    //THIS MEANS THAT THE LENGTH OF A COLUMN INPUT MINUS ONE IS EQUAL TO THE NUMBER OF ACTUAL DATA.

    /**
     * Returns the mean of a set of numbers in a column of a CSV file.
     * @param data Data read from a CSV file using the CSVReader class.
     * @return The arithmetic mean (the average) of the data provided.
     */
    public static double mean(ArrayList<String> data) {
        double sum = 0;
        int n = data.size();
        for (int i=0; i<n; i++) {  
            sum += Double.parseDouble(data.get(i));
        }
        return sum/n;
    }


    /**
     * Gets the covariance of X and Y, where Y is a variable that is suspected to depend on X.
     * @param X Data read from a CSV file representing an independent variable.
     * @param Y Data read from a CSV file that is suspected to depend on X.
     * @return The covariance Cov(X,Y)
     */
    public static double covariance(ArrayList<String> X, ArrayList<String> Y) {
        int n = X.size();
        
        double xBar = mean(X);
        double yBar = mean(Y);
        double numerator = 0;

        for (int i=0; i<n; i++) {
            numerator += (Double.parseDouble(X.get(i)) - xBar)*(Double.parseDouble(Y.get(i)) - yBar);
        }
        return numerator/(n-1);
    }

    /**
     * Returns the Pearson correlation coefficient of two variables X, Y, where Y is suspected to depend on X.
     * @param X Data read from a CSV file representing the variable X.
     * @param Y Data read from a CSV file representing the variable Y.
     * @return 
     */
    public static double pearsonCorrelation(ArrayList<String> X, ArrayList<String> Y) {
        int n = X.size();

        double xBar = mean(X);
        double yBar = mean(Y);
        double numerator = 0;
        double denominator1 = 0;
        double denominator2 = 0;

        for (int i=0; i<n; i++) {
            numerator += (Double.parseDouble(X.get(i)) - xBar)*(Double.parseDouble(Y.get(i)) - yBar);
            denominator1 += (Double.parseDouble(X.get(i)) - xBar)*(Double.parseDouble(X.get(i)) - xBar);
            denominator2 += (Double.parseDouble(Y.get(i)) - yBar)*(Double.parseDouble(Y.get(i)) - yBar);
        }

        return numerator/Math.sqrt(denominator1*denominator2);
    }

   /**
    * Provides the coefficients of the equation of the line that approximates the relationship between two variables.
    * @param X Data read from a column of a CSV file representing the independent variable.
    * @param Y Data read from a column of a CSV file representing the probably dependent variable.
    * @return An array of two numbers where the number at index 0 is the X-intercept of the regression line and the number at index 1 is the coefficient of X in the regression line.
    */
    public static double[] linearRegresssionCoefficients(ArrayList<String> X, ArrayList<String> Y) { 
        int n = X.size();

        double sumOfY = 0;
        double sumOfX = 0;
        double sumOfXSquared = 0;
        double sumOfXTimesY = 0;

        for (int j=0; j<n; j++) {
            sumOfY += Double.parseDouble(Y.get(j));
        }

        for (int i=0; i<n; i++) {
            sumOfX += Double.parseDouble(X.get(i));
            sumOfXSquared += Double.parseDouble(X.get(i))*Double.parseDouble(X.get(i));
        }

        for (int k=0; k<n; k++) {
            sumOfXTimesY += Double.parseDouble(X.get(k))*Double.parseDouble(Y.get(k));
        }

        double a = (sumOfY*sumOfXSquared - sumOfX*sumOfXTimesY)/(n*sumOfXSquared - sumOfX*sumOfX); //x-intercept
        double b = (n*sumOfXTimesY - sumOfX*sumOfY)/(n*sumOfXSquared - sumOfX*sumOfX); //coefficient of X

        return new double[]{a,b};
    }

    /**
     * Gets the coefficient of determination (r-squared) of X and Y, the data of both of which are read from a CSV File.
     * @param X Data read from a column of a CSV file representing the independent variable.
     * @param Y Data read from a column of a CSV file representing the probably dependent variable.
     * @return The coefficient of determination.
     */
    public static double rSquared(ArrayList<String> X, ArrayList<String> Y) { //Y against X
        int n = X.size();
        
        double yBar = mean(Y);
        double betaZero = linearRegresssionCoefficients(X,Y)[0];
        double betaOne = linearRegresssionCoefficients(X,Y)[1];

        double SSres = 0;
        double SStot = 0;

        for (int i=0; i<n; i++) {
            SStot += (Double.parseDouble(Y.get(i)) - yBar)*(Double.parseDouble(Y.get(i)) - yBar);
            SSres += (Double.parseDouble(Y.get(i)) - (betaZero+betaOne*Double.parseDouble(X.get(i))))*(Double.parseDouble(Y.get(i)) - (betaZero+betaOne*Double.parseDouble(X.get(i))));
        }

        return (1 - SSres/SStot);
    }
   



}