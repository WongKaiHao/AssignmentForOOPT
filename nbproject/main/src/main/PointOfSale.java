package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class PointOfSale {//main class

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int wrongCountAdmin = 0;
        int wrongCountStaff = 0;
        int choice = 0;
        String selection, username, pass;
        char last = 'Y';
        String newPass;
        String confirmPass;
        boolean login = false;
        LocalDate salesDate = LocalDate.of(2020, Month.MARCH, 01);
        ArrayList<Product> product = new ArrayList<Product>();
        Product product1 = new Product("P0001", "Paracetamol", 300, 2.50);
        Product product2 = new Product("P0002", "Aspirin", 300, 3.50);
        Product product3 = new Product("P0003", "Vitamin-C", 300, 1.50);
        product.add(product1);
        product.add(product2);
        product.add(product3);
        ArrayList<Product> sample = new ArrayList<Product>();
        sample.add(product1);
        sample.add(product2);
        sample.add(product3);
        ArrayList<Integer> quantity = new ArrayList<Integer>();
        quantity.add(25);
        quantity.add(25);
        quantity.add(25);
        ArrayList<Order> order = new ArrayList<Order>();
        Order order1 = new Order("D0001", quantity, 187.50, 11.25, 9.38, salesDate, sample);
        order.add(order1);
        ArrayList<Cashier> cashier = new ArrayList<Cashier>();
        Cashier cashier1 = new Cashier("Wong Kai Hao", "Cfghj1", "No 16,Jalan Satria 7/3,Taman Titiwangsa,86000 Kluang,Johor", "C0001");
        Cashier cashier2 = new Cashier("Ho Wei Lee", "Cghj234", "No 16,Jalan Satria 7/3,Taman Titiwangsa,86000 Kluang,Johor", "C0002");
        Cashier cashier3 = new Cashier("Tan JunYuan", "Cfffdf5", "No 16,Jalan Satria 7/3,Taman Titiwangsa,86000 Kluang,Johor", "C0003");
        cashier.add(cashier1);
        cashier.add(cashier2);
        cashier.add(cashier3);
        ArrayList<Report> summary = new ArrayList<Report>();
        Report summary1 = new Report(quantity, 187.50, 11.25, 9.38);
        summary.add(summary1);
        int current = 3;//store the number of Cashier objects
        int pCount = 3;//store the number of Product objects
        int sCount = 1;
        int rCount = 1;
        Admin sys = new Admin(cashier, product);//we assume only one Admin account is allowed

        do {
            try {
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
                System.out.println("                                                 MAIN PAGE                                                       ");
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
                System.out.println("1. Login Admin");
                System.out.println("2. Login Cashier");
                System.out.println("3. Quit");
                System.out.print("Enter your choice:");
                selection = input.next();
                choice = Integer.parseInt(selection);
                if (choice == 1) {
                    System.out.println("\n-----------------------------------------------------------------------------------------------------------------");
                    System.out.println("                                                Login Admin Page                                                 ");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------");
                    System.out.print("Enter userName: ");
                    username = input.next();
                    System.out.print("Enter password: ");
                    pass = input.next();

                    login = sys.validateLogin(username, pass);
                    if (login) {
                        JOptionPane.showMessageDialog(null, "Congrat ,login successfully ! ! !\nWelcome\n", "Login Successfully", JOptionPane.PLAIN_MESSAGE);
                        do {
                            try {
                                menuAdmin();
                                System.out.print("Enter your choice : ");
                                selection = input.next();
                                choice = Integer.parseInt(selection);
                                if (choice == 1) {
                                    current = aCashier(current, sys);
                                } else if (choice == 2) {
                                    uCashier(cashier, sys);
                                } else if (choice == 3) {
                                    vCashier(sys);
                                } else if (choice == 4) {
                                    dCashier(sys);
                                } else if (choice == 5) {
                                    pCount = aProduct(pCount, sys);
                                } else if (choice == 6) {
                                    uProduct(product, sys);
                                } else if (choice == 7) {
                                    vProduct(sys);
                                } else if (choice == 8) {
                                    dProduct(sys);
                                } else if (choice == 9) {
                                    generateSummaryReport(product, sys, summary, rCount);
                                } else if (choice == 10) {
                                    checkOrderHandle(sys);
                                } else if (choice == 11) {
                                    checkWorkingHour(sys);
                                } else if (choice == 12) {
                                    System.out.println("Welcome");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Please choose options given by the system ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        } while (choice != 12);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password.Please try again! \n", "ERROR", JOptionPane.ERROR_MESSAGE);
                        wrongCountAdmin++;
                        if (wrongCountAdmin >= 3) {
                            forgetPassword(choice);
                            System.out.print("Enter your new password: ");
                            newPass = input.next();
                            System.out.print("Confirm your new password: ");
                            confirmPass = input.next();

                            if (newPass.equals(confirmPass)) {
                                sys.setPassword(newPass);
                                JOptionPane.showMessageDialog(null, "Reset successfully. \n", "INFO", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "The new password are not matched! \n", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else if (choice == 2) {
                    int outCashier = 0;
                    System.out.println("\n-----------------------------------------------------------------------------------------------------------------");
                    System.out.println("                                               Login Cashier Page                                                ");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------");
                    System.out.print("Enter userName: ");
                    input.nextLine();
                    username = input.nextLine();

                    System.out.print("Enter password: ");
                    pass = input.next();
                    for (int i = 0; i < current; i++) {
                        login = cashier.get(i).validateLogin(username, pass);
                        if (login) {
                            LocalTime startWork = LocalTime.now();
                            String msg = "Congrat, login Successfully ! \nWelcome " + cashier.get(i).getcName() + " ! ! !\n";
                            JOptionPane.showMessageDialog(null, msg, "Login Successfully", JOptionPane.PLAIN_MESSAGE);
                            do {
                                try {
                                    menuCashier();
                                    System.out.print("Enter your choice : ");
                                    selection = input.next();
                                    outCashier = Integer.parseInt(selection);
                                    if (outCashier == 1) {
                                        sCount = receiveOrder(sys, cashier.get(i), product, order, sCount, last, summary, rCount);
                                        do {
                                            System.out.print("Is this the last order for today (Y=YES,N=NO) : ");
                                            last = input.next().charAt(0);
                                            if (Character.toUpperCase(last) == 'Y') {
                                                rCount++;
                                            } else if (Character.toUpperCase(last) == 'N') {
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                                            }
                                        } while (Character.toUpperCase(last) != 'N' && Character.toUpperCase(last) != 'Y');
                                    } else if (outCashier == 2) {
                                        searchProduct(product);
                                    } else {
                                        System.out.println("");
                                    }
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            } while (outCashier != 3);
                            LocalTime endWork = LocalTime.now();
                            cashier.get(i).setDailyWorkingHour(startWork, endWork);
                            break;
                        }
                    }
                    if (!login) {
                        JOptionPane.showMessageDialog(null, "Invalid username or password.Please try again! \n", "ERROR", JOptionPane.ERROR_MESSAGE);
                        wrongCountStaff++;
                        if (wrongCountStaff >= 3) {
                            forgetPassword(choice);
                        }
                    }
                } else if (choice == 3) {
                    System.out.println("\n\nWish you have a nice day.\nThank you\n");
                } else {
                    JOptionPane.showMessageDialog(null, "Please choose the option given\n", "INFO", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (choice != 3);
    }//end main

    public static void forgetPassword(int staffType) {
        if (staffType == 2) {
            JOptionPane.showMessageDialog(null, "Forget Password? \nTry to contact admin.\nTel: 011-2785 3910 \nE-mail: wongkaihao@gmail.com", "WARNING", JOptionPane.WARNING_MESSAGE);
        } else if (staffType == 1) {
            JOptionPane.showMessageDialog(null, "Forget Password? \nPlease reset your password.", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void menuAdmin() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                 Admin Main Page                                                 ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("1.Add cashier.");
        System.out.println("2.Update cashier information.");
        System.out.println("3.View all the current cashier.");
        System.out.println("4.Delete current cashier.");
        System.out.println("5.Add product.");
        System.out.println("6.Update product.");
        System.out.println("7.View all product.");
        System.out.println("8.Delete product.");
        System.out.println("9.View DAILY SALES SUMMARY.");
        System.out.println("10.Search the cashier which handle the order.");
        System.out.println("11.Check cashier working hours.");
        System.out.println("12.Log Out.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
    }

    //1st function for admin: Add Cashier
    public static int aCashier(int current, Admin sys) {
        char add;
        String pass;
        boolean valid = true;
        do {
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println("                                           Cashier Registration Page                                             ");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.print("Enter new cashier name: ");
            input.nextLine();
            String user = input.nextLine();
            do {
                System.out.print("Enter new password for new cashier(include at least 1 digits and 1 alpha): ");
                pass = input.next();
                if (sys.validatePass(pass)) {
                    valid = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Please set the password again(include at least 7 characters at least 1 digits and 1 alpha)\n", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
            } while (valid);
            System.out.print("Enter address for new cashier: ");
            input.nextLine();
            String address = input.nextLine();
            System.out.print("\nDo you want to confirm to add the new cashier(Y=YES,N=NO)?");
            add = input.next().charAt(0);
            if (Character.toUpperCase(add) == 'Y') {
                String id = getID('C', current + 1);
                Cashier cashiers = new Cashier(user, pass, address, id);
                sys.addCashier(cashiers);
                current++;
                JOptionPane.showMessageDialog(null, "Register Successfully\n", "Successful", JOptionPane.PLAIN_MESSAGE);
                String msg = "The new cashier account ID is " + id;
                JOptionPane.showMessageDialog(null, msg, "INFO", JOptionPane.INFORMATION_MESSAGE);
            } else if (Character.toUpperCase(add) == 'N') {
                JOptionPane.showMessageDialog(null, "The registration process of cashier cancelled ! ! ! \n", "INFO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid respond, please register the cashier again ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            System.out.print("Do you want to continue to add the new cashier(Y=YES,N=NO)?");
            add = input.next().charAt(0);
            if (Character.toUpperCase(add) != 'N' && Character.toUpperCase(add) != 'Y') {
                JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (Character.toUpperCase(add) != 'N');
        return current;
    }

    //2nd function for admin: Update Cashier Detail
    public static void uCashier(ArrayList<Cashier> cashier, Admin sys) {
        int choice = 0;

        boolean match, validateInput = false;
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("                                               Update Cashier Page                                               ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        do {
            try {
                match = false;
                System.out.println("");
                System.out.println("1.Update the cashier based on the cashier ID");
                System.out.println("2.Update the cashier based on the cashier name");
                System.out.println("3.Cancel");
                System.out.print("Enter Your choice:");
                String selection = input.next();
                choice = Integer.parseInt(selection);
                if (choice == 1) {
                    System.out.print("Enter the cashier ID: ");
                    String id = input.next();
                    for (int i = 0; i < cashier.size(); i++) {
                        if (id.matches(cashier.get(i).getStaffID())) {
                            System.out.println("-------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                 Current Cashier                                                 |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------");
                            System.out.println(" Cashier ID \t Name \t\t Password \t\t Current Address \t\t\t\t\t  ");
                            System.out.println("============\t ============ \t ========== \t =================================== \t\t\t\t  ");
                            System.out.println(cashier.get(i).toString());
                            System.out.println("-------------------------------------------------------------------------------------------------------------------");
                            System.out.println("Total current cashier: " + sys.getCashierList().size());
                            System.out.println("================================");
                            do {
                                validateInput = false;
                                try {
                                    System.out.println("1.Update the cashier name");
                                    System.out.println("2.Update the cashier Address");
                                    System.out.println("3.Update the cashier password");
                                    System.out.print("Enter the choice:");
                                    selection = input.next();
                                    choice = Integer.parseInt(selection);
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    validateInput = true;
                                }
                            } while (validateInput);
                            if (choice == 1) {
                                System.out.print("Enter the new cashier name:");
                                input.nextLine();
                                String name = input.nextLine();
                                cashier.get(i).setcName(name);
                            } else if (choice == 2) {
                                System.out.print("Enter the new cashier's current address:");
                                input.nextLine();
                                String address = input.nextLine();
                                cashier.get(i).setcAddress(address);
                            } else if (choice == 3) {
                                boolean pas = true;
                                do {
                                    System.out.print("Enter the new cashier's new password:");
                                    String pass = input.next();
                                    if (sys.validatePass(pass)) {
                                        cashier.get(i).setPassword(pass);
                                        System.out.print("Did you confirm to make the change ? ");
                                        char yes = input.next().charAt(0);
                                        if (Character.toUpperCase(yes) == 'Y') {
                                            JOptionPane.showMessageDialog(null, "The modification process of the cashier done\n", "Successful", JOptionPane.PLAIN_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "The changes for cashier that you make already cancelled\n", "INFO", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        pas = false;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Please set the password again(include at least 7 characters at least 1 digits and 1 alpha)\n", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                } while (pas);

                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                            match = true;
                        }
                    }
                    if (!match) {
                        JOptionPane.showMessageDialog(null, "No matched cashier ID found ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (choice == 2) {
                    System.out.print("Enter the cashier name: ");
                    input.nextLine();
                    String name = input.nextLine();
                    for (int i = 0; i < cashier.size(); i++) {
                        if (name.matches(cashier.get(i).getcName())) {
                            System.out.println("-------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                 Current Cashier                                                 |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------");
                            System.out.println(" Cashier ID \t Name \t\t Password \t\t Current Address \t\t\t\t\t  ");
                            System.out.println("============\t ============ \t ========== \t =================================== \t\t\t\t  ");
                            System.out.println(cashier.get(i).toString());
                            System.out.println("-------------------------------------------------------------------------------------------------------------------");
                            System.out.println("Total current cashier: " + sys.getCashierList().size());
                            System.out.println("================================");
                            do {
                                validateInput = false;
                                try {
                                    System.out.println("1.Update the cashier name");
                                    System.out.println("2.Update the cashier Address");
                                    System.out.println("3.Update the cashier password");
                                    System.out.print("Enter the choice:");
                                    selection = input.next();
                                    choice = Integer.parseInt(selection);
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    validateInput = true;
                                }
                            } while (validateInput);
                            if (choice == 1) {
                                System.out.print("Enter the new cashier name:");
                                input.nextLine();
                                name = input.nextLine();
                                cashier.get(i).setcName(name);
                            } else if (choice == 2) {
                                System.out.print("Enter the new cashier's current address:");
                                input.nextLine();
                                String address = input.nextLine();
                                cashier.get(i).setcAddress(address);
                            } else if (choice == 3) {
                                boolean pas = true;
                                do {
                                    System.out.print("Enter the new cashier's new password:");
                                    String pass = input.next();
                                    if (sys.validatePass(pass)) {
                                        cashier.get(i).setPassword(pass);
                                        System.out.print("Did you confirm to make the change ? ");
                                        char yes = input.next().charAt(0);
                                        if (Character.toUpperCase(yes) == 'Y') {
                                            JOptionPane.showMessageDialog(null, "The modification process of the cashier done\n", "Successful", JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "The changes for cashier that you make already cancelled\n", "INFO", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        pas = false;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Please set the password again(include at least 7 characters at least 1 digits and 1 alpha)\n", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                } while (pas);
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                            match = true;
                        }
                    }
                    if (!match) {
                        JOptionPane.showMessageDialog(null, "No matched cashier name found ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (choice == 3) {
                    System.out.println("");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (choice != 3);
    }

    //3rd function for admin: View the current cashier
    public static void vCashier(Admin sys) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                 Current Cashier                                                 |");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Cashier ID \t Name \t\t Password \t\t Current Address \t\t\t\t\t  ");
        System.out.println("============\t ============ \t ========== \t =================================== \t\t\t\t  ");
        sys.viewCashierList();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("Total current cashier: " + sys.getCashierList().size());
    }

    //4th function for admin: View the current cashier
    public static void dCashier(Admin sys) {
        char delete;
        boolean validateDelete;
        do {
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.println("|                                                 Current Cashier                                                 |");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.println(" Cashier ID \t Name \t\t Password \t\t Current Address \t\t\t\t\t  ");
            System.out.println("============\t ============ \t ========== \t =================================== \t\t\t\t  ");
            sys.viewCashierList();
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.println("Total current cashier: " + sys.getCashierList().size());
            System.out.print("\nEnter Cashier Id:");
            String id = input.next();
            System.out.print("Do you want to confirm to delete cashier account?");
            delete = input.next().charAt(0);
            if (Character.toUpperCase(delete) == 'Y') {
                validateDelete = sys.deleteCashier(id);
                if (!validateDelete) {
                    JOptionPane.showMessageDialog(null, "No matched cashier ID found ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Deletion of cashier account done ! ! !\n", "Successful", JOptionPane.PLAIN_MESSAGE);
                }
            } else if (Character.toUpperCase(delete) == 'N') {
                JOptionPane.showMessageDialog(null, "Deletion of cashier account cancelled ! ! !\n", "INFO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            System.out.print("Do you want to continue to delete cashier account?");
            delete = input.next().charAt(0);
            if (Character.toUpperCase(delete) != 'Y' && Character.toUpperCase(delete) != 'N') {
                JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (Character.toUpperCase(delete) != 'N');
    }

    //5th function for admin: Add product
    public static int aProduct(int pCount, Admin sys) {
        char confirm = 0;
        boolean validateInput = false;
        double unitprice = 0;
        int quantity = 0;
        do {
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                 Add Product Page                                                ");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println("Do you want to add product?(Y = yes, N = no): ");
            confirm = input.next().charAt(0);
            if (Character.toUpperCase(confirm) == 'Y') {
                System.out.print("Enter new product name: ");
                input.nextLine();
                String prodName = input.nextLine();
                do {
                    validateInput = false;
                    try {
                        System.out.print("Enter the new product quantity: ");
                        String quan = input.next();
                        quantity = Integer.parseInt(quan);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                        validateInput = true;
                    }
                } while (validateInput);
                do {
                    validateInput = false;
                    try {
                        System.out.print("Enter the new product price: ");
                        String price = input.next();
                        unitprice = Double.parseDouble(price);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                        validateInput = true;
                    }
                } while (validateInput);
                System.out.println("Do you confirm to add this product?(Y = yes, N = no): ");
                confirm = input.next().charAt(0);
                if (Character.toUpperCase(confirm) == 'Y') {
                    String prodId = getID('P', pCount + 1);
                    Product products = new Product(prodId, prodName, Math.abs(quantity), Math.abs(unitprice));
                    sys.addProduct(products);
                    pCount++;
                    JOptionPane.showMessageDialog(null, "The register product process done!", "Successful", JOptionPane.PLAIN_MESSAGE);
                    String msg = "The new product ID is " + prodId;
                    JOptionPane.showMessageDialog(null, msg, "INFO", JOptionPane.INFORMATION_MESSAGE);
                } else if (Character.toUpperCase(confirm) == 'N') {
                    JOptionPane.showMessageDialog(null, "The register product process is not completed!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else if (Character.toUpperCase(confirm) == 'N') {
                JOptionPane.showMessageDialog(null, "Exit registration of product.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (Character.toUpperCase(confirm) != 'N');
        return pCount;
    }

    //6th function for admin: Update product details
    public static void uProduct(ArrayList<Product> product, Admin sys) {
        char sure;
        boolean update = false, validateInput = false;
        int choose = 0, i, quantity = 0;
        double unitprice = 0;
        String selection;
        System.out.println(" -----------------------------------------------------------------------------------------------------------------");
        System.out.println("                                             Update Product Page                                                  ");
        System.out.println(" -----------------------------------------------------------------------------------------------------------------");
        System.out.println(" Product ID\t Product Name \t\t Quantity \t Price(RM)  \t\t\t\t\t\t  ");
        System.out.println(" ==========\t ============\t\t ======== \t =========== \t\t\t\t\t\t  ");
        for (i = 0; i < sys.getProductList().size(); i++) {
            System.out.println(product.get(i).toString());
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.print("Please enter the product id that you want to update: ");
        String productID = input.next();
        for (i = 0; i < sys.getProductList().size(); i++) {
            if (productID.matches(product.get(i).getProductID())) {
                do {
                    do {
                        validateInput = false;
                        try {
                            System.out.println("1. UPDATE PRODUCT NAME");
                            System.out.println("2. UPDATE PRODUCT QUANTITY");
                            System.out.println("3. UPDATE PRODUCT PRICE");
                            System.out.println("4. CANCEL UPDATE");
                            System.out.println("Enter Your Choice: ");
                            selection = input.next();
                            choose = Integer.parseInt(selection);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                            validateInput = true;
                        }
                    } while (validateInput);
                    if (choose == 1) {
                        System.out.print("Enter the new product name: ");
                        String productName = input.next();
                        System.out.print("Are you sure to update product?(Y = yes, N = no): ");
                        sure = input.next().charAt(0);
                        if (Character.toUpperCase(sure) == 'Y') {
                            product.get(i).setProductName(productName);
                            JOptionPane.showMessageDialog(null, "The modification process of the product name done.\n", "Successful", JOptionPane.PLAIN_MESSAGE);
                        } else if (Character.toUpperCase(sure) == 'N') {
                            JOptionPane.showMessageDialog(null, "The modification process of the product name cancelled.\n", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (choose == 2) {
                        do {
                            validateInput = false;
                            try {
                                System.out.print("Enter the new quantity: ");
                                String quan = input.next();
                                quantity = Integer.parseInt(quan);
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                                validateInput = true;
                            }
                        } while (validateInput);
                        product.get(i).setQuantity(Math.abs(quantity));
                        System.out.println("Are you sure to update product?(Y = yes, N = no): ");
                        sure = input.next().charAt(0);
                        if (Character.toUpperCase(sure) == 'Y') {
                            product.get(i).setQuantity(quantity);
                            JOptionPane.showMessageDialog(null, "The modification process of the product quantity done.\n", "Successful", JOptionPane.PLAIN_MESSAGE);
                        } else if (Character.toUpperCase(sure) == 'N') {
                            JOptionPane.showMessageDialog(null, "The modification process of the product quantity cancelled.\n", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (choose == 3) {
                        do {
                            validateInput = false;
                            try {
                                System.out.print("Enter the new product price: ");
                                String price = input.next();
                                unitprice = Double.parseDouble(price);
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                                validateInput = true;
                            }
                        } while (validateInput);
                        System.out.print("Are you sure to update product?(Y = yes, N = no): ");
                        sure = input.next().charAt(0);
                        if (Character.toUpperCase(sure) == 'Y') {
                            product.get(i).setUnitPrice(Math.abs(unitprice));
                            JOptionPane.showMessageDialog(null, "The modification process of the product price done\n", "Successful", JOptionPane.PLAIN_MESSAGE);
                        } else if (Character.toUpperCase(sure) == 'N') {
                            JOptionPane.showMessageDialog(null, "The modification process of the product price cancelled.\n", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (choose == 4) {
                        System.out.println("Thank You");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } while (choose != 4);
                update = true;
            }
        }
        if (!update) {
            JOptionPane.showMessageDialog(null, "No matched product ID found ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    //7th function for admin: View the product detail
    public static void vProduct(Admin sys) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("                                               Product Page                                                      ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println(" Product ID\t Product Name \t\t Quantity \t Price(RM)  \t\t\t\t\t\t  ");
        System.out.println(" ==========\t ============\t\t ======== \t =========== \t\t\t\t\t\t  ");
        sys.viewProductList();
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("Total number of product: " + sys.getProductList().size());
    }

    //8th function for admin: Delete the product detail
    public static void dProduct(Admin sys) {
        char delete = 'Y';
        boolean validateDelete;
        do {
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println("                                           Delete Product Page                                                   ");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println(" Product ID\t Product Name \t\t Quantity \t Price(RM)  \t\t\t\t\t\t  ");
            System.out.println(" ==========\t ============\t\t ======== \t =========== \t\t\t\t\t\t  ");
            sys.viewProductList();
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println("Total number of product: " + sys.getProductList().size());
            if (Character.toUpperCase(delete) == 'Y') {
                System.out.print("Please enter the product id to delete: ");
                String prodId = input.next();
                System.out.print("Do you confirm to delete?(Y = yes, N = no): ");
                delete = input.next().charAt(0);
                if (Character.toUpperCase(delete) == 'Y') {
                    validateDelete = sys.deleteProduct(prodId);
                    if (!validateDelete) {
                        JOptionPane.showMessageDialog(null, "No matched cashier ID found ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Deletion of Product done ! ! !\n", "Successful", JOptionPane.PLAIN_MESSAGE);
                    }
                } else if (Character.toUpperCase(delete) == 'N') {
                    JOptionPane.showMessageDialog(null, "Deletion of Product cancelled ! ! !\n", "INFO", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            System.out.print("Do you want to continue for delete section?(Y = yes, N = no): ");
            delete = input.next().charAt(0);
            if (Character.toUpperCase(delete) != 'Y' && Character.toUpperCase(delete) != 'N') {
                JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (Character.toUpperCase(delete) != 'N');
    }

    //9th function for admin: Print Daily Sales Report
    public static void generateSummaryReport(ArrayList<Product> product, Admin sys, ArrayList<Report> report, int rCount) {
        double total = 0;
        ArrayList<Integer> quantity = new ArrayList<>();
        quantity = report.get(rCount - 1).getQuantity();
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("|                          DAILY SALES SUMMMARY                       \t |");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("|                           N0 16,Jalan Rambutan,                      \t |");
        System.out.println("|                             Taman Titiwangsa,                        \t |");
        System.out.println("|                               86000 Kluang,                          \t |");
        System.out.println("|                                  Johor.                              \t |");
        System.out.printf("|DATE:%s                                                 \t |\n", getTodayDate());
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("|Product ID \t Quantity \t Unit Price \t\tSub-Total\t |");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < sys.getProductList().size(); i++) {
            System.out.printf("|%s \t\t %d \t\t RM %.2f \t\t RM %.2f\t |\n", product.get(i).getProductID(), quantity.get(i), product.get(i).getUnitPrice(), (product.get(i).getUnitPrice() * quantity.get(i)));
            total += product.get(i).getUnitPrice() * quantity.get(i);
        }
        System.out.println("|                                                         --------       |");
        System.out.printf("|                                            Subtotal  : RM %.2f\t |\n", total);
        System.out.printf("|                    Total discount given in this day  : RM %.2f \t |\n", report.get(rCount - 1).getTotalDiscountGiven());
        System.out.println("|                                                         --------       |");
        System.out.printf("|                                          Net Total  : RM %.2f\t |\n", total - report.get(rCount - 1).getTotalDiscountGiven());
        System.out.printf("|                          Total taxation in this day  : RM %.2f\t |\n", report.get(rCount - 1).getTotalTax());
        System.out.println("|                                                         --------       |");
        System.out.printf("|                                          Net Total  : RM %.2f\t |\n", total - report.get(rCount - 1).getTotalDiscountGiven() + report.get(rCount - 1).getTotalTax());
        System.out.println("--------------------------------------------------------------------------");
    }

    //10th function for admin: Search the cashier already handle how many order
    public static void checkOrderHandle(Admin sys) {
        boolean check = false;
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                 Current Cashier                                                 |");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Cashier ID \t Name \t\t Password \t\t Current Address \t\t\t\t\t  ");
        System.out.println("============\t ============ \t ========== \t =================================== \t\t\t\t  ");
        sys.viewCashierList();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("Total current cashier: " + sys.getCashierList().size());
        System.out.println("======================");
        System.out.print("Enter Cashier Id:");
        String id = input.next();
        for (int i = 0; i < sys.getCashierList().size(); i++) {
            if (id.equals(sys.getCashierList().get(i).getStaffID()) && sys.getCashierList().get(i).getOrderList().size() > 0) {
                System.out.println("----------------------------------------======================================-----------------------------------");
                System.out.println("                                          Total Order Handle By Certain Cashier                                  ");
                System.out.println("----------------------------------------======================================-----------------------------------");
                System.out.println("Order ID \t Order Date \t Total Amount(RM)");
                System.out.println("======== \t ========== \t ================");
                sys.searchOrderHandleByCashier(id);
                check = true;
            }
        }
        if (!check) {
            JOptionPane.showMessageDialog(null, "No orders handle by " + id + " cashier", "INFO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //11th function for admin: Check cashier working hour
    public static void checkWorkingHour(Admin sys) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                          Working Hours of Cashier                                               |");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Cashier ID \t Name \t\t  Working Hours   ");
        System.out.println("============\t ============ \t ==============  ");
        for (int i = 0; i < sys.getCashierList().size(); i++) {
            sys.getCashierList().get(i).showDailyWorkingHour();
        }
    }

    //Print Cashier Menu
    public static void menuCashier() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                 Cashier Main Page                                               ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("1.Receive Order.");
        System.out.println("2.Search Product.");
        System.out.println("3.Log Out.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
    }

    //First function for cashier:receive order
    public static int receiveOrder(Admin sys, Cashier cashier, ArrayList<Product> product, ArrayList<Order> order, int sCount, char last, ArrayList<Report> summary, int rCount) {
        char entry = 'Y';
        boolean inputs = false, validateInput = false;
        int i, quan = 0;
        double subTotal = 0;
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<Integer> quanti = new ArrayList<Integer>();
        ArrayList<Integer> quan1 = new ArrayList<Integer>();
        for (i = 0; i < product.size(); i++) {//each order used
            quan1.add(i, 0);
        }
        if (Character.toUpperCase(last) == 'Y') {//report used
            for (i = 0; i < product.size(); i++) {
                quanti.add(i, 0);
            }
        } else {
            for (i = 0; i < product.size(); i++) {
                quanti.add(i, summary.get(rCount).getQuantity().get(i));
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                 Ordering Page                                                   ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        do {
            System.out.print("Enter the product ID on the product label:");
            String id = input.next();
            for (i = 0; i < product.size(); i++) {
                if (id.equals(product.get(i).getProductID())) {
                    do {
                        validateInput = false;
                        try {
                            System.out.print("Enter the quantity purchased : ");
                            String quan2 = input.next();
                            quan = Integer.parseInt(quan2);
                            if (Math.abs(quan) > product.get(i).getQuantity()) {
                                validateInput = true;
                                JOptionPane.showMessageDialog(null, "Insufficient products on-hand", "INFO", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                inputs = true;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                            validateInput = true;
                        }
                    } while (validateInput);
                }
            }
            if (inputs) {
                System.out.print("\nDo you want to confirm to order (Y=YES,N=NO)?");
                entry = input.next().charAt(0);
                if (Character.toUpperCase(entry) == 'Y') {
                    for (i = 0; i < product.size(); i++) {
                        if (id.equals(product.get(i).getProductID())) {
                            subTotal += Math.abs(quan) * product.get(i).getUnitPrice();
                            quanti.set(i, Math.abs(quan) + quanti.get(i));//report used
                            quan1.set(i, Math.abs(quan));//each order used
                            int num = product.get(i).getQuantity() - Math.abs(quan);
                            product.get(i).setQuantity(num);
                        }
                        products.add(product.get(i));
                    }
                    JOptionPane.showMessageDialog(null, "The ordering process done ! ! ! \n", "Successful", JOptionPane.PLAIN_MESSAGE);
                } else if (Character.toUpperCase(entry) == 'N') {
                    JOptionPane.showMessageDialog(null, "The ordering process of items cancelled ! ! ! \n", "INFO", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid respond, please enter again ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                System.out.print("Do you want to continue to order(Y=YES,N=NO)?");
                entry = input.next().charAt(0);
                if (Character.toUpperCase(entry) != 'N' && Character.toUpperCase(entry) != 'Y') {
                    JOptionPane.showMessageDialog(null, "Invalid response ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                inputs = false;
            } else {
                JOptionPane.showMessageDialog(null, "The product ID is not found.\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while (Character.toUpperCase(entry) != 'N');
        if (subTotal > 0) {
            sCount = payOrder(sys, cashier, quanti, quan1, products, subTotal, order, sCount, last, summary, rCount);
        }
        return sCount;
    }

    public static int payOrder(Admin sys, Cashier cashier, ArrayList<Integer> quanti, ArrayList<Integer> quan1, ArrayList<Product> products, double subTotal, ArrayList<Order> order, int sCount, char last, ArrayList<Report> summary, int rCount) {
        double tax;
        boolean mber = true;
        double discount = 0, cash = 0, balance, total;
        LocalDate nowDate = LocalDate.now();
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                 Payment Page                                                    ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        do {
            System.out.print("Got member card ?(Y=YES) ");
            char member = input.next().charAt(0);
            if (Character.toUpperCase(member) == 'Y') {
                discount = 0.05;
                mber = false;
            } else if (Character.toUpperCase(member) == 'N') {
                discount = 0;
                mber = false;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Response \n", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (mber);
        discount = order.get(order.size() - 1).countDiscount(subTotal, discount);
        tax = order.get(order.size() - 1).countTax(subTotal);
        total = order.get(order.size() - 1).countTotal(subTotal);
        do {
            System.out.println("=================================================================================================================");
            System.out.printf("The payment is     RM %.2f\n", subTotal);
            if (discount > 0) {
                System.out.printf("Discount \t : RM %.2f\n", discount);
            }
            System.out.printf("Taxation \t : RM %.2f\n", tax);
            System.out.println("                   ---------");
            System.out.printf("Total Payment    : RM %.2f\n", total);
            try {
                System.out.print("Cash paid        : RM ");
                String pay = input.next();
                cash = Double.parseDouble(pay);

                System.out.println("=================================================================================================================\n");
                if ((cash - total) >= 0) {
                    Order orders = new Order(getID('D', sCount), quan1, total, tax, discount, nowDate, products);
                    balance = order.get(order.size() - 1).countBalance(cash, subTotal);
                    sCount++;
                    for (int i = 0; i < sys.getCashierList().size(); i++) {
                        if (cashier.getStaffID().equals(sys.getCashierList().get(i).getStaffID())) {
                            sys.updateOrderHandle(i, orders);
                        }
                    }
                    if (Character.toUpperCase(last) == 'N') {
                        ArrayList<Integer> quantity1 = new ArrayList<Integer>();
                        for (int i = 0; i < quanti.size(); i++) {
                            quantity1.add(i, quanti.get(i));
                        }
                        summary.get(rCount).setQuantity(quantity1);
                        summary.get(rCount).setTotalSaleAmountOfDay(order.get(order.size() - 1).getTotal());
                        double taxs = summary.get(rCount).getTotalTax();
                        summary.get(rCount).setTotalTax(taxs + tax);
                        double dis = summary.get(rCount).getTotalDiscountGiven();
                        summary.get(rCount).setTotalDiscountGiven(dis + discount);
                    } else if (Character.toUpperCase(last) == 'Y') {
                        Report reports = new Report();
                        summary.add(reports);
                        summary.get(rCount).setQuantity(quanti);
                        summary.get(rCount).setTotalTax(tax);
                        summary.get(rCount).setTotalDiscountGiven(discount);
                    }
                    printReceipt(quan1, cash, discount, subTotal, tax, orders);
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient cash paid, need to pay more\n", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter the value with correct data type ! ! !\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } while ((cash - total) < 0);
        return sCount;
    }

    public static void printReceipt(ArrayList<Integer> quan1, double cash, double discount, double subTotal, double tax, Order orders) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime today = LocalDateTime.now();
        String formatDateTime = today.format(format);
        System.out.println("---------------------------------------------------------------------");
        System.out.println("|                          MD Enterprise                            |");
        System.out.println("|                     N0 16,Jalan Rambutan,                         |");
        System.out.println("|                       Taman Titiwangsa,                           |");
        System.out.println("|                         86000 Kluang,                             |");
        System.out.println("|                            Johor.                                 |");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("|                           Receipt                                 |");
        System.out.println("|                           -------                                 |");
        System.out.printf("|Order No: %s\n", orders.getOrderID() + "                                                    |");
        System.out.println("|Today date and time: " + formatDateTime + "                           |");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("|Quantity \t Product Description \t\t Amount (RM)        |");
        System.out.printf("|--------  \t ------------------- \t\t -----------        |\n");
        for (int i = 0; i < quan1.size(); i++) {
            if (quan1.get(i) > 0) {
                System.out.printf("|%d \t\t %s \t\t\t RM %.2f           |\n", orders.getQuantity().get(i), orders.getOrderProduct().get(i).getProductName(), orders.getOrderProduct().get(i).getUnitPrice() * orders.getQuantity().get(i));
            }
        }
        System.out.println("|--------  \t ------------------- \t\t -----------        |");
        System.out.printf("|   \t\t\t\t\tSUBTOTAL: RM %.2f \t    |\n", subTotal);
        System.out.printf("|   \t\t\t\t\tDISCOUNT: RM %.2f \t    |\n", orders.getDiscount());
        System.out.printf("|   \t\t\t\t\t\t  ---------- \t    |\n");
        System.out.printf("|   \t\t\t\t\t   TOTAL: RM %.2f \t    |\n", subTotal - orders.getDiscount());
        System.out.printf("|   \t\t\t\t\t     TAX: RM %.2f \t    |\n", orders.getTax());
        System.out.printf("|   \t\t\t\t\t\t  ---------- \t    |\n");
        System.out.printf("|   \t\t\t\t       NET TOTAL: RM %.2f \t    |\n", orders.getTotal());
        System.out.println("|                                                                   |");
        System.out.printf("|   \t\t\t\t\t   CASH : RM %.2f \t    |\n", cash);
        System.out.printf("|   \t\t\t\t\t  CHANGE: RM %.2f \t    |\n", cash - orders.getTotal());
        System.out.println("---------------------------------------------------------------------");
        System.out.println("|                Thank You and Please Come Again.                   |");
        System.out.println("---------------------------------------------------------------------\n");
    }

    //Second function for cashier:receive product
    public static void searchProduct(ArrayList<Product> product) {
        boolean validInput = false;
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("                       Search Product Page                    ");
        System.out.println("--------------------------------------------------------------");
        System.out.print("Please enter the product id: ");
        String productID = input.next();

        for (int i = 0; i < product.size(); i++) {
            if (productID.matches(product.get(i).getProductID())) {
                System.out.println("--------------------------------------------------------------");
                System.out.println("Product ID\t Product Name \t\t Quantity \t Price");
                System.out.println("==========\t =============\t\t ======== \t======");
                System.out.println(product.get(i).toString());
                validInput = true;
            }
        }
        if (!validInput) {
            JOptionPane.showMessageDialog(null, "No matched product ID found", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String getTodayDate() {
        LocalDate today = LocalDate.now();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatedDate = today.format(format);
        return formatedDate;
    }

    public static String getID(char ch, int count) {
        if (count < 10) {
            return ch + "000" + count;
        } else if (count > 9) {
            return ch + "00" + count;
        } else if (count > 99) {
            return ch + "0" + count;
        } else if (count > 999) {
            return ch + Integer.toString(count);
        } else {
            return null;
        }
    }
}
