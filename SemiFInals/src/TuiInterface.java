import java.util.List;
import java.util.Scanner;

public class TuiInterface {

    private Scanner scanner;
    private List<Item> catalog;

    private Customer loggedInCustomer;
    private Order activeOrder;

    private boolean running;

    public TuiInterface() {

        scanner = new Scanner(System.in);

        running = true;

        loggedInCustomer = null;
        activeOrder = null;

        catalog = List.of(

                new PrintedBook(
                        101,
                        "Java Programming",
                        150.00,
                        "Herbert S.",
                        10,
                        1.2
                ),

                new Ebook(
                        102,
                        "Clean Code (PDF)",
                        89.99,
                        "Robert C.",
                        "PDF",
                        4.5
                ),

                new Magazine(
                        103,
                        "Tech Monthly",
                        25.50,
                        "Vol 12",
                        "August",
                        2026,
                        "O'Reilly"
                )
        );
    }

    public void run() {

        while (running) {

            clearScreen();

            System.out.println("==============================");
            System.out.println("       ONLINE BOOKSTORE");
            System.out.println("==============================");

            if (loggedInCustomer != null) {

                showCustomerSession();
                printStoreMenu();

                System.out.print(
                        "\nSelect choice or Item ID (Q to quit): "
                );

                String choice = scanner.nextLine().trim();

                if (choice.equalsIgnoreCase("Q")) {

                    running = false;

                } else {

                    handleStorefrontInput(choice);
                }

            } else {

                System.out.println("\nStatus: Guest Session\n");

                printAuthMenu();

                String choice = scanner.nextLine().trim();

                if (choice.equalsIgnoreCase("Q")) {

                    running = false;

                } else {

                    handleAuthInput(choice);
                }
            }
        }

        System.out.println("\nSystem closed safely. Goodbye!");
    }

    private void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void showCustomerSession() {

        System.out.println(
                "\nUser: " + loggedInCustomer.getName()
        );

        System.out.println(
                "Customer ID: "
                        + loggedInCustomer.getCustomerID()
        );

        System.out.println(
                "Address: "
                        + loggedInCustomer.getAddress()
        );

        System.out.println(
                "Cart: "
                        + activeOrder.getItemCount()
                        + " item(s)"
        );
    }

    private void printAuthMenu() {

        System.out.println("[1] Register & Login New Account");

        System.out.println(
                "[2] Quick-Login with Default Profile (Adrian)"
        );

        System.out.println("[Q] Quit");

        System.out.print("\nInput choice: ");
    }

    private void printStoreMenu() {

        System.out.println("\n------------ ITEMS ------------");

        System.out.printf(
                "%-6s %-25s %-10s%n",
                "ID",
                "TITLE",
                "PRICE"
        );

        for (Item item : catalog) {

            System.out.printf(
                    "%-6d %-25s P%-10.2f%n",
                    item.getItemID(),
                    item.getTitle(),
                    item.getPrice()
            );
        }

        System.out.println("\n[P] Process Payment & Place Order");
        System.out.println("[C] View Cart / Order Summary");
        System.out.println("[L] Logout Account");
        System.out.println("[Q] Quit");
    }

    private void handleAuthInput(String choice) {

        if (choice.equals("1")) {

            registerCustomer();

        } else if (choice.equals("2")) {

            quickLogin();

        } else {

            System.out.println("Invalid choice.");
        }
    }

    private void registerCustomer() {

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        loggedInCustomer = new Customer(
                generateID(),
                name,
                email,
                password,
                address
        );

        if (loggedInCustomer.register()) {

            System.out.println(
                    "\n" + loggedInCustomer.getName()
                            + " successfully registered!"
            );
        }

        if (loggedInCustomer.login()) {

            System.out.println(
                    loggedInCustomer.getName()
                            + " successfully logged in!"
            );
        }

        activeOrder = new Order(generateID());
    }

    private void quickLogin() {

        loggedInCustomer = new Customer(
                12345,
                "Adrian",
                "adrian@gmail.com",
                "password123",
                "123 Main St"
        );

        loggedInCustomer.register();
        loggedInCustomer.login();

        activeOrder = new Order(generateID());

        System.out.println(
                "\nWelcome, "
                        + loggedInCustomer.getName()
                        + "!"
        );
    }

    private void handleStorefrontInput(String choice) {

        if (choice.equalsIgnoreCase("P")) {

            processCheckout();

        } else if (choice.equalsIgnoreCase("C")) {

            viewCart();

        } else if (choice.equalsIgnoreCase("L")) {

            logout();

        } else {

            selectItem(choice);
        }
    }

    private void selectItem(String choice) {

        try {

            int inputID = Integer.parseInt(choice);

            Item selectedItem = findItemByID(inputID);

            if (selectedItem != null) {

                activeOrder.addItem(selectedItem);

                System.out.println(
                        "\nAdded \""
                                + selectedItem.getTitle()
                                + "\" to cart."
                );

            } else {

                System.out.println("\nItem ID not found.");
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "\nInvalid input. Please enter an Item ID."
            );
        }
    }

    private Item findItemByID(int itemID) {

        for (Item item : catalog) {

            if (item.getItemID() == itemID) {

                return item;
            }
        }

        return null;
    }

    private void viewCart() {

        System.out.println("\n====== CURRENT CART ======");

        if (activeOrder.isEmpty()) {

            System.out.println("Your cart is empty.");

            return;
        }

        displayOrderItems();

        System.out.printf(
                "\nTOTAL: P%.2f%n",
                activeOrder.calculateTotal()
        );
    }

    private void displayOrderItems() {

        for (Item item : activeOrder.getItems()) {

            displayItemInfo(item);

            System.out.println(
                    "----------------------------"
            );
        }
    }

    private void displayItemInfo(Item item) {

        System.out.println(
                "Item ID: " + item.getItemID()
        );

        System.out.println(
                "Title: " + item.getTitle()
        );

        System.out.printf(
                "Price: P%.2f%n",
                item.getPrice()
        );

        if (item instanceof Ebook) {

            Ebook ebook = (Ebook) item;

            System.out.println(
                    "Author: " + ebook.getAuthor()
            );

            System.out.println(
                    "File Format: "
                            + ebook.getFileFormat()
            );

            System.out.println(
                    "File Size: "
                            + ebook.getFileSize()
                            + " MB"
            );

        } else if (item instanceof PrintedBook) {

            PrintedBook book = (PrintedBook) item;

            System.out.println(
                    "Author: " + book.getAuthor()
            );

            System.out.println(
                    "Stock: " + book.getStock()
            );

            System.out.println(
                    "Weight: "
                            + book.getWeight()
                            + " kg"
            );

        } else if (item instanceof Magazine) {

            Magazine magazine = (Magazine) item;

            System.out.println(
                    "Issue No: "
                            + magazine.getIssueNo()
            );

            System.out.println(
                    "Month: "
                            + magazine.getMonth()
            );

            System.out.println(
                    "Year: "
                            + magazine.getYear()
            );

            System.out.println(
                    "Publisher: "
                            + magazine.getPublisher()
            );
        }
    }

    private void processCheckout() {

        if (activeOrder.isEmpty()) {

            System.out.println("\nYour cart is empty!");

            return;
        }

        System.out.println("\nChoose Payment Method:");
        System.out.println("[1] GCash");
        System.out.println("[2] Credit Card");

        System.out.print("Input choice: ");

        String choice = scanner.nextLine().trim();

        PaymentMethod payment;

        if (choice.equals("1")) {

            payment = new GCashPayment(generateID());

        } else if (choice.equals("2")) {

            payment = new CardPayment(generateID());

        } else {

            System.out.println("\nInvalid payment method.");

            return;
        }

        double total = activeOrder.calculateTotal();

        activeOrder.setPayment(payment);

        boolean paymentSuccessful =
                payment.processPayment(total);

        boolean orderConfirmed =
                activeOrder.confirmOrder();

        if (paymentSuccessful && orderConfirmed) {

            System.out.println(
                    "\nOrder confirmed successfully!"
            );

            System.out.println(
                    "Payment successful!"
            );

            showReceipt(payment);

            activeOrder = new Order(generateID());

        } else {

            System.out.println(
                    "\nOrder could not be processed."
            );
        }
    }

    private void showReceipt(PaymentMethod payment) {

        System.out.println(
                "\n====== FINAL RECEIPT ======"
        );

        System.out.println(
                "Customer: "
                        + loggedInCustomer.getName()
        );

        System.out.println("\nItems:");

        displayOrderItems();

        System.out.printf(
                "\nTotal: P%.2f%n",
                activeOrder.calculateTotal()
        );

        System.out.println(
                "\nPayment ID: "
                        + payment.getPaymentID()
        );

        System.out.println(
                "Payment Method: "
                        + payment.getPaymentMethod()
        );

        System.out.printf(
                "Amount Paid: P%.2f%n",
                activeOrder.calculateTotal()
        );

        System.out.println(
                "\nThank you for your purchase!"
        );
    }

    private void logout() {

        loggedInCustomer = null;
        activeOrder = null;

        System.out.println(
                "\nLogged out successfully."
        );
    }

    private int generateID() {

        return (int) (Math.random() * 100000);
    }
}

