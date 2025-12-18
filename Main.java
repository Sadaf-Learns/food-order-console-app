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
            System.out.println("1-ثبت سفارش");
            System.out.println("2-مشاهده سفارش");
            System.out.println("3-ویرایش سفارش");
            System.out.println("4-حذف شفارش");
            System.out.println("0-خروج");
            System.out.println("عدد مورد نظر راوارد کنید:");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("گزینه1-ثبت سفارش");
                    System.out.println("نام غذا را وارد کنید.");
                    String foodName = scanner.nextLine();
                    System.out.println("آیا سفارش بیرون بر است؟(y/n) :");
                    String deliveryInput = scanner.nextLine();
                    boolean isDelivery = deliveryInput.equalsIgnoreCase("y");
                    String address = "";
                    if (isDelivery) {
                        while (true) {
                            System.out.println("آدرس را وارد کنید: ");
                            address = scanner.nextLine();
                            if (!address.isEmpty()) {
                                break;
                            }
                            System.out.println("آدرس نمیتواند خالی باشد.");
                        }
                    }
                    System.out.println("ساعت و تاریخ تحویل را مشخص کنید: (yyyy-MM-ddTHH:mm) ");
                    String deliveryTimeInput=scanner.nextLine();
                    LocalDateTime deliveryDateTime=LocalDateTime.parse(deliveryTimeInput);
                    String date = java.time.LocalDate.now().toString();
                    String dateTime = java.time.LocalDateTime.now().toString();
                    int id = orders.size() + 1;
                    Order order = createOrder(id,foodName,isDelivery,address,deliveryDateTime); //R
                    orders.add(order);
                    System.out.println("سفارش با شناسه " + id + "ثبت شد! ");

                    break;
                case 2:
                    System.out.println("گزینه2-مشاهده سفارش");
                    System.out.println("شناسه سفارش را وارد کنید: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    Order foundorder = findOrderId(orders,searchId); //R

                    if (foundorder != null) {
                        System.out.println("...اطلاعات سفارش...");
                        System.out.println("شناسه:" + foundorder.id);
                        System.out.println("نام غذا:" + foundorder.foodName);
                        System.out.println("تاریخ ثبت: " + foundorder.date);
                        System.out.println("بیرون بر: " + (foundorder.isDelivery ? "بله" : "خیر"));
                        if (foundorder.isDelivery) {
                            System.out.println("آدرس:" + foundorder.address);
                        }
                        System.out.println("..........");
                    } else {
                        System.out.println("سفارشی با این شناسه پیدا نشد.");
                    }
                    break;
                case 3:
                    System.out.println("گزینه3-ویرایش سفارش");
                    System.out.println("شناسه سفارشی که میخواهید ادیت کنید وارد کنید: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();
                    Order editOrder = findOrderId(orders,editId); //R
                    if (editOrder == null) {
                        System.out.println("سفارشی بااین شناسه یافت نشد. ");
                        break;
                    }
                    java.time.LocalDateTime registerTime = java.time.LocalDateTime.parse(editOrder.dateTime);
                    java.time.LocalDateTime now = java.time.LocalDateTime.now();
                    long minutesPassed = java.time.Duration.between(registerTime, now).toMinutes();
                    if (minutesPassed > 10) {
                        System.out.println("زمان زیادی گذشته وامکان ویرایش نیست.");
                        break;
                    }
                    System.out.println("چه چیزی را می‌خواهید ویرایش کنید؟");
                    System.out.println("1- نام غذا");
                    System.out.println("2- وضعیت بیرون‌بر");
                    System.out.println("3- آدرس");
                    System.out.println("0- انصراف");
                    int editChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (editChoice) {
                        case 1:
                            System.out.println("نام غذای جدید وارد کنید: ");
                            editOrder.foodName = scanner.nextLine();
                            System.out.println("نام غذا تغییر کرد.");
                            break;
                        case 2:
                            System.out.println("آیا غذا بیرون بر باشد؟(y/n)");
                            String d = scanner.nextLine();
                            editOrder.isDelivery = d.equalsIgnoreCase("y");//Y=y,N=n(equalsignorecase)
                            if (editOrder.isDelivery) {
                                System.out.println("ادرس جدید را وارد کنید: ");
                                editOrder.address = scanner.nextLine();
                            } else {
                                editOrder.address = "";
                            }

                    System.out.println("وضعیت بیرون بر تغییر کرد. ");
                    break;
                case 3:
                    if (!editOrder.isDelivery) {
                        System.out.println("سفارش بیرون بر نیست. ");
                        break;
                    }
                    System.out.println("آدرس جدید را وارد کنید: ");
                    editOrder.address = scanner.nextLine();
                    System.out.println("آدرس تغییر کرد!");
                    break;
                case 0:
                    System.out.println("انصراف");
                    break;
                default:
                    System.out.println("انتخاب نامعتبر است.");
            }
            break;
                case 4:
                    System.out.println("گزینه4-حذف سفارش");
                    System.out.println("شناسه سفارشی که میخواهید حذف کنید را وارد کنید: ");
                    int deleteId=scanner.nextInt();
                    scanner.nextLine();
                    Order deleteOrder = findOrderId(orders,deleteId); //R

                    if (deleteOrder == null) {
                        System.out.println("سفارشی بااین شناسه پیدا نشد.");
                        break;
                    }
                    orders.remove(deleteOrder); //orders arraylist=> remove(obj)
                    System.out.println( "سفارش شناسه "+ deleteId+"حذف شد ");
                    break;
                case 5:
                    System.out.println("زمان تحویل را مشخص کنید");
                case 0:
                    System.out.println("خروج از برنامه");
                    return;
                default:
                    System.out.println("بین بازه 0-4 انتخاب شود");


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