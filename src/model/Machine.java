package model;

import controller.Employeec;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class Machine extends Table {

    @Entity(type = "INTEGER", size = 32, primary = true)
    int id;

    @Entity(type = "VARCHAR", size = 50, isnull = false)
    String model;

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public static boolean checkMachine() throws Exception {

        String sql = "Select * from Machine";
        PreparedStatement pst = Database.CONNECTION.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()){
            int id = rs.getInt(1);

            if(id==Employeec.idmasine){
                return true;
            }
        }

        return false;
    }

}
