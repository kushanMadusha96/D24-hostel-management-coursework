package lk.ijse.D24.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.D24.bo.BoFactory;
import lk.ijse.D24.bo.cutom.RegisterBO;
import lk.ijse.D24.utill.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {
    public AnchorPane loginPane;
    public TextField txtUserName;
    public TextField txtPasword;

    RegisterBO registerBO = (RegisterBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.REGISTER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextFieldValidation();
    }

    public void setTextFieldValidation(){
        ValidationUtil.setFocus(txtUserName,ValidationUtil.namePattern);
        ValidationUtil.setFocus(txtPasword,ValidationUtil.passwordPattern);

    }

    private boolean isValidated(){
        if(txtUserName.getStyle().equals(Paint.valueOf("red")) || txtPasword.getStyle().equals(Paint.valueOf("red"))

        ){
            return false;
        }else if(txtPasword.getText().equals("") ||
                txtUserName.getText().equals("")

        ){
            return false;
        }
        return true;
    }

    public void btnRegisterOnAction(ActionEvent event) {
        if(!isValidated()){
            new Alert(Alert.AlertType.WARNING,
                    "Fill All Fields Correctly !",
                    ButtonType.OK
            ).show();
            return;
        }
        String userName = txtUserName.getText();
        String password = txtPasword.getText();

        boolean isRegistered = registerBO.registerUser(userName,password);
        new Alert(Alert.AlertType.CONFIRMATION,"user registered successfully...").show();

        if(true == isRegistered) {
            try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        }
    }
}
