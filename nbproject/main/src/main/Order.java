package main;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private String orderID;
    private ArrayList<Integer> quantity;
    private double total;
    private double tax;
    private double discount;
    private LocalDate orderDate;
    private ArrayList<Product> orderProduct;

    public Order() {
    }

    public Order(String orderID, ArrayList<Integer> quantity, double total, double tax, double discount, LocalDate orderDate, ArrayList<Product> orderProduct) {
        this.orderID = orderID;
        this.quantity = quantity;
        this.total = total;
        this.tax = tax;
        this.discount = discount;
        this.orderDate = orderDate;
        this.orderProduct = orderProduct;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(ArrayList<Integer> quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<Product> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(ArrayList<Product> orderProduct) {
        this.orderProduct = orderProduct;
    }
    
    public void addOrderProduct(Product product) {
        orderProduct.add(product);
    }
    
    public double countDiscount(double subTotal,double discount){
        this.discount = subTotal * discount;
        return this.discount;
    }

    public double countTax(double subTotal){
        this.tax = subTotal * 0.06;
        return tax;
    }
    
    public double countTotal(double subTotal){
        return (subTotal - this.discount + this.tax);
    }
    
    public double countBalance(double cash,double subTotal){
        return cash - (subTotal - this.discount + this.tax);
        
    }
    
    public String toString(){
        return this.orderID;
    }
}