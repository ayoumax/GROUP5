/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

/**
 *
 * @author Macky
 */
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class PayrollCalculator {

    private static final DecimalFormat df = new DecimalFormat("₱#,##0.00");

    public static String generatePayslip(int employeeId, String month, String week) {
    try {
        List<String[]> employees = CSVHandler.readCSV("data/employee.csv");

        String[] empData = null;
        for (String[] row : employees) {
            if (String.valueOf(employeeId).equals(row[0].trim())) {
                empData = row;
                break;
            }
        }

        if (empData == null) return "Employee not found.";

        // Employee details
        String fullName = empData[2].trim() + " " + empData[1].trim();
        String position = empData[7].trim();
        String supervisor = empData[8].trim().isEmpty() ? "N/A" : empData[8].trim();
        double monthlySalary = Double.parseDouble(empData[9].trim());
        double rice = Double.parseDouble(empData[10].trim()) / 4;
        double phone = Double.parseDouble(empData[11].trim()) / 4;
        double clothing = Double.parseDouble(empData[12].trim()) / 4;
        double hourlyRate = Double.parseDouble(empData[13].trim());

        String sss = empData[3].trim();
        String phil = empData[4].trim();
        String tin = empData[5].trim();
        String pagibig = empData[6].trim();

        String targetWeek = week.replace("Week-", "").trim();
        double weeklyHours = 0, overtimeHrs = 0, overtimePay = 0;
        

        System.out.println("Debugging - Reading attendance for EmpID=" + employeeId + " Week=" + targetWeek);

        try (BufferedReader br = new BufferedReader(new FileReader("data/attendance.csv"))) {
            String line;
            boolean headerSkipped = false;

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // skip header
                }

                String[] row = line.split(",", -1);
                if (row.length < 13) {
                    continue;
                }

                if (row[0].trim().equals(String.valueOf(employeeId)) && row[3].trim().equals(targetWeek)) {
                    try {
                        if (!row[8].isEmpty()) {
                            weeklyHours += Double.parseDouble(row[8].trim());   // Wkly Hrs Worked
                        }
                        //  Sum all OT hours for the week
                            if (!row[9].trim().isEmpty()) {
                            try {
                                double parsedOT = Double.parseDouble(row[9].trim());
                                overtimeHrs += parsedOT;
                            } catch (NumberFormatException e) {
                                System.err.println("Invalid OT for Employee" + employeeId + ": " + row[9]);
                            }
                        }

                        //  OT Pay
                        if (overtimePay == 0 && !row[12].isEmpty()) {
                            double parsedPay = Double.parseDouble(row[12].trim());
                            if (parsedPay > 0) {
                                overtimePay = parsedPay;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing attendance: " + Arrays.toString(row));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading attendance.csv: " + e.getMessage());
        }

        double weeklyPay = weeklyHours * hourlyRate;
        double sssDeduct = getSSS(monthlySalary) / 4;
        double philDeduct = getPhilHealth(monthlySalary) / 4;
        double pagibigDeduct = getPagIbig(monthlySalary) / 4;
        double govTotal = sssDeduct + philDeduct + pagibigDeduct;

        double grossTaxable = weeklyPay - govTotal;
        double tax = getWithholdingTax(monthlySalary) / 4;

        double grossDeductions = govTotal + tax;
        double netPay = weeklyPay - grossDeductions + rice + phone + clothing + overtimePay;
        

        StringBuilder sb = new StringBuilder();
        
        sb.append("================================= MOTORPH PAYSLIP ==================================\n");
        
        sb.append(String.format("Employee Name:   %-20s Position:    %-20s\n", fullName, position));
        sb.append(String.format("Employee ID:     %-20s Supervisor:  %-20s\n", employeeId, supervisor));
        sb.append(String.format("Pay Period:      %-20s SSS #:       %-20s\n", targetWeek, sss));
        sb.append(String.format("Hourly Rate:     %-20s Pag-IBIG #:   %-20s\n", df.format(hourlyRate), pagibig));
        sb.append(String.format("Base Salary:     %-20s PhilHealth #: %-20s\n", df.format(monthlySalary), phil));

        sb.append("====================================================================================\n");
        sb.append(String.format("%-30s %-10s %-15s\n", "Description", "Hours", "Amount"));
        sb.append("====================================================================================\n");

        sb.append(String.format("%-30s %-10s %-15s\n", "Weekly Hours Worked:", String.format("%.2f", weeklyHours), df.format(weeklyPay)));
        sb.append(String.format("%-30s %-10s %-15s\n", "Overtime Pay:", "", df.format(overtimePay)));

        sb.append("------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-30s %-10s %-15s\n", "Rice Subsidy:", "", df.format(rice)));
        sb.append(String.format("%-30s %-10s %-15s\n", "Phone Allowance:", "", df.format(phone)));
        sb.append(String.format("%-30s %-10s %-15s\n", "Clothing Allowance:", "", df.format(clothing)));

        sb.append("------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-30s %-10s %-15s\n", "SSS Deduction:", "", df.format(sssDeduct)));
        sb.append(String.format("%-30s %-10s %-15s\n", "PhilHealth:", "", df.format(philDeduct)));
        sb.append(String.format("%-30s %-10s %-15s\n", "Pag-IBIG:", "", df.format(pagibigDeduct)));

        sb.append("------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-30s %-10s %-15s\n", "Total Gov't Deductions:", "", df.format(govTotal)));
        sb.append(String.format("%-30s %-10s %-15s\n", "Weekly Gross Taxable Pay:", "", df.format(grossTaxable)));
        sb.append(String.format("%-30s %-10s %-15s\n", "Withholding Tax:", "", df.format(tax)));

        sb.append("====================================================================================\n");
        sb.append(String.format("%-30s %-10s %-15s\n", "Total Deductions:", "", df.format(-grossDeductions)));
        sb.append(String.format("%-30s %-10s %-15s\n", "Net Pay:", "", df.format(netPay)));
        sb.append("====================================================================================\n");

        return sb.toString();
    } catch (Exception e) {
        return "Error computing payslip: " + e.getMessage();
    }
}

    // Deduction Methods
    private static double getSSS(double salary) {
        double[] limits = {
            3249, 3749, 4249, 4749, 5249, 5749, 6249, 6749, 7249, 7749,
            8249, 8749, 9249, 9749, 10249, 10749, 11249, 11749, 12249,
            12749, 13249, 13749, 14249, 14749, 15249, 15749, 16249, 16749,
            17249, 17749, 18249, 18749, 19249, 19749, 20249, 20749, 21249,
            21749, 22249, 22749, 23249, 23749, 24249, 24749
        };
        double[] contributions = {
            135, 157.5, 180, 202.5, 225, 247.5, 270, 292.5, 315, 337.5,
            360, 382.5, 405, 427.5, 450, 472.5, 495, 517.5, 540, 562.5,
            585, 607.5, 630, 652.5, 675, 697.5, 720, 742.5, 765, 787.5,
            810, 832.5, 855, 877.5, 900, 922.5, 945, 967.5, 990, 1012.5,
            1035, 1057.5, 1080, 1102.5, 1125
        };
        for (int i = 0; i < limits.length; i++) {
            if (salary <= limits[i]) {
                return contributions[i];
            }
        }
        return 1125;
    }

    private static double getPhilHealth(double salary) {
        return Math.min(salary * 0.03, 1800) / 2;
    }

    private static double getPagIbig(double salary) {
        return Math.min(100, salary * 0.02);
    }

    private static double getWithholdingTax(double salary) {
        if (salary <= 20832) return 0;
        else if (salary <= 33332) return (salary - 20833) * 0.20;
        else if (salary <= 66666) return 2500 + (salary - 33333) * 0.25;
        else if (salary <= 166666) return 10833 + (salary - 66667) * 0.30;
        else if (salary <= 666666) return 40833 + (salary - 166667) * 0.32;
        else return 200833.33 + (salary - 666667) * 0.35;
    }

    public static List<String> getAvailableMonthsForEmployee(int employeeId) {
        return Arrays.asList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
    }

    public static List<String> getWeeksForEmployeeMonth(int employeeId, String selectedMonth) {
        if (selectedMonth.equals("June")) {
           return Arrays.asList("Week-6/3/2024", "Week-6/10/2024", "Week-6/17/2024", "Week-6/24/2024");
        } else if (selectedMonth.equals("July")) {
           return Arrays.asList("Week-7/1/2024", "Week-7/8/2024", "Week-7/15/2024", "Week-7/22/2024", "Week-7/29/2024");
        } else if (selectedMonth.equals("August")) {    
           return Arrays.asList("Week-8/5/2024", "Week-8/12/2024", "Week-8/19/2024", "Week-8/26/2024");
        } else if (selectedMonth.equals("September")) {    
           return Arrays.asList("Week-9/2/2024", "Week-9/9/2024", "Week-9/16/2024", "Week-9/23/2024", "Week-9/30/2024");
        } else if (selectedMonth.equals("October")) {    
           return Arrays.asList("Week-10/7/2024", "Week-10/14/2024", "Week-10/21/2024", "Week-10/28/2024");
        } else if (selectedMonth.equals("November")) {    
           return Arrays.asList("Week-11/4/2024", "Week-11/11/2024", "Week-11/18/2024", "Week-11/25/2024");
        } else if (selectedMonth.equals("December")) {    
           return Arrays.asList("Week-12/2/2024", "Week-12/9/2024", "Week-12/16/2024", "Week-12/23/2024", "Week-12/30/2024");
        }
        return new ArrayList<>();
    }
}
