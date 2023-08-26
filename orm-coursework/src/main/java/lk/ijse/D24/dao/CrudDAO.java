package lk.ijse.D24.dao;

import lk.ijse.D24.entity.Reservation;
import lk.ijse.D24.entity.Student;

import java.util.List;

public interface CrudDAO<T,id> extends SuperDAO {

    void save(T entity);

    void delete(String id);

    void update(T entity);

    Student search(String id);

    List<Reservation> getAll();
}
