package main;

import java.time.LocalTime;
import java.util.ArrayList;

public class Cashier extends Staff{
    private String cName;
    private String password;
    private String cAddress;
    private LocalTime dailyWorkingHour;
    private ArrayList<Order> orderList;

    public Cashier(){
    }

    public Cashier(String cName, String password, String cAddress, String staffID) {
        super(staffID);
        this.cName = cName;
        this.password = password;
        this.cAddress = cAddress;
        this.dailyWorkingHour=LocalTime.of(0, 0);
        orderList = new ArrayList<Order>();
    }

    public Cashier(String cName, String password, String cAddress, ArrayList<Order> orderList, String staffID) {
        super(staffID);
        this.cName = cName;
        this.password = password;
        this.cAddress = cAddress;
        this.dailyWorkingHour=LocalTime.of(0, 0);
        this.orderList = orderList;
    }

    public Cashier(String cName, String password, String cAddress, LocalTime dailyWorkingHour, ArrayList<Order> orderList, String staffID) {
        super(staffID);
        this.cName = cName;
        this.password = password;
        this.cAddress = cAddress;
        this.dailyWorkingHour = dailyWorkingHour;
        this.orderList = orderList;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public String getDailyWorkingHour() {
        String time=dailyWorkingHour.getHour()+"hour "+ dailyWorkingHour.getMinute()+"minutes";
        return time;
    }

    public void setDailyWorkingHour(LocalTime startWorking,LocalTime endWorking) {
        int hour,minute,hours,minutes,more,remaining;
        if(endWorking.getMinute()>=startWorking.getMinute()){
            hour=endWorking.getHour()-startWorking.getHour();
            minute=endWorking.getMinute()-startWorking.getMinute();
        }else{
            hour=endWorking.getHour()-startWorking.getHour()-1;
            minute=(endWorking.getMinute()+60)-startWorking.getMinute();
        }
        hours=this.dailyWorkingHour.getHour()+hour;
        minutes=this.dailyWorkingHour.getMinute()+minute;
        if(minutes>59){
            more=minutes/60;
            remaining=minutes%60;
        }else{
            more=0;
            remaining=0;
        }
        hours+=more;
        minutes+=remaining;
        this.dailyWorkingHour = LocalTime.of(hours, minutes);
    }
    
    public void showDailyWorkingHour() {
        System.out.println(super.toString()+" \t\t "+this.cName + " \t " + this.getDailyWorkingHour());
    }
    
    public boolean updatePass(String pass){
        return validatePass(pass);
    }
    
    public void addOrder(Order order){
        this.orderList.add(order);
    }
    
    public String showCashierName(){
        return "\nCashier ID : " + super.toString() + "\nName : "+ cName + "\n";
    }
    
    @Override
    public boolean validateLogin(String username,String pass){
        return username.equals(this.cName) && pass.equals(this.password);
    }
    
    @Override
    public String toString() {
        return super.toString() +" \t\t "+ cName + " \t " + password + " \t " + cAddress ;
    }
}