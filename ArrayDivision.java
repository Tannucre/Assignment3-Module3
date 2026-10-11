import java.util.Scanner;

public class ArrayDivision {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Array: ");
            String input = sc.nextLine();

            // Convert string elements into integer array
            String[] values = input.split(",");
            int[] array = new int[values.length];

            for (int i = 0; i < values.length; i++) {
                array[i] = Integer.parseInt(values[i].trim());
            }

            System.out.print("Enter index: ");
            int index = sc.nextInt();

            System.out.print("Enter divisor: ");
            int divisor = sc.nextInt();

            int result = array[index] / divisor;

            System.out.println("Result: " + result);
        }

        catch (ArithmeticException e) {
            System.out.println("Division by zero not allowed");
        }

        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception: Array index out of bounds");
        }

        sc.close();
    }
}