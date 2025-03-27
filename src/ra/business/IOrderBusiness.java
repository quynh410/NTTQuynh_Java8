package ra.business;

import ra.entity.Order;

import java.util.List;
import java.util.Scanner;

public interface IOrderBusiness {
    void addOrder(Scanner scanner);
    void displayOrder();
    void updateOrderStatus(Scanner scanner);
    List<Order> getOrderOverdue();
    List<Order> getOrderDelivied(); // lấy danh sách đơn hàng đã giao
    default double getTotalRevenue(){
        return getOrderOverdue().stream().filter(Order::getStatus).mapToDouble(Order::getTotalAmount).sum();
    }
}
