package lk.ijse.D24.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.sql.Date;

@Entity
public class Reservation {
    @Id
    private String resId;
    private Date resDate;
    private String studentId;
    private String roomType;
    private double keyMoney;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    public Reservation() {
    }

    public Reservation(String resId, Date resDate, String studentId, String roomType, double keyMoney, Student student) {
        this.resId = resId;
        this.resDate = resDate;
        this.studentId = studentId;
        this.roomType = roomType;
        this.keyMoney = keyMoney;
        this.student = student;
    }

    public String getResId() {        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public Date getResDate() {
        return resDate;
    }

    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(double keyMoney) {
        this.keyMoney = keyMoney;
    }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }
}
