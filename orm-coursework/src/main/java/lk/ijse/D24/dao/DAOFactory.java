package lk.ijse.D24.dao;

import lk.ijse.D24.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DaoTypes {
        STUDENT,ROOM,RESERVATION,QUERY,REGISTER
    }

    public SuperDAO getDao(DaoTypes daoType){
        switch (daoType){
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case REGISTER:
                return new RegisterDAOImpl();
            default:
                return null;
        }
    }
}
