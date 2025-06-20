/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;
import com.opencsv.bean.*;
import java.io.*;
import java.util.*;


/**
 *
 * @author WINDOWS 10
 */
public class CSVHandler {
    private static final String CSV_PATH = "data/employee.csv";

     public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Reader reader = new FileReader(CSV_PATH)) {
            CsvToBean<EmployeeCSV> csvToBean = new CsvToBeanBuilder<EmployeeCSV>(reader)
                .withType(EmployeeCSV.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

            for (EmployeeCSV csv : csvToBean) {
                GovernmentDetails gov = new GovernmentDetails(csv.getSss(), csv.getPhilHealth(), csv.getTin(), csv.getPagIbig());
                CompensationDetails comp = new CompensationDetails(csv.getBasicSalary(), csv.getAllowance());

                Employee emp = new Employee(csv.getEmployeeId(), csv.getLastName(), csv.getFirstName(), 
                                             csv.getAge(), csv.getPosition(), csv.getBasicSalary(), gov, comp);
                employees.add(emp);
            }
        } catch (Exception e) {
            System.err.println("Error loading CSV: " + e.getMessage());
        }
        return employees;
    }

    public static void saveEmployees(List<Employee> employees) {
        try (Writer writer = new FileWriter(CSV_PATH)) {
            StatefulBeanToCsv<EmployeeCSV> beanToCsv = new StatefulBeanToCsvBuilder<EmployeeCSV>(writer)
                    .withApplyQuotesToAll(false)
                    .build();

            List<EmployeeCSV> csvList = new ArrayList<>();
            for (Employee emp : employees) {
                EmployeeCSV csv = new EmployeeCSV();
                csv.setEmployeeId(emp.getEmployeeId());
                csv.setLastName(emp.getLastName());
                csv.setFirstName(emp.getFirstName());
                csv.setAge(emp.getAge());
                csv.setPosition(emp.getPosition());
                csv.setBasicSalary(emp.getSalary());
                csv.setAllowance(emp.getCompensation().getAllowance());

                GovernmentDetails gov = emp.getGovernmentDetails();
                csv.setSss(gov.getSssNumber());
                csv.setPhilHealth(gov.getPhilHealthNumber());
                csv.setTin(gov.getTin());
                csv.setPagIbig(gov.getPagIbigNumber());

                csvList.add(csv);
            }

            beanToCsv.write(csvList);
        } catch (Exception e) {
            System.err.println("Error saving CSV: " + e.getMessage());
        }
    }

    public static List<Employee> loadEmployees(String filePath) {
          List<Employee> employees = new ArrayList<>();
        try (Reader reader = new FileReader(filePath)){
        CsvToBean<EmployeeCSV> csvToBean = new CsvToBeanBuilder<EmployeeCSV>(reader)
                .withType(EmployeeCSV.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        for (EmployeeCSV csv : csvToBean) {
            System.out.println("Loaded CSV Row" + csv.getEmployeeId()+ "-" + csv.getFirstName());
            GovernmentDetails gov = new GovernmentDetails(csv.getSss(), csv.getPhilHealth(), csv.getTin(), csv.getPagIbig());
            CompensationDetails comp = new CompensationDetails(csv.getBasicSalary(), csv.getAllowance());
            Employee emp;
            emp = new Employee(csv.getEmployeeId(), csv.getLastName(), csv.getFirstName(),
                    csv.getAge(), csv.getPosition(), csv.getBasicSalary(), gov, comp);
            employees.add(emp);
        }
    } catch (Exception e) {
        System.err.println("Error loading from CSV: " + e.getMessage());
        e.printStackTrace();
    }
         return employees;
    }
        static void saveEmployees(List<Employee> employees, String filePath) {
    try (Writer writer = new FileWriter(filePath)) {
        StatefulBeanToCsv<EmployeeCSV> beanToCsv = new StatefulBeanToCsvBuilder<EmployeeCSV>(writer)
                .withApplyQuotesToAll(false)
                .build();

        List<EmployeeCSV> csvList = new ArrayList<>();
        for (Employee emp : employees) {
            EmployeeCSV csv = new EmployeeCSV();
            csv.setEmployeeId(emp.getEmployeeId());
            csv.setLastName(emp.getLastName());
            csv.setFirstName(emp.getFirstName());
            csv.setAge(emp.getAge());
            csv.setPosition(emp.getPosition());
            csv.setBasicSalary(emp.getSalary());
            csv.setAllowance(emp.getCompensation().getAllowance());

            GovernmentDetails gov = emp.getGovernmentDetails();
            csv.setSss(gov.getSssNumber());
            csv.setPhilHealth(gov.getPhilHealthNumber());
            csv.setTin(gov.getTin());
            csv.setPagIbig(gov.getPagIbigNumber());

            csvList.add(csv);
        }

        beanToCsv.write(csvList);
    } catch (Exception e) {
        System.err.println("Error saving to CSV: " + e.getMessage());
    }
        }
}

        
    


    
   
