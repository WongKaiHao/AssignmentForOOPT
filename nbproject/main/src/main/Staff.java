package main;

public abstract class Staff {
    private String staffID;

    protected Staff() {
        this("-");
    }

    protected Staff(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    
    public abstract boolean validateLogin(String username,String pass);
    
    protected static boolean validatePass(String pass){
        int charCount = 0;
        int numCount = 0;
        if(pass.length()>6){
            for (int i = 0; i < pass.length(); i++) {
                char ch = pass.charAt(i);
                if (no_Number(ch)) 
                    numCount++;
                else if (no_Letter(ch)) 
                    charCount++;
                else 
                    return false;
            }
        }
        return (numCount>=1)&&(charCount>=1);
    }
    
    private static boolean no_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }

    private static boolean no_Number(char ch) {
        return (ch >= '0' && ch <= '9');
    }
    
    @Override
    public String toString() {
        return " " + staffID;
    }
}