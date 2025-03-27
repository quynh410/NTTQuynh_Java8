package ra.entity;

import java.util.Optional;
import java.util.Scanner;

public class Customer implements IApp {
    private static int idCounter = 1;
    private  int id;
    private String cus_name;
    private Optional<String> email;

    public Customer() {
    }

    public Customer(String cus_name, Optional<String> email) {
        this.id = idCounter++;
        this.cus_name = cus_name;
        this.email = email;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Customer.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Nhập tên khách hàng: ");
        this.cus_name = sc.nextLine();
        System.out.println("Nhập email khách hàng: ");
        String emailInput = sc.nextLine();
        this.email = emailInput.isEmpty() ? Optional.empty() : Optional.of(emailInput);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", cus_name='" + cus_name + '\'' +
                ", email=" + email +
                '}';
    }
}
