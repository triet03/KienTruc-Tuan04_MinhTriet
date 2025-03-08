package org.example;

import java.util.ArrayList;
import java.util.List;

// Interface chiến lược tính thuế
interface TaxStrategy {
    double calculateTax(double price);
}

// Thuế tiêu thụ (Consumption Tax)
class ConsumptionTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return price * 0.05; // 5% thuế
    }
}

// Thuế VAT (Value Added Tax)
class VATTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return price * 0.1; // 10% VAT
    }
}

// Thuế xa xỉ (Luxury Tax)
class LuxuryTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return price * 0.2; // 20% thuế xa xỉ
    }
}

// Lớp Product chứa thông tin sản phẩm và áp dụng chiến lược thuế
class Product {
    private String name;
    private double price;
    private TaxStrategy taxStrategy;

    public Product(String name, double price, TaxStrategy taxStrategy) {
        this.name = name;
        this.price = price;
        this.taxStrategy = taxStrategy;
    }

    public double getFinalPrice() {
        return price + taxStrategy.calculateTax(price);
    }

    public void displayInfo() {
        System.out.println(name + " | Giá gốc: " + price + " | Giá sau thuế: " + getFinalPrice());
    }
}

// Chạy chương trình
public class StrategyPatternExample {
    public static void main(String[] args) {
        // Danh sách sản phẩm
        List<Product> products = new ArrayList<>();
        products.add(new Product("Sữa", 50000, new ConsumptionTax()));
        products.add(new Product("Điện thoại", 15000000, new VATTax()));
        products.add(new Product("Đồng hồ Rolex", 200000000, new LuxuryTax()));

        // Hiển thị thông tin sản phẩm sau khi tính thuế
        for (Product product : products) {
            product.displayInfo();
        }
    }
}

