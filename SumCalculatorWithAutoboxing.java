import java.util.*;

public class SumCalculatorWithAutoboxing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.print("Enter numbers separated by spaces: ");
        String input = scanner.nextLine();

        String[] tokens = input.split(" ");

        // Autoboxing: converting int to Integer while adding to ArrayList
        for (String token : tokens) {
            try {
                int num = Integer.parseInt(token); // parse string to int
                numbers.add(num); // autoboxing to Integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid number skipped: " + token);
            }
        }

        // Compute sum using unboxing (Integer to int)
        int sum = 0;
        for (Integer number : numbers) {
            sum += number; // unboxing
        }

        System.out.println("Sum of the numbers: " + sum);
        scanner.close();
    }
}
