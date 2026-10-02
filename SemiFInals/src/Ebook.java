public class Ebook extends Item {

    private String author;
    private String fileFormat;
    private double fileSize;

    public Ebook(int itemID, String title, double price,
                 String author, String fileFormat, double fileSize) {

        super(itemID, title, price);

        this.author = author;
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
    }

    public String getAuthor() {
        return author;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public double getFileSize() {
        return fileSize;
    }
}