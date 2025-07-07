package main.motorphgui;

import com.opencsv.bean.CsvBindByName;

public class CompensationRow {

    @CsvBindByName(column = "Employee #")
    private int employeeId;

    @CsvBindByName(column = "Rice Subsidy")
    private String riceRaw;                 // "1,500"

    @CsvBindByName(column = "Phone Allowance")
    private String phoneRaw;                // "2,000"

    @CsvBindByName(column = "Clothing Allowance")
    private String clothingRaw;             // "1,000"

    @CsvBindByName(column = "Hourly Rate")
    private String rateRaw;                 // "535.71"

    /* helpers to strip commas and cast */
    private static double toDouble(String s) {
        return Double.parseDouble(s.replace(",", "").trim());
    }

    // ── getters you’ll use later ───────────────────────────────
    public int     getEmployeeId()      { return employeeId; }
    public double  getRiceSubsidy()     { return toDouble(riceRaw); }
    public double  getPhoneAllowance()  { return toDouble(phoneRaw); }
    public double  getClothingAllowance(){ return toDouble(clothingRaw); }
    public double  getHourlyRate()      { return toDouble(rateRaw); }
}
