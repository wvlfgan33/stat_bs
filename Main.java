import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //System.out.println(SpecialFunctions.erf(BigDecimal.valueOf(2)));
        
    
        CSVFile csvfile = new CSVFile(new File("HHData.csv"));

        ArrayList<String> X = csvfile.getDataColumn("famsize");
        ArrayList<String> Y = csvfile.getDataColumn("ressize");

        System.out.println(Operations.linearRegresssionCoefficients(X, Y)[0]);
        System.out.println(Operations.linearRegresssionCoefficients(X, Y)[1]);
        System.out.println(Operations.rSquared(X, Y));
        //System.out.println(Operations.median(X));
    }

}