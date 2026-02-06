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
        tableBuilder.append(String.format("amount — category — date%n"));

        for (final Transaction transaction: transactions) {
            tableBuilder.append(
                    String.format(
                            "%.2f — %s — %s%n",
                            transaction.getAmount(),
                            transaction.getCategory(),
                            transaction.getDate().toString()
                    )
            );
        }
        String table = tableBuilder.toString();
        System.out.println(table);
    }
}
