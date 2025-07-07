package main.motorphgui;

import java.util.Map;

public class Employee {
    private int employeeId;
    private String fullName;
    private String position;

    private double hourlyRate;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;

    public Employee(int employeeId, String fullName, String position) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.position = position;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getRiceSubsidy() {
        return riceSubsidy;
    }

    public double getPhoneAllowance() {
        return phoneAllowance;
    }

    public double getClothingAllowance() {
        return clothingAllowance;
    }

    // Setters
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setRiceSubsidy(double riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }

    public void setPhoneAllowance(double phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public void setClothingAllowance(double clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }
    
    Map<Integer, CompensationRow> compData = CompensationLoader.loadCompensation("data/compensation.csv");

    for (Employee emp : employeeList) {
    CompensationRow comp = compData.get(emp.getEmployeeId());
        if (comp != null) {
            emp.setHourlyRate(comp.getHourlyRate());
            emp.setRiceSubsidy(comp.getRiceSubsidy());
            emp.setPhoneAllowance(comp.getPhoneAllowance());
            emp.setClothingAllowance(comp.getClothingAllowance());
        }
    }
}
