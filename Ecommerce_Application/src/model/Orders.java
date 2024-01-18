package model;

public class Orders {
    private int order_id;
    private int customer_id;
    private String order_date; // Change type to String
    private double total_price;
    private String shipping_address;

    // Constructor
    public Orders(int order_id, int customer_id, String order_date, double total_price, String shipping_address) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.total_price = total_price;
        this.shipping_address = shipping_address;
    }
    
    

    // Getter and Setter methods

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    // Other methods and functionalities can be added as needed
}

