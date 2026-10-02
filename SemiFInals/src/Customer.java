public class Customer {

    private int customerID;
    private String name;
    private String email;
    private String password;
    private String address;

    public Customer(int customerID, String name, String email,
                    String password, String address) {

        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public boolean register() {
        return true;
    }

    public boolean login() {
        return true;
    }
}