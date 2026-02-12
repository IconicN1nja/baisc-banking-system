package model;

public class CurrentAcc extends Account {

    private final double OVERDRAFT = 5000;

    public CurrentAcc(String holderName,int enteredPin,double initialBalance){
        super(holderName, enteredPin, initialBalance);
    }

    //Debit Method
    @Override
    public boolean debit(double amount, String description, int enteredPin) {
        // pin validation
        if(!validatePIN(enteredPin))
            return false;

        // checking validity of the entered amt
        if (amount <= 0)
            return false;

        // checking for sufficient balance
        if ((balance + OVERDRAFT) < amount)
            return false;

        // deducting amt
        this.balance -= amount;

        //recording transaction
        recordTransaction(description, "Debit", amount);

        return true;
    }
}
