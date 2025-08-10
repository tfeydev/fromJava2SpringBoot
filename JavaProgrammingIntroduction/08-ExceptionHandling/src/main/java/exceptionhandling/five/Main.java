package exceptionhandling.five;

import javax.annotation.processing.Filer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = null;

        try {
            // Connect to a new file
            fileReader = new FileReader("storys.txt");

            // -1 means the end of file
            int character = 0;
            while ((character = fileReader.read()) != -1) {
                System.out.println((char) character);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }

}
