package ra.business;

import ra.entity.Customer;
import ra.entity.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderBusiness implements IOrderBusiness{
    private List<Order> orders = new ArrayList<>();

    @Override
    public void addOrder(Scanner scanner) {
        if(CustomerBusiness.getCustomers().isEmpty()){
            System.out.println("Chưa có khách hàng . Vui lòng thêm khách hàng!!!");
            return;
        }
        System.out.println("Chọn khách hàng theo ID: ");
        for(Customer customer : CustomerBusiness.getCustomers()){
            System.out.println(customer.getId());
        }
        int customerId = Integer.parseInt(scanner.nextLine());
        Customer customer = CustomerBusiness.getCustomers().stream().filter(c -> c.getId() == customerId).findFirst().orElse(null);
        if(customer == null){
            System.out.println("Khách hàng ko tồn tại");
            return;
        }
        Order order = new Order();
        order.inputData(scanner);
        orders.add(order);
        System.out.println("Thêm đơn hàng thành công!!");
    }

    @Override
    public void displayOrder() {
        System.out.println("Danh sách đơn hàng");
        for(Order order : orders){
            System.out.println(order);
        }
    }

    @Override
    public void updateOrderStatus(Scanner scanner) {
        System.out.println("Nhập id đơn hàng cần cập nhật: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        for(Order order : orders){
            if(order.getId() == orderId){
                order.setStatus(true);
                System.out.println("Cập nhật trạng thái thành công");
                return;
            }
        }
        System.out.println("Không tìm thấy đơn hàng");
    }

    @Override
    public List<Order> getOrderOverdue() {
        return orders.stream().filter(order -> !order.getStatus() && order.getOrderDate().isBefore(LocalDate.now().minusDays(7))).collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrderDelivied() {
        // lấy danh sách các đơn hàng đã giao
        return ;
    }
}
