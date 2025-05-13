/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphapp;

/**
 *
 * @author Ayou
 */
import java.util.Date;

public class Leave {

    private int leaveId;
    private int employeeId;
    private Date startDate;
    private Date endDate;
    private String status;

    public Leave(int leaveId, int employeeId, Date startDate, Date endDate) {
        this.leaveId = leaveId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending";
    }

    public void requestLeave() {
        System.out.println("Leave requested from " + startDate + " to " + endDate);
    }

    public void approveLeave() {
        status = "Approved";
    }

    public void rejectLeave() {
        status = "Rejected";
    }
}
