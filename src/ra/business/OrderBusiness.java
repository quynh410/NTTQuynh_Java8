package ra.business;

import ra.entity.Customer;
import ra.entity.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderBusiness implements IOrderBusiness {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void addOrder(Scanner scanner) {
        List<Customer> customers = CustomerBusiness.getCustomers();
        if (customers.isEmpty()) {
            System.out.println("Chưa có khách hàng. Vui lòng thêm khách hàng!");
            return;
        }

        System.out.println("Chọn khách hàng theo ID: ");
        customers.forEach(customer -> System.out.println("ID: " + customer.getId() + " - Tên: " + customer.getCus_name()));

        try {
            int customerId = Integer.parseInt(scanner.nextLine());
            Optional<Customer> optionalCustomer = customers.stream()
                    .filter(c -> c.getId() == customerId)
                    .findFirst();

            if (optionalCustomer.isEmpty()) {
                System.out.println("Khách hàng không tồn tại.");
                return;
            }

            Order order = new Order();
            order.setCustomer(optionalCustomer.get());  // Gán customer cho order
            order.inputData(scanner);
            orders.add(order);
            System.out.println("Thêm đơn hàng thành công!");
        } catch (NumberFormatException e) {
            System.out.println("ID khách hàng phải là số nguyên hợp lệ.");
        }
    }


    @Override
    public void displayOrder() {
        if (orders.isEmpty()) {
            System.out.println("Không có đơn hàng nào.");
            return;
        }

        // Tiêu đề bảng
        System.out.println("\n======================== DANH SÁCH ĐƠN HÀNG ========================");
        System.out.printf("%-10s %-20s %-20s %-15s %-15s\n",
                "ID ĐƠN", "KHÁCH HÀNG", "NGÀY ĐẶT HÀNG", "TỔNG TIỀN", "TRẠNG THÁI");
        System.out.println("------------------------------------------------------------------");

        // Hiển thị từng đơn hàng
        for (Order order : orders) {
            System.out.printf("%-10d %-20s %-20s %-15.2f %-15s\n",
                    order.getId(),
                    order.getCustomer().getCus_name(),
                    order.getOrderDate(),
                    order.getTotalAmount(),
                    (order.getStatus() ? "Đã giao" : "Chưa giao"));
        }

        System.out.println("==================================================================");
        System.out.printf("Tổng số đơn hàng: %d\n", orders.size());
    }

    @Override
    public void updateOrderStatus(Scanner scanner) {
        if (orders.isEmpty()) {
            System.out.println("Không có đơn hàng để cập nhật.");
            return;
        }

        System.out.println("Nhập ID đơn hàng cần cập nhật trạng thái: ");
        try {
            int orderId = Integer.parseInt(scanner.nextLine());
            Optional<Order> optionalOrder = orders.stream()
                    .filter(order -> order.getId() == orderId)
                    .findFirst();

            if (optionalOrder.isPresent()) {
                optionalOrder.get().setStatus(true);
                System.out.println("Cập nhật trạng thái thành công!");
            } else {
                System.out.println("Không tìm thấy đơn hàng với ID: " + orderId);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID đơn hàng phải là số nguyên hợp lệ.");
        }
    }

    @Override
    public List<Order> getOrderOverdue() {
        return orders.stream()
                .filter(order -> !order.getStatus() && order.getOrderDate().isBefore(LocalDate.now().minusDays(7)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrderDelivied() {
        return orders.stream()
                .filter(Order::getStatus)
                .collect(Collectors.toList());
    }
}