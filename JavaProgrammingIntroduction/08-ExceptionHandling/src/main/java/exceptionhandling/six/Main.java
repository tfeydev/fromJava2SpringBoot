package exceptionhandling.six;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("story.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
