import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountManager {
    private final List<Transaction> transactions = new ArrayList<>();

    void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    double getTotalExpenses() {
        double expenses = 0;
        for (final Transaction transaction: transactions) {
            expenses += transaction.getAmount();
        }

        return expenses;
    }

    void displayTransactions() {
        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append(String.format("%-10s %-10s %-10s%n", "Amount", "Category", "Date"));

        for (final Transaction transaction: transactions) {
            tableBuilder.append(
                    String.format(
                            "%-10.2f %-10s %-10s%n",
                            transaction.getAmount(),
                            transaction.getCategory(),
                            transaction.getDate().toString()
                    )
            );
        }
        String table = tableBuilder.toString();
        System.out.print(table);
    }

    public void saveToFile(String fileName) {
        try (
                FileWriter fileWriter = new FileWriter(fileName);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                ) {
            for (final Transaction transaction: transactions) {
                printWriter.printf(
                        "%.2f,%s,%s%n",
                        transaction.getAmount(),
                        transaction.getCategory(),
                        transaction.getDate()
                );
            }
        } catch (IOException e) {
            System.out.println("Failed to save message" + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return; // prevents crash on first run

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    double amount = Double.parseDouble(parts[0]);
                    String category = parts[1];
                    LocalDate date = LocalDate.parse(parts[2]);

                    transactions.add(new Expense(amount, category, date));
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
