package irrigationmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class DBOperations {

    String url = "jdbc:sqlite:storehouse.sqlite";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    boolean addNewItemDetails(NewItemDetails id) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "INSERT INTO items VALUES (?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);

            pst.setString(1, id.getsBook());
            pst.setInt(2, id.getPage());
            pst.setInt(3, id.getItemID());
            pst.setString(4, id.getItem());
            pst.setInt(5, id.getSerialNo());
            pst.setString(6, id.getCategory());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    boolean addOfficer(NewOfficer no){
        try {
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "INSERT INTO officer VALUES (?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);

            pst.setString(1, no.getName());
            pst.setString(2, no.getDesignation());
            pst.setString(3, no.getNic());
            pst.setString(4, no.getOffice_address());
            pst.setString(5, no.getHome_address());
            pst.setInt(6, no.getOffice_no());
            pst.setInt(7, no.getMobile_no());
            pst.setString(8, no.getEmail());

            pst.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    NewOfficer viewOfficer(String name){
        try {
            NewOfficer officer = new NewOfficer();
            
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "SELECT * FROM officer WHERE officer_name='" + name + "'";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                officer.setName(rs.getString(1));
                officer.setDesignation(rs.getString(2));
                officer.setNic(rs.getString(3));
                officer.setOffice_address(rs.getString(4));
                officer.setHome_address(rs.getString(5));
                officer.setOffice_no(Integer.parseInt(rs.getString(6)));
                officer.setMobile_no(Integer.parseInt(rs.getString(7)));
                officer.setEmail(rs.getString(8));
            }
            return officer;

        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    ArrayList<NewItemDetails> getNewItem() {
        try {
            ArrayList<NewItemDetails> lst = new ArrayList<NewItemDetails>();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "SELECT * FROM items";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                NewItemDetails id = new NewItemDetails();
                id.setsBook(rs.getString(1));
                id.setPage(rs.getInt(2));
                id.setItemID(rs.getInt(3));
                id.setItem(rs.getString(4));
                id.setSerialNo(rs.getInt(5));
                id.setItemDescription(rs.getString(6));
                lst.add(id);
            }
            return lst;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    ArrayList<ItemDetails> getSearchedNewItem(String x) {
        try {
            ArrayList<ItemDetails> lst = new ArrayList<ItemDetails>();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "SELECT * FROM item_details WHERE item_name ='" + x + "'";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                ItemDetails id = new ItemDetails();
                id.setsBook(rs.getString(1));
                id.setItem(rs.getString(2));
                id.setDate(rs.getString(3));
                id.setRecivedOrIssued(rs.getString(4));
                id.setNoOfBill(rs.getString(5));
                id.setStorehouseNo(rs.getString(11));
                id.setRackNo(rs.getString(12));
                
                lst.add(id);
            }
            return lst;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    boolean addItemDetails(ItemDetails id) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "INSERT INTO item_details VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);

            pst.setString(1, id.getsBook());
            pst.setString(2, id.getItem());
            pst.setString(3, id.getDate());
            pst.setString(4, id.getRecivedOrIssued());
            pst.setString(5, id.getNoOfBill());
            pst.setInt(6, id.getRecived());
            pst.setInt(7, id.getIssued());

            int balance = 0;

            PreparedStatement statementOne = con.prepareStatement("SELECT SUM(received_quantity) FROM item_details WHERE item_name='" + id.getItem() + "'");
            ResultSet resultOne = statementOne.executeQuery();

            PreparedStatement statementTwo = con.prepareStatement("SELECT SUM(issued_quantity) FROM item_details WHERE item_name='" + id.getItem() + "'");
            ResultSet resultTwo = statementTwo.executeQuery();

            balance = resultOne.getInt(1) - resultTwo.getInt(1) + id.getRecived() - id.getIssued();
            System.out.println(resultOne.getInt(1));
            System.out.println(resultTwo.getInt(1));
            System.out.println(balance);
            pst.setInt(8, balance);
            pst.setString(9, id.getOrderCode());
            pst.setString(10, id.getOrderNo());
            pst.setString(11, id.getStorehouseNo());
            pst.setString(12, id.getRackNo());

            pst.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    

    ArrayList<NewItemDetails> getLog(String log) {
        try {
            ArrayList<NewItemDetails> lst = new ArrayList<NewItemDetails>();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            
            String query = "SELECT * FROM items WHERE sbook='" + log + "'";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                NewItemDetails id = new NewItemDetails();
                id.setsBook(rs.getString(1));
                id.setPage(rs.getInt(2));
                id.setItemID(rs.getInt(3));
                id.setItem(rs.getString(4));
                id.setSerialNo(rs.getInt(5));
                id.setItemDescription(rs.getString(6));
                lst.add(id);
            }
            return lst;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    String search(String name) {
        String log;
        try {
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "SELECT item_name,sbook FROM items";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                if (name.equals(rs.getString("item_name"))) {
                    log = rs.getString("sbook");
                    return log;
                }
            }
            return null;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    ArrayList<ItemDetails> getFullDetails(String name) {
        try {
            ArrayList<ItemDetails> lst = new ArrayList<ItemDetails>();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "SELECT * FROM item_details WHERE item_name='" + name + "'";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                ItemDetails id = new ItemDetails();
                id.setsBook(rs.getString(1));
                id.setItem(rs.getString(2));
                id.setDate(rs.getString(3));
                id.setRecivedOrIssued(rs.getString(4));
                id.setNoOfBill(rs.getString(5));
                id.setRecived(rs.getInt(6));
                id.setIssued(rs.getInt(7));
                id.setBalance(rs.getInt(8));
                id.setOrderCode(rs.getString(9));
                id.setOrderNo(rs.getString(10));
                id.setStorehouseNo(rs.getString(11));
                id.setRackNo(rs.getString(12));                
                lst.add(id);
            }
            return lst;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    ArrayList<ItemDetails> getofficerTransactions(String name) {
        try {
            ArrayList<ItemDetails> lst = new ArrayList<ItemDetails>();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "SELECT * FROM item_details WHERE received_form_or_issued_to='" + name + "'";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                ItemDetails id = new ItemDetails();
                id.setsBook(rs.getString(1));
                id.setItem(rs.getString(2));
                id.setDate(rs.getString(3));
                id.setRecivedOrIssued(rs.getString(4));
                id.setNoOfBill(rs.getString(5));
                id.setRecived(rs.getInt(6));
                id.setIssued(rs.getInt(7));
                id.setBalance(rs.getInt(8));
                id.setOrderCode(rs.getString(9));
                id.setOrderNo(rs.getString(10));
                id.setStorehouseNo(rs.getString(11));
                id.setRackNo(rs.getString(12));                
                lst.add(id);
            }
            return lst;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    HashSet<String> FileCombo(String x, String y) {
        try {
            String query;
            HashSet<String> itm = new HashSet();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            query = "SELECT * FROM '" + y + "' WHERE item_name LIKE '" + x + "%'";
        pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                itm.add(rs.getString("item_name"));

            }
            return itm;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("File Combo >>"+e);
            return null;
        }
    }
     HashSet<String> getSerialNo(String x) {
        try {
            String query;
            HashSet<String> itm = new HashSet();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            query = "SELECT * FROM items WHERE item_name='" + x + "'";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                itm.add(rs.getString("item_id"));

            }
            return itm;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Get Serial No >>"+e);
            return null;
        }
    }
     HashSet<String> getStoreBook(String x) {
        try {
            String query;
            HashSet<String> itm = new HashSet();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            query = "SELECT * FROM storeBook WHERE category='" + x + "'";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                itm.add(rs.getString("s/book"));

            }
            return itm;

        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
     
    HashSet<String> getSBook() {
        try {
            String query;
            HashSet<String> itm = new HashSet();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            query = "SELECT * FROM items ";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                itm.add(rs.getString("sbook"));

            }
            return itm;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("GetSBook >>"+e);
            return null;
        }
    }

    
      HashSet<String> getCategory() {
        try {
            String query;
            HashSet<String> itm = new HashSet();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            query = "SELECT * FROM storeBook ";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                itm.add(rs.getString("category"));

            }
            return itm;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Get Category >>"+e);
            return null;
        }
    }

    ArrayList<NewItemDetails> getItem(String x,String y) {
        try {
            ArrayList<NewItemDetails> lst = new ArrayList<NewItemDetails>();

            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "SELECT * FROM items WHERE item_name ='" + x + "' AND item_id ='" + y + "' ";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                NewItemDetails id = new NewItemDetails();
                id.setsBook(rs.getString(1));
                id.setPage(rs.getInt(2));
                id.setItemID(rs.getInt(3));
                id.setItem(rs.getString(4));
                id.setSerialNo(rs.getInt(5));
                id.setItemDescription(rs.getString(6));
                lst.add(id);

            }

            return lst;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }
    }
    
    
    
    public boolean deleteItem(NewItemDetails nid){
        try {
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "DELETE FROM items WHERE item_name='" + nid.getItem() + "'";
            pst = (PreparedStatement)con.prepareStatement(query);
            pst.executeUpdate();
            String query2 = "DELETE FROM item_details WHERE item_name='" + nid.getItem() + "'";
            pst = (PreparedStatement)con.prepareStatement(query2);
            pst.executeUpdate();
            
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    
    public boolean deleteItemDetails(ItemDetails id){
        try {
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "DELETE FROM item_details WHERE item_name='" + id.getItem() + "'";
            pst = (PreparedStatement)con.prepareStatement(query);
            pst.executeUpdate();
            
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    HashSet<String> getOfficer(String x) {
        try {
            
            String query;
            HashSet<String> itm = new HashSet();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            
              query ="SELECT * FROM officer WHERE officer_name LIKE '" + x + "%'";
              
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                itm.add(rs.getString("officer_name"));
            }
            return itm;

        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
     
     HashSet<String> getOfficer() {
        try {
            String query;
            HashSet<String> itm = new HashSet();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            
              query ="SELECT * FROM officer ";
              
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                itm.add(rs.getString("officer_name"));

            }
            return itm;

        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
     
     ArrayList<ItemDetails> getReportDetails(String first, String second) {
        try {
            ArrayList<ItemDetails> lst = new ArrayList<ItemDetails>();
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            String query = "SELECT * FROM item_details where date >= "+"'"+first+"'"+" and  date <= "+"'"+second+"'";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                ItemDetails id = new ItemDetails();
                id.setsBook(rs.getString(1));
                id.setItem(rs.getString(2));
                id.setDate(rs.getString(3));
                id.setRecivedOrIssued(rs.getString(4));
                id.setNoOfBill(rs.getString(5));
                id.setRecived(rs.getInt(6));
                id.setIssued(rs.getInt(7));
                id.setBalance(rs.getInt(8));
                id.setOrderCode(rs.getString(9));
                id.setOrderNo(rs.getString(10));
                id.setStorehouseNo(rs.getString(11));
                id.setRackNo(rs.getString(12));
                id.setSerialNo(rs.getString(13));
                id.setPage(rs.getString(14));
                lst.add(id);
            }
            return lst;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
}