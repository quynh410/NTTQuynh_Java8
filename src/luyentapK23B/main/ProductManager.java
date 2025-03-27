package luyentapK23B.main;

import luyentapK23B.entity.Product;
import luyentapK23B.util.ProductFilter;

import java.util.*;
import java.util.stream.Collectors;

public class ProductManager {
    private static List<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayProductList();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProductById();
                    break;
                case 5:
                    searchProductByName();
                    break;
                case 6:
                    filterProducts();
                    break;
                case 7:
                    sortProducts();
                    break;
                case 8:
                    System.out.println("Thoát chương trình...");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("************************* MENU *************************");
        System.out.println("1. Danh sách sản phẩm");
        System.out.println("2. Thêm sản phẩm mới");
        System.out.println("3. Cập nhật sản phẩm");
        System.out.println("4. Xóa sản phẩm theo ID");
        System.out.println("5. Tìm kiếm sản phẩm theo tên");
        System.out.println("6. Lọc sản phẩm");
        System.out.println("7. Sắp xếp sản phẩm theo giá");
        System.out.println("8. Thoát");
        System.out.print("Nhập lựa chọn của bạn: ");
    }

    private static void displayProductList() {
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
            return;
        }
        products.forEach(Product::displayData);
    }

    private static void addProduct() {
        Product newProduct = new Product();
        newProduct.inputData(scanner);

        boolean isDuplicate = products.stream()
                .anyMatch(p -> p.getName().equals(newProduct.getName()));

        if (isDuplicate) {
            System.out.println("Tên sản phẩm đã tồn tại. Không thể thêm sản phẩm trùng.");
            return;
        }

        products.add(newProduct);
        System.out.println("Thêm sản phẩm thành công.");
    }

    private static void updateProduct() {
        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        int productId = Integer.parseInt(scanner.nextLine());

        Optional<Product> productToUpdate = products.stream()
                .filter(p -> p.getId() == productId)
                .findFirst();

        if (productToUpdate.isPresent()) {
            Product product = productToUpdate.get();
            product.inputData(scanner);
            System.out.println("Cập nhật sản phẩm thành công.");
        } else {
            System.out.println("Không tìm thấy sản phẩm.");
        }
    }

    private static void deleteProductById() {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        int productId = Integer.parseInt(scanner.nextLine());

        boolean removed = products.removeIf(p -> p.getId() == productId);

        if (removed) {
            System.out.println("Xóa sản phẩm thành công.");
        } else {
            System.out.println("Không tìm thấy sản phẩm.");
        }
    }

    private static void searchProductByName() {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String searchName = scanner.nextLine();

        List<Product> foundProducts = products.stream()
                .filter(p -> p.getName().toLowerCase().contains(searchName.toLowerCase()))
                .collect(Collectors.toList());

        if (foundProducts.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào.");
        } else {
            foundProducts.forEach(Product::displayData);
        }
    }

    private static void filterProducts() {
        System.out.println("Lọc sản phẩm:");
        System.out.println("1. Lọc theo giá");
        System.out.println("2. Lọc theo danh mục");
        System.out.print("Nhập lựa chọn của bạn: ");
        int filterChoice = Integer.parseInt(scanner.nextLine());

        switch (filterChoice) {
            case 1:
                filterByPrice();
                break;
            case 2:
                filterByCategory();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    private static void filterByPrice() {
        System.out.print("Nhập giá tối thiểu: ");
        float minPrice = Float.parseFloat(scanner.nextLine());

        ProductFilter priceFilter = product -> product.getPrice() > minPrice;

        List<Product> filteredProducts = products.stream()
                .filter(priceFilter::filter)
                .collect(Collectors.toList());

        if (filteredProducts.isEmpty()) {
            System.out.println("Không có sản phẩm nào có giá lớn hơn mức đã nhập.");
        } else {
            filteredProducts.forEach(Product::displayData);
        }
    }

    private static void filterByCategory() {
        System.out.print("Nhập danh mục cần lọc: ");
        String category = scanner.nextLine();

        ProductFilter categoryFilter = product -> product.getCategory().equalsIgnoreCase(category);

        List<Product> filteredProducts = products.stream()
                .filter(categoryFilter::filter)
                .collect(Collectors.toList());

        if (filteredProducts.isEmpty()) {
            System.out.println("Không có sản phẩm nào thuộc danh mục này.");
        } else {
            filteredProducts.forEach(Product::displayData);
        }
    }

    private static void sortProducts() {
        System.out.println("Sắp xếp sản phẩm:");
        System.out.println("1. Sắp xếp theo giá tăng dần");
        System.out.println("2. Sắp xếp theo giá giảm dần");
        System.out.print("Nhập lựa chọn của bạn: ");
        int sortChoice = Integer.parseInt(scanner.nextLine());

        switch (sortChoice) {
            case 1:
                products = products.stream()
                        .sorted(Comparator.comparing(Product::getPrice))
                        .collect(Collectors.toList());
                break;
            case 2:
                products = products.stream()
                        .sorted(Comparator.comparing(Product::getPrice).reversed())
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }

        System.out.println("Sắp xếp sản phẩm thành công.");
        displayProductList();
    }
}
