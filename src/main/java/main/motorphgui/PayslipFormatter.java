/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

/**
 *
 * @author Trapal RYN
 */

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PayslipFormatter {

    private static final DecimalFormat MONEY
            = new DecimalFormat("¤ ###,##0.00", DecimalFormatSymbols.getInstance(new Locale("en", "PH")));
    private static final DecimalFormat HOURS = new DecimalFormat("###,##0.00");

    /**
     * Builds a detailed payslip. Supply whatever objects you already have.
     */
    public static String build(Employee emp,
            LocalDate periodStart,
            LocalDate periodEnd,
            double hourlyRate,
            double basicHours,
            double overtimeHours,
            double tardinessHours,
            double riceSubsidy,
            double phoneAllowance,
            double clothingAllowance,
            double sss, double philHealth, double pagIbig, double tax) {

        double basicPay = hourlyRate * basicHours;
        double overtimePay = hourlyRate * 1.25 * overtimeHours;
        double tardyDeduct = hourlyRate * tardinessHours;   // negative hours expected
        double gross = basicPay + overtimePay - tardyDeduct // note minus minus = plus
                + riceSubsidy + phoneAllowance + clothingAllowance;
        double totalDed = sss + philHealth + pagIbig + tax;
        double net = gross - totalDed;

        StringBuilder sb = new StringBuilder();

        sb.append("======================================================\n");
        sb.append(String.format("%-54s%n", center("MOTORPH PAYROLL STATEMENT", 54)));
        sb.append("======================================================\n");
        sb.append(String.format("  Employee: %-35s%n", emp.getFullName()));
        sb.append(String.format("  ID: %-40s%n", emp.getEmployeeId()));
        sb.append(String.format("  Position: %-32s%n", emp.getPosition()));
        sb.append(String.format("  Period: %s to %s%n",
                periodStart, periodEnd));
        sb.append(String.format("  Hourly Rate: %s%n", MONEY.format(hourlyRate)));
        sb.append(String.format("  Days Late: %d%n", (int) Math.ceil(tardinessHours / 8)));
        sb.append("------------------------------------------------------\n");
        sb.append(String.format("  %-23s %10s %15s%n", "Description", "Hours", "Amount"));
        sb.append("------------------------------------------------------\n");

        sb.append(line("Basic Pay", basicHours, basicPay, true));
        sb.append(line("Overtime (1.25x)", overtimeHours, overtimePay, true));
        sb.append(line("Tardiness", -tardinessHours, -tardyDeduct, false));
        sb.append(line("Rice Subsidy", 0, riceSubsidy, true));
        sb.append(line("Phone Allowance", 0, phoneAllowance, true));
        sb.append(line("Clothing Allowance", 0, clothingAllowance, true));

        sb.append("------------------------------------------------------\n");
        sb.append(String.format("  GROSS PAY%42s%n", MONEY.format(gross)));
        sb.append("------------------------------------------------------\n");
        sb.append(line("SSS Deduction", 0, -sss, false));
        sb.append(line("PhilHealth", 0, -philHealth, false));
        sb.append(line("Pag-IBIG", 0, -pagIbig, false));
        sb.append(line("Withholding Tax", 0, -tax, false));
        sb.append("------------------------------------------------------\n");
        sb.append(String.format("  TOTAL DEDUCTIONS%29s%n", MONEY.format(-totalDed)));
        sb.append("======================================================\n");
        sb.append(String.format("  NET PAY:%37s%n", MONEY.format(net)));
        sb.append("======================================================");

        return sb.toString();
    }

    /* Helper to format each row */
    private static String line(String desc, double hrs, double amt, boolean positive) {
        String hoursStr = (hrs == 0) ? "" : (hrs < 0 ? "- " : "") + HOURS.format(Math.abs(hrs));
        String amtStr = MONEY.format(positive ? amt : -amt);
        if (!positive) {
            amtStr = "-" + amtStr;                // add trailing minus for negatives
        }
        return String.format("  %-23s %10s %15s%n", desc, hoursStr, amtStr);
    }

    private static String center(String text, int width) {
        int pad = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, pad)) + text;
    }
}
//How to use it in your GUI code

//String payslip = PayslipFormatter.build(
//        employee,                         // Employee object
//        LocalDate.of(2024,6,3),
//        LocalDate.of(2024,6,30),
//        535.71,       // hourly rate
//        157.80,       // basic hours
//        32.65,        // OT hours
//        22.57,        // tardy hours (positive number)
//        1500.00, 2000.00, 1000.00,        // allowances
//        1125.00, 900.00, 100.00, 10680.09 // SSS, PhilHealth, Pag‑IBIG, Tax
//);

// show it in a JTextArea with monospaced font
//txtPayslip.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
//txtPayslip.setText(payslip);
