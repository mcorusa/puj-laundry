package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ShortReservations {

    public LocalDateTime appointmentDateStart;
    public LocalDateTime appointmentDateEnd;
    public String model;
    public String studentName;
    public String description;
    public String type;
    public int reservationCounter;
    public Float income;

    public ShortReservations(LocalDateTime appointmentDateStart, LocalDateTime appointmentDateEnd, String model) {
        this.appointmentDateStart = appointmentDateStart;
        this.appointmentDateEnd = appointmentDateEnd;
        this.model = model;
    }

    public ShortReservations(LocalDateTime appointmentDateStart, String studentName, String description) {
        this.appointmentDateStart = appointmentDateStart;
        this.studentName = studentName;
        this.description = description;
    }

    public ShortReservations(String type, int reservationCounter) {
        this.type = type;
        this.reservationCounter = reservationCounter;
    }

    public ShortReservations(Float income) {
        this.income = income;
    }

    public ShortReservations(String studentName, String type, int reservationCounter) {
        this.studentName = studentName;
        this.type = null;
        this.reservationCounter = reservationCounter;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReservationCounter() {
        return reservationCounter;
    }

    public void setReservationCounter(int reservationCounter) {
        this.reservationCounter = reservationCounter;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "ShortReservations{" +
                "appointmentDateStart=" + appointmentDateStart +
                ", appointmentDateEnd=" + appointmentDateEnd +
                ", model='" + model + '\'' +
                '}';
    }


    public static List<ShortReservations> getshortReservations() throws Exception {
        List<ShortReservations> reservations = new ArrayList<ShortReservations>();


        String sql = "SELECT appointmentDateStart, appointmentDateEnd, model \n" +
                "FROM reservation \n" +
                "join cycleonmachine on reservation.cycleonmachineFk=cycleonmachine.id\n" +
                "join machine on cycleonmachine.machineFk=machine.id\n" +
                "ORDER BY appointmentDateStart";
        PreparedStatement pst = Database.CONNECTION.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {


            Timestamp resstart = rs.getTimestamp(1);
            Timestamp resend = rs.getTimestamp(2);
            String model = rs.getString(3);

            //Timestamp u LocalDateTime
            Timestamp stamp1 = new Timestamp(resstart.getTime());
            LocalDateTime resstartDate = stamp1.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            Timestamp stamp2 = new Timestamp(resend.getTime());
            LocalDateTime resendDate = stamp2.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();



            ShortReservations r = new ShortReservations(resstartDate,resendDate,model);
            reservations.add(r);

            //System.out.println("Rezervacija: xx " + r.appointmentDateStart + " " + r.appointmentDateEnd + " " + r.model);
        }
        return reservations;
    }

    public static List<ShortReservations> getshortReservationsInfo() throws Exception {
        List<ShortReservations> reservations = new ArrayList<ShortReservations>();


        String sql = "SELECT CONCAT(student.firstname, ' ', student.lastname) AS 'Student', reservation.appointmentDateStart AS 'Appointment',  cycleonmachine.description\n" +
                "FROM reservation \n" +
                "INNER JOIN student\n" +
                "ON reservation.studentFk = student.id\n" +
                "INNER JOIN cycleonmachine\n" +
                "ON reservation.cycleonmachineFk = cycleonmachine.id\n" +
                "INNER JOIN cycle\n" +
                "ON cycleonmachine.cycleFk = cycle.id";
        PreparedStatement pst = Database.CONNECTION.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {


            String name = rs.getString(1);
            Timestamp resstart = rs.getTimestamp(2);
            String description = rs.getString(3);

            //Timestamp - LocalDateTime
            Timestamp stamp1 = new Timestamp(resstart.getTime());
            LocalDateTime resstartDate = stamp1.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();


            ShortReservations r = new ShortReservations(resstartDate,name,description);
            reservations.add(r);

            //System.out.println("Rezervacija: yy " + r.studentName + " " + r.appointmentDateStart + " " + r.description);
        }
        return reservations;
    }


    public static List<ShortReservations> getshortReservationsCycles() throws Exception {
        List<ShortReservations> cyclereservations = new ArrayList<ShortReservations>();


        String sql = "SELECT type, COUNT(type) AS 'usage'\n" +
                "FROM cycle\n" +
                "INNER JOIN cycleonmachine\n" +
                "ON cycle.id=cycleonmachine.cycleFk\n" +
                "INNER JOIN reservation\n" +
                "on cycleonmachine.id=reservation.cycleonmachineFk\n" +
                "GROUP BY type\n" +
                "ORDER BY COUNT(type) DESC";
        PreparedStatement pst = Database.CONNECTION.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            String type = rs.getString(1);
            int count = rs.getInt(2);

            ShortReservations r = new ShortReservations(type, count);
            cyclereservations.add(r);
        }

        return cyclereservations;
    }


    public static String weeklyIncome() throws SQLException {

        String sql = "SELECT SUM(cycle.price) as 'weekly incomes'\n" +
                "FROM reservation\n" +
                "INNER JOIN cycleonmachine\n" +
                "ON reservation.cycleonmachineFk=cycleonmachine.id\n" +
                "INNER JOIN cycle\n" +
                "ON cycleonmachine.cycleFk=cycle.id\n" +
                "\n" +
                "WHERE WEEK(reservation.appointmentDateStart) = WEEK(CURDATE())";

        PreparedStatement pst = Database.CONNECTION.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        String weeklyincome;

        if(rs.next()){
            Float week=rs.getFloat(1);
            weeklyincome = week.toString();
            return weeklyincome+ " KM";
        }

        return "";

    }

    public static String monthlyIncome() throws SQLException {
        String sql = "SELECT SUM(cycle.price) as 'monthly incomes'\n" +
                "FROM reservation\n" +
                "INNER JOIN cycleonmachine\n" +
                "ON reservation.cycleonmachineFk=cycleonmachine.id\n" +
                "INNER JOIN cycle\n" +
                "ON cycleonmachine.cycleFk=cycle.id\n" +
                "\n" +
                "WHERE MONTH(reservation.appointmentDateStart) = MONTH(CURDATE()+ INTERVAL 1 MONTH) AND YEAR(reservation.appointmentDateStart) = YEAR(CURDATE())";

        PreparedStatement pst = Database.CONNECTION.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        String monthlyincome;

        if(rs.next()){
            Float week=rs.getFloat(1);
            monthlyincome = week.toString();
            return monthlyincome+ " KM";
        }

        return "";

    }

    public static String yearlyIncome() throws SQLException {
        String sql = "SELECT SUM(cycle.price) as 'yearly incomes'\n" +
                "FROM reservation\n" +
                "INNER JOIN cycleonmachine\n" +
                "ON reservation.cycleonmachineFk=cycleonmachine.id\n" +
                "INNER JOIN cycle\n" +
                "ON cycleonmachine.cycleFk=cycle.id\n" +
                "\n" +
                "WHERE YEAR(reservation.appointmentDateStart) = YEAR(CURDATE())";

        PreparedStatement pst = Database.CONNECTION.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        String yearlyincome;

        if(rs.next()){
            Float year=rs.getFloat(1);
            yearlyincome = year.toString();
            return yearlyincome + " KM";
        }

        return "";

    }

    public static List<ShortReservations> getActiveStudents() throws Exception {

        List<ShortReservations> activeStudents = new ArrayList<ShortReservations>();

        String sql = "SELECT CONCAT (student.firstname, ' ', student.lastname) AS 'Student', COUNT(reservation.appointmentDateStart) as \"Reservations\"\n" +
                "\n" +
                "FROM reservation\n" +
                "join Student on reservation.studentFk = student.id\n" +
                "where month(reservation.appointmentDateStart) = MONTH(CURDATE()+ INTERVAL 1 MONTH) \n" +
                "group by reservation.studentFk, student.firstname, student.lastname\n" +
                "ORDER BY (reservation.appointmentDateStart) DESC\n" +
                "LIMIT 3";

        PreparedStatement pst = Database.CONNECTION.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String name = rs.getString(1);
            String type=null;
            int activecounter = rs.getInt(2);
            ShortReservations r = new ShortReservations(name, type, activecounter);
            activeStudents.add(r);
            //System.out.println("Rezervacija: xx " + r.studentName + " " + r.reservationCounter);
        }
        return activeStudents;
    }

}
