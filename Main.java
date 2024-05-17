import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(SpecialFunctions.erf(1));
        
        //CSVFile csvfile = new CSVFile(new File("HHData.csv"));

        //ArrayList<String> X = csvfile.getDataColumn("famsize");
        //ArrayList<String> Y = csvfile.getDataColumn("ressize");

        //System.out.println(Operations.covariance(X, Y));
        //System.out.println(Operations.median(X));
    }

}