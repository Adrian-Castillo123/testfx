public class GCashPayment implements PaymentMethod {

    private int paymentID;

    public GCashPayment(int paymentID) {
        this.paymentID = paymentID;
    }

    @Override
    public boolean processPayment(double amount) {

        return amount > 0;
    }

    @Override
    public String getPaymentMethod() {

        return "GCash";
    }

    @Override
    public int getPaymentID() {

        return paymentID;
    }
}

