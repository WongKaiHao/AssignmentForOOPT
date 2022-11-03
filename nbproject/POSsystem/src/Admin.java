import java.text.DecimalFormat;
import java.util.ArrayList;

public class Admin extends Staff {

    private String adminName;
    private String password;
    private ArrayList<Cashier> cashierList;
    private ArrayList<Product> productList;

    public Admin() {
    }

    public Admin(ArrayList<Cashier> cashierList, ArrayList<Product> productList) {
        super("A0001");
        this.adminName = "SYS";
        this.password = "Admin123";
        this.cashierList = cashierList;
        this.productList = productList;
    }

    public Admin(String id,String adminName, String password) {
        super(id);
        this.adminName = adminName;
        this.password = password;
        this.cashierList = new ArrayList<Cashier>();
        this.productList = new ArrayList<Product>();
    }

    public Admin(String adminName, String password, ArrayList<Cashier> cashierList, String staffID) {
        super(staffID);
        this.adminName = adminName;
        this.password = password;
        this.cashierList = cashierList;
        this.productList = new ArrayList<Product>();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Cashier> getCashierList() {
        return cashierList;
    }

    public void setCashierList(ArrayList<Cashier> cashierList) {
        this.cashierList = cashierList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void addCashier(Cashier cashier) {
        this.cashierList.add(cashier);
    }

    public void viewCashierList() {
        for (int i = 0; i < cashierList.size(); i++) {
            System.out.println(cashierList.get(i).toString());
        }
    }

    public void searchOrderHandleByCashier(String id) {
        DecimalFormat dp = new DecimalFormat("#.00");
        for (int i = 0; i < cashierList.size(); i++) {
            if (id.equals(cashierList.get(i).getStaffID())) {
                cashierList.get(i).showCashierName();
                for (int j = 0; j < cashierList.get(i).getOrderList().size(); j++) {
                    System.out.println(cashierList.get(i).getOrderList().get(j).getOrderID() + " \t\t " + cashierList.get(i).getOrderList().get(j).getOrderDate()+" \t\t " + dp.format(cashierList.get(i).getOrderList().get(j).getTotal()));
                }
            }
        }
    }

    public boolean deleteCashier(String id) {
        boolean validateDelete=false;
        for (int i = 0; i < cashierList.size(); i++) {
            if (id.matches(cashierList.get(i).getStaffID())) {
                cashierList.remove(i);
                validateDelete=true;
            }
        }
        return validateDelete;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void viewProductList() {
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).toString());
        }
    }

    public boolean deleteProduct(String prodId) {
        boolean validateDelete=false;
        for (int i = 0; i < productList.size(); i++) {
            if (prodId.matches(productList.get(i).getProductID())) {
                productList.remove(i);
                validateDelete=true;
            }
        }
        return validateDelete;
    }

    public void updateOrderHandle(int i, Order order) {
        cashierList.get(i).addOrder(order);
    }

    @Override
    public boolean validateLogin(String username, String pass) {
        return username.equalsIgnoreCase(this.adminName) && pass.equals(this.password);
    }
}
