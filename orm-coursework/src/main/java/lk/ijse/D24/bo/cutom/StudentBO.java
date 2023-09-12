package lk.ijse.D24.bo.cutom;

import lk.ijse.D24.bo.SuperBo;
import lk.ijse.D24.entity.Student;
import lk.ijse.D24.model.ReservationDTO;
import lk.ijse.D24.model.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBo {

    Student searchStudentOnId(String sId);

    List<StudentDTO> getAllStudentDetails();

    void updateStudent(StudentDTO makeStudentDTO);

}
