package lk.ijse.D24.dao.cutom;

import lk.ijse.D24.dao.CrudDAO;
import lk.ijse.D24.dao.SuperDAO;
import lk.ijse.D24.entity.Register;

public interface RegisterDAO extends SuperDAO {

    boolean registerUser(Register register);

    Long isThereUser(String userName, String password);

    boolean updateUser(Register register);
}
