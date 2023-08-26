package lk.ijse.D24.bo.impl;

import lk.ijse.D24.bo.cutom.ReservationBO;
import lk.ijse.D24.dao.DAOFactory;
import lk.ijse.D24.dao.cutom.ReservationDAO;
import lk.ijse.D24.dao.cutom.StudentDAO;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Student;
import lk.ijse.D24.model.ReservationDTO;
import lk.ijse.D24.model.ReservationDetailDTO;
import lk.ijse.D24.model.StudentDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReservationBoImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.RESERVATION);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.STUDENT);
    @Override
    public String genarateNextReservationId() {
         String lastResId = reservationDAO.getLastReseavationId();

        if(!lastResId.isEmpty()) {
            String[] splitId = lastResId.split("R00");
            int newResId = Integer.parseInt(splitId[1]);
            if (newResId < 9) {
                newResId++;
                return "R00" + newResId;
            } else if (newResId < 99) {
                newResId++;
                return "R0" + newResId;
            } else if (newResId < 999) {
                newResId++;
                return "R" + newResId;
            }
            else {
                newResId++;
                return "R" + newResId;
            }
        }
        return "R001";
    }

    @Override
    public void makeReservation(StudentDTO studentDTO, ReservationDTO reservationDTO) {
        Student std = new Student(studentDTO.getStudentId(),studentDTO.getStudentName(),studentDTO.getAddress(),studentDTO.getContactNo(),studentDTO.getDob(),studentDTO.getGender());
        Reservation reservation = new Reservation(reservationDTO.getResId(),reservationDTO.getResDate(),reservationDTO.getStudentId(),reservationDTO.getRoomType(),reservationDTO.getKeyMoney(),std);

        Student student = new Student();
        student.setStudentId(std.getStudentId());
        student.setStudentName(std.getStudentName());
        student.setDob(std.getDob());
        student.setGender(std.getGender());
        student.setContactNo(std.getContactNo());
        student.setAddress(std.getAddress());
        student.getReservations().add(reservation);

        studentDAO.save(student);
    }

    @Override
    public List<ReservationDetailDTO> getAllReservationDetail() {
        List<ReservationDetailDTO> reservationDetailDTOS = new ArrayList<>();

        List<Reservation> allReservationDetail = reservationDAO.getAll();
        for (Reservation details : allReservationDetail) {
            reservationDetailDTOS.add(new ReservationDetailDTO(
                    details.getResId(),
                    details.getStudentId(),
                    details.getStudent().getStudentName(),
                    details.getResDate(),
                    details.getRoomType(),
                    details.getKeyMoney(),
                    details.getStudent().getGender(),
                    details.getStudent().getDob(),
                    details.getStudent().getAddress(),
                    details.getStudent().getContactNo()
            ));
        }
        return reservationDetailDTOS;
    }

    @Override
    public void updateReservationDetails(StudentDTO updateStudentDTO, ReservationDTO updateReservationDTO) {
        Student std = new Student(updateStudentDTO.getStudentId(),updateStudentDTO.getStudentName(),updateStudentDTO.getAddress(),updateStudentDTO.getContactNo(),updateStudentDTO.getDob(),updateStudentDTO.getGender());

        Reservation reservationEntity = new Reservation();
        reservationEntity.setResId(updateReservationDTO.getResId());
        reservationEntity.setResDate(updateReservationDTO.getResDate());
        reservationEntity.setStudentId(updateStudentDTO.getStudentId());
        reservationEntity.setRoomType(updateReservationDTO.getRoomType());
        reservationEntity.setKeyMoney(updateReservationDTO.getKeyMoney());
        reservationEntity.setStudent(std);

        reservationDAO.update(reservationEntity);
    }
}
