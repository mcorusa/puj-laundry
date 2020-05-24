package model;

/* Klasa ne smije imati konstruktor */
/* Klasa mora imati get i set metode za sve atribute*/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Employee extends Table {
    @Entity(type = "INTEGER", size = 32, primary = true)
    int id;

    @Entity(type = "VARCHAR", size = 50, isnull = false)
    String firstname;

    @Entity(type = "VARCHAR", size = 50, isnull = false)
    String lastname;

    @Entity(type = "VARCHAR", size = 150, isnull = false)
    String email;

    @Entity(type = "VARCHAR", size = 256, isnull = false)
    String username;

    @Entity(type = "VARCHAR", size = 256, isnull = false)
    String password;

    @Entity(type = "VARCHAR", size = 50, isnull = false)
    String role;

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public static List<Employee> select(){
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try {
            Statement stmnt = null;
            try {
                stmnt = Database.CONNECTION.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet rs = stmnt.executeQuery("SELECT * FROM employee");

            while(rs.next()){
                employees.add(new Employee());
            }
            return employees;
        } catch (SQLException e) {
            System.out.println("Neuspješno izvlačenje korisnika iz baze: " + e.getMessage());
            return employees;
        }
    }

    public static List<Employee> selectByRole(String role){
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("SELECT * FROM employee WHERE role=?");
            stmnt.setString(1, role);
            ResultSet rs = stmnt.executeQuery();

            while(rs.next()){
                employees.add(new Employee());
            }
            return employees;
        } catch (SQLException e) {
            System.out.println("Neuspješno izvlačenje korisnika iz baze: " + e.getMessage());
            return employees;
        }
    }
    public static Employee login (String username, String password) throws Exception {
        String sql = "SELECT id FROM Employee where username=? AND password=?";
        PreparedStatement query = Database.CONNECTION.prepareStatement(sql);
        query.setString(1, username);
        query.setString(2, password);
        ResultSet rs = query.executeQuery();

        if(rs.next()){
            return(Employee) Employee.get(Employee.class,  rs.getInt(1));
        } else{
            return  null;
        }

    }

}
