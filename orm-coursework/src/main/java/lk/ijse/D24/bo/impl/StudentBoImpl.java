package lk.ijse.D24.bo.impl;

import lk.ijse.D24.bo.cutom.StudentBO;
import lk.ijse.D24.dao.DAOFactory;
import lk.ijse.D24.dao.cutom.StudentDAO;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Student;
import lk.ijse.D24.model.ReservationDTO;
import lk.ijse.D24.model.ReservationDetailDTO;
import lk.ijse.D24.model.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public class StudentBoImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.STUDENT);

    @Override
    public Student searchStudentOnId(String sId) {
        return studentDAO.search(sId);
    }

    @Override
    public List<StudentDTO> getAllStudentDetails() {
        List<StudentDTO> studentDTOS = new ArrayList<>();

        List<Student> allStudentDetails = studentDAO.getAll();
        for (Student student : allStudentDetails) {
            studentDTOS.add(new StudentDTO(
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getAddress(),
                    student.getContactNo(),
                    student.getDob(),
                    student.getGender()
            ));
        }
        return studentDTOS;
    }

    @Override
    public void updateStudent(StudentDTO makeStudentDTO) {
        studentDAO.update(new Student(
                makeStudentDTO.getStudentId(),
                makeStudentDTO.getStudentName(),
                makeStudentDTO.getAddress(),
                makeStudentDTO.getContactNo(),
                makeStudentDTO.getDob(),
                makeStudentDTO.getGender()
        ));
    }
}
