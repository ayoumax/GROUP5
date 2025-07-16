package main.motorphgui;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompensationLoader {

    /** Reads compensation.csv and returns a map keyed by employeeId. */
    public static Map<Integer, CompensationRow> load(String csvPath) throws Exception {
        try (Reader reader = new FileReader("data/compensation.csv")) {
            List<CompensationRow> rows =
                    new CsvToBeanBuilder<CompensationRow>(reader)
                         .withType(CompensationRow.class)
                         .withIgnoreLeadingWhiteSpace(true)
                         .build()
                         .parse();

            return rows.stream()
                       .collect(Collectors.toMap(
                               CompensationRow::getEmployeeId,
                               row -> row));
        }
    }

    static Map<Integer, CompensationRow> loadCompensation(String datacompensationcsv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
