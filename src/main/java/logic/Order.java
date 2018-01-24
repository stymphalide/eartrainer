package logic;


public class Order extends Feature {

    Order(String order) {
        super(order, "order")
        this.name = order;
    }
    public String getOrderName() {
        return this.name;
    }
}