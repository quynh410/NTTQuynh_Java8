package ra.presentation;

import ra.business.CustomerBusiness;
import ra.business.OrderBusiness;
import ra.entity.Order;

import java.util.List;
import java.util.Scanner;

public class ShopManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderBusiness orderBusiness = new OrderBusiness();
        while (true) {
            System.out.println("**********************SHOP MENU******************* ");
            System.out.println("1. Quản lý khách hàng");
            System.out.println("2. Quản lý đơn hàng");
            System.out.println("3. Thoát \n");
            System.out.println("***********************+++++++******************** ");
            System.out.print("Chọn chức năng 1-3: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    managerCustomer(sc);
                    break;
                case 2:
                    managerOrders(sc);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("lựa chọn ko hợp lệ. Vui lòng chọn lại");
            }
        }
    }

    private static void managerCustomer(Scanner sc) {
        while (true) {
            System.out.println("***********************CUSTOMER MENU****************** \n");
            System.out.println("1. Danh sách khách hàng");
            System.out.println("2. Thêm mới khách hàng ");
            System.out.println("3. Thoát         \n");
            System.out.println("******************************************************");
            System.out.print("Chọn chức năng 1-3: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    CustomerBusiness.displayCustomer();
                    break;
                case 2:
                    CustomerBusiness.addCustomer(sc);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("lựa chọn ko hợp lệ. Vui lòng chọn lại");
            }
        }
    }

    private static void managerOrders(Scanner sc) {
        OrderBusiness order = new OrderBusiness();
        while (true) {
            System.out.println("\n*************************ORDER MENU******************** ");
            System.out.println("1. Danh sách đơn hàng");
            System.out.println("2. Thêm mới đơn hàng");
            System.out.println("3. Cập nhật trạng thái đơn hàng");
            System.out.println("4. Danh sách đơn hàng quá hạn");
            System.out.println("5. Thống kê số lượng đơn hàng đã giao (Trạng thái true)");
            System.out.println("6. Tính tổng doanh thu các đơn hàng đã giao");
            System.out.println("7. Thoát");
            System.out.println("********************************************************");
            System.out.print("Chọn chức năng 1-7: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    order.displayOrder();
                    break;
                case 2:
                    order.addOrder(sc);
                    break;
                case 3:
                    order.updateOrderStatus(sc);
                    break;
                case 4:
                    List<Order> overdue = order.getOrderOverdue();
                    displayOverdueOrders(overdue);
                    break;
                case 5:
                    List<Order> delivered = order.getOrderDelivied();
                    System.out.println("Số lượng đơn hàng đã giao: " + delivered.size());
                    break;
                case 6:
                    List<Order> revenueList = order.getOrderDelivied();
                    double total = revenueList.stream()
                            .mapToDouble(Order::getTotalAmount)
                            .sum();
                    System.out.printf("Tổng doanh thu các đơn hàng đã giao: %.2f\n", total);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
            }
        }
    }

    private static void displayOverdueOrders(List<Order> overdueOrders) {
        if (overdueOrders.isEmpty()) {
            System.out.println("Không có đơn hàng nào quá hạn!!!");
            return;
        }

        // Tiêu đề bảng
        System.out.println("\n======================== DANH SÁCH ĐƠN HÀNG QUÁ HẠN ========================");
        System.out.printf("%-10s %-20s %-20s %-15s %-15s\n",
                "ID ĐƠN", "KHÁCH HÀNG", "NGÀY ĐẶT HÀNG", "TỔNG TIỀN", "TRẠNG THÁI");
        System.out.println("------------------------------------------------------------------------");

        // Hiển thị từng đơn hàng
        for (Order o : overdueOrders) {
            System.out.printf("%-10d %-20s %-20s %-15.2f %-15s\n",
                    o.getId(),
                    o.getCustomer().getCus_name(),
                    o.getOrderDate(),
                    o.getTotalAmount(),
                    "Chưa giao");
        }

        System.out.println("========================================================================");
        System.out.printf("Tổng số đơn hàng quá hạn: %d\n", overdueOrders.size());
    }
}
