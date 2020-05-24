package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import main.Main;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
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
    TableColumn <Employee, Integer> columnEmployeeID;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //populating tables (employees, students, machines, cycles), setting certain columns editable

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
        this.columnStudentLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.columnStudentTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        this.columnStudentName.setEditable(true);
        this.columnStudentName.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnStudentLastname.setEditable(true);
        this.columnStudentLastname.setCellFactory(TextFieldTableCell.forTableColumn());
        this.columnStudentTelephone.setEditable(true);
        this.columnStudentTelephone.setCellFactory(TextFieldTableCell.forTableColumn());
        this.populateTableStudent();

        this.columnMachineModel.setCellValueFactory(new PropertyValueFactory<>("model"));

        this.columnMachineModel.setEditable(true);
        this.columnMachineModel.setCellFactory(TextFieldTableCell.forTableColumn());
        this.populateTableMachine();

        this.columnCycleType.setCellValueFactory(new PropertyValueFactory<>( "type"));
        this.columnCycleDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        this.columnCyclePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        this.columnCycleType.setEditable(true);
        this.columnCycleType.setCellFactory(TextFieldTableCell.forTableColumn());
        //this.columnCycleDuration.setEditable(true);
        //this.columnCycleDuration.setCellFactory((TextFieldTableCell.forTableColumn());
        //this.columnCyclePrice.setEditable(true);
        //this.columnCyclePrice.setCellFactory(TextFieldTableCell.forTableColumn());
        this.populateTableCycle();

    }

    private void populateTableEmployee(){
        try {
            this.tableEmployee.getItems().setAll((Collection<? extends Employee>) Employee.list(Employee.class));
            this.tableEmployee.setEditable(true);
        } catch (Exception e) {
            System.out.println("Error Fetching Data into Table");
        }
    }

    private void populateTableStudent(){
        try {
            this.tableStudent.getItems().setAll((Collection<? extends Student>) Student.list(Student.class));
            this.tableStudent.setEditable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void populateTableMachine(){
        try {
            this.tableMachine.getItems().setAll((Collection<? extends Machine>) Machine.list(Machine.class));
            this.tableMachine.setEditable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateTableCycle(){
        try {
            this.tableCycle.getItems().setAll((Collection<? extends Cycle>) Cycle.list(Cycle.class));
            this.tableCycle.setEditable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void removeEmployee (ActionEvent event) throws Exception {
        Employee e = tableEmployee.getSelectionModel().getSelectedItem();
        e.delete();
        this.populateTableEmployee();
    }

    @FXML
    public void removeStudent (ActionEvent event) throws Exception {
        Student s = tableStudent.getSelectionModel().getSelectedItem();
        s.delete();
        this.populateTableStudent();
    }

    @FXML
    public void removeMachine (ActionEvent event) throws Exception {
        Machine m = tableMachine.getSelectionModel().getSelectedItem();
        m.delete();
        this.populateTableMachine();
    }

    public void removeCycle (ActionEvent event) throws Exception {
        Cycle c = tableCycle.getSelectionModel().getSelectedItem();
        c.delete();
        this.populateTableCycle();
    }



    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

        //STACKPANE ---> showing certain Pane
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

        //handle BACK BUTTON
        if(event.getSource().equals(BtnBack2) || event.getSource().equals(BtnBack3) || event.getSource().equals(BtnBack4) || event.getSource().equals(BtnBack5)) {
            Pn1admin.toFront();
        }


        //Adding employee to database
        if(event.getSource().equals(btnAddemployee)){
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

                } catch (Exception e) {
                    System.out.println("Nope");
                }

                this.populateTableEmployee();
            }
        }

        //Adding student to database
        if(event.getSource().equals(btnAddstudent)){
            if(!this.txtSname.equals("") &&
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

                } catch (Exception e) {
                    System.out.println("Nope");
                }
            }
            this.populateTableStudent();
        }

        //Adding machine to database
        if(event.getSource().equals(btnAddMachine)){
            if(!this.txtModel.equals("")) {
                Machine ma = new Machine();
                ma.setModel(this.txtModel.getText());
                try {
                    ma.save();
                    this.txtModel.setText("");
                } catch (Exception e) {
                    System.err.println("Nope");
                }
                this.populateTableMachine();
            }
        }

        //Adding Cycle to database
        if(event.getSource().equals(btnAddCycle)){
            if(!this.txtType.equals("") &&
                    !this.txtDuration.getText().equals("") &&
                    !this.txtPrice.getText().equals("")) {
                Cycle cy = new Cycle();
                cy.setType(this.txtType.getText());
                cy.setDuration(Integer.parseInt(this.txtDuration.getText()));
                cy.setPrice(Float.parseFloat(this.txtPrice.getText()));
                try {
                    cy.save();
                    this.txtType.setText("");
                    this.txtDuration.setText("");
                    this.txtPrice.setText("");
                } catch (Exception e) {
                    System.err.println("Nope");
                }
            }
            this.populateTableCycle();
        }

        //Adding Cycle to the certain Machine
        if(event.getSource().equals(btnAddCycleOnMachine)){

            Cycle c = tableCycle.getSelectionModel().getSelectedItem();
            Machine m = tableMachine.getSelectionModel().getSelectedItem();

            int ide = c.getId();
            int idem = m.getId();

            String machindescription = m.getModel();
            String cycledescription = c.getType();

            CycleOnMachine cm = new CycleOnMachine();
            cm.setCycleFk(ide);
            cm.setMachineFk(idem);
            cm.setDescription(cycledescription +" on "+ machindescription);


            try {
                cm.save();
                tableMachine.getSelectionModel().clearSelection();
                tableCycle.getSelectionModel().clearSelection();

            } catch (Exception e) {
                System.out.println("Nope");
            }

        }

    }
    @FXML
    public  void Logout (ActionEvent ev) throws IOException {
        Main.showWindow(getClass(), "../view/Login.fxml", "Login", 700, 500);
        Login.loggedInEmployee=null;
    }


    //edit tables' fields
    @FXML
    public void editLastnameEmployee(TableColumn.CellEditEvent<Employee, String> evt) throws Exception {
        Employee e = evt.getRowValue();
        e.setLastname(evt.getNewValue());
        e.update();
    }

    @FXML
    public void editEmailEmployee(TableColumn.CellEditEvent<Employee, String> evt) throws Exception {
        Employee e = evt.getRowValue();
        e.setLastname(evt.getNewValue());
        e.update();
    }

    @FXML
    public void editFirstnameStudent(TableColumn.CellEditEvent<Student, String> evt) throws Exception {
        Student s = evt.getRowValue();
        s.setLastname(evt.getNewValue());
        s.update();
    }

    @FXML
    public void editLastnameStudent(TableColumn.CellEditEvent<Student, String> evt) throws Exception {
        Student s = evt.getRowValue();
        s.setLastname(evt.getNewValue());
        s.update();
    }

    @FXML
    public void editTelephoneStudent(TableColumn.CellEditEvent<Student, String> evt) throws Exception {
        Student s = evt.getRowValue();
        s.setLastname(evt.getNewValue());
        s.update();
    }

    @FXML
    public void editMachineModel(TableColumn.CellEditEvent<Machine, String> evt) throws Exception {
        Machine m = evt.getRowValue();
        m.setModel(evt.getNewValue());
        m.update();
    }

    @FXML
    public void editCycleType(TableColumn.CellEditEvent<Cycle, String> evt) throws Exception {
        Cycle c = evt.getRowValue();
        c.setType(evt.getNewValue());
        c.update();
    }


}
