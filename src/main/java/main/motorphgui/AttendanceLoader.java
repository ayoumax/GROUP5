package main.motorphgui;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class AttendanceLoader {

    public static List<AttendanceRecord> loadAttendanceRecords (String filePath) {
        List<AttendanceRecord> records = new ArrayList<>();
        try (Reader reader = new FileReader("data/attendancerecord.csv")) {

            CsvToBean<AttendanceRecord> csvToBean = new CsvToBeanBuilder<AttendanceRecord>(reader)
                    .withType(AttendanceRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (AttendanceRecord record : csvToBean) {
                try {
                    record.finalizeParsing();  // parse the LocalDate and LocalTime
                    records.add(record);
                } catch (Exception ex) {
                    System.err.println("Skipping malformed row for employee ID: " + record.getEmployeeId());
                }
            }

        } catch (Exception e) {
            System.err.println("Error loading attendance file: " + e.getMessage());
            e.printStackTrace();
        }

        return records;
    }

    public static float calculateMonthlyHoursWorked(int empId, int month, int year, List<AttendanceRecord> records) {
        float totalHours = 0;
        for (AttendanceRecord record : records) {
            if (record.getEmployeeId() == empId &&
                record.getDate().getMonthValue() == month &&
                record.getDate().getYear() == year) {
                totalHours += record.getTotalHoursWorked();
            }
        }
        return totalHours;
    }
}
