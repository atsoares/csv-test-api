package java.csvtest.api.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.csvtest.api.entities.CsvFile;
import java.csvtest.api.exceptions.CSVInvalidException;
import java.csvtest.api.exceptions.ObjectAlreadyExistsException;
import java.csvtest.api.exceptions.ObjectDoesNotExistException;
import java.csvtest.api.repositories.CsvFileRepository;
import java.csvtest.api.service.CsvFileService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvFileServiceImpl implements CsvFileService {

    @Autowired
    private final CsvFileRepository repository;

    public CsvFileServiceImpl(CsvFileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void processCsv(MultipartFile file) throws IOException, CSVInvalidException, ObjectAlreadyExistsException {
        List<CsvFile> list = new ArrayList<>();

        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(file.getInputStream(), "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();

        for (CSVRecord csvRecord : csvRecords) {
            String primary_key = csvRecord.get("primary_key");
            String name = csvRecord.get("name");
            String description = csvRecord.get("description");

            if(primary_key.isEmpty() || primary_key == null)
                throw new CSVInvalidException();

            CsvFile csv = createCsvFile(new CsvFile(primary_key, name, description));
            list.add(csv);
        }
    }

    public CsvFile createCsvFile(CsvFile csvFile) throws ObjectAlreadyExistsException {

        if(repository.existsByPrimaryKey(csvFile.getPrimary_key())){
            throw new ObjectAlreadyExistsException();
        }

        return repository.save(csvFile);
    }

    @Override
    public CsvFile getObjectByPrimaryKey(String primary_key) throws ObjectDoesNotExistException {
        return repository.findByPrimaryKey(primary_key).orElseThrow(ObjectDoesNotExistException::new);
    }

    @Override
    public void deleteObjectByPrimaryKey(String primary_key) throws ObjectDoesNotExistException {
        Optional<CsvFile> csv = Optional.ofNullable(repository.findByPrimaryKey(primary_key).orElseThrow(ObjectDoesNotExistException::new));
        csv.ifPresent(repository::delete);
    }

}
;