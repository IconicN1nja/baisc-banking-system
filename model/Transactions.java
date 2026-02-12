package model;

import java.time.LocalDate;

public class Transactions {
    private final String description, type;
    private final LocalDate date;
    private final double amount;
    private final double balance;

    public Transactions(String description, String type, LocalDate date, double amount, double balance) {
        this.description = description;
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() { return type; }

    public double getBalanceAfter() { return balance; }

    @Override
    public String toString() {
        return date + " | " + type + " | Rs." + amount +
                " | Balance: Rs." + balance +
                " | " + description;
    }

}
