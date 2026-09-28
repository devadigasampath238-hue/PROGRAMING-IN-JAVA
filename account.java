import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class Account {
    private String accountHolderName;
    private double accountBalance;

    public Account(String accountHolderName, double accountBalance) {
        this.accountHolderName = accountHolderName;
        this.accountBalance = accountBalance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Withdrawal amount must be greater than zero."
            );
        }

        if (amount > accountBalance) {
            throw new InsufficientBalanceException(
                "Insufficient balance. Available balance: " + accountBalance
            );
        }

        accountBalance = accountBalance - amount;

        System.out.println("Withdrawal successful.");
        System.out.println("Withdrawn amount: " + amount);
        System.out.println("Remaining balance: " + accountBalance);
    }

    public void displayDetails() {
        System.out.println("\nAccount Holder: " + accountHolderName);
        System.out.println("Account Balance: " + accountBalance);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter account balance: ");
        double balance = sc.nextDouble();

        if (balance < 0) {
            System.out.println("Account balance cannot be negative.");
            sc.close();
            return;
        }

        Account account = new Account(name, balance);

        account.displayDetails();

        System.out.print("\nEnter withdrawal amount: ");
        double amount = sc.nextDouble();

        try {
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
