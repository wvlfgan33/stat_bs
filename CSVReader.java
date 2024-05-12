import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {
    /**
     * This converts a CSV file into an ArrayList of ArrayLists (of Strings), with each ArrayList representing a column of data.
     * @param file The CSV file to be read.
     * @return The ArrayList of ArrayLists of Strings whose data can then be manipulated with Java code.
     * @throws FileNotFoundException 
     */
    public static ArrayList<ArrayList<String>> read(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        
        String[] firstDataValues = input.nextLine().split(","); //Initialize the ArrayLists
        for (int i=0; i<firstDataValues.length; i++) {
            output.add(new ArrayList<String>());
            output.get(i).add(firstDataValues[i]);
        }

        while (input.hasNextLine()) {
            String[] otherDataValues = input.nextLine().split(",");
            for (int i=0; i<output.size(); i++) {
                output.get(i).add(otherDataValues[i]);
            }
        }

        return output;
    }
}