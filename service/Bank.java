
package service;

import java.util.ArrayList;
import java.util.List;
import model.*;

public class Bank {
    private final List<Account> accList = new ArrayList<>();

    // account creation
    public Account createAccount(String name, int pin, double iniBal, int type) {

        Account acc;

        switch (type) {
            case 1 -> acc = new SavingsAcc(name, pin, iniBal);
            case 2 -> acc = new CurrentAcc(name, pin, iniBal);
            default -> {
                System.out.println("Invalid type. Creating Savings Account.");
                acc = new SavingsAcc(name, pin, iniBal);
            }
        }

        accList.add(acc);
        return acc;
    }

    // account creation
    public Account findAccount(String accNum) {
        for (Account acc : accList) {
            if (acc.getAccNum(false).equals(accNum))
                return acc;
        }

        return null;
    }

    // bank to bank transfer
    public boolean transfer(String fromAccNum, String toAccNum, double amount, int enteredPin) {

        // Prevent self-transfer
        if (fromAccNum.equals(toAccNum))
            return false;

        // Find accounts
        Account sender = findAccount(fromAccNum);
        Account receiver = findAccount(toAccNum);

        if (sender == null || receiver == null)
            return false;

        // Validate amount
        if (amount <= 0)
            return false;

        // Try withdrawing from sender
        boolean debited = sender.debit(
                amount,
                "Transfer to " + receiver.getAccNum(true),
                enteredPin);

        if (!debited)
            return false;

        // Credit receiver
        boolean credited = receiver.credit(
                amount,
                "Transfer from " + sender.getAccNum(true));

        // if credit fails then rollback
        if (!credited) {
            sender.credit(amount, "Rollback transfer");
        }

        return true;
    }

    public void showTransactionHistroy(String accNum, int pin) {
        Account acc = findAccount(accNum);

        if (acc == null) {
            System.out.println("ACCOUNT NOT FOUND");
            return;
        }

        acc.showTransactionHistroy(pin);
    }

    // GETTERS
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accList);
    }

}
