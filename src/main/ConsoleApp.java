package main;
import util.DatabaseUtil;
import java.util.Scanner;

public class ConsoleApp {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a binary number (or type 'exit' to quit): ");
            String binaryInput = scanner.nextLine();

            if ("exit".equalsIgnoreCase(binaryInput)) {
                System.out.println("Goodbye!");
                break;
            }
            
            if (isValidBinary(binaryInput)) {
                int decimalOutput = Integer.parseInt(binaryInput, 2);
                System.out.println("Decimal Value: " + decimalOutput);
                DatabaseUtil.saveConversion(binaryInput, decimalOutput);
            } else {
                System.out.println("Invalid binary number! Try again.");
            }
        }
        scanner.close();
    }

    private static boolean isValidBinary(String binary) {
        return binary.matches("[01]+");
    }
}