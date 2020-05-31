package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import javafx.util.converter.FloatStringConverter;
import main.Main;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class Admin implements Initializable {

    //------------------admin menu-----------------------
    @FXML
    private Pane Pn1admin;
    @FXML
    Button BtnAddemployee;
    @FXML
    Button BtnAddmachine;
    @FXML
    Button BtnAddstudent;
    @FXML
    Button BtnReports;

    //------------------manage employees------------------
    @FXML
    private Pane Pn2employee;
    @FXML
    TextField TxtName;
    @FXML
    TextField TxtLastname;
    @FXML
    TextField TxtEmail;
    @FXML
    TextField TxtUsername;
    @FXML
    PasswordField TxtPassword;
    @FXML
    Button btnAddemployee;
    @FXML
    Button btnRemoveemployee;
    @FXML
    Button BtnBack2;
    @FXML
    TableView<Employee> tableEmployee;
    @FXML
    TableColumn <Employee, String> columnEmployeeName;
    @FXML
    TableColumn <Employee, String> columnEmployeeLastname;
    @FXML
    TableColumn <Employee, String> columnEmployeeEmail;
    @FXML
    TableColumn <Employee, String> columnEmployeeUsername;
    @FXML
    TableColumn <Employee, String> columnEmployeePassword;

    //------------------manage students------------------------------
    @FXML
    private Pane Pn3student;
    @FXML
    TextField txtSname;
    @FXML
    TextField txtSlastname;
    @FXML
    TextField txtStelephone;
    @FXML
    Button btnAddstudent;
    @FXML
    Button btnDeletestudent;
    @FXML
    Button BtnBack3;
    @FXML
    TableView<Student> tableStudent;
    @FXML
    TableColumn <Student, String> columnStudentName;
    @FXML
    TableColumn <Student, String> columnStudentLastname;
    @FXML
    TableColumn <Student, String> columnStudentTelephone;

    //------------------manage machines and cycles----------------------
    @FXML
    private Pane Pn4machine;
    @FXML
    TextField txtModel;
    @FXML
    TextField txtType;
    @FXML
    TextField txtDuration;
    @FXML
    TextField txtPrice;

    @FXML
    Button btnAddMachine;
    @FXML
    Button btnRemoveMachine;
    @FXML
    Button btnAddCycle;
    @FXML
    Button btnAddCycleOnMachine;
    @FXML
    Button BtnBack4;
    @FXML
    TableView <Machine> tableMachine;
    @FXML
    TableColumn <Machine, String> columnMachineModel;
    @FXML
    TableView <Cycle> tableCycle;
    @FXML
    TableColumn <Cycle, String> columnCycleType;
    @FXML
    TableColumn <Cycle, Integer> columnCycleDuration;
    @FXML
    TableColumn <Cycle, Float> columnCyclePrice;


    //---------------reports---------------------------------------------------
    @FXML
    private Pane Pn5reports;
    @FXML
    Button BtnBack5;

    @FXML
    TableView<ShortReservations> tableReservationsInfo;
    @FXML
    TableColumn<ShortReservations, String> columnReservationsInfoStudent;
    @FXML
    TableColumn<ShortReservations, LocalDateTime> columnReservationsInfoAppointment;
    @FXML
    TableColumn<ShortReservations, String> columnReservationsInfoDescription;

    @FXML
    TableView<ShortReservations> tableCycleCounter;
    @FXML
    TableColumn<ShortReservations, String> columnCycleCounterType;
    @FXML
    TableColumn<ShortReservations, Integer> columnCycleCounterCount;

    @FXML
    TableView<ShortReservations> tableActive;
    @FXML
    TableColumn<ShortReservations, String> columnActiveStudent;
    @FXML
    TableColumn<ShortReservations, Integer> columnActiveReservation;
    @FXML
    TableColumn<ShortReservations, String> col;


    @FXML
    TextField txtWeeklyIncome;
    @FXML
    TextField txtMonthlyIncome;
    @FXML
    TextField txtYearIncome;

    public static List lista;
    public static List listaa;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.columnEmployeeName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.columnEmployeeLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.columnEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.columnEmployeeUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        this.columnEmployeePassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        this.columnEmployeeLastname.setEditable(true);
        this.columnEmployeeLastname.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnEmployeeEmail.setEditable(true);
        this.columnEmployeeEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        this.populateTableEmployee();


        this.columnStudentName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.columnStudentName.setEditable(true);
        this.columnStudentName.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnStudentLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.columnStudentLastname.setEditable(true);
        this.columnStudentLastname.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnStudentTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        this.columnStudentTelephone.setEditable(true);
        this.columnStudentTelephone.setCellFactory(TextFieldTableCell.forTableColumn());
        this.populateTableStudent();


        this.columnMachineModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        this.columnMachineModel.setEditable(true);
        this.columnMachineModel.setCellFactory(TextFieldTableCell.forTableColumn());
        this.populateTableMachine();

        this.columnCycleType.setCellValueFactory(new PropertyValueFactory<>( "type"));
        this.columnCycleType.setEditable(true);
        this.columnCycleType.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnCycleDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        this.columnCyclePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.columnCyclePrice.setEditable(true);
        this.columnCyclePrice.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        this.populateTableCycle();

        this.columnReservationsInfoStudent.setCellValueFactory(new PropertyValueFactory<>( "studentName"));
        this.columnReservationsInfoAppointment.setCellValueFactory(new PropertyValueFactory<>("appointmentDateStart"));
        this.columnReservationsInfoDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.populateTableReservationsInfo(); //reports


        this.columnCycleCounterType.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.columnCycleCounterCount.setCellValueFactory(new PropertyValueFactory<>("reservationCounter"));
        this.populateTableCycleCounter(); //reports

        this.columnActiveStudent.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        this.columnActiveReservation.setCellValueFactory(new PropertyValueFactory<>("reservationCounter"));
        this.col.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.populateTableActive(); //reports

        this.fillIncome(); //reports


    }

    private void populateTableEmployee(){
        try {
            this.tableEmployee.getItems().setAll((Collection<? extends Employee>) Employee.list(Employee.class));
            this.tableEmployee.setEditable(true);
        } catch (Exception e) {
            System.out.println("Error Fetching Data into Table Employees");
        }
    }
    private void populateTableStudent(){
        try {
            this.tableStudent.getItems().setAll((Collection<? extends Student>) Student.list(Student.class));
            this.tableStudent.setEditable(true);
        } catch (Exception e) {
            System.out.println("Error Fetching Data into Table Students");
        }
    }
    private void populateTableMachine(){
        try {
            this.tableMachine.getItems().setAll((Collection<? extends Machine>) Machine.list(Machine.class));
            this.tableMachine.setEditable(true);
        } catch (Exception e) {
            System.out.println("Error Fetching Data into Table Machines");
        }
    }
    private void populateTableCycle(){
        try {
            this.tableCycle.getItems().setAll((Collection<? extends Cycle>) Cycle.list(Cycle.class));
            this.tableCycle.setEditable(true);
        } catch (Exception e) {
            System.out.println("Error Fetching Data into Table Cycles");
        }
    }

    private void fillIncome(){
        try {

            txtWeeklyIncome.setText(ShortReservations.weeklyIncome());
            txtMonthlyIncome.setText(ShortReservations.monthlyIncome());
            txtYearIncome.setText(ShortReservations.yearlyIncome());

        } catch (SQLException throwables) {
            System.out.println("Error income");
        }
    }
    private void populateTableActive(){

        try {
            listaa=ShortReservations.getActiveStudents();
            this.tableActive.getItems().setAll(listaa);
        } catch (Exception e) {
            System.out.println("Error fetching active students");
        }

    }
    private void populateTableReservationsInfo(){
        try {
            lista=ShortReservations.getshortReservationsInfo();
            this.tableReservationsInfo.getItems().setAll(lista);

        } catch (Exception e) {
            System.out.println("Error Fetching Data into Table Reservations Info");
        }
    }
    private void populateTableCycleCounter(){
        try {
            lista=ShortReservations.getshortReservationsCycles();
            this.tableCycleCounter.getItems().setAll(lista);

        } catch (Exception e) {
            System.out.println("Error Fetching Data into Table Cycle Counter");
        }
    }


    //adding
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {


        if(event.getSource().equals(BtnAddemployee)){
            Pn2employee.toFront();
            this.populateTableEmployee();
        }

        if(event.getSource().equals(BtnAddstudent)){
            Pn3student.toFront();
        }

        if(event.getSource().equals(BtnAddmachine)) {
            Pn4machine.toFront();
        }

        if(event.getSource().equals(BtnReports)) {
            Pn5reports.toFront();
        }

        if(event.getSource().equals(BtnBack2) || event.getSource().equals(BtnBack3) || event.getSource().equals(BtnBack4) || event.getSource().equals(BtnBack5)) {
            Pn1admin.toFront();
        }


        //Adding employee to database
        if(event.getSource().equals(btnAddemployee)){
            Window owner = btnAddemployee.getScene().getWindow();

            if(!this.TxtName.equals("") &&
                    !this.TxtLastname.getText().equals("") &&
                    !this.TxtUsername.getText().equals("") &&
                    !this.TxtPassword.getText().equals("") ){
                Employee em = new Employee();
                em.setFirstname(this.TxtName.getText());
                em.setLastname(this.TxtLastname.getText());
                em.setEmail(this.TxtEmail.getText());
                em.setUsername(this.TxtUsername.getText());
                em.setPassword(this.TxtPassword.getText());
                em.setRole("zaposlenik");
                try {
                    em.save();
                    this.TxtName.setText("");
                    this.TxtLastname.setText("");
                    this.TxtEmail.setText("");
                    this.TxtUsername.setText("");
                    this.TxtPassword.setText("");
                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Employee added");

                } catch (Exception e) {
                    System.out.println("Nope");
                    //AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please insert data");
                }
                this.populateTableEmployee();
            }
            else{AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please insert data");}
        }

        //Adding student to database
        if(event.getSource().equals(btnAddstudent)){
            Window owner = btnAddstudent.getScene().getWindow();
            if(!this.txtSname.getText().equals("") &&
                    !this.txtSlastname.getText().equals("") &&
                    !this.txtStelephone.getText().equals("")) {


                Student st = new Student();
                st.setFirstname(this.txtSname.getText());
                st.setLastname(this.txtSlastname.getText());
                st.setTelephone(this.txtStelephone.getText());

                try {
                    st.save();
                    this.txtSname.setText("");
                    this.txtSlastname.setText("");
                    this.txtStelephone.setText("");
                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Student added");

                } catch (Exception e) {
                    System.out.println("Nope");
                    //AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "ERROR", "Please insert data");
                }
                this.populateTableStudent();
            }
            else{AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please insert data");}


        }

        //Adding machine to database
        if(event.getSource().equals(btnAddMachine)){
            Window owner = btnAddMachine.getScene().getWindow();
            if(!this.txtModel.getText().equals("")) {

                try {
                    Machine ma = new Machine();
                    ma.setModel(this.txtModel.getText());
                    ma.save();
                    this.txtModel.setText("");
                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Machine added");

                } catch (Exception e) {
                    System.err.println("Nope");
                    //AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "ERROR", "Please insert data");
                }
                this.populateTableMachine();
            }
            else{AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please insert data");}
        }

        //Adding Cycle to database
        if(event.getSource().equals(btnAddCycle)){
            Window owner = btnAddCycle.getScene().getWindow();
            if(!this.txtType.getText().equals("") &&
                    !this.txtDuration.getText().equals("") &&
                    !this.txtPrice.getText().equals("")) {
                ;
                try {
                    Cycle cy = new Cycle();
                    cy.setType(this.txtType.getText());
                    cy.setDuration(Integer.parseInt(this.txtDuration.getText()));
                    cy.setPrice(Float.parseFloat(this.txtPrice.getText()));
                    cy.save();
                    this.txtType.setText("");
                    this.txtDuration.setText("");
                    this.txtPrice.setText("");
                    this.populateTableCycle();
                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Cycle added");
                } catch (Exception e) {
                    System.err.println("Nope");
                }
            }
            else{AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please insert data");}

        }

        //Adding Cycle to the certain Machine
        if(event.getSource().equals(btnAddCycleOnMachine)){
            Window owner = btnAddCycleOnMachine.getScene().getWindow();

            try {
                Cycle c = tableCycle.getSelectionModel().getSelectedItem();
                Machine m = tableMachine.getSelectionModel().getSelectedItem();

                String machindescription = m.getModel();
                String cycledescription = c.getType();

                CycleOnMachine cm = new CycleOnMachine();
                cm.setCycleFk(c.getId());
                cm.setMachineFk(m.getId());
                cm.setDescription(cycledescription +" on "+ machindescription);
                cm.save();
                tableMachine.getSelectionModel().clearSelection();
                tableCycle.getSelectionModel().clearSelection();
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Cycle added to machine");


            } catch (Exception e) {
                System.out.println("Nope");
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please choose Cycle or Machine");
            }

        }

    }


    @FXML
    public void removeEmployee (ActionEvent event) throws Exception {
        Window owner = btnRemoveemployee.getScene().getWindow();
        try {
            Employee e = tableEmployee.getSelectionModel().getSelectedItem();
            if(e.getRole().equals("zaposlenik")){
                e.delete();
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Employee removed");
                this.populateTableEmployee();
            }
            else{AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "You can't delete Admin user!");}

        } catch (Exception e) {
            System.out.println("Error removing employee");
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please select employee");
        }
    }

    @FXML
    public void removeStudent (ActionEvent event) throws Exception {
        Window owner = btnDeletestudent.getScene().getWindow();
        try {
            Student s = tableStudent.getSelectionModel().getSelectedItem();
            s.delete();
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Student removed");
            this.populateTableStudent();
        } catch (Exception e) {
            System.out.println("Error removing student");
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please select student");
        }
    }

    @FXML
    public void removeMachine (ActionEvent event) throws Exception {
        Window owner = btnRemoveMachine.getScene().getWindow();
        try {
            Machine m = tableMachine.getSelectionModel().getSelectedItem();
            m.delete();
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Machine removed");
            this.populateTableMachine();
        } catch (Exception e) {
            System.out.println("Error removing machine");
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Machine can't be deleted");
        }
    }

    public void removeCycle (ActionEvent event) throws Exception {
        Window owner = btnRemoveMachine.getScene().getWindow();
        try {
            Cycle c = tableCycle.getSelectionModel().getSelectedItem();
            c.delete();
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Cycle removed");
            this.populateTableCycle();
        } catch (Exception e) {
            System.out.println("Error removing cycle");
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Please select cycle");
        }
    }


    @FXML
    public  void Logout (ActionEvent ev) throws IOException {
        Main.showWindow(getClass(), "../view/Login.fxml", "Login", 700, 500);
        Login.loggedInEmployee=null;
    }

    @FXML
    public void editLastnameEmployee(TableColumn.CellEditEvent<Employee, String> evt) throws Exception {
        try {
            Employee e = evt.getRowValue();
            e.setLastname(evt.getNewValue());
            e.update();
        } catch (Exception e) {
            System.out.println("Error editing employee");
        }
    }

    @FXML
    public void editEmailEmployee(TableColumn.CellEditEvent<Employee, String> evt) throws Exception {
        try {
            Employee e = evt.getRowValue();

            e.setLastname(evt.getNewValue());
            e.update();
        } catch (Exception e) {
            System.out.println("Error editing employee");
        }
    }

    @FXML
    public void editFirstnameStudent(TableColumn.CellEditEvent<Student, String> evt) throws Exception {
        try {
            Student s = evt.getRowValue();
            s.setLastname(evt.getNewValue());
            s.update();
        } catch (Exception e) {
            System.out.println("Error editing student");
        }
    }

    @FXML
    public void editLastnameStudent(TableColumn.CellEditEvent<Student, String> evt) throws Exception {
        try {
            Student s = evt.getRowValue();

            s.setLastname(evt.getNewValue());
            s.update();
        } catch (Exception e) {
            System.out.println("Error editing student");
        }
    }

    @FXML
    public void editTelephoneStudent(TableColumn.CellEditEvent<Student, String> evt) throws Exception {
        try {
            Student s = evt.getRowValue();
            s.setLastname(evt.getNewValue());
            s.update();
        } catch (Exception e) {
            System.out.println("Error editing student");
        }
    }

    @FXML
    public void editMachineModel(TableColumn.CellEditEvent<Machine, String> evt) throws Exception {
       try {
           Machine m = evt.getRowValue();
           m.setModel(evt.getNewValue());
           m.update();
       } catch (Exception e) {
           System.out.println("Error editing machine");
       }
    }

    @FXML
    public void editCycleType(TableColumn.CellEditEvent<Cycle, String> evt) throws Exception {
        try {
            Cycle c = evt.getRowValue();
            c.setType(evt.getNewValue());
            c.update();
        } catch (Exception e) {
            System.out.println("Error editing cycle");
        }
    }

    @FXML
    public void editCyclePrice(TableColumn.CellEditEvent<Cycle, Float> evt) throws Exception {
        try {
            Cycle c = evt.getRowValue();

            c.setPrice(evt.getNewValue());
            c.update();
        } catch (Exception e) {
            System.out.println("Error editing price");
        }
    }


}
