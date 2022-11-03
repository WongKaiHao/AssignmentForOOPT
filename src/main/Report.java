package main;

import java.util.ArrayList;

public class Report{
    private ArrayList<Integer> quantity;
    private double totalSaleAmountOfDay;
    private double totalTax;
    private double totalDiscountGiven;

    public Report() {
    }

    public Report(ArrayList<Integer> quantity, double totalSaleAmountOfDay, double totalTax, double totalDiscountGiven) {
        this.quantity = quantity;
        this.totalSaleAmountOfDay = totalSaleAmountOfDay;
        this.totalTax = totalTax;
        this.totalDiscountGiven = totalDiscountGiven;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(ArrayList<Integer> quantity) {
        this.quantity = quantity;
    }

    public double getTotalSaleAmountOfDay() {
        return totalSaleAmountOfDay;
    }

    public void setTotalSaleAmountOfDay(double totalSaleAmountOfDay) {
        this.totalSaleAmountOfDay = totalSaleAmountOfDay;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public double getTotalDiscountGiven() {
        return totalDiscountGiven;
    }

    public void setTotalDiscountGiven(double totalDiscountGiven) {
        this.totalDiscountGiven = totalDiscountGiven;
    }
}
