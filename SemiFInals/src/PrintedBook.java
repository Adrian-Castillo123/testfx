public class PrintedBook extends Item {

    private String author;
    private int stock;
    private double weight;

    public PrintedBook(int itemID, String title, double price,
                       String author, int stock, double weight) {

        super(itemID, title, price);

        this.author = author;
        this.stock = stock;
        this.weight = weight;
    }

    public String getAuthor() {
        return author;
    }

    public int getStock() {
        return stock;
    }

    public double getWeight() {
        return weight;
    }

    public void setStock(int stock) {

        if (stock >= 0) {
            this.stock = stock;
        }
    }

    public boolean reduceStock() {

        if (stock > 0) {
            stock--;
            return true;
        }

        return false;
    }
}