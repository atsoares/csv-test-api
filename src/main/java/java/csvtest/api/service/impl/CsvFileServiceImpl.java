package java.csvtest.api.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.csvtest.api.entities.CsvFile;
import java.csvtest.api.repositories.CsvFileRepository;
import java.csvtest.api.service.CsvFileService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvFileServiceImpl implements CsvFileService {

    private final CsvFileRepository repository;

    public CsvFileServiceImpl(CsvFileRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CsvFile> processCsv(MultipartFile file) throws IOException {
        List<CsvFile> list = new ArrayList<>();

        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(file.getInputStream(), "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();

        for (CSVRecord csvRecord : csvRecords) {
            String primary_key = csvRecord.get("primary_key");
            String name = csvRecord.get("name");
            String description = csvRecord.get("description");

            CsvFile csv = repository.save(new CsvFile(primary_key, name, description));
            list.add(csv);
        }

        return list;
    }

}
;