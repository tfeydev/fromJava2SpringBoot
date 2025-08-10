package br.com.techthor.hashsets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        var books = new HashSet<>(List.of("Computer Software", "Computer Hardware"));

        books.add("Demo book");
        books.add("Harry and his friends");
        books.add("Eric takes on photography");
        books.add("Chád loves Java");
        books.add("Always be learning");

//        books.remove("Eric takes on photography");

          /*Does not do anything*/
          //books.remove(0);

//        for (String book: books) {
//            System.out.println(book);
//        }

        System.out.println(books.contains("Harry and his friends"));

        System.out.println(books.size());

        books.clear();

        System.out.println(books.isEmpty());

    }
}
