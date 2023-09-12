package lk.ijse.D24.dao.impl;

import lk.ijse.D24.dao.FactoryConfiguration;
import lk.ijse.D24.dao.cutom.RegisterDAO;
import lk.ijse.D24.entity.Register;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RegisterDAOImpl implements RegisterDAO {

    @Override
    public boolean registerUser(Register entity) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Long isThereUser(String userName, String password) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select COUNT(e) from Register e where e.password = :password OR e.userName = :username");
        query.setParameter("password",password);
        query.setParameter("username",userName);
        Long count = (Long) query.uniqueResult();
        transaction.commit();
        session.close();
        return count;
    }

    @Override
    public boolean updateUser(Register entity) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }
}
