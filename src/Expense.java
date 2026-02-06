import java.time.LocalDate;

public class Expense extends Transaction {
    public Expense(double amount, String category, LocalDate date) {
        super(amount, category, date);
    }
}
