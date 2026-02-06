import java.util.ArrayList;
import java.util.List;

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
}
