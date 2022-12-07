package java.csvtest.api.controllers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.csvtest.api.entities.CsvFile;
import java.csvtest.api.repositories.CsvFileRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/csv")
public class CsvFileController {

    @Autowired
    private CsvFileRepository repository;

    @PostMapping
    public ResponseEntity<Void> processReading(@RequestParam MultipartFile file) throws IOException {

        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(file.getInputStream(), "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();

        for (CSVRecord csvRecord : csvRecords) {
            String primary_key = csvRecord.get("primary_key");
            String name = csvRecord.get("name");
            String description = csvRecord.get("description");

            CsvFile csv = repository.save(new CsvFile(primary_key, name, description));
        }

        return null;
    }
}