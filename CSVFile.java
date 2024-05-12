import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CSVFile {
    private File file;
    private ArrayList<ArrayList<String>> data;

    /**
     * Constructor. 
     * @param file The CSV file to be read whose data will then be converted into an ArrayList of ArrayLists.
     * @throws FileNotFoundException 
     */
    public CSVFile(File file) throws FileNotFoundException {
        this.file = file;
        this.data = CSVReader.read(file);
    }

    /**
     * Returns the column based on its position going from left to right (indexing starts at 0).
     * @param index The index of the desired column.
     * @return The column of the CSV file with the given index.
     */
    public ArrayList<String> getColumn(int index) {
        return this.data.get(index);
    }

    /**
     * Returns a column from a CSV file whose header matches the given input.
     * @param name The supposed name of the column in a CSV file that is to be returned.
     * @return The column whose header matches the input. This returns an empty list of such a column with the given name cannot be found.
     */
    public ArrayList<String> getColumn(String name) {
        for (ArrayList<String> lists : this.data) {
            if (lists.get(0).equals(name)) {
                return lists;
            }
        }
        return new ArrayList<String>();  
    }

    public ArrayList<String> getDataColumn(int index) {
        ArrayList<String> output = getColumn(index);
        output.remove(0);
        return output;
    }

    public ArrayList<String> getDataColumn(String name) {
        ArrayList<String> output = getColumn(name);
        output.remove(0);
        return output;
    }

    /**
     * Changes the file to be read and consequently the data that is to be manipulated.
     * @param file 
     * @throws FileNotFoundException 
     */
    public void setFileAndData(File file) throws FileNotFoundException {
        this.file = file;
        this.data = CSVReader.read(file);
    }

}