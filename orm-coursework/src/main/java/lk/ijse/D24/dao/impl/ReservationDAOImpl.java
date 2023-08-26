package lk.ijse.D24.dao.impl;

import lk.ijse.D24.dao.FactoryConfiguration;
import lk.ijse.D24.dao.cutom.ReservationDAO;
import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public String getLastReseavationId() {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("select resId from Reservation order by resId desc limit 1");
            String lastResId = (String) query.uniqueResult();
            transaction.commit();
            session.close();
            return lastResId;
    }

//    @Override
//    public Student updateResavationDetails(Reservation reservation) {
//        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
//        Transaction transaction = session.beginTransaction();
//        Query query = session.createQuery("update Reservation set resData = ?1, studentId = ?2, roomtype = ?3, keyMoney = ?4, student = ?5 where resId = ?6");
//        query.setParameter(1,reservation.getResDate());
//        query.setParameter(2,reservation.getStudentId());
//        query.setParameter(3,reservation.getRoomType());
//        query.setParameter(4,reservation.getKeyMoney());
//        query.setParameter(5,reservation.getStudent());
//        query.setParameter(6,reservation.getResId());
//
//        transaction.commit();
//        session.close();
//
//    }

    @Override
    public List<Reservation> getAll() {

        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Reservation");
        List<Reservation> reservationDetails = query.list();
        transaction.commit();
        session.close();
        return reservationDetails;
    }

    @Override
    public void save(Reservation entity) {}

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Reservation reservation) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(reservation);
        transaction.commit();
        session.close();
    }

    @Override
    public Student search(String id) {
        return null;
    }
}
