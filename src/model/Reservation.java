package model;

import controller.Employeec;
import java.sql.*;
import java.time.LocalDateTime;



public class Reservation extends Table {

    @Entity(type = "INTEGER", size = 32, primary = true)
    int id;

    @Entity(type = "DATETIME", size = 6, isnull = false)
    LocalDateTime appointmentDateStart;

    @Entity(type = "DATETIME", size = 6, isnull = false)
    LocalDateTime appointmentDateEnd;


    @ForeignKey(table = "Student", attribute = "id")
    @Entity(type = "INTEGER", size = 32)
    int studentFk;

    @ForeignKey(table = "Employee", attribute = "id")
    @Entity(type = "INTEGER", size = 32)
    int employeeFk;

    @ForeignKey(table = "CycleOnMachine", attribute = "id")
    @Entity(type = "INTEGER", size = 32)
    int cycleonmachineFk;

    public int getId() {
        return id;
    }


    public LocalDateTime getAppointmentDateStart() {
        return appointmentDateStart;
    }

    public void setAppointmentDateStart(LocalDateTime appointmentDateStart) {
        this.appointmentDateStart = appointmentDateStart;
    }

    public LocalDateTime getAppointmentDateEnd() {
        return appointmentDateEnd;
    }

    public void setAppointmentDateEnd(LocalDateTime appointmentDateEnd) {
        this.appointmentDateEnd = appointmentDateEnd;
    }
    /*
        @Entity(type = "VARCHAR", size = 50, isnull = false)
        String model;
    */
    public void setId(int id) {
        this.id = id;
    }


    public int getStudentFk() {
        return studentFk;
    }

    public void setStudentFk(int studentFk) {
        this.studentFk = studentFk;
    }

    public int getEmployeeFk() {
        return employeeFk;
    }

    public void setEmployeeFk(int employeeFk) {
        this.employeeFk = employeeFk;
    }

    public int getCycleonmachineFk() {
        return cycleonmachineFk;
    }

    public void setCycleonmachineFk(int cycleonmachineFk) {
        this.cycleonmachineFk = cycleonmachineFk;
    }

    /*public ArrayList<Reservation> getReservations() throws SQLException {
        ArrayList<Reservation> listOfReservations = new ArrayList<>();

        String sql = "Select * from Reservation";

        PreparedStatement pst = Database.CONNECTION.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {


        }
        return listOfReservations;
    }

    */

/*
    public static List<Reservation> getReservations() throws Exception {
        List<Reservation> reservations = new ArrayList<Reservation>();
        String sql = "Select * from Reservation";

        PreparedStatement pst = Database.CONNECTION.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

            while (rs.next()){
                System.out.println(rs.getDate(3));
                reservations.add(new Reservation());
            }

        return reservations;
    }
*/

    public static boolean checkAvailability() throws Exception {

        String sql = "Select * from Reservation INNER JOIN CycleOnMachine on Reservation.cycleonmachineFk=CycleOnMachine.id INNER JOIN Machine ON CycleOnMachine.machineFk=Machine.id";
        PreparedStatement pst = Database.CONNECTION.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Timestamp timestampStart = rs.getTimestamp(2);
            Timestamp timestampEnd = rs.getTimestamp(3);
            int modelID = rs.getInt(11);
            String model = rs.getString(12);


            Timestamp newRstart = Timestamp.valueOf(Employeec.startDate);
            Timestamp newRend = Timestamp.valueOf(Employeec.endDate);

            //System.out.println("podaci koji se spremaju u bazu: " + newRstart);


            if (Employeec.idmasine == modelID) {
                if (timestampStart.equals(newRstart)) {
                    return false;
                } else if (timestampEnd.after(newRstart) && (timestampStart.before(newRend))) {
                    return false;
                }
            }
        }
        return true;
    }

}
