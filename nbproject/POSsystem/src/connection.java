/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
 /** * * @author Taranjeet */ 

/**
 *
 * @author User
 */
public class connection {
    public static void main(String[] args) {
        con();
    }
    
    public static void con(){
        try {                 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");         
            String path= "C:\\Users\\User\\Documents\\POSsystem.accdb";  
            String ur = "jdbc:ucanaccess://"+path;  
            Connection con = DriverManager.getConnection(ur);                
            try {                     
                Statement st = con.createStatement();     /**Your database Table name */ 
                ResultSet rs = st.executeQuery("SELECT * FROM Admin"); /*here cars is a table select your own table here*/        
                while(rs.next()){                         
                    System.out.print(rs.getString(1));       
                    System.out.println(" "+rs.getString(2));
                } 
            }catch(Exception ex){                    
                System.out.println("error occured "+ex);                   
            } 
        }catch(Exception x){                    
            System.out.println("error occured"+x);             
        }
    }                    
}
