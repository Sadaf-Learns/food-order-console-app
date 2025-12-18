import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        ArrayList<Order> orders = new ArrayList<>();
        while (true){
            System.out.println("1-Create order");
            System.out.println("2-View order");
            System.out.println("3-Edit order");
            System.out.println("4-Delete order");
            System.out.println("0-Exit");
            System.out.println("Please enter your choice:");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Option1- Create order");
                    System.out.println("Enter food name");
                    String foodName = scanner.nextLine();
                    System.out.println("Is this order for delivery?(y/n): ");
                    String deliveryInput = scanner.nextLine();
                    boolean isDelivery = deliveryInput.equalsIgnoreCase("y");
                    String address = "";
                    if (isDelivery) {
                        while (true) {
                            System.out.println("Enter delivery address: ");
                            address = scanner.nextLine();
                            if (!address.isEmpty()) {
                                break;
                            }
                            System.out.println("Address cannot be empty.");
                        }
                    }
                    System.out.println("Enter delivery date and time (yyyy-MM-ddTHH:mm): ");
                    String deliveryTimeInput=scanner.nextLine();
                    LocalDateTime deliveryDateTime=LocalDateTime.parse(deliveryTimeInput);
                    int id = orders.size() + 1;
                    Order order = createOrder(id,foodName,isDelivery,address,deliveryDateTime); //R
                    orders.add(order);
                    System.out.println("Order with ID " + id + "has been successfully created.");

                    break;
                case 2:
                    System.out.println("Option2- View order");
                    System.out.println("Enter order ID: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    Order foundorder = findOrderId(orders,searchId); //R

                    if (foundorder != null) {
                        System.out.println("------Order Details------");
                        System.out.println("ID: " + foundorder.id);
                        System.out.println("Food Name: " + foundorder.foodName);
                        System.out.println("Order Date: " + foundorder.date);
                        System.out.println("Delivery: " + (foundorder.isDelivery ? "Yes" : "No"));
                        if (foundorder.isDelivery) {
                            System.out.println("Delivery Address: " + foundorder.address);
                        }
                        System.out.println("..........");
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;
                case 3:
                    System.out.println("Option3- Edit order");
                    System.out.println("Enter the order ID you want to edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();
                    Order editOrder = findOrderId(orders,editId); //R
                    if (editOrder == null) {
                        System.out.println("Order not found. ");
                        break;
                    }
                    java.time.LocalDateTime registerTime = java.time.LocalDateTime.parse(editOrder.dateTime);
                    java.time.LocalDateTime now = java.time.LocalDateTime.now();
                    long minutesPassed = java.time.Duration.between(registerTime, now).toMinutes();
                    if (minutesPassed > 10) {
                        System.out.println("Too much time has passed. Editing is not allowed. ");
                        break;
                    }
                    System.out.println("What do you want to edit?");
                    System.out.println("1- Food Name");
                    System.out.println("2- Delivery status");
                    System.out.println("3- Delivery address");
                    System.out.println("0- Cancel");
                    int editChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (editChoice) {
                        case 1:
                            System.out.println("Enter new food name: ");
                            editOrder.foodName = scanner.nextLine();
                            System.out.println("Food name updated successfully. ");
                            break;
                        case 2:
                            System.out.println("Is it Delivery?(y/n): ");
                            String d = scanner.nextLine();
                            editOrder.isDelivery = d.equalsIgnoreCase("y");//Y=y,N=n(equalsignorecase)
                            if (editOrder.isDelivery) {
                                System.out.println("Enter new address: ");
                                editOrder.address = scanner.nextLine();
                            } else {
                                editOrder.address = "";
                            }

                    System.out.println("Delivery status updated.");
                    break;
                case 3:
                    if (!editOrder.isDelivery) {
                        System.out.println("This order is not delivery.");
                        break;
                    }
                    System.out.println("Enter new address: ");
                    editOrder.address = scanner.nextLine();
                    System.out.println("Address update successfully.");
                    break;
                case 0:
                    System.out.println("Operation canceled.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            break;
                case 4:
                    System.out.println("Option4- Delete order");
                    System.out.println("Enter Id you want to delete: ");
                    int deleteId=scanner.nextInt();
                    scanner.nextLine();
                    Order deleteOrder = findOrderId(orders,deleteId); //R

                    if (deleteOrder == null) {
                        System.out.println("Order not found.");
                        break;
                    }
                    orders.remove(deleteOrder); //orders arraylist=> remove(obj)
                    System.out.println( "Order with ID"+ deleteId+"has been deleted.");
                    break;
                case 5:
                    System.out.println("Enter delivery time: ");
                case 0:
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Choose between 0 and 5.");


                }
            }
        }
        static Order findOrderId(ArrayList<Order> orders, int id) {
            for (Order o : orders) {
                if (o.id == id) {
                    return o;
                }
            }
            return null;
        }
        static Order createOrder(int id,String foodName,boolean isDelivery,String address,LocalDateTime deliveryDateTime){
        Order order=new Order();
            order.id = id;
            order.foodName = foodName;
            order.isDelivery = isDelivery;
            order.address = address;
            order.date = java.time.LocalDate.now().toString();
            order.dateTime = java.time.LocalDateTime.now().toString();
            order.deliveryDateTime = deliveryDateTime;
            return order;
        }
}