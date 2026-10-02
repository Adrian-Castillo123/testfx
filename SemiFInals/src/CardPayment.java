
public class CardPayment implements PaymentMethod {

    private int paymentID;

    public CardPayment(int paymentID) {
        this.paymentID = paymentID;
    }

    @Override
    public boolean processPayment(double amount) {

        return amount > 0;
    }

    @Override
    public String getPaymentMethod() {

        return "Credit Card";
    }

    @Override
    public int getPaymentID() {

        return paymentID;
    }
}

