package exceptionhandling.two;

public class Main {

    public static void main(String[] args) {

        parseString(null);

    }

    public static void parseString(String numberString) {

        try {
            System.out.println(numberString.length());
            int numberInt = Integer.parseInt(numberString);
            System.out.println(numberString);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("String needs to be a valid int");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
