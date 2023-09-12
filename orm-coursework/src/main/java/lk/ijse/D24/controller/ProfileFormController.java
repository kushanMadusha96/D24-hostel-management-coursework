package lk.ijse.D24.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.D24.bo.BoFactory;
import lk.ijse.D24.bo.cutom.RegisterBO;

import java.io.IOException;

public class ProfileFormController {
    public TextField txtUsername;
    public TextField txtPassword;

    RegisterBO registerBO = (RegisterBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.REGISTER);

    public void btnUpdateProfileOnAction(ActionEvent event) {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        boolean isUpdated = registerBO.updateUser(userName,password);

        if(true == isUpdated) {
            try {
                new Alert(Alert.AlertType.CONFIRMATION,"user updated successfully...").show();
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
