package controller;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import main.Main;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    ComboBox<String> cmbCycles;
    @FXML
    ComboBox<String> cmbMachines;
    @FXML
    DatePicker datePickerreservation;

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


    //STACKPANE - Panes showing
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
    }


    @FXML
    public  void Logout (ActionEvent ev) throws IOException {
        Main.showWindow(getClass(), "../view/Login.fxml", "Login", 700, 500);
        Login.loggedInEmployee=null;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //populating comboxes
        this.cmbCycles.setItems(FXCollections.observableArrayList(getData()));

        this.lblLoggedEmployee.setText(Login.loggedInEmployee.getFirstname() + " " + Login.loggedInEmployee.getLastname());


        //populating tables (students (one in pane students, and one in pane reservations))
        this.columnStudentName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.columnStudentLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.columnStudentTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        this.populateTableStudent();

        this.colName.setCellValueFactory(new PropertyValueFactory<>("firstname" ));
        this.colSurname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.populateTableStudentsReservation();

        this.columnCyclesOnMachines.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.populateTableCyclesOnMachines();
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
        if(!this.txtStname.equals("") &&
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

            } catch (Exception e) {
                System.out.println("Nope");
            }
        }
        this.populateTableStudent();
        this.populateTableStudentsReservation();
    }
    //Saving reservation in Database
    @FXML
    public void makeReservation (ActionEvent event){

        LocalDate datum = datePickerreservation.getValue();
        CycleOnMachine cm = tableCyclesOnMachines.getSelectionModel().getSelectedItem();
        int ide = cm.getId();


        Reservation r = new Reservation();
        r.setEmployeeFk(Login.loggedInEmployee.getId());
        r.setStudentFk(tableStudentsReservation.getSelectionModel().getSelectedItem().getId());
        r.setAppointmentDate(datum);
        r.setAppointmentDate(datum);
        r.setCycleonmachineFk(ide);


        try {
            r.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tableCyclesOnMachines.getSelectionModel().clearSelection();
        this.tableStudentsReservation.getSelectionModel().clearSelection();
    }

    @FXML
    public void removeStudent (ActionEvent event) throws Exception {
        Student s = tableStudent.getSelectionModel().getSelectedItem();
        s.delete();
        this.populateTableStudent();
    }

    // combobox
    private List<String> getData() {

        List<String> options = new ArrayList<>();

        try {
            String query = "select description from cycleonmachine";
            PreparedStatement statement = Database.CONNECTION.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                options.add(rs.getString("description"));
            }

            statement.close();
            rs.close();

            // Return the List
            return options;

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
