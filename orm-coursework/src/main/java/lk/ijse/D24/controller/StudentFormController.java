package lk.ijse.D24.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.D24.bo.BoFactory;
import lk.ijse.D24.bo.cutom.StudentBO;
import lk.ijse.D24.dao.FactoryConfiguration;
import lk.ijse.D24.entity.Student;
import lk.ijse.D24.model.ReservationDetailDTO;
import lk.ijse.D24.model.StudentDTO;
import lk.ijse.D24.tm.ReservationDetailTM;
import lk.ijse.D24.tm.StudentTM;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {
    @FXML
    private TableColumn<?, ?> clmAddress;

    @FXML
    private TableColumn<?, ?> clmContact;

    @FXML
    private TableColumn<?, ?> clmDob;

    @FXML
    private TableColumn<?, ?> clmGender;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmStudentId;

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private AnchorPane reservationPane;

    @FXML
    private TableView<StudentTM> tblStudentDetails;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker txtDob;

    @FXML
    private TextField txtSeach;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtStudentName;

    StudentBO studentBO = (StudentBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.STUDENT);
    List<StudentDTO> allStudentDetails;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGenderForComboBox();
        setCellValueFactory();
        loadDataToTable();
        loadRowDataToFields();
    }

    void setGenderForComboBox() {
        cmbGender.getItems().add("Men");
        cmbGender.getItems().add("Women");
    }

    StudentDTO makeStudentDTO() {
        String studentId = txtStudentId.getText();
        String studentName = txtStudentName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());
        LocalDate dob = txtDob.getValue();
        String gender = cmbGender.getValue();

        StudentDTO studentDTO = new StudentDTO(studentId, studentName, address, contact, dob, gender);
        return studentDTO;
    }

    @FXML
    void clickedOnSearchBar(MouseEvent event) {
        allStudentDetails = studentBO.getAllStudentDetails();
    }

    public void btnUpdateReservationDetailOnAction(javafx.event.ActionEvent event) {
        studentBO.updateStudent(makeStudentDTO());
        loadDataToTable();
        clearFields();
    }

    void clearFields() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDob.getEditor().clear();
        cmbGender.setValue("");
    }

    public void btnClearOnAction(javafx.event.ActionEvent event) {
       clearFields();
    }

    void setCellValueFactory() {
        clmStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        clmDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
    }

    void loadRowDataToFields() {
        tblStudentDetails.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                txtStudentId.setText(tblStudentDetails.getSelectionModel().getSelectedItem().getStudentId());
                txtStudentName.setText(tblStudentDetails.getSelectionModel().getSelectedItem().getStudentName());
                txtContact.setText(String.valueOf(tblStudentDetails.getSelectionModel().getSelectedItem().getContactNo()));
                txtAddress.setText(tblStudentDetails.getSelectionModel().getSelectedItem().getAddress());
                cmbGender.setValue(tblStudentDetails.getSelectionModel().getSelectedItem().getGender());
                txtDob.setValue(tblStudentDetails.getSelectionModel().getSelectedItem().getDob());
            }
        });
    }

    int count = 0;
    public void btnSearchByStudentIdOnAction(javafx.event.ActionEvent event) {
        ObservableList<StudentTM> searchedRow = FXCollections.observableArrayList();

        String valueOnSerchBar = txtSeach.getText();

        for (StudentDTO studentDTO : allStudentDetails) {
            if (valueOnSerchBar.equalsIgnoreCase(studentDTO.getStudentId())) {
                searchedRow.add(new StudentTM(
                        studentDTO.getStudentId(),
                        studentDTO.getStudentName(),
                        studentDTO.getAddress(),
                        studentDTO.getContactNo(),
                        studentDTO.getDob(),
                        studentDTO.getGender()
                ));
                tblStudentDetails.setItems(searchedRow);
                count++;
            }
        }
        if (count == 0) {
            new Alert(Alert.AlertType.INFORMATION, "No Student Found This Id..!").show();
            tblStudentDetails.getItems();
        }
        else {
            count = 0;
        }
    }

    public void typesOnSearchBar(javafx.scene.input.KeyEvent keyEvent) {
        String searchBarValue = txtSeach.getText();
        if (searchBarValue.isEmpty()) {
            loadDataToTable();
        }
    }

    private void loadDataToTable() {
        ObservableList<StudentTM> tableData = FXCollections.observableArrayList();

        List<StudentDTO> allDetails = studentBO.getAllStudentDetails();

        for (StudentDTO studentDTO : allDetails) {
            tableData.add(new StudentTM(
                    studentDTO.getStudentId(),
                    studentDTO.getStudentName(),
                    studentDTO.getAddress(),
                    studentDTO.getContactNo(),
                    studentDTO.getDob(),
                    studentDTO.getGender()
            ));
        }
        tblStudentDetails.setItems(tableData);
    }

    public void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/home_form.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.show();
        stage.centerOnScreen();
    }
}


