package lk.ijse.D24.bo.impl;

import lk.ijse.D24.bo.cutom.RegisterBO;
import lk.ijse.D24.dao.DAOFactory;
import lk.ijse.D24.dao.cutom.RegisterDAO;
import lk.ijse.D24.entity.Register;

public class RegisterBOImpl implements RegisterBO {

    RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.REGISTER);

    @Override
    public boolean registerUser(String userName, String password) {
        Register register = new Register(userName, password);
        return registerDAO.registerUser(register);
    }

    @Override
    public boolean isThereUser(String userName, String password) {
        Long count =  registerDAO.isThereUser(userName, password);
        if(count > 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateUser(String userName, String password) {
        Register register = new Register(userName, password);
        return registerDAO.updateUser(register);
    }
}
