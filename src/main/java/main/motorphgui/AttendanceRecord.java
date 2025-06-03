/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @author WINDOWS 10
 */
public class AttendanceRecord {
    private int employeeId;
    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;

    public AttendanceRecord(int employeeId, LocalDate date, LocalTime timeIn, LocalTime timeOut) {
        this.employeeId = employeeId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public float getTotalHoursWorked() {
        return (float) java.time.Duration.between(timeIn, timeOut).toHours();
    }
    
}
