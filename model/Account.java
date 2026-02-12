package model;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Account {
    private final String holderName;
    private final String accNum;
    private static int lastAccNum = 0;

    protected double balance;
    private final int pin;

    private ArrayList<Transactions> tr;

    // Constructor
    public Account(String holderName, int enteredPin, double initialBalance) {
        this.holderName = holderName;
        this.accNum = generateAccountNumber();
        this.balance = initialBalance;
        this.pin = enteredPin;
        this.tr = new ArrayList<>();
    }

    // Account number generator
    private static String generateAccountNumber() {
        lastAccNum++;
        return "AD2026" + String.format("%06d", lastAccNum);
    }

    // Pin Validator
    protected boolean validatePIN(int enteredPin) {
        return this.pin == enteredPin;
    }

    // Credit Method
    public boolean credit(double amount, String description) {
        // checking validity of the entered amt
        if (amount <= 0)
            return false;

        // Updating balance
        this.balance += amount;

        // recording transactions
        recordTransaction(description, "Credit", amount);

        return true;
    }

    // Deposit Method
    public abstract boolean debit(double amount, String description, int enteredPin);

    public void recordTransaction(String description, String type, double amount) {
        tr.add(
                new Transactions(description, type, LocalDate.now(), amount, this.balance));
    }

    public void showTransactionHistroy(int pin) {
        if (!validatePIN(pin)) {
            System.out.println("INVALID PIN. ACCESS DENIED");
            return;
        }

        if (tr.isEmpty()) {
            System.out.println("NO TRANSACTIONS FOUND");
            return;
        }

        System.out.println("\n===TRANACTION HISTORY===");
        for (Transactions t : tr) {
            System.out.println(
                    "Date: " + t.getDate() +
                            " | Type: " + t.getType() +
                            " | Desc: " + t.getDescription() +
                            " | Amount: " + t.getAmount() +
                            " | Balance After: " + t.getBalanceAfter());
        }
    }

    // GETTERS
    public String getAccNum(boolean masked) {
        if (masked) {
            return "XXXX" + accNum.substring(accNum.length() - 4);
        }
        return accNum;
    }

    public String getholderName() {
        return holderName;
    }

    public double getAccBalance() {
        return balance;
    }
}