package exceptionhandling.four;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        try {
//            FileReader fileReader = new FileReader("file.txt");
//        }
//        catch (FileNotFoundException e) {
//            System.out.println("File does not exist");
//        }

        FileReader fileReader = new FileReader("file.txt");

//        try {
//            readFile();
//        } catch (FileNotFoundException e) {
//            System.out.println(e);
//        }

    }

    public static void readFile() throws FileNotFoundException {
        FileReader fileReader = new FileReader("file.txt");
    }
}
