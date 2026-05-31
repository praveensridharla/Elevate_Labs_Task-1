import java.util.Scanner;

/**
 * Java Console Calculator
 * Supports addition, subtraction, multiplication, and division.
 * Runs in a loop until the user chooses to exit.
 */
public class Java_Console_Calculator {

    // ── Arithmetic methods ──────────────────────────────────────────────────

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides a by b.
     * Throws ArithmeticException if b is zero to avoid undefined results.
     */
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return a / b;
    }

    // ── Helper: print a styled separator line ───────────────────────────────

    private static void printLine() {
        System.out.println("------------------------------------------");
    }

    // ── Main program ────────────────────────────────────────────────────────

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("==========================================");
        System.out.println("       JAVA CONSOLE CALCULATOR");
        System.out.println("==========================================");

        while (running) {
            // Display menu
            System.out.println("\nSelect an operation:");
            System.out.println("  1. Addition       (+)");
            System.out.println("  2. Subtraction    (-)");
            System.out.println("  3. Multiplication (*)");
            System.out.println("  4. Division       (/)");
            System.out.println("  5. Exit");
            printLine();
            System.out.print("Enter your choice (1-5): ");

            // Read menu choice
            String choiceInput = scanner.nextLine().trim();

            // Handle exit early
            if (choiceInput.equals("5")) {
                System.out.println("\nThank you for using the calculator.");
                running = false;
                break;
            }

            // Validate menu choice
            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            // Read the two operands
            double num1, num2;
            try {
                System.out.print("Enter first number  : ");
                num1 = Double.parseDouble(scanner.nextLine().trim());

                System.out.print("Enter second number : ");
                num2 = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number entered. Please try again.");
                continue;
            }

            // Perform calculation and display result
            printLine();
            try {
                double result;
                String operationName;
                char operatorSymbol;

                switch (choice) {
                    case 1:
                        result        = add(num1, num2);
                        operationName = "Addition";
                        operatorSymbol = '+';
                        break;
                    case 2:
                        result        = subtract(num1, num2);
                        operationName = "Subtraction";
                        operatorSymbol = '-';
                        break;
                    case 3:
                        result        = multiply(num1, num2);
                        operationName = "Multiplication";
                        operatorSymbol = '*';
                        break;
                    case 4:
                        result        = divide(num1, num2);
                        operationName = "Division";
                        operatorSymbol = '/';
                        break;
                    default:
                        System.out.println("Unexpected error. Please try again.");
                        continue;
                }

                // Format: avoid unnecessary decimal places for whole numbers
                String formattedResult = (result == Math.floor(result) && !Double.isInfinite(result))
                        ? String.valueOf((long) result)
                        : String.valueOf(result);

                System.out.printf("  %s: %.2f %c %.2f = %s%n",
                        operationName, num1, operatorSymbol, num2, formattedResult);

            } catch (ArithmeticException e) {
                System.out.println("  Error: " + e.getMessage());
            }

            printLine();
        }

        scanner.close();
    }
}