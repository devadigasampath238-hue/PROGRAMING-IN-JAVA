import java.util.Scanner;

class BankAccount {
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(int accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public synchronized void deposit(String userName, double amount) {
        if (amount <= 0) {
            System.out.println(userName + ": Invalid deposit amount.");
            return;
        }

        balance += amount;

        System.out.println(userName + " deposited: " + amount);
        System.out.println("Updated Balance: " + balance);
    }

    public synchronized void withdraw(String userName, double amount) {
        if (amount <= 0) {
            System.out.println(userName + ": Invalid withdrawal amount.");
            return;
        }

        if (amount > balance) {
            System.out.println(userName + ": Insufficient Balance.");
            return;
        }

        balance -= amount;

        System.out.println(userName + " withdrew: " + amount);
        System.out.println("Updated Balance: " + balance);
    }

    public synchronized void displayBalance() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Final Balance: " + balance);
    }
}

class UserTransaction extends Thread {
    private BankAccount account;
    private String userName;
    private String transactionType;
    private double amount;

    public UserTransaction(BankAccount account, String userName,
                           String transactionType, double amount) {
        this.account = account;
        this.userName = userName;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public void run() {
        if (transactionType.equalsIgnoreCase("deposit")) {
            account.deposit(userName, amount);
        } else if (transactionType.equalsIgnoreCase("withdraw")) {
            account.withdraw(userName, amount);
        } else {
            System.out.println(userName + ": Invalid transaction type.");
        }
    }
}

public class Synchronization {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String holderName = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        if (balance < 0) {
            System.out.println("Balance cannot be negative.");
            sc.close();
            return;
        }

        BankAccount account = new BankAccount(
            accountNumber, holderName, balance
        );

        System.out.print("Enter number of transactions: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n <= 0) {
            System.out.println("Number of transactions must be positive.");
            sc.close();
            return;
        }

        UserTransaction[] transactions = new UserTransaction[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nTransaction " + (i + 1));

            System.out.print("Enter User Name: ");
            String userName = sc.nextLine();

            System.out.print("Enter Transaction Type (deposit/withdraw): ");
            String type = sc.nextLine();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            transactions[i] = new UserTransaction(
                account, userName, type, amount
            );
        }

        for (int i = 0; i < n; i++) {
            transactions[i].start();
        }

        try {
            for (int i = 0; i < n; i++) {
                transactions[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println("Transaction interrupted.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\nAll Transactions Completed");
        account.displayBalance();

        sc.close();
    }
}
