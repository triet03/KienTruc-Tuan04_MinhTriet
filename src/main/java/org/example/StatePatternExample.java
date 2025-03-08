package org.example;

// Interface trạng thái đơn hàng
interface OrderState {
    void handleOrder(OrderContext context);
}

// Trạng thái Mới tạo
class NewOrderState implements OrderState {
    @Override
    public void handleOrder(OrderContext context) {
        System.out.println("Đơn hàng mới được tạo. Kiểm tra thông tin đơn hàng.");
        context.setState(new ProcessingOrderState());
    }
}

// Trạng thái Đang xử lý
class ProcessingOrderState implements OrderState {
    @Override
    public void handleOrder(OrderContext context) {
        System.out.println("Đơn hàng đang được xử lý. Đóng gói và vận chuyển.");
        context.setState(new DeliveredOrderState());
    }
}

// Trạng thái Đã giao
class DeliveredOrderState implements OrderState {
    @Override
    public void handleOrder(OrderContext context) {
        System.out.println("Đơn hàng đã được giao thành công.");
    }
}

// Trạng thái Hủy
class CancelledOrderState implements OrderState {
    @Override
    public void handleOrder(OrderContext context) {
        System.out.println("Đơn hàng đã bị hủy. Hoàn tiền cho khách hàng.");
    }
}

// Context quản lý trạng thái đơn hàng
class OrderContext {
    private OrderState state;

    public OrderContext() {
        this.state = new NewOrderState(); // Mặc định là trạng thái mới tạo
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void processOrder() {
        state.handleOrder(this);
    }
}

// Main chạy thử
public class StatePatternExample {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();

        order.processOrder(); // Chuyển từ Mới tạo -> Đang xử lý
        order.processOrder(); // Chuyển từ Đang xử lý -> Đã giao
        order.processOrder(); // Đã giao (không thay đổi)

        // Trường hợp hủy đơn
        OrderContext cancelledOrder = new OrderContext();
        cancelledOrder.setState(new CancelledOrderState());
        cancelledOrder.processOrder();
    }
}
