package luyentapK23B.entity;

import luyentapK23B.Validator.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class Product implements IProduct{
    private static int idCounter = 1;
    private  int id;
    private String name;
    private float price;
    private String category;
    private LocalDate createdDate;

    public Product() {
        this.id = idCounter++;
        this.createdDate = LocalDate.now();
    }

    public Product(int id, String name, float price, String category, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.createdDate = createdDate;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Product.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!Validator.validateProductName(name)) {
            throw new IllegalArgumentException("Tên sản phẩm phải từ 30-100 ký tự");
        }
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (!Validator.validatePrice(price)) {
            throw new IllegalArgumentException("Giá sản phẩm phải lớn hơn 0");
        }
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    @Override
    public void inputData(Scanner scanner) {
        System.out.println("Nhập tên sản phẩm:");
        this.name = scanner.nextLine();

        System.out.println("Nhập giá sản phẩm:");
        this.price = Float.parseFloat(scanner.nextLine());

        System.out.println("Nhập danh mục sản phẩm:");
        this.category = scanner.nextLine();
    }

    @Override
    public void displayData() {
        System.out.printf("Mã sản phẩm: %d\n", id);
        System.out.printf("Tên sản phẩm: %s\n", name);
        System.out.printf("Giá: %.2f\n", price);
        System.out.printf("Danh mục: %s\n", category);
        System.out.printf("Ngày tạo: %s\n", createdDate);
    }


}
