import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class LinkedListGUI extends JFrame {
    private final LinkedList<Dessert> orderList;
    private final JTextArea displayArea;

    public LinkedListGUI() {
        orderList = new LinkedList<>();
        loadOrdersFromFile("src/orderList.txt");

        // Setup GUI
        setTitle("Moon Dessert System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4));

        JButton newOrderButton = new JButton("New Order");
        JButton searchOrderButton = new JButton("Search Order");
        JButton updateOrderButton = new JButton("Update Order");
        JButton deleteOrderButton = new JButton("Delete Order");
        JButton displayOrdersButton = new JButton("Display All Orders");
        JButton sortOrdersButton = new JButton("Sort Orders");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(newOrderButton);
        buttonPanel.add(searchOrderButton);
        buttonPanel.add(updateOrderButton);
        buttonPanel.add(deleteOrderButton);
        buttonPanel.add(displayOrdersButton);
        buttonPanel.add(sortOrdersButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        newOrderButton.addActionListener(e -> enterOrder());
        searchOrderButton.addActionListener(e -> searchOrder());
        updateOrderButton.addActionListener(e -> updateOrder());
        deleteOrderButton.addActionListener(e -> deleteOrder());
        displayOrdersButton.addActionListener(e -> displayOrder());
        sortOrdersButton.addActionListener(e -> sortOrder());
        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void loadOrdersFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String dataRow;
            while ((dataRow = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(dataRow, ";");
                String custName = st.nextToken();
                String custPhone = st.nextToken();
                String type = st.nextToken();
                Customer cust = new Customer(custName, custPhone);

                if (type.equalsIgnoreCase("Cookie")) {
                    String flavor = st.nextToken();
                    int quantity = Integer.parseInt(st.nextToken());
                    char addOn = st.nextToken().charAt(0);
                    String addType = "-";
                    if (addOn == 'Y' || addOn == 'y') {
                        addType = st.nextToken();
                    }
                    orderList.addLast(new CookieOrder(cust, type, flavor, quantity, addOn, addType));
                } else if (type.equalsIgnoreCase("Cake")) {
                    String flavor = st.nextToken();
                    int quantity = Integer.parseInt(st.nextToken());
                    int candle = Integer.parseInt(st.nextToken());
                    String lettering = st.nextToken();
                    orderList.addLast(new CakeOrder(cust, type, flavor, quantity, candle, lettering));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading orders: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

private void enterOrder() {
    
    String custName = JOptionPane.showInputDialog(this, "Enter Customer Name:");
    String custPhone = JOptionPane.showInputDialog(this, "Enter Customer Phone:");
    if (custName == null || custPhone == null || custName.isEmpty() || custPhone.isEmpty()) return;

    Customer cust = new Customer(custName, custPhone);
    
    
    String type = JOptionPane.showInputDialog(this, "Enter Order Type (1: Cake / 2: Cookie):");
    if (type == null || type.isEmpty()) return;

  
    double totalPrice = 0.0;

    if (type.equals("1")) { // Cake
        String flavorChoice = JOptionPane.showInputDialog(this, 
                "Select Cake Flavor:\n1. Red Velvet\n2. Fresh Cream\n3. Chocolate");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Quantity:"));
        String flavor = "";

        switch (flavorChoice) {
            case "1": flavor = "Red Velvet"; break;
            case "2": flavor = "Fresh Cream"; break;
            case "3": flavor = "Chocolate"; break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid flavor choice.");
                return;
        }

        String candleChoice = JOptionPane.showInputDialog(this, "Do you want candles? (Y/N):");
        boolean hasCandles = candleChoice.equalsIgnoreCase("Y");
        int numCandles = 0;

        if (hasCandles) {
            numCandles = Integer.parseInt(JOptionPane.showInputDialog(this, "How many candles do you want?"));
        }

        String letteringChoice = JOptionPane.showInputDialog(this, "Do you want Choose Lettering? (Y/N):");
        String lettering = "-";
        if (letteringChoice.equalsIgnoreCase("Y")) {
            lettering = JOptionPane.showInputDialog(this, "Enter Lettering (e.g., Happy Birthday, Congratulations):");
        }

        double price = 0.0;
        if (flavor.equals("Red Velvet")) {
            price = 55.00;
        } else if (flavor.equals("Fresh Cream")) {
            price = 70.00;
        } else if (flavor.equals("Chocolate")) {
            price = 60.00;
        }

        double candleCost = numCandles * 10.00;
        price += candleCost;

        totalPrice = price * quantity;
        orderList.addLast(new CakeOrder(cust, type, flavor, quantity, numCandles, lettering));
        JOptionPane.showMessageDialog(this, "Cake order placed successfully! Total Price: RM" + totalPrice);

    } else if (type.equals("2")) { // Cookie
        String flavorChoice = JOptionPane.showInputDialog(this, 
                "Select Cookie Flavor:\n1. Chocolate\n2. Oatmeal\n3. Matcha");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Quantity:"));
        String flavor = "";

        switch (flavorChoice) {
            case "1": flavor = "Chocolate"; break;
            case "2": flavor = "Oatmeal"; break;
            case "3": flavor = "Matcha"; break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid flavor choice.");
                return;
        }

  

      

       char addOn = JOptionPane.showInputDialog(this, "Do you want add-ons? (Y/N):").charAt(0);
String addType = "-";

if (addOn == 'Y' || addOn == 'y') {
    String addTypeChoice = JOptionPane.showInputDialog(this, 
            "Select Add-On Type:\n1. Chocolate Chip\n2. Walnuts\n3. Almonds");

    switch (addTypeChoice) {
        case "1":
            addType = "Chocolate Chip";
            break;
        case "2":
            addType = "Walnuts";
            break;
        case "3":
            addType = "Almonds";
            break;
        default:
            JOptionPane.showMessageDialog(this, "Invalid add-on choice.");
            return;
    }
}

double cookiePrice = 0.0;
if (flavor.equals("Chocolate")) {
    cookiePrice = 5.90;
} else if (flavor.equals("Oatmeal")) {
    cookiePrice = 6.90;
} else if (flavor.equals("Matcha")) {
    cookiePrice = 6.90;
}

// Add-on price adjustment
if (addOn == 'Y' || addOn == 'y') {
    cookiePrice += 0.50;
}

totalPrice = cookiePrice * quantity;
addOn = (addOn == 'Y' || addOn == 'y') ? 'Y' : 'N';
orderList.addLast(new CookieOrder(cust, type, flavor, quantity, addOn, addType));
JOptionPane.showMessageDialog(this, "Cookie order placed successfully! Total Price: RM" + totalPrice);

    } else {
        JOptionPane.showMessageDialog(this, "Invalid order type.");
    }
}




 private void searchOrder() {
        String keyword = JOptionPane.showInputDialog(this, "Enter keyword (Customer Name/Phone Number):");
        if (keyword == null || keyword.isEmpty()) return;

        StringBuilder result = new StringBuilder("Search Results:\n");
        boolean found = false;
        for (Dessert order : orderList) {
            if (order.getCustomer().getCustName().toLowerCase().contains(keyword.toLowerCase()) ||
                    order.getCustomer().getCustPhone().contains(keyword)) {
                result.append(order).append("\n");
                found = true;
            }
        }

        if (!found) {
            result.append("No orders found.");
        }

        displayArea.setText(result.toString());
    }



   private void updateOrder() {
    String searchName = JOptionPane.showInputDialog(this, "Enter the Customer Name to update the order:");
    if (searchName == null || searchName.isEmpty()) return;

    Dessert selectedOrder = null;
    for (Dessert order : orderList) {
        if (order.getCustomer().getCustName().equalsIgnoreCase(searchName)) {
            selectedOrder = order;
            break;
        }
    }

    if (selectedOrder == null) {
        JOptionPane.showMessageDialog(this, "No orders found for Customer Name: " + searchName);
        return;
    }

    if (selectedOrder instanceof CakeOrder) {
        CakeOrder cakeOrder = (CakeOrder) selectedOrder;

        // Let the user decide what to update
        String[] options = {"Flavor", "Quantity", "Candle", "Lettering", "Exit"};
        int choice;
        do {
            choice = JOptionPane.showOptionDialog(
                this,
                "What do you want to update?",
                "Update Cake Order",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );

            switch (choice) {
                case 0: // Update Flavor
                    String flavorInput = JOptionPane.showInputDialog(this, "Select Cake Flavor:\n1. Red Velvet\n2. Fresh Cream\n3. Chocolate");
                    if (flavorInput != null && !flavorInput.isEmpty()) {
                        switch (flavorInput) {
                            case "1": cakeOrder.setFlavor("Red Velvet"); break;
                            case "2": cakeOrder.setFlavor("Fresh Cream"); break;
                            case "3": cakeOrder.setFlavor("Chocolate"); break;
                            default: JOptionPane.showMessageDialog(this, "Invalid choice for flavor!"); break;
                        }
                    }
                    break;

                case 1: // Update Quantity
                    try {
                        int newQuantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new quantity:"));
                        cakeOrder.setQty(newQuantity);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Invalid quantity!");
                    }
                    break;

                case 2: // Update Candle
                    int candleChoice = JOptionPane.showConfirmDialog(this, "Do you want to add candles?", "Candles", JOptionPane.YES_NO_OPTION);
                    if (candleChoice == JOptionPane.YES_OPTION) {
                        try {
                            int newCandle = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new candle count:"));
                            cakeOrder.setCandle(newCandle);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Invalid candle count!");
                        }
                    } else {
                        cakeOrder.setCandle(0); // Set to 0 if no candles
                    }
                    break;

                case 3: // Update Lettering
                    int letteringChoice = JOptionPane.showConfirmDialog(this, "Do you want to add lettering?", "Lettering", JOptionPane.YES_NO_OPTION);
                    if (letteringChoice == JOptionPane.YES_OPTION) {
                        String newLettering = JOptionPane.showInputDialog(this, "Enter new lettering:");
                        if (newLettering != null) cakeOrder.setLettering(newLettering);
                    } else {
                        cakeOrder.setLettering(""); // Remove lettering
                    }
                    break;

                default: // Exit
                    break;
            }
        } while (choice != 4); // Exit option

    } else if (selectedOrder instanceof CookieOrder) {
        CookieOrder cookieOrder = (CookieOrder) selectedOrder;

        // Let the user decide what to update
        String[] options = {"Flavor", "Quantity", "Add-On", "Exit"};
        int choice;
        do {
            choice = JOptionPane.showOptionDialog(
                this,
                "What do you want to update?",
                "Update Cookie Order",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );

            switch (choice) {
                case 0: // Update Flavor
                    String flavorInput = JOptionPane.showInputDialog(this, "Select Cookie Flavor:\n1. Chocolate\n2. Oatmeal\n3. Matcha");
                    if (flavorInput != null && !flavorInput.isEmpty()) {
                        switch (flavorInput) {
                            case "1": cookieOrder.setFlavor("Chocolate"); break;
                            case "2": cookieOrder.setFlavor("Oatmeal"); break;
                            case "3": cookieOrder.setFlavor("Matcha"); break;
                            default: JOptionPane.showMessageDialog(this, "Invalid choice for flavor!"); break;
                        }
                    }
                    break;

                case 1: // Update Quantity
                    try {
                        int newQuantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter new quantity:"));
                        cookieOrder.setQty(newQuantity);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Invalid quantity!");
                    }
                    break;

                case 2: // Update Add-On
                    String addOnInput = JOptionPane.showInputDialog(this, "Select Cookie Add-On:\n1. Chocolate Chip\n2. Walnuts\n3. Almonds");
                    if (addOnInput != null && !addOnInput.isEmpty()) {
                        switch (addOnInput) {
                            case "1": cookieOrder.setAddType("Chocolate Chip"); break;
                            case "2": cookieOrder.setAddType("Walnuts"); break;
                            case "3": cookieOrder.setAddType("Almonds"); break;
                            default: JOptionPane.showMessageDialog(this, "Invalid choice for add-on!"); break;
                        }
                        cookieOrder.setAddOn('Y');
                    } else {
                        cookieOrder.setAddOn('N'); // No add-on
                        cookieOrder.setAddType(""); // Clear add-on type
                    }
                    break;

                default: // Exit
                    break;
            }
        } while (choice != 3); // Exit option
    }

    JOptionPane.showMessageDialog(this, "Order updated successfully!");
}



private void displayOrder() {
    // Check if there are orders in the list
    if (orderList.isEmpty()) {
        displayArea.setText("No orders to display.");
    } else {
        StringBuilder allOrders = new StringBuilder("All Orders:\n");

        // Loop through each order and append it to the result
        for (Dessert order : orderList) {
            allOrders.append(order).append("\n");  // Ensure each order has a newline after it
        }

        // Update the display area with the full list of orders
        displayArea.setText(allOrders.toString());
    }

    // Refresh the display to ensure changes are visible
    displayArea.revalidate();  // Revalidate the display area to ensure it gets updated
    displayArea.repaint();     // Repaint to reflect changes on the screen
}

    
 private void deleteOrder() {
    String searchName = JOptionPane.showInputDialog(this, "Enter the Customer Name to delete the order:");
    if (searchName == null || searchName.isEmpty()) return;

    Dessert toRemove = null;
    for (Dessert order : orderList) {
        if (order.getCustomer().getCustName().equalsIgnoreCase(searchName)) {
            toRemove = order;
            break;
        }
    }

    if (toRemove != null) {
        orderList.remove(toRemove);
        JOptionPane.showMessageDialog(this, "Order deleted successfully!");
    } else {
        JOptionPane.showMessageDialog(this, "No orders found for Customer Name: " + searchName);
    }
}


private void sortOrder() {
    if (orderList.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No orders to sort.");
        return;
    }

    String[] options = {"Cake Orders", "Cookie Orders"};
    String choice = (String) JOptionPane.showInputDialog(
            this,
            "Sort orders by:",
            "Sort Orders",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
    );

    if (choice == null) return; // User canceled

    String[] sortOptions = {"Customer Name ", "Phone Number "};
    String sortChoice = (String) JOptionPane.showInputDialog(
            this,
            "Sort by:",
            "Sort Criteria",
            JOptionPane.QUESTION_MESSAGE,
            null,
            sortOptions,
            sortOptions[0]
    );

    if (sortChoice == null) return; // User canceled

    // Sort based on the user's choice
    switch (choice) {
        case "Cake Orders":
            if (sortChoice.equals("Customer Name ")) {
                orderList.sort((o1, o2) -> o1.getCustomer().getCustName().compareToIgnoreCase(o2.getCustomer().getCustName()));
                JOptionPane.showMessageDialog(this, "Cake orders sorted successfully by Customer Name !");
            } else if (sortChoice.equals("Phone Number ")) {
                orderList.sort((o1, o2) -> o1.getCustomer().getCustPhone().compareTo(o2.getCustomer().getCustPhone()));
                JOptionPane.showMessageDialog(this, "Cake orders sorted successfully by Phone Number !");
            }
            break;
        case "Cookie Orders":
            if (sortChoice.equals("Customer Name ")) {
                orderList.sort((o1, o2) -> o1.getCustomer().getCustName().compareToIgnoreCase(o2.getCustomer().getCustName()));
                JOptionPane.showMessageDialog(this, "Cookie orders sorted successfully by Customer Name !");
            } else if (sortChoice.equals("Phone Number ")) {
                orderList.sort((o1, o2) -> o1.getCustomer().getCustPhone().compareTo(o2.getCustomer().getCustPhone()));
                JOptionPane.showMessageDialog(this, "Cookie orders sorted successfully by Phone Number !");
            }
            break;
        default:
            JOptionPane.showMessageDialog(this, "Invalid choice.");
            return;
    }

    displayOrder(); // Refresh display after sorting
}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(LinkedListGUI::new);
    }
}
