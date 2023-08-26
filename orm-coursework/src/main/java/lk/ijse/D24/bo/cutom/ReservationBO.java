package lk.ijse.D24.bo.cutom;

import lk.ijse.D24.bo.SuperBo;
import lk.ijse.D24.dao.SuperDAO;
import lk.ijse.D24.model.ReservationDTO;
import lk.ijse.D24.model.ReservationDetailDTO;
import lk.ijse.D24.model.StudentDTO;

import java.sql.Date;
import java.util.List;

public interface ReservationBO extends SuperBo {

    String genarateNextReservationId();

    void makeReservation(StudentDTO makeStudentDTO, ReservationDTO makeReservationDTO);

    List<ReservationDetailDTO> getAllReservationDetail();

    void updateReservationDetails(StudentDTO makeStudentDTO, ReservationDTO makeReservationDTO);
}
