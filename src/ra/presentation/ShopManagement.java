package ra.presentation;

import ra.business.CustomerBusiness;
import ra.business.OrderBusiness;

import java.util.Scanner;

public class ShopManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderBusiness orderbusiness = new OrderBusiness();
        while (true) {
            System.out.println("**********************SHOP MENU******************* ");
            System.out.println("1. Quản lý khách hàng");
            System.out.println("2. Quản lý đơn hàng");
            System.out.println("3. Thoát \n");
            System.out.println("***********************+++++++******************** ");
            System.out.println("Chọn chức năng: 1-3");
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
            System.out.println("Mời lựa chọn : 1-3");
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
        while (true) {
            System.out.println("*************************ORDER MENU******************** \n");
            System.out.println("1. Danh sách đơn hàng");
            System.out.println("2. Thêm mới đơn hàng");
            System.out.println("3. Cập nhật trạng thái đơn hàng");
            System.out.println("4. Danh sách đơn hàng quá hạn");
            System.out.println("5. Thống kê số lương đơn hàng đã giao (Trạng thái true)");
            System.out.println("6. Tính tỏngo doanh thu các đơn hàng đã giao");
            System.out.println("7. Thoát");
            System.out.println("********************************************************");
            System.out.println("Mời bạn lưa chọn : 1 - 7");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;

                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    return;
                default:
                    System.out.println("lưacj chọn ko hợp lệ . Vui lòng chọn lại");
            }
        }
    }
}
