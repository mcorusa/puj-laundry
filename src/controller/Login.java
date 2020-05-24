package controller;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Employee;

import java.io.IOException;
import java.security.Key;

public class Login {
    @FXML
    public static Employee loggedInEmployee;
    @FXML
    Button loginBtn;
    @FXML
    TextField usernameTxt;
    @FXML
    TextField passwdTxt;
    @FXML
    Label errorLbl;

    @FXML
    public void input (KeyEvent evt){
        if (evt.getCode() == KeyCode.ENTER) {
            loginBtn.fire();
            evt.consume();
        }
    }
    @FXML
    public void loginm(ActionEvent ev){

        String username = this.usernameTxt.getText();
        String password = this.passwdTxt.getText();

        if (username.equals("") || password.equals("")){
            errorLbl.setVisible(true);

        } else {
            errorLbl.setVisible(false);

            try {
                Login.loggedInEmployee= Employee.login(username, password);

                if(Login.loggedInEmployee!=null )
                {
                    if(Login.loggedInEmployee.getRole().equals("admin")){Main.showWindow(getClass(), "../view/Admin.fxml", "Welcome Admin", 700, 500); }
                    else {Main.showWindow(getClass(), "../view/Employee.fxml", "Welcome", 700, 500);}
                }
                else{ errorLbl.setVisible(true); }


            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}