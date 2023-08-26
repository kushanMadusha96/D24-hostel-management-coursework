package lk.ijse.D24.dao.impl;


import lk.ijse.D24.dao.DAOFactory;
import lk.ijse.D24.dao.FactoryConfiguration;
import lk.ijse.D24.dao.cutom.ReservationDAO;
import lk.ijse.D24.dao.cutom.StudentDAO;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.RESERVATION);

    @Override
    public void save(Student student) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();;
    }

    @Override
    public void delete(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(id);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Student entity) {
//        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.persist(entity);
//        transaction.commit();
//        session.close();
    }

    @Override
    public Student search(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM student WHERE studentId = ?",id);
        nativeQuery.addEntity(Student.class);
        Student student = (Student) nativeQuery.uniqueResult();
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public List<Reservation> getAll() {
        return null;
    }

//    @Override
//    public void studentMakeRoomReservation(Student student) {
//
//    }





//    @Override
//    public void studentMakeRoomReservation(Student std) {
//        Student student = new Student();
//        student.setStudentId(std.getStudentId());
//        student.setStudentId(std.getStudentName());
//        student.setAddress(std.getAddress());
//        student.setContactNo(std.getContactNo());
//        student.setDob(std.getDob());
//        student.setGender(std.getGender());
//
//        Reservation reservation = new Reservation();
//        reservation.setResId(std.getReservations().get(1).getResId());
//        reservation.setResDate(std.getReservations().get(1).getResDate());
//        reservation.setStudentId(std.getReservations().get(1).getStudentId());
//        reservation.setRoomType(std.getReservations().get(1).getRoomType());
//        reservation.setKeyMoney(std.getReservations().get(1).getKeyMoney());
//        reservation.setStudent(student);
//
//        student.getReservations().add(reservation);
//
//        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.persist(student);
//        session.persist(reservation);
//        transaction.commit();
//        session.close();
//    }
}
