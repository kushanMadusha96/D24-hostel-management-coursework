package lk.ijse.D24.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.D24.bo.BoFactory;
import lk.ijse.D24.bo.cutom.ReservationBO;
import lk.ijse.D24.bo.cutom.StudentBO;
import lk.ijse.D24.model.ReservationDTO;
import lk.ijse.D24.model.ReservationDetailDTO;
import lk.ijse.D24.model.StudentDTO;
import lk.ijse.D24.tm.ReservationDetailTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RoomReservationController implements Initializable {
    @FXML
    public JFXComboBox cmbFilter;

    @FXML
    public TextField txtReservationId;

    @FXML
    public TextField txtKeyMoneyAmount;

    @FXML
    public TableColumn<ReservationDetailTM,?> clmRoomType;

    @FXML
    public TableColumn<ReservationDetailTM,?> clmRestId;

    @FXML
    public TableColumn<ReservationDetailTM,?> clmRestDate;

    @FXML
    public TableView<ReservationDetailTM> tblReservationDetail;

    @FXML
    public ComboBox<String> cmbRoomType;

    @FXML
    public ComboBox<String> cmbGender;

    @FXML
    private AnchorPane ancorpaneTable;

    @FXML
    private TableColumn<ReservationDetailTM, ?> clmAddress;

    @FXML
    private TableColumn<ReservationDetailTM, ?> clmContact;

    @FXML
    private TableColumn<ReservationDetailTM, ?> clmDob;

    @FXML
    private TableColumn<ReservationDetailTM, ?> clmGender;

    @FXML
    private TableColumn<ReservationDetailTM, ?> clmMoney;

    @FXML
    private TableColumn<ReservationDetailTM, ?> clmName;

    @FXML
    private TableColumn<ReservationDetailTM, ?> clmStudentId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker txtDob;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtStudentName;

    StudentBO studentBO = (StudentBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.STUDENT);
    ReservationBO reservationBO = (ReservationBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.RESERVATION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtReservationId.setText(reservationBO.genarateNextReservationId());
        setGenderForComboBox();
        setRoomTypeForComboBox();
        setCellValueFactory();
        loadDataToTable();
        loadRowDataToFields();
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

    ReservationDTO makeReservationDTO() {
        String reservationId = txtReservationId.getText();
        java.util.Date date = new java.util.Date();
        java.sql.Date registerDate = new java.sql.Date(date.getTime());
        String studentId = txtStudentId.getText();
        String roomType = cmbRoomType.getValue();
        double keymoney = Double.parseDouble(txtKeyMoneyAmount.getText());

        ReservationDTO reservationDTO = new ReservationDTO(reservationId, studentId, registerDate, roomType, keymoney);
        return reservationDTO;
    }

    void clearFields() {
        txtReservationId.setText(reservationBO.genarateNextReservationId());
        txtStudentId.clear();
        txtStudentName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDob.getEditor().clear();
        txtKeyMoneyAmount.clear();
        cmbRoomType.setValue("");
        cmbGender.setValue("");
    }

    void setGenderForComboBox(){
        cmbGender.getItems().add("Men");
        cmbGender.getItems().add("Women");
    }

    void setRoomTypeForComboBox(){
        cmbRoomType.getItems().add("Non-AC");
        cmbRoomType.getItems().add("Non-AC / Food");
        cmbRoomType.getItems().add("AC");
        cmbRoomType.getItems().add("AC / Food");
    }

    void setCellValueFactory(){
       clmStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
       clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
       clmContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
       clmDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
       clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
       clmMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
       clmName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
       clmRestId.setCellValueFactory(new PropertyValueFactory<>("resId"));
       clmRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
       clmRestDate.setCellValueFactory(new PropertyValueFactory<>("resDate"));
    }

    void loadDataToTable(){
        ObservableList<ReservationDetailTM> tableData = FXCollections.observableArrayList();

        List<ReservationDetailDTO> allDetails = reservationBO.getAllReservationDetail();

        for ( ReservationDetailDTO reservationDetailDTO : allDetails ) {
            tableData.add(new ReservationDetailTM(
                    reservationDetailDTO.getResId(),
                    reservationDetailDTO.getStudentId(),
                    reservationDetailDTO.getStudentName(),
                    reservationDetailDTO.getResDate(),
                    reservationDetailDTO.getRoomType(),
                    reservationDetailDTO.getKeyMoney(),
                    reservationDetailDTO.getGender(),
                    reservationDetailDTO.getDob(),
                    reservationDetailDTO.getAddress(),
                    reservationDetailDTO.getContactNo()
            ));
        }
        tblReservationDetail.setItems(tableData);
    }

    void loadRowDataToFields() {
        tblReservationDetail.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                txtStudentId.setText(tblReservationDetail.getSelectionModel().getSelectedItem().getStudentId());
                txtReservationId.setText(tblReservationDetail.getSelectionModel().getSelectedItem().getResId());
                txtStudentName.setText(tblReservationDetail.getSelectionModel().getSelectedItem().getStudentName());
                txtContact.setText(String.valueOf(tblReservationDetail.getSelectionModel().getSelectedItem().getContactNo()));
                txtAddress.setText(tblReservationDetail.getSelectionModel().getSelectedItem().getAddress());
                txtKeyMoneyAmount.setText(String.valueOf(tblReservationDetail.getSelectionModel().getSelectedItem().getKeyMoney()));
                cmbRoomType.setValue(tblReservationDetail.getSelectionModel().getSelectedItem().getRoomType());
                cmbGender.setValue(tblReservationDetail.getSelectionModel().getSelectedItem().getGender());
                txtDob.setValue(tblReservationDetail.getSelectionModel().getSelectedItem().getDob());
            }
        });
    }

    public void btnMakeRoomReservationOnAction(ActionEvent event) {
        reservationBO.makeReservation(makeStudentDTO(), makeReservationDTO());
    }

    public void btnUpdateReservationDetailOnAction(ActionEvent event) {
        reservationBO.updateReservationDetails(makeStudentDTO(),makeReservationDTO() );
        loadDataToTable();
        clearFields();
    }

    public void btnDeleteStudentOnAction(ActionEvent event) {
        studentBO.deleteStudent(txtStudentId.getText());
    }

    public void txtSearchStudentOnId(ActionEvent event) {
        studentBO.searchStudentOnId(txtStudentId.getText());
    }

    public void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

}