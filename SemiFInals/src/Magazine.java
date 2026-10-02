public class Magazine extends Item {

    private String issueNo;
    private String month;
    private int year;
    private String publisher;

    public Magazine(int itemID, String title, double price,
                    String issueNo, String month,
                    int year, String publisher) {

        super(itemID, title, price);

        this.issueNo = issueNo;
        this.month = month;
        this.year = year;
        this.publisher = publisher;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }
}