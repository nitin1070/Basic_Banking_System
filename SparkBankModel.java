/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SparkBankCodes;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class SparkBankModel {

    private static Connection conn;
    private static PreparedStatement ps;
    private static SparkBankPojo obj;

    static {

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver Loaded Succesfully");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "NitinMeena", "nitin");
            System.out.println("connection open successfully");

        } catch (SQLException ex) {

            System.out.println("Error in DB");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Diver not found");
            ex.printStackTrace();
        }
    }

    public static List<SparkBankPojo> getAllUsers() {
        List<SparkBankPojo> userList = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM costumers order by id");

            while (rs.next()) {
                SparkBankPojo obj = new SparkBankPojo();
                obj.setId(rs.getInt(1));
                obj.setName(rs.getString(2));
                obj.setMail(rs.getString(3));
                obj.setBalance(rs.getDouble(4));

                userList.add(obj);
            }
        } catch (SQLException ex) {

            System.out.println("Error in DB");
            ex.printStackTrace();
        } finally {
            return userList;
        }
    }

    public static List<SparkBankPojo> getUserById(String id) throws SQLException {
        List<SparkBankPojo> userlist = new ArrayList<>();

        ps = conn.prepareStatement("select * from costumers where id=?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            SparkBankPojo obj = new SparkBankPojo();
            obj.setId(rs.getInt(1));
            obj.setName(rs.getString(2));
            obj.setMail(rs.getString(3));
            obj.setBalance(rs.getDouble(4));
            userlist.add(obj);

        }
        return userlist;
    }

    public static boolean UpdateReceiverMoney(String name, double bal) throws SQLException {
        ps = conn.prepareStatement("UPDATE costumers set current_balance=current_balance + ? where name= ?");
        ps.setDouble(1, bal);
        ps.setString(2, name);
        int res = ps.executeUpdate();

        if (res == 1) {
            return true;
        }
        return false;
    }

    public static boolean UpdateSenderMoney(int id, double bal) throws SQLException {
        ps = conn.prepareStatement("UPDATE costumers set current_balance=current_balance - ? where id = ?");
        ps.setDouble(1, bal);
        ps.setInt(2, id);
        int res = ps.executeUpdate();

        if (res == 1) {
            return true;
        }
        return false;
    }

    public static int getTransactionDate(TransactionHistoryPojo obj) throws SQLException {
        ps = conn.prepareStatement("insert into history(id,sender_name,receiver_name,ammount,transaction_date)values(?,?,?,?,?)");
        ps.setInt(1, obj.getId());
        ps.setString(2, obj.getSenderName());
        ps.setString(3, obj.getReceiverName());
        ps.setDouble(4, obj.getAmmount());
        ps.setDate(5, obj.getDate());

        int x = ps.executeUpdate();
        if (x == 1) {
            return 1;
        }
        return 0;
    }

    public static List<TransactionHistoryPojo> getTransactionHistory() throws SQLException {
        List<TransactionHistoryPojo> HistoryList = new ArrayList<>();

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM history");

        while (rs.next()) {
            TransactionHistoryPojo obj = new TransactionHistoryPojo();
            obj.setId(rs.getInt(1));
            obj.setSenderName(rs.getString(2));
            obj.setReceiverName(rs.getString(3));
            obj.setAmmount(rs.getDouble(4));
            obj.setDate(rs.getDate(5));
            HistoryList.add(obj);

        }
        return HistoryList;
    }

    public static int getContactDetails(ContactPojo obj)throws SQLException{
         ps = conn.prepareStatement("insert into contact(first_name,last_name,mobile,subject)values(?,?,?,?)");
         ps.setString(1,obj.getFirstname());
         ps.setString(2,obj.getLastname());
         ps.setString(3, obj.getMobile());
         ps.setString(4, obj.getSubject());
         
         int x=ps.executeUpdate();
         if(x==1)
             return 1;  
            return 0;
    }
            
     
    public static void closeConnection() {
        try {
            conn.close();

        } catch (SQLException ex) {

            System.out.println("connection closed");
            ex.printStackTrace();
        }
    }

}
