import java.text.DecimalFormat;
//import java.time.LocalDate;
//import java.time.Month;

public class Product {
    private String productID;
    private String productName;
    private int quantity;
    private double unitPrice;
    //private LocalDate promotionTime;
    //private double pDiscount;
    
    public Product() {
    }

    public Product(String productID, String productName, int quantity, double unitPrice) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    //    this.promotionTime=new LocalDate();
    //    this.pDiscount=0.00;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

//    public LocalDate getPromotionTime() {
//        return promotionTime;
//    }
//
//    public void setPromotionTime(LocalDate promotionTime) {
//        this.promotionTime = promotionTime;
//    }
    
    @Override
    public String toString() {
        DecimalFormat dp = new DecimalFormat("#.00");
        return " " + productID+" \t\t "+ productName + " \t\t " + quantity + " \t\t " + dp.format(unitPrice);
    }
}