
package employermain;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DBUpdater {

    JTextField jTextField1, jTextField2, jTextField3, jTextField4;
    
    
    //CONNECTING TO DB
    public Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/employers";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "";
        Connection conn=DriverManager.getConnection(url,user,pass);
        return conn;
    }
    
    
    
    //INSERT INTO DB
    public Boolean add(String name, String age, String address, String income){
        String sql ="insert into person(name,age,address,income) values(?,?,?,?)";
        try{
            try ( 
            //GET CONNECTION
                    Connection conn = connect();
                    PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql)) {
                    st.setString(1,name);
                    st.setString(2,age);
                    st.setString(3,address);
                    st.setString(4,income);
                    st.execute();
            }
            return true;
        }
        catch(SQLException e){
        return false;
        }   
    }
    
    //RETRIEVE DATA
    public DefaultTableModel getData() {
        //ADD COLUMNS TO TABLE MODEL
            DefaultTableModel df = new DefaultTableModel();
            df.addColumn("Id");
            df.addColumn("Name");
            df.addColumn("Age");
            df.addColumn("Address");
            df.addColumn("Income");

            //SQL STATEMENT
            String sql="select * from person";
    
     try{
        try (Connection conn = connect();
            Statement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery(sql)) {
            
            //LOOP TROUGH GETTING ALL VALUES
            while(rs.next()){
                
                //GET VALUES
                String id = rs.getString(1);
                String name = rs.getString(2);
                String age = rs.getString(3);
                String address = rs.getString(4);
                String income = rs.getString(5);
                
                df.addRow(new String []{id,name,age,address,income});
            }
        }
         return df;
        }
        
        catch(SQLException e){
       
        } 
        return null;
        
        
    }
    
    
    //UPDATE DATA
    public Boolean update(String id, String name, String age, String address, String income){
    
        //SQL STATEMENT
        String sql="update person set name= '"+name+"', age='"+age+"', address='"+address+"', income='"+income+"' where idperson='"+id+"'";
        try{
        
            //STATEMENT
            try (Connection conn = connect();
        //STATEMENT
                 Statement st = conn.prepareStatement(sql)) {
                 st.execute(sql);
            }
        return true;
        }
        catch(SQLException e){
        
            return false;
        }
    }
    
    //DELETE DATA
    public Boolean delete (String id) {
    
        //SQL STMT
        String sql="DELETE FROM person WHERE idperson = '"+id+"'";
        String query1 = "ALTER TABLE person AUTO_INCREMENT = 1" ;
        
        try{
            try (Connection conn = connect();
                Statement st = conn.prepareStatement(sql))
            {
                st.execute(sql);
                st.execute(query1);
            }
         return true;
        }
        catch(SQLException e){
        return false;
        }
    }
    
    
    
   //FILTER RESULTS
    public ArrayList<Person> listPersons(String valToSearch) {
    ArrayList<Person> personList = new ArrayList<>();
    
    try{
    java.sql.Connection conn = connect();
    Statement st = conn.createStatement();
    String query="SELECT * FROM `person` WHERE name LIKE '%"+valToSearch+"%'";
    ResultSet rs = st.executeQuery(query);
    
    while(rs.next()){
        Person person = new Person(
        rs.getInt("idperson"),
        rs.getString("name"),
        rs.getInt("age"),
        rs.getString("address"),
        rs.getDouble("income")
        );
        personList.add(person);
    }
    }
    catch(SQLException e){}
    return personList;
    } 
    
    
    //SELECTING EMPLOYEE FROM DATABASE
    public ArrayList<Person> personList() throws ClassNotFoundException, SQLException{
        ArrayList<Person> personList = new ArrayList<>();
        Connection con = connect();
        String query = "select * from person";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        Person p;
        while(rs.next()){
        p = new Person(rs.getInt("idperson"), rs.getString("name"), rs.getInt("age"), rs.getString("address"), rs.getDouble("income"));
        personList.add(p);
        
        }

        return personList;
    
    }
}
