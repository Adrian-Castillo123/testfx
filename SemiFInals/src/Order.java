import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderID;
    private List<Item> items;
    private PaymentMethod payment;
    private boolean confirmed;

    public Order(int orderID) {

        this.orderID = orderID;
        this.items = new ArrayList<>();
        this.payment = null;
        this.confirmed = false;
    }

    public int getOrderID() {
        return orderID;
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public PaymentMethod getPayment() {
        return payment;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void addItem(Item item) {

        if (item != null) {
            items.add(item);
        }
    }

    public int getItemCount() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double calculateTotal() {

        double total = 0;

        for (Item item : items) {
            total += item.getPrice();
        }

        return total;
    }

    public void setPayment(PaymentMethod payment) {

        this.payment = payment;
    }

    public boolean confirmOrder() {

        if (items.isEmpty()) {
            return false;
        }

        for (Item item : items) {

            if (item instanceof PrintedBook) {

                PrintedBook book = (PrintedBook) item;

                if (book.getStock() <= 0) {
                    return false;
                }
            }
        }

        for (Item item : items) {

            if (item instanceof PrintedBook) {

                PrintedBook book = (PrintedBook) item;

                book.reduceStock();
            }
        }

        confirmed = true;

        return true;
    }
}

