package ra.business;

import ra.entity.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerBusiness {
    private static ArrayList<Customer> customers = new ArrayList<>();

    public static void addCustomer(Scanner sc) {
        Customer customer = new Customer();
        customer.inputData(sc);
        customers.add(customer);
        System.out.println("Thêm khách hàng thành công!!!");
    }

    public static void displayCustomer() {
        System.out.println("Danh sách khách hàng:");
        for(Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }
    public static ArrayList<Customer> getCustomers() {
        return customers;
    }
}
