import java.util.Scanner;
import model.*;
import service.Bank;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        boolean running = true;

        while (running) {
            System.out.println("\n=====WELCOME TO THE BANK=====");
            System.out.println("1. CREATE A BANK ACCOUNT");
            System.out.println("2. DEPOSIT FUNDS TO YOUR ACCOUNT");
            System.out.println("3. WITHDRAW FUNDS FROM YOUR ACCOUNT");
            System.out.println("4. TRANSFER MONEY TO ANOTHER ACCOUNT");
            System.out.println("5. SHOW TRANSACTION HISTORY");
            System.out.println("6. EXIT");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter Your Name");
                    String name = sc.next();

                    System.out.println("Set a 4-digit PIN: ");
                    int pin = sc.nextInt();

                    System.out.println("Enter Initial Balance");
                    double iniBal = sc.nextDouble();

                    System.out.println("Enter Account Type: \n(1) SAVINGS \n(2)CURRENT");
                    int type = sc.nextInt();

                    Account acc = bank.createAccount(name, pin, iniBal, type);

                    System.out.println("Account created successfully!");
                    System.out.println("Account Number: " + acc.getAccNum(false));
                }
                case 2 -> {
                    System.out.println("Enter Your Account Number");
                    String accNum = sc.next();

                    System.out.println("Enter Deposite Amount");
                    double depAmt = sc.nextDouble();

                    Account deposit = bank.findAccount(accNum);

                    if (deposit == null) {
                        System.out.println("ACCOUNT NOT FOUND");
                        System.out.println("DEPOSIT FAILED");
                    } else {
                        boolean credit = deposit.credit(depAmt, "CASH DEPOSITE");
                        if (!credit)
                            System.out.println("DEPOSTIE FAILED");
                        else
                            System.out.println("DEPOSIT SUCCESSFUL");
                    }
                }

                case 3 -> {
                    System.out.println("Enter Your Account Number");
                    String accNum = sc.next();

                    System.out.println("Enter Withdraw Amount");
                    double witdAmt = sc.nextDouble();

                    System.out.println("Enter PIN");
                    int pin = sc.nextInt();

                    Account withdraw = bank.findAccount(accNum);

                    if (withdraw == null) {
                        System.out.println("ACCOUNT NOT FOUND");
                        System.out.println(" FAILED");
                    } else {
                        boolean debit = withdraw.debit(witdAmt, "CASH WITHDRAW", pin);
                        if (!debit)
                            System.out.println("WITHDRAW FAILED");
                        else
                            System.out.println("WITHDRAW SUCCESSFUL");
                    }
                }

                case 4 -> {
                    System.out.println("Enter Senders Account number");
                    String fromAccNum = sc.next();

                    System.out.println("Enter Receivers Account number");
                    String toAccNum = sc.next();

                    System.out.println("Enter Amount");
                    double amt = sc.nextDouble();

                    System.out.println("Enter PIN");
                    int pin = sc.nextInt();

                    if (bank.transfer(fromAccNum, toAccNum, amt, pin)) {
                        System.out.println("TRANSFER SUCCESSFUL");
                    } else {
                        System.out.println("TRANSFER FAILED");
                    }
                }

                case 5 -> {
                    System.out.println("Enter Your Account Number");
                    String accNum = sc.next();

                    System.out.println("Enter PIN");
                    int pin = sc.nextInt();

                    bank.showTransactionHistroy(accNum, pin);
                }
                case 6 -> {
                    System.out.println("THANKS FOR VISITING PLEASE VISIT AGAIN");
                    running = false;
                }

                default -> System.out.println("INVALID INPUT");

            }
        }

        sc.close();
    }
}
