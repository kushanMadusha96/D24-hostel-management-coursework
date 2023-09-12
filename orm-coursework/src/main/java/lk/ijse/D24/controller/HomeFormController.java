package lk.ijse.D24.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.ijse.D24.bo.BoFactory;
import lk.ijse.D24.bo.cutom.RoomBO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeFormController implements Initializable {

    public Label lblAcRoomCount;
    public Label lblAcFoodCount;
    public Label lblNonAcRoomCount;
    public Label lblNonAcFoodRoomCount;

    RoomBO roomBO = (RoomBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ROOM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setRoomCountForLabel();
    }
    public void btnRervationDetailOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/room_reservation.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.show();
        stage.centerOnScreen();
    }

    public void btnRoomOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/room_form.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.show();
        stage.centerOnScreen();
    }

//    public void btnProfileOnAction(ActionEvent event) {
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("/view/profile_form.fxml"));
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//            stage.centerOnScreen();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//    }

    void setRoomCountForLabel() {
        lblAcFoodCount.setText(String.valueOf(roomBO.acRoomWithFoodCount()));
        lblAcRoomCount.setText(String.valueOf(roomBO.acRoomWithoutFoodCount()));
        lblNonAcFoodRoomCount.setText(String.valueOf(roomBO.nonAcWithFoodRoomCount()));
        lblNonAcRoomCount.setText(String.valueOf(roomBO.nonAcWithoutFoodRoomCount()));
    }


    public void btnstudentOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/student_form.fxml"));
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
