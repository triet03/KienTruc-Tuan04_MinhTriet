package org.example;

// Interface chiến lược thanh toán
interface PaymentStrategy {
    void pay(double amount);
}

// Thanh toán bằng Thẻ tín dụng
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán " + amount + " bằng Thẻ tín dụng (" + cardNumber + ")");
    }
}

// Thanh toán bằng PayPal
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán " + amount + " qua PayPal (" + email + ")");
    }
}

// Lớp Payment cơ bản
class Payment {
    protected PaymentStrategy strategy;
    protected double amount;

    public Payment(PaymentStrategy strategy, double amount) {
        this.strategy = strategy;
        this.amount = amount;
    }

    public void processPayment() {
        strategy.pay(amount);
    }
}

// Decorator để thêm phí xử lý
class ProcessingFeeDecorator extends Payment {
    private double fee;

    public ProcessingFeeDecorator(Payment basePayment, double fee) {
        super(basePayment.strategy, basePayment.amount + fee);
        this.fee = fee;
    }

    @Override
    public void processPayment() {
        System.out.println("Áp dụng phí xử lý: " + fee);
        strategy.pay(amount);
    }
}

// Decorator để áp dụng mã giảm giá
class DiscountDecorator extends Payment {
    private double discount;

    public DiscountDecorator(Payment basePayment, double discount) {
        super(basePayment.strategy, basePayment.amount - discount);
        this.discount = discount;
    }

    @Override
    public void processPayment() {
        System.out.println("Áp dụng mã giảm giá: -" + discount);
        strategy.pay(amount);
    }
}

// Chạy chương trình
public class PaymentSystem {
    public static void main(String[] args) {
        // Thanh toán bằng thẻ tín dụng, không có phí xử lý
        Payment payment1 = new Payment(new CreditCardPayment("1234-5678-9876-5432"), 500000);
        payment1.processPayment();

        System.out.println("----------------------");

        // Thanh toán qua PayPal + Phí xử lý
        Payment payment2 = new ProcessingFeeDecorator(
                new Payment(new PayPalPayment("user@example.com"), 300000), 15000);
        payment2.processPayment();

        System.out.println("----------------------");

        // Thanh toán bằng thẻ tín dụng + Phí xử lý + Giảm giá
        Payment payment3 = new DiscountDecorator(
                new ProcessingFeeDecorator(
                        new Payment(new CreditCardPayment("4321-8765-6789-1234"), 1000000), 20000), 50000);
        payment3.processPayment();
    }
}

