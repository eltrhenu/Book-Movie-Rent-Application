package rentstore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataAccessLayer {
	/**
	 * To read file
	 * @param file 
	 * @return return all the lines in the file
	 */
    public ArrayList<String> readFile(String file){
        ArrayList<String> tempArray = new ArrayList<String>();
        String temp;
        try {
            BufferedReader flatFile = new BufferedReader(new FileReader(file));
            while ( (temp = flatFile.readLine()) != null){
                tempArray.add(temp);
            }
            return tempArray;

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
