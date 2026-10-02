public abstract class Item {

    private int itemID;
    private String title;
    private double price;

    public Item(int itemID, String title, double price) {

        this.itemID = itemID;
        this.title = title;
        this.price = price;
    }

    public int getItemID() {
        return itemID;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}