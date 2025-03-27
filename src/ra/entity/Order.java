package ra.entity;

import java.time.LocalDate;
import java.util.Scanner;

public class Order implements IApp {
    private  static int idCounter = 1;
    private int id;
    private Customer customer;
    private LocalDate orderDate;
    private Double totalAmount;
    private Boolean status;

    public Order() {
    }

    public Order(int id, Customer customer, LocalDate orderDate, Double totalAmount, Boolean status) {
        this.id = idCounter++;
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Order.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Nhập ngày đặt hàng dd/mm/yyyy");
        this.orderDate  = LocalDate.parse(sc.nextLine());
        System.out.println("Nhập tổng tiền đơn hàng: ");
        this.totalAmount = Double.parseDouble(sc.nextLine());
        this.status = false;

    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }
}
