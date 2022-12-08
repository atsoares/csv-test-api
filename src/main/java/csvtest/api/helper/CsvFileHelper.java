package csvtest.api.helper;

import csvtest.api.constants.Constants;
import csvtest.api.entities.CsvFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvFileHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<CsvFile> csvToObject(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.RFC4180.builder().setHeader().setSkipHeaderRecord(true).build());) {

            List<CsvFile> csvFiles = new ArrayList<CsvFile>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                CsvFile csvFile = new CsvFile(
                        csvRecord.get("PRIMARY_KEY"),
                        csvRecord.get("NAME"),
                        csvRecord.get("DESCRIPTION"),
                        getConvertedTime(csvRecord.get("UPDATED_TIMESTAMP")));

                csvFiles.add(csvFile);
            }

            return csvFiles;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static LocalDateTime getConvertedTime(String date){
        if(!date.isEmpty()){
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(Constants.EN_DATE_TIME_FORMAT));
        }
        return null;
    }

}
