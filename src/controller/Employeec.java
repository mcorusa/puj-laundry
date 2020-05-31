package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import main.Main;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Employeec implements Initializable {


    //--------------employee menu--------------
    @FXML
    private Pane Pn1employee;
    @FXML
    Label lblLoggedEmployee;
    @FXML
    Button btnLogOut;
    @FXML
    Button BtnStudents;
    @FXML
    Button BtnReservations;


    //--------------employee student--------------
    @FXML
    private Pane Pn2student;
    @FXML
    Button btnBacks;
    @FXML
    Button btnAddstudent;
    @FXML
    TextField txtStname;
    @FXML
    TextField txtStlastname;
    @FXML
    TextField txtSttelephone;



    //--------------employee reservation--------------
    @FXML
    private Pane Pn3reservation;
    @FXML
    Button btnBackr;
    @FXML
    Button btnMakeReservation;
    @FXML
    DatePicker datePickerreservation;
    @FXML
    TextField txtTimePicker;
    @FXML
    TableView <Student>  tableStudent;
    @FXML
    TableColumn<Student, String> columnStudentName;
    @FXML
    TableColumn<Student, String> columnStudentLastname;
    @FXML
    TableColumn<Student, String> columnStudentTelephone;
    @FXML
    TableView <Student>  tableStudentsReservation;
    @FXML
    TableColumn<Student, String> colName;
    @FXML
    TableColumn<Student, String> colSurname;
    @FXML
    TableView <CycleOnMachine>  tableCyclesOnMachines;
    @FXML
    TableColumn<CycleOnMachine, String> columnCyclesOnMachines;
    @FXML
    Button btnBackReceipt;
    @FXML
    Button btnOK;
    @FXML
    public Pane PnReceipt;
    @FXML
    Button btnReceipt;
    @FXML
    TextField txtE;
    @FXML
    TextField txtS;
    @FXML
    TextField txtST;
    @FXML
    TextField txtET;
    @FXML
    TextField txtP;
    @FXML
    Label lblOccupied;
    @FXML
    TableView <Reservation>  tableReservations;
    @FXML
    TableColumn<Reservation, String> columnReservationStart;
    @FXML
    TableColumn<Reservation, String> columnReservationEnd;
    @FXML
    TableColumn<Machine, String> columnReservationMachine;


    public static LocalDateTime startDate;
    public static LocalDateTime endDate;
    public static int idmasine;
    public static String occupied;
    public static  List listar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.lblLoggedEmployee.setText(Login.loggedInEmployee.getFirstname() + " " +
                Login.loggedInEmployee.getLastname());


        this.columnStudentName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.columnStudentLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.columnStudentTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        this.populateTableStudent();

        this.colName.setCellValueFactory(new PropertyValueFactory<>("firstname" ));
        this.colSurname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.populateTableStudentsReservation();

        this.columnCyclesOnMachines.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.populateTableCyclesOnMachines();


        this.columnReservationStart.setCellValueFactory(new PropertyValueFactory<>("appointmentDateStart"));
        this.columnReservationEnd.setCellValueFactory(new PropertyValueFactory<>("appointmentDateEnd"));
        this.columnReservationMachine.setCellValueFactory(new PropertyValueFactory<>("model"));
        this.populateTableReservations();


    }
    @FXML
    private void populateTableStudent(){
        try {
            this.tableStudent.getItems().setAll((Collection<? extends Student>) Student.list(Student.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void populateTableStudentsReservation(){
        try {
            this.tableStudentsReservation.getItems().setAll((Collection<? extends Student>) Student.list(Student.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void populateTableReservations(){
        try {
            listar= ShortReservations.getshortReservations();
            this.tableReservations.getItems().setAll(listar) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void populateTableCyclesOnMachines(){
        try {
            this.tableCyclesOnMachines.getItems().setAll((Collection<? extends CycleOnMachine>) CycleOnMachine.list(CycleOnMachine.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Adding student to Database
    @FXML
    public void addStudent (ActionEvent event) throws Exception {
        Window owner = btnAddstudent.getScene().getWindow();

        if(!this.txtStname.getText().equals("") &&
                !this.txtStlastname.getText().equals("") &&
                !this.txtSttelephone.getText().equals("")) {

            Student st = new Student();
            st.setFirstname(this.txtStname.getText());
            st.setLastname(this.txtStlastname.getText());
            st.setTelephone(this.txtSttelephone.getText());

            try {
                st.save();
                this.txtStname.setText("");
                this.txtStlastname.setText("");
                this.txtSttelephone.setText("");
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Sucess", "Student added");

            } catch (Exception e) {
                System.out.println("Nope");
            }
        }
        else{AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please insert data");}


        this.populateTableStudent();
        this.populateTableStudentsReservation();

    }
    @FXML
    public void removeStudent (ActionEvent event) throws Exception {
        Window owner = btnAddstudent.getScene().getWindow();
        try{
            Student s = tableStudent.getSelectionModel().getSelectedItem();
            s.delete();
            this.populateTableStudent();
            this.populateTableStudentsReservation();
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Sucess", "Student removed");
        } catch (Exception e) {
            System.out.println("Error removing Student");
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please select student");
        }

    }




    //Saving reservation in Database

    @FXML
    public void makeReservation (ActionEvent event) throws Exception {

        Window owner = btnMakeReservation.getScene().getWindow();

        LocalDate date_input = datePickerreservation.getValue();

        if (date_input == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please pick a date");
            return;
        }

        //TIME PROVJERE

        String timeInput = txtTimePicker.getText();

        if (timeInput.equals("") || timeInput.length()<3 || !(timeInput.contains(":"))) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please input valid time format!");
            return;
        }


        if (timeInput.charAt(2) == ':' && timeInput.length() == 4) {
            Character tmp = timeInput.charAt(3);
            timeInput = timeInput.substring(0, 3) + "0" + timeInput.substring(3);
            //System.out.println(timeInput);

        } else if (timeInput.charAt(1) == ':' && timeInput.length() == 4) {
            timeInput = "0" + timeInput;
            //System.out.println(timeInput);

        } else if (timeInput.length() == 3) {
            timeInput = "0" + timeInput;
            timeInput = timeInput.substring(0, 3) + "0" + timeInput.substring(3);
            //System.out.println(timeInput);

        }

        /*else {
            System.out.println("Invalid time input");
            txtTimePicker.setText("");

            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Netočni podaci");
            return;
        }*/
        if (timeInput.length() == 5 && timeInput.charAt(2) == ':') {
            timeInput = timeInput;
        }



        //System.out.println(timeInput);


        LocalDateTime dateTime = null;
        try {
            String formatiraniDatumStart = date_input.
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + timeInput;

            dateTime = LocalDateTime.
                    parse(formatiraniDatumStart, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            //e.printStackTrace();
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Invalid Date/Time");
            return;
        }


        //Get endtime via choosen Cycle

        Cycle c = null;
        try {
            c = tableCyclesOnMachines.getSelectionModel().getSelectedItem().getCycleFk();
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Empty Cycle");
            return;
        }

        int duration = c.getDuration();
        String myTime = timeInput;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date d = df.parse(myTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, duration);
        String newTime = df.format(cal.getTime());

        //System.out.println(newTime);

        Machine m =tableCyclesOnMachines.getSelectionModel().getSelectedItem().getMachineFk();
        idmasine = m.getId();

        String formatiraniDatumEnd = date_input.
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + newTime;

        LocalDateTime dateTime2 = LocalDateTime.
                parse(formatiraniDatumEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        startDate = dateTime;
        endDate = dateTime2;

        if (Reservation.checkAvailability()) {


            try {
                CycleOnMachine cm = tableCyclesOnMachines.getSelectionModel().getSelectedItem();
                int ide = cm.getId();
                Reservation r = new Reservation();
                r.setEmployeeFk(Login.loggedInEmployee.getId());
                r.setStudentFk(tableStudentsReservation.getSelectionModel().getSelectedItem().getId());
                r.setAppointmentDateStart(dateTime);
                r.setAppointmentDateEnd(dateTime2);
                r.setCycleonmachineFk(ide);
                r.save();
                txtE.setText(Login.loggedInEmployee.getFirstname() + " " + Login.loggedInEmployee.getLastname());
                txtS.setText(tableStudentsReservation.getSelectionModel().getSelectedItem().getFirstname() + " " +
                        tableStudentsReservation.getSelectionModel().getSelectedItem().getLastname());
                txtST.setText(dateTime.toString());
                txtET.setText(dateTime2.toString());
                txtP.setText(String.valueOf(c.getPrice()));
                tableStudentsReservation.getSelectionModel().clearSelection();
                tableCyclesOnMachines.getSelectionModel().clearSelection();
                txtTimePicker.setText("");
                datePickerreservation.setValue(null);

                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success!",
                        "Reservation successfully stored!");
                this.populateTableReservations();

            } catch (Exception e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Error saving Reservation"+e.getMessage());
                e.printStackTrace();
                return;
            }

            this.tableCyclesOnMachines.getSelectionModel().clearSelection();
            this.tableStudentsReservation.getSelectionModel().clearSelection();

        }
        else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Occupied appointment");

        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event){
        if(event.getSource().equals(BtnStudents)){
            Pn2student.toFront();
        }
        if(event.getSource().equals(BtnReservations)){
            Pn3reservation.toFront();
        }
        if(event.getSource().equals(btnBackr) || event.getSource().equals(btnBacks)) {
            Pn1employee.toFront();
        }
        if(event.getSource().equals(btnBackReceipt)) {
            Pn3reservation.toFront();
        }
        if(event.getSource().equals(btnOK)) {
            Pn1employee.toFront();
        }
    }

    @FXML
    public  void Logout (ActionEvent ev) throws IOException {
        Main.showWindow(getClass(), "../view/Login.fxml", "Login", 700, 500);
        Login.loggedInEmployee=null;
    }

    @FXML
    public  void showReceipt (ActionEvent ev) {
        PnReceipt.toFront();
    }

}
