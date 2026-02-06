import java.time.LocalDate;

public abstract class Transaction {
    private double amount;
    private String category;
    private LocalDate date;

    public Transaction(double amount, String category, LocalDate date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero");
        }

        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

}
