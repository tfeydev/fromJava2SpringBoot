package exceptionhandling.one;

public class Main {

    public static void main(String[] args) {

        String numberString = "luv2code";

        try {
            int numberInt = Integer.parseInt(numberString);
            System.out.println(numberString);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
