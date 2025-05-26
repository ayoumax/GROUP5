/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

import java.io.*;
import java.util.*;

/**
 *
 * @author WINDOWS 10
 */
public class CSVHandler {
      public static void saveEmployees(List<Employee> employees, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Employee emp : employees) {
                CompensationDetails comp = emp.getCompensationDetails();
                GovernmentDetails gov = emp.getGovernmentDetails();

                bw.write(emp.getEmployeeId() + "," +
                         emp.getName() + "," +
                         emp.getAge() + "," +
                         emp.getPosition() + "," +
                         comp.getBasicSalary() + "," +
                         comp.getAllowance() + "," +
                         gov.getSssNumber() + "," +
                         gov.getPhilHealthNumber() + "," +
                         gov.getPagIbigNumber());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving employees: " + e.getMessage());
        }
    }

    public static List<Employee> loadEmployees(String filename) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 9) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String position = parts[3];
                    float salary = Float.parseFloat(parts[4]);
                    float allowance = Float.parseFloat(parts[5]);
                    String sss = parts[6];
                    String philHealth = parts[7];
                    String pagIbig = parts[8];

                    CompensationDetails comp = new CompensationDetails(salary, allowance);
                    GovernmentDetails gov = new GovernmentDetails(sss, philHealth, pagIbig);

             Employee emp = new Employee(id, name, age, position, salary, gov, comp); // Fixed instantiation
                    employees.add(emp);
                   
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading employees: " + e.getMessage());
        }

        return employees;
    }
}