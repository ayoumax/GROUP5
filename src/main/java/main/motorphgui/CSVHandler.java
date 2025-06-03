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
         emp.getLastName() + "," +
         emp.getFirstName() + "," +
         emp.getAge() + "," +
         emp.getPosition() + "," +
         comp.getBasicSalary() + "," +
         comp.getAllowance() + "," +
         gov.getSssNumber() + "," +
         gov.getPhilHealthNumber() + "," +
         gov.getTin() + "," +
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
                if (parts.length >= 11) {
                    int id = Integer.parseInt(parts[0].trim().replaceAll("\"",""));
                    String lastName = parts[1].trim();
                    String firstName = parts[2].trim();
                    int age = Integer.parseInt(parts[3].trim().replaceAll("\"",""));
                    String position = parts[4].trim();
                    float salary = Float.parseFloat(parts[5].trim().replaceAll("\"",""));
                    float allowance = Float.parseFloat(parts[6].trim().replaceAll("\"",""));
                    String sss = parts[7].trim();
                    String philHealth = parts[8].trim();
                    String tin = parts[9].trim();
                    String pagIbig = parts[10].trim();
                    
                    GovernmentDetails gov = new GovernmentDetails(sss, philHealth, tin, pagIbig);
                    CompensationDetails comp = new CompensationDetails(salary, allowance);
                    
             Employee emp = new Employee(id,lastName, firstName, age, position, salary, gov, comp); // Fixed instantiation
                    employees.add(emp);
                   
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading employees: " + e.getMessage());
        }

        return employees;
    }
}