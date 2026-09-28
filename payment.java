class Payment {

    public void makePayment(double amount) {
        System.out.println("Payment processed: " + amount);
    }

    public void makePayment(double amount, String transactionId) {
        System.out.println("Payment processed: " + amount);
        System.out.println("Transaction ID: " + transactionId);
    }
}

class CreditCardPayment extends Payment {

    @Override
    public void makePayment(double amount) {
        System.out.println("Credit Card Payment: " + amount);
    }
}

class UPIPayment extends Payment {

    @Override
    public void makePayment(double amount) {
        System.out.println("UPI Payment: " + amount);
    }
}

class NetBankingPayment extends Payment {

    @Override
    public void makePayment(double amount) {
        System.out.println("Net Banking Payment: " + amount);
    }
}

public class payment {

    public static void main(String[] args) {

        Payment payment;

        payment = new CreditCardPayment();
        payment.makePayment(5000.00);

        payment.makePayment(2500.00, "TXN1001");

        payment = new UPIPayment();
        payment.makePayment(1200.50);

        payment = new NetBankingPayment();
        payment.makePayment(8500.00);
    }
}
