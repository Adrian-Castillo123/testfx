
public interface PaymentMethod {

    boolean processPayment(double amount);

    String getPaymentMethod();

    int getPaymentID();
}

