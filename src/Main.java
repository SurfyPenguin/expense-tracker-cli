import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option = 0;
        AccountManager accountManager = new AccountManager();
        Scanner scanner = new Scanner(System.in);
        StringBuilder menuBuilder = new StringBuilder();

        menuBuilder.append(String.format("%nMENU%n"));
        menuBuilder.append(
                String.format("1. Add Expense%n" +
                        "2. View Transactions%n" +
                        "3. View Total%n" +
                        "4. Exit")
        );
        while (true) {
            System.out.println(menuBuilder);

            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Wrong Option!: " + e.getMessage());
                scanner.nextLine();
            }

            switch (option) {
                case 1:
                    try {
                        System.out.print("Amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Category: ");
                        String category = scanner.nextLine();

                        LocalDate date = LocalDate.now();
                        Transaction expense = new Expense(amount, category, date);
                        accountManager.addTransaction(expense);
                        break;

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Wrong input type provided: " + e.getMessage());
                        scanner.nextLine();
                        break;
                    }

                case 2:
                    System.out.printf("%nTRANSACTIONS%n%n");
                    accountManager.displayTransactions();
                    break;

                case 3:
                    System.out.printf("%nTOTAL%n");
                    System.out.printf("Total Expenses: %.2f%n", accountManager.getTotalExpenses());
                    break;

                case 4:
                    System.out.printf("%nExiting...%n");
                    System.exit(0);

                default:
                    System.out.printf("%nOption is not valid. Please try again!%n");
                    break;
            }
        }
    }
}
