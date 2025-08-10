public class MethodReturnDataDemo {

    public static void main(String[] args) {

        int val = 100;
        int output = summation(val);
        System.out.println("Summation of " + val + " is " + output);

    }

    // define method version 2
    static int summation(int num) {

        int result = num * (num + 1) / 2;

        return result;

    }

    // define method version 1
/*    static int summation(int num) {

        int result = 0;

        for (int i = 1; i <= num; i++) {

            result += i;
        }

        return result;

    }*/

}
