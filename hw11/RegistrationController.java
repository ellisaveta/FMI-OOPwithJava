package registrationform.hw11;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistrationController  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnValidate;

    @FXML
    private TextField txtConfirmPass;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhone;

    @FXML
    void btnQuitOnAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnValidateOnAction(ActionEvent event) {
        if(!Validate.validateName(txtName.getText()))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid name!");
            a.show();
        }
        else if(!Validate.validatePhone(txtPhone.getText()))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid phone!");
            a.show();
        }
        else if(!Validate.validateEmail(txtEmail.getText()))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid email!");
            a.show();
        }
        else if(!Validate.validatePassword(txtPassword.getText()))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid password!");
            a.show();
        }
        else if(!Validate.validateConfirmPass(txtPassword.getText(), txtConfirmPass.getText()))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Confirm password does not match with password!");
            a.show();
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Valid input! Thank you!");
            a.show();
        }
    }

    @FXML
    void initialize() {
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'registration-view.fxml'.";
        assert btnValidate != null : "fx:id=\"btnValidate\" was not injected: check your FXML file 'registration-view.fxml'.";
        assert txtConfirmPass != null : "fx:id=\"txtConfirmPass\" was not injected: check your FXML file 'registration-view.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'registration-view.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'registration-view.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'registration-view.fxml'.";
        assert txtPhone != null : "fx:id=\"txtPhone\" was not injected: check your FXML file 'registration-view.fxml'.";

    }

}
