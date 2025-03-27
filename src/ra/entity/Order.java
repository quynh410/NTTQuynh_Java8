package ra.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Order implements IApp {
    private static int idCounter = 1;
    private int id;
    private Customer customer;
    private LocalDate orderDate;
    private Double totalAmount;
    private Boolean status;

    public Order() {
        this.id = idCounter++;
    }

    public Order(Customer customer, LocalDate orderDate, Double totalAmount) {
        this.id = idCounter++;
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = false;
    }

    @Override
    public void inputData(Scanner sc) {
        while (true) {
            try {
                System.out.println("Nhập ngày đặt hàng (dd/MM/yyyy):");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                this.orderDate = LocalDate.parse(sc.nextLine(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy");
            }
        }

        while (true) {
            try {
                System.out.println("Nhập tổng tiền đơn hàng:");
                this.totalAmount = Double.parseDouble(sc.nextLine());
                if (this.totalAmount <= 0) {
                    System.out.println("Tổng tiền phải là số dương.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Tổng tiền phải là số hợp lệ.");
            }
        }

        this.status = false;
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
