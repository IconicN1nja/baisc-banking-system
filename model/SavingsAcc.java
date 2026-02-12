package model;

public class SavingsAcc extends Account{

    private static final double MIN_ACC_BAL = 2000;
    private static final double INTEREST_RATE = 0.05;

    public SavingsAcc(String holderName,int enteredPin,double initialBalance){
        super(holderName, enteredPin, initialBalance);
    }

    //Debit Method
    @Override
    public boolean debit(double amount, String description, int enteredPin){
        // pin validation
        if(!validatePIN(enteredPin))
            return false;

        // checking validity of the entered amt
        if (amount <= 0)
            return false;

        // checking for sufficient balance
        if ((this.balance - amount) < MIN_ACC_BAL)
            return false;

        // deducting amt
        this.balance -= amount;

        //recording transaction
        recordTransaction(description, "Debit", amount);

        return true;
    }

    //yearly interest
    public void addInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
    }
}
