package lk.ijse.D24.controller;

import com.jfoenix.controls.JFXButton;
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

public class LoginFormController implements Initializable {

    public TextField txtUserName;
    public TextField txtPaswordOnAction;
    public AnchorPane loginPane;

    RegisterBO registerBO = (RegisterBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.REGISTER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextFieldValidation();
    }

    public void setTextFieldValidation(){
        ValidationUtil.setFocus(txtUserName,ValidationUtil.namePattern);
        ValidationUtil.setFocus(txtPaswordOnAction,ValidationUtil.passwordPattern);

    }

    private boolean isValidated(){
        if(txtUserName.getStyle().equals(Paint.valueOf("red")) || txtPaswordOnAction.getStyle().equals(Paint.valueOf("red"))

        ){
            return false;
        }else if(txtPaswordOnAction.getText().equals("") ||
                txtUserName.getText().equals("")

        ){
            return false;
        }
        return true;
    }

    public void btnLoginOnAction(ActionEvent event) {
        if(!isValidated()){
            new Alert(Alert.AlertType.WARNING,
                    "Fill All Fields Correctly !",
                    ButtonType.OK
            ).show();
            return;
        }

        String userName = txtUserName.getText();
        String password = txtPaswordOnAction.getText();

        boolean isThere = registerBO.isThereUser(userName, password);

        if( true == isThere) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.centerOnScreen();
//                new Alert(Alert.AlertType.CONFIRMATION, "login successfully...").show();
            } catch (IOException ioException) {
                new Alert(Alert.AlertType.WARNING, "something went wrong, please try again...").show();
                System.out.println(ioException);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "can't find user! please register...").show();
        }
    }

    public void btnRegisterOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/register_form.fxml"));
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
