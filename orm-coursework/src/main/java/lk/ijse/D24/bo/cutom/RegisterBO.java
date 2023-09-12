package lk.ijse.D24.bo.cutom;

import lk.ijse.D24.bo.SuperBo;

public interface RegisterBO extends SuperBo {
    boolean registerUser(String userName, String password);

    boolean isThereUser(String userName, String password);

    boolean updateUser(String userName, String password);
}
