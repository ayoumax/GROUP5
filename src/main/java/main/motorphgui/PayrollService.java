package main.motorphgui;

import java.time.Duration;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

public class PayrollService {

    /** Generates the detailed payslip text (monospaced recommended). */
    public static String generatePayslip(Employee emp,
                                         YearMonth ym,
                                         List<AttendanceRecord> allRecs) {

        try {
            /* ── 1.  Attendance aggregation ──────────────────────────────── */
            final int STANDARD_DAY_HRS = 8;
            final LocalTime SCHEDULED_IN = LocalTime.of(9, 0);

            double basicHrs = 0, otHrs = 0, tardyHrs = 0, daysLate = 0;

            for (AttendanceRecord rec : allRecs) {
                if (rec.getEmployeeId() != emp.getEmployeeId()) continue;
                if (!YearMonth.from(rec.getDate()).equals(ym)) continue;

                double worked = rec.getTotalHoursWorked();
                basicHrs += Math.min(worked, STANDARD_DAY_HRS);
                otHrs    += Math.max(0, worked - STANDARD_DAY_HRS);

                if (rec.getTimeIn() != null && rec.getTimeIn().isAfter(SCHEDULED_IN)) {
                    tardyHrs += Duration.between(SCHEDULED_IN, rec.getTimeIn()).toMinutes() / 60.0;
                    daysLate++;
                }
            }

            /* ── 2.  Pay factors & allowances ────────────────────────────── */
            double rate          = emp.getHourlyRate();
            double otMultiplier  = emp.getPosition().toLowerCase().contains("it") ? 1.5 : 1.25;

            double riceSubsidy   = emp.getRiceSubsidy();      // add getters in Employee
            double phoneAllow    = emp.getPhoneAllowance();
            double clothingAllow = emp.getClothingAllowance();

            /* ── 3.  Gross pay calculation ───────────────────────────────── */
            double basicPay    = rate * basicHrs;
            double overtimePay = rate * otMultiplier * otHrs;
            double tardyDeduct = rate * tardyHrs;             // will display as negative

            double grossPay = basicPay + overtimePay - tardyDeduct
                              + riceSubsidy + phoneAllow + clothingAllow;

            /* ── 4.  Statutory deductions via GovernmentDetails ─────────── */
            double pagibig     = GovernmentDetails.calculatePagibig(grossPay);
            double philHealth  = GovernmentDetails.calculatePhilhealth(grossPay);
            double sss         = GovernmentDetails.calculateSSS(grossPay);

            double govTotal    = pagibig + philHealth + sss;
            double withholding = GovernmentDetails.calculateWithholdingTax(grossPay - govTotal);

            double totalDed    = govTotal + withholding;
            double netPay      = grossPay - totalDed;

            /* ── 5.  Build formatted payslip ─────────────────────────────── */
            return PayslipFormatter.build(
                    emp,
                    ym.atDay(1),
                    ym.atEndOfMonth(),
                    rate,
                    basicHrs,
                    otHrs,
                    tardyHrs,
                    riceSubsidy,
                    phoneAllow,
                    clothingAllow,
                    sss,
                    philHealth,
                    pagibig,
                    withholding
            ).replace("Days Late:", String.format("Days Late: %d", (int) daysLate));

        } catch (Exception ex) {
            return "⚠ Unable to generate payslip for " + emp.getEmployeeId()
                   + ": " + ex.getMessage();
        }
    }
}
