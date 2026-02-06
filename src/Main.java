import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        Scanner scanner = new Scanner(System.in);
        String menu = String.format("%n1. Add Expense%n2. View Transaction%n3. View Total%n4. Exit");

        while (true) {
            int choice = -1;
            System.out.println(menu);
            System.out.print("Choose: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a number (1–4)");
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    try {
                        Transaction expense = getExpenseFromUser(scanner);
                        accountManager.addTransaction(expense);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Amount must be positive");
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Please provide appropriate input type");
                        scanner.nextLine();
                    }
                    break;

                case 2:
                    System.out.printf("%nTRANSACTIONS%n");
                    accountManager.displayTransactions();
                    break;

                case 3:
                    System.out.printf("Total Expenses: %.2f%n", accountManager.getTotalExpenses());
                    break;

                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please try again!");
                    break;
            }
        }
    }

    public static Transaction getExpenseFromUser(Scanner scanner) {
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        LocalDate date = LocalDate.now();

        return new Expense(amount, category, date);
    }
}
