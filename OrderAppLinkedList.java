import java.io.IOException;
import java.util.*;
import java.io.*;
public class OrderAppLinkedList {
    public static void main(String[] args) {
        try {
            Scanner inLine = new Scanner(System.in);
            LinkedList<Dessert> orderList = new LinkedList<>();
    
            FileReader fr = new FileReader("orderList.txt");
            BufferedReader br = new BufferedReader(fr);
    
            String dataRow = br.readLine();
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
            br.close();
    
            boolean isCont = false;
            while (!isCont) {
                System.out.println("\nWelcome to Moon Dessert System");
                System.out.println("___________________________");
                System.out.println("| MENU                    |");
                System.out.println("|_________________________|");
                System.out.println("| A | New Order           |"); 
                System.out.println("|___|_____________________|");
                System.out.println("| B | Search Order        |");    
                System.out.println("|___|_____________________|");
                System.out.println("| C | Update              |");  
                System.out.println("|___|_____________________|");
                System.out.println("| D | Deletion            |");  
                System.out.println("|___|_____________________|");
                System.out.println("| E | Display All Orders  |");  
                System.out.println("|___|_____________________|");
                System.out.println("| F | Sort Orders         |");   
                System.out.println("|___|_____________________|");
                System.out.println("| X | Exit                |");         
                System.out.println("|___|_____________________|");
    
                System.out.print("\nEnter Choice: ");
                String opt = inLine.nextLine();  
                if (opt.equalsIgnoreCase("A")) {
                    enterOrder(orderList);  // Add new order
                } else if (opt.equalsIgnoreCase("B")) {
                    searchOrder(orderList);  // Search for order
                } else if (opt.equalsIgnoreCase("C")) {
                    updateOrder(orderList);
                } else if (opt.equalsIgnoreCase("D")){
                    deleteOrder(orderList, "orderListQueue.txt");
                } else if (opt.equalsIgnoreCase("E")) {
                    System.out.println("\nPreparing orders: ");
                    displayOrder(orderList);  // Display orders
                } else if (opt.equalsIgnoreCase("F")) {
                    sortOrder(orderList, "orderListQueue.txt");  // Sort orders
                } else if (opt.equalsIgnoreCase("X")) { 
                    System.out.println("\nCompleted orders: ");
                    displayOrder(orderList);
                    isCont = true;
                } else {
                    System.out.println("\nInvalid Key!");
                    isCont = false;
                }
            }
        } catch (EOFException eof) {
            System.out.println("\nProblem: " + eof.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("\nProblem: " + e.getMessage());
        } catch (IOException ioe) {
            System.out.println("\nProblem: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("\nSomething went wrong.");
        } finally {
            System.out.println("\nEnd of program");
        }
    }
    public static void enterOrder(LinkedList<Dessert> orderList) { //A
        Scanner inLine = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        
        System.out.println("\nWelcome to Moon Dessert System");
        System.out.println("___________________________________________________");
        System.out.println("|     Types      |      Cake      |     Cookie     |");
        System.out.println("|----------------|----------------|----------------|");
        System.out.println("|                |   Red Velvet   |    Chocolate   |");
        System.out.println("|                |    RM55.00     |     RM5.90     |");
        System.out.println("|                |----------------|----------------|");
        System.out.println("|     Flavor     |  Fresh Cream   |     Oatmeal    |");
        System.out.println("|                |    RM70.00     |     RM6.90     |");
        System.out.println("|                |----------------|----------------|");
        System.out.println("|                |   Chocolate    |     Matcha     |");
        System.out.println("|                |    RM70.00     |     RM6.90     |");
        System.out.println("|----------------|----------------|----------------|");
        System.out.println("|                |                | Chocolate chip |");
        System.out.println("|                |                |     Walnuts    |");
        System.out.println("|     Add-on     |       --       |     Almonds    |");
        System.out.println("|                |                |                |");
        System.out.println("|                |                |     Rm0.50     |");
        System.out.println("|                |                |  (per cookie)  |");
        System.out.println("---------------------------------------------------");
            
        while (true) {
            System.out.println("\nDo you want to add more orders?");
            System.out.println("__________________ ");
            System.out.println("| 1 | Cake        |");
            System.out.println("|___|_____________|");
            System.out.println("| 2 | Cookie      |");
            System.out.println("|___|_____________|");
            System.out.println("| 3 | Back        |");
            System.out.println("|___|_____________|");
            
            System.out.print("\nEnter Choice(1|2|3): ");
            int orders = in.nextInt();
            if (orders == 1) {
                System.out.print("\nEnter Customer Name: ");
                String custName = inLine.nextLine();
                System.out.print("Enter Customer Phone: ");
                String custPhone = inLine.nextLine();
                String type = "Cake";
        
                System.out.println("\nEnter flavor");
                System.out.println("__________________ ");
                System.out.println("| 1 | Red Velvet  |");
                System.out.println("|___|_____________|");
                System.out.println("| 2 | Fresh Cream |");
                System.out.println("|___|_____________|");
                System.out.println("| 3 | Chocolate   |");
                System.out.println("|___|_____________|");
                
                int flavor;
                String Flavor = " ";
                int quantity = 0;
                int candle = 0;
                String lettering =" ";
                while(true){
                    System.out.print("\nEnter Choice(1|2|3): ");
                    flavor = in.nextInt();
                    if(flavor == 1){
                        Flavor = "Red Velvet";
                        break;
                    }
                    else if (flavor == 2){
                        Flavor = "Fresh Cream";
                        break;
                    }
                    else if (flavor == 3){
                        Flavor = "Chocolate";
                        break;
                    }
                    else {
                        System.out.println("\nInvalid flavor. Please choose from the available options.");
                    }
                }
                System.out.print("\nEnter quantity: ");
                quantity = in.nextInt();
                System.out.print("Enter candle: ");
                candle = in.nextInt();
                System.out.print("Enter lettering: ");
                lettering = inLine.nextLine();
                
                Customer cust = new Customer(custName, custPhone);
                Dessert dessert = new CakeOrder(cust, type, Flavor, quantity, candle, lettering);
                orderList.addLast(dessert);
                
                System.out.println("\n------------------------------");
                System.out.println("Cake Receipt");
                System.out.println("------------------------------");
                System.out.println("Customer Name: " +dessert.getCustomer().getCustName());
                System.out.println("Phone Number: " +dessert.getCustomer().getCustPhone());
                System.out.println("Flavor: " +dessert.getFlavor());
                System.out.println("Quantity: " +dessert.getQty());
                System.out.println("Candle: " +dessert.getCandle());
                System.out.println("Lettering: " +dessert.getLettering());
                System.out.println("\nStatus: Order Placed");
                System.out.println("-------------------------------");
                System.out.printf("Total Price: RM %.2f%n", dessert.calcTotal());
                System.out.println("------------------------------");
            } else if (orders == 2) {
                System.out.print("\nEnter Customer Name: ");
                String custName = inLine.nextLine();
                System.out.print("Enter Customer Phone: ");
                String custPhone = inLine.nextLine();
                String type = "Cookie";
                
                System.out.println("\nEnter flavor");
                System.out.println("__________________ ");
                System.out.println("| 1 | Chocolate   |");
                System.out.println("|___|_____________|");
                System.out.println("| 2 | Oatmeal     |");
                System.out.println("|___|_____________|");
                System.out.println("| 3 | Matcha      |");
                System.out.println("|___|_____________|");
                
                int flavor;
                String Flavor = " ";
                int quantity = 0;
                char addOn = ' ';
                while(true){
                    System.out.print("\nEnter Choice(1|2|3): ");
                    flavor = in.nextInt();
                    if(flavor == 1){
                        Flavor = "Chocolate";
                        break;
                    }
                    else if (flavor == 2){
                        Flavor = "Oatmeal";
                        break;
                    }
                    else if (flavor == 3){
                        Flavor = "Matcha";
                        break;
                    }
                    else {
                        System.out.println("\nInvalid flavor. Please choose from the available options.");
                    }
                }
                System.out.print("Enter quantity: ");
                quantity = in.nextInt();
                System.out.print("Do you want add-ons? (Y/N): ");
                addOn = in.next().charAt(0);
                
                String addtype = " ";
                if (addOn == 'Y' || addOn == 'y') {
                    System.out.println("\nEnter add type");
                    System.out.println("_____________________ ");
                    System.out.println("| 1 | Chocolate Chip |");
                    System.out.println("|___|________________|");
                    System.out.println("| 2 | Walnuts        |");
                    System.out.println("|___|________________|");
                    System.out.println("| 3 | Almonds        |");
                    System.out.println("|___|________________|");
                    
                    int AddType;
                    while(true){
                        System.out.print("\nEnter Choice(1|2|3): ");
                        AddType = in.nextInt();
                        if(AddType == 1){
                            addtype = "Chocolate Chip";
                            break;
                        }
                        else if (AddType == 2){
                            addtype = "Walnuts";
                            break;
                        }
                        else if (AddType == 3){
                            addtype = "Almonds";
                            break;
                        }
                        else {
                            System.out.println("\nInvalid flavor. Please choose from the available options.");
                        }
                    }
                } else if (addOn == 'N' || addOn == 'n') {
                    addtype = "-";
                } else {
                    System.out.println("\nInvalid input");
                }
                Customer cust = new Customer(custName, custPhone);
                Dessert dessert = new CookieOrder(cust, type, Flavor, quantity, addOn, addtype);
                orderList.addLast(dessert);
                
                System.out.println("\n------------------------------");
                System.out.println("Cookie Receipt");
                System.out.println("------------------------------");
                System.out.println("Customer Name: " +dessert.getCustomer().getCustName());
                System.out.println("Phone Number: " +dessert.getCustomer().getCustPhone());
                System.out.println("Flavor: " +dessert.getFlavor());
                System.out.println("Quantity: " +dessert.getQty());
                System.out.println("Add Type: " +dessert.getAddType());
                System.out.println("\nStatus: Order Placed");
                System.out.println("-------------------------------");
                System.out.printf("Total Price: RM %.2f%n", dessert.calcTotal());
                System.out.println("------------------------------");
            } else if (orders == 3) {
                System.out.println("\nExiting order entry...");
                break;
            } else {
                System.out.println("\nInvalid input");
            }
        }
    }
    public static void searchOrder(LinkedList<Dessert> orderList) {
        Scanner inLine = new Scanner(System.in);
        System.out.print("Enter keyword (Customer Name/Phone Number): ");
        String keyword = inLine.nextLine().toLowerCase();
    
        int count = 0;
        LinkedList<Dessert> tempList = new LinkedList<>(); // Temporary list for storing elements
    
        System.out.println("\nSearching for orders...");
        while (!orderList.isEmpty()) {
            Dessert data = orderList.removeFirst(); // Remove the first element from the list
            if (data.getCustName().toLowerCase().equals(keyword) || data.getCustPhone().toLowerCase().equals(keyword)) {
                System.out.println(data.toString());
                count++;
            }
            tempList.addLast(data); // Store the removed element in the temp list
        }
    
        // Restore the original list from the temporary list
        while (!tempList.isEmpty()) {
            orderList.addLast(tempList.removeFirst());
        }
    
        System.out.println("\n" +count+ " order(s) found.");
    }
    public static void updateOrder(LinkedList<Dessert> orderList) { //C
        Scanner in = new Scanner(System.in);
        Scanner inLine = new Scanner(System.in);
        
        LinkedList<Dessert> tempList = new LinkedList<>();
    
        System.out.print("\nEnter the Customer Name to update the order: ");
        String searchName = inLine.nextLine();
    
        // Search for matching orders
        LinkedList<Dessert> matchingOrders = new LinkedList<>();
        while (!orderList.isEmpty()) {
            Dessert order = orderList.removeFirst();
            if (order.getCustName().equalsIgnoreCase(searchName)) {
                matchingOrders.addLast(order); // Add matching orders to matchingOrders list
            }
            tempList.addLast(order); // Keep all orders in tempList to restore later
        }
    
        // Restore original orderList
        while (!tempList.isEmpty()) {
            orderList.addLast(tempList.removeFirst());
        }
    
        // Check if any orders match
        if (!matchingOrders.isEmpty()) {
            System.out.println("\nMatching orders found:");
            int index = 1;
    
            // Display all matching orders
            while (!matchingOrders.isEmpty()) {
                Dessert order = matchingOrders.removeFirst();
                System.out.println("\n" + index + " - " + order.getClass().getSimpleName() + ": \n" + order);
                tempList.addLast(order); // Temporarily store to preserve matchingOrders
                index++;
            }
    
            // Restore matchingOrders
            while (!tempList.isEmpty()) {
                matchingOrders.addLast(tempList.removeFirst());
            }
    
            int updateNo = 0;
            boolean validInput = false;
            while (!validInput) { 
                System.out.print("\nEnter the number of the order you want to update: ");
                if (in.hasNextInt()) {
                    updateNo = in.nextInt();
                    if (updateNo >= 1 && updateNo <= index - 1) {
                        validInput = true;
                    } else {
                        System.out.println("\nInvalid selection. Please try again.");
                    }
                } else {
                    System.out.println("\nInvalid input. Please enter a number.");
                    in.nextInt();
                }
            }
            // Retrieve the selected order
            Dessert selectedOrder = null;
            
            // Iterate through the matching orders
            for (int i = 0; i < index - 1; i++) {
                Dessert order = matchingOrders.removeFirst();  // Get the first order
                if (i + 1 == updateNo) {
                    selectedOrder = order;  // Select the order if it matches the input index
                }
                tempList.addLast(order);  // Add the order back to tempList to preserve them
            }
            // Restore matchingOrders
            while (!tempList.isEmpty()) {
                matchingOrders.addLast(tempList.removeFirst());
            }
    
            // Update the selected order
            if (selectedOrder != null) {
                System.out.println("\nWhat would you like to update?");
                if (selectedOrder instanceof CakeOrder) {
                    System.out.println("____________________");
                    System.out.println("| 1 | Flavor       |");
                    System.out.println("|___|______________|");
                    System.out.println("| 2 | Quantity     |");
                    System.out.println("|___|______________|");
                    System.out.println("| 3 | Candle       |");
                    System.out.println("|___|______________|");
                    System.out.println("| 4 | Lettering    |");
                    System.out.println("|___|______________|");
                } else if (selectedOrder instanceof CookieOrder) {
                    System.out.println("____________________");
                    System.out.println("| 1 | Flavor       |");
                    System.out.println("|___|______________|");
                    System.out.println("| 2 | Quantity     |");
                    System.out.println("|___|______________|");
                    System.out.println("| 3 | Add-On (Y/N) |");
                    System.out.println("|___|______________|");
                    System.out.println("| 4 | Add Type     |");
                    System.out.println("|___|______________|");
                }
                System.out.print("\nEnter your choice(1|2|3|4): ");
                if (in.hasNextInt()) {
                    int choice = in.nextInt();
                    if (selectedOrder instanceof CakeOrder) {
                        CakeOrder cake = (CakeOrder) selectedOrder;
                        if (choice == 1) {
                            int flavor;
                            String newFlavor = " ";
    
                            System.out.println("\nEnter new flavor");
                            System.out.println("__________________ ");
                            System.out.println("| 1 | Red Velvet  |");
                            System.out.println("|___|_____________|");
                            System.out.println("| 2 | Fresh Cream |");
                            System.out.println("|___|_____________|");
                            System.out.println("| 3 | Chocolate   |");
                            System.out.println("|___|_____________|");
    
                            while (true) {
                                System.out.print("\nEnter your choice(1|2|3): ");
                                flavor = in.nextInt();
                                if (flavor == 1) {
                                    newFlavor = "Red Velvet";
                                    break;
                                } else if (flavor == 2) {
                                    newFlavor = "Fresh Cream";
                                    break;
                                } else if (flavor == 3) {
                                    newFlavor = "Chocolate";
                                    break;
                                } else {
                                    System.out.println("\nInvalid flavor. Please try again.");
                                }
                            }
                            System.out.println("\nStatus updated!");
                            cake.setFlavor(newFlavor);
                        } else if (choice == 2) {
                            System.out.print("Enter new quantity: ");
                            cake.setQty(in.nextInt());
                            System.out.println("\nStatus updated!");
                        } else if (choice == 3) {
                            System.out.print("Enter new candle count: ");
                            cake.setCandle(in.nextInt());
                            System.out.println("\nStatus updated!");
                        } else if (choice == 4) {
                            System.out.print("Enter new lettering: ");
                            cake.setLettering(inLine.nextLine());
                            System.out.println("\nStatus updated!");
                        } else {
                            System.out.println("\nInvalid choice.");
                        }
                    } else if (selectedOrder instanceof CookieOrder) {
                        CookieOrder cookie = (CookieOrder) selectedOrder;
                        if (choice == 1) {
                            int newFlavor;
    
                            System.out.println("\nEnter new flavor");
                            System.out.println("_____________________ ");
                            System.out.println("| 1 | Chocolate      |");
                            System.out.println("|___|________________|");
                            System.out.println("| 2 | Oatmeal        |");
                            System.out.println("|___|________________|");
                            System.out.println("| 3 | Matcha         |");
                            System.out.println("|___|________________|");
    
                            String NewFlavor = " ";
                            while (true) {
                                System.out.print("\nEnter your choice(1|2|3): ");
                                newFlavor = in.nextInt();
                                if (newFlavor == 1) {
                                    NewFlavor = "Chocolate";
                                    break;
                                } else if (newFlavor == 2) {
                                    NewFlavor = "Oatmeal";
                                    break;
                                } else if (newFlavor == 3) {
                                    NewFlavor = "Matcha";
                                    break;
                                } else {
                                    System.out.println("\nInvalid flavor. Please try again.");
                                }
                            }
                            System.out.println("\nStatus updated!");
                            
                            cookie.setFlavor(NewFlavor);
                        } else if (choice == 2) {
                            System.out.print("Enter new quantity: ");
                            cookie.setQty(in.nextInt());
                            System.out.println("\nStatus updated!");
                        } else if (choice == 3) {
                            char newAddOn;
                            while (true) {
                                System.out.print("Enter new add-on (Y/N): ");
                                newAddOn = in.next().charAt(0);
                                if (newAddOn == 'Y' || newAddOn == 'y') {
                                    String addType = "";
                                    int newAddType;
    
                                    System.out.println("\nSelect new add-on type:");
                                    System.out.println("_____________________ ");
                                    System.out.println("| 1 | Chocolate Chip |");
                                    System.out.println("|___|________________|");
                                    System.out.println("| 2 | Walnuts        |");
                                    System.out.println("|___|________________|");
                                    System.out.println("| 3 | Almonds        |");
                                    System.out.println("|___|________________|");
    
                                    while (true) {
                                        System.out.print("\nEnter your choice(1|2|3): ");
                                        newAddType = in.nextInt();
                                        if (newAddType == 1) {
                                            addType = "Chocolate Chip";
                                            break;
                                        } else if (newAddType == 2) {
                                            addType = "Walnuts";
                                            break;
                                        } else if (newAddType == 3) {
                                            addType = "Almonds";
                                            break;
                                        } else {
                                            System.out.println("\nInvalid choice. Please try again.");
                                        }
                                    }
                                    cookie.setAddOn('Y');
                                    cookie.setAddType(addType);
                                    System.out.println("\nStatus updated!");
                                    break;
                                } else if (newAddOn == 'N' || newAddOn == 'n') {
                                    cookie.setAddOn('N');
                                    cookie.setAddType("-");
                                    System.out.println("\nStatus updated!");
                                    break;
                                } else {
                                    System.out.println("\nInvalid input. Please enter 'Y' or 'N'.");
                                }
                            }
                        } else if (choice == 4) {
                            int newaddtype;
    
                            System.out.println("\nEnter new add type");
                            System.out.println("_____________________ ");
                            System.out.println("| 1 | Chocolate Chip |");
                            System.out.println("|___|________________|");
                            System.out.println("| 2 | Walnuts        |");
                            System.out.println("|___|________________|");
                            System.out.println("| 3 | Almonds        |");
                            System.out.println("|___|________________|");
    
                            String NewAddType = " ";
                            while (true) {
                                System.out.print("\nEnter your choice(1|2|3): ");
                                newaddtype = in.nextInt();
                                if (newaddtype == 1) {
                                    NewAddType = "Chocolate Chip";
                                    break;
                                } else if (newaddtype == 2) {
                                    NewAddType = "Walnuts";
                                    break;
                                } else if (newaddtype == 3) {
                                    NewAddType = "Almonds";
                                    break;
                                } else {
                                    System.out.println("\nInvalid flavor. Please try again.");
                                }
                            }
                            System.out.println("\nStatus updated!");
                            cookie.setAddType(NewAddType);
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    }
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } else {
            System.out.println("No orders found for Customer Name: " + searchName);
        }
    }
    public static void deleteOrder(LinkedList<Dessert> orderList, String filePath) {
        Scanner in = new Scanner(System.in);
    
        System.out.print("\nEnter customer name to delete: ");
        String searchName = in.nextLine();
    
        boolean delete = false;
        LinkedList<Dessert> tempLL = new LinkedList<>();
    
        // Process the linked list
        while (!orderList.isEmpty()) {
            Dessert order = orderList.removeFirst();
            if (order.getCustName().equalsIgnoreCase(searchName)) {
                delete = true;
                System.out.println("\nOrder for customer has been deleted.");
            } else {
                tempLL.addLast(order); // Keep orders that don't match
            }
        }
    
        // Restore the orders back to the original list
        while (!tempLL.isEmpty()) {
            orderList.addLast(tempLL.removeFirst());
        }
    
        if (!delete) {
            System.out.println("No order found for customer '" + searchName + "'.");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                while (!orderList.isEmpty()) {
                    Dessert order = orderList.removeFirst();
                    writer.write(order.toString()); // Write each order to the file
                    writer.newLine();
                    tempLL.addLast(order); // Temporarily store the orders
                }
        
                // Restore the orders back to the original list
                while (!tempLL.isEmpty()) {
                    orderList.addLast(tempLL.removeFirst());
                }
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
    }
    public static void displayOrder(LinkedList<Dessert> orderList) { //D
        System.out.println("\nCake Orders");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Customer Name        Phone Number    Order Type   Flavor          Quantity   Candle     Lettering                 Price (RM)");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    
        double totalCakePrice = 0;
        double totalCookiePrice = 0;
    
        LinkedList<Dessert> tempList = new LinkedList<>(); 
        LinkedList<Dessert> cakeList = new LinkedList<>(); 
        LinkedList<Dessert> cookieList = new LinkedList<>();
    
        while (!orderList.isEmpty()) {
            Dessert order = orderList.removeFirst();
    
            if (order instanceof CakeOrder) {
                cakeList.addLast(order); // Save cake orders separately
            } else if (order instanceof CookieOrder) {
                cookieList.addLast(order); // Save cookie orders separately
            }
    
            // Add the order back to the tempList
            tempList.addLast(order);
        }
    
        // Restore original order list
        while (!tempList.isEmpty()) {
            orderList.addLast(tempList.removeFirst());
        }
    
        // Display cake orders
        while (!cakeList.isEmpty()) {
            CakeOrder cakeOrder = (CakeOrder) cakeList.removeFirst();
    
            System.out.printf("%-20s %-15s %-12s %-15s %-10d %-10d %-25s %.2f%n",
                    cakeOrder.getCustomer().getCustName(),
                    cakeOrder.getCustomer().getCustPhone(),
                    cakeOrder.getType(),
                    cakeOrder.getFlavor(),
                    cakeOrder.getQty(),
                    cakeOrder.getCandle(),
                    cakeOrder.getLettering(),
                    cakeOrder.calcTotal());
    
            totalCakePrice += cakeOrder.calcTotal();
        }
    
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Total Price for Cake Orders: RM %.2f%n", totalCakePrice);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    
        System.out.println("\nCookie Orders");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Customer Name        Phone Number    Order Type   Flavor          Quantity   Add-On?         AddType              Price (RM)");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    
        // Display cookie orders
        while (!cookieList.isEmpty()) {
            CookieOrder cookieOrder = (CookieOrder) cookieList.removeFirst();
    
            String setAddOn;
            String addOnType;
            if (cookieOrder.getAddOn() == 'Y' || cookieOrder.getAddOn() == 'y') {
                setAddOn = "Yes";
            } else {
                setAddOn = "No";
            }
            if (setAddOn.equals("Yes")) {
                addOnType = cookieOrder.getAddType();
            } else {
                addOnType = "-";
            }

    
            // Display cookie details
            System.out.printf("%-20s %-15s %-12s %-15s %-10d %-15s %-20s %.2f%n",
                    cookieOrder.getCustomer().getCustName(),
                    cookieOrder.getCustomer().getCustPhone(),
                    cookieOrder.getType(),
                    cookieOrder.getFlavor(),
                    cookieOrder.getQty(),
                    setAddOn,
                    addOnType,
                    cookieOrder.calcTotal());
    
            totalCookiePrice += cookieOrder.calcTotal();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Total Price for Cookie Orders: RM %.2f%n", totalCookiePrice);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }
    public static void sortOrder(LinkedList<Dessert> orderList, String filePath) { //E  
        if (orderList.isEmpty()) {
            System.out.println("The order list is empty. Nothing to sort.");
            return;
        }
        Scanner in = new Scanner(System.in);
        
        int sortBy = 0;
        while(true){
            System.out.println("\nChoose an attribute to sort by:");
            System.out.println("______________");
            System.out.println("| 1 | Name   |");
            System.out.println("|___|________|");
            System.out.println("| 2 | Phone  |");
            System.out.println("|___|________|");
        
            System.out.print("\nEnter your choice(1|2): ");
            sortBy = in.nextInt();
            if (sortBy == 1 || sortBy == 2){
                break;
            }
            else{
                System.out.println("\nInvalid input");
            }
        }
    
        LinkedList<Dessert> sortedList = new LinkedList<>();
        while (!orderList.isEmpty()) {
            Dessert min = null;
            LinkedList<Dessert> tempList = new LinkedList<>();
    
            // Find the minimum Dessert object based on the sortBy attribute
            while (!orderList.isEmpty()) {
                Dessert current = orderList.removeFirst();
    
                if (min == null || (sortBy == 1
                        ? current.getCustomer().getCustName().compareToIgnoreCase(min.getCustomer().getCustName()) < 0
                        : current.getCustomer().getCustPhone().compareToIgnoreCase(min.getCustomer().getCustPhone()) < 0)) {
                    if (min != null) {
                        tempList.addLast(min);
                    }
                    min = current;
                } else {
                    tempList.addLast(current);
                }
            }
    
            // Rebuild the orderList excluding the minimum element
            while (!tempList.isEmpty()) {
                orderList.addLast(tempList.removeFirst());
            }
    
            // Add the minimum element to the sorted list
            if (min != null) {
                sortedList.addLast(min);
            }
        }
    
        //Restore the sorted order to the original list
        while (!sortedList.isEmpty()) {
            orderList.addLast(sortedList.removeFirst());
        }
    
        //Write the sorted list to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            LinkedList<Dessert> tempList = new LinkedList<>();
            while (!orderList.isEmpty()) {
                Dessert order = orderList.removeFirst();
                writer.write(order.toString());
                writer.newLine();
                tempList.addLast(order);
            }
    
            // Restore the original list after writing to the file
            while (!tempList.isEmpty()) {
                orderList.addLast(tempList.removeFirst());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}