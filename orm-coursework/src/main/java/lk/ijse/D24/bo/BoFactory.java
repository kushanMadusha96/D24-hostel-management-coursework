package lk.ijse.D24.bo;

import lk.ijse.D24.bo.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBoFactory() {
        return boFactory == null ? boFactory = new BoFactory() : boFactory;
    }

    public enum BoTypes {
        STUDENT, ROOM,RESERVATION,PAYMENT,REGISTER
    }

    public SuperBo getBo(BoTypes boType) {
        switch (boType) {
            case STUDENT:
                return new StudentBoImpl();
            case ROOM:
                return new RoomBoImpl();
            case RESERVATION:
                return new ReservationBoImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case REGISTER:
                return new RegisterBOImpl();
            default:
                return null;
        }
    }
}
