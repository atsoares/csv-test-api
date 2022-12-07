package csvtest.api.service.impl;

import csvtest.api.entities.CsvFile;
import csvtest.api.exceptions.CSVInvalidException;
import csvtest.api.exceptions.ObjectDoesNotExistException;
import csvtest.api.helper.CsvFileHelper;
import csvtest.api.repositories.CsvFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import csvtest.api.service.CsvFileService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CsvFileServiceImpl implements CsvFileService {

    @Autowired
    private final CsvFileRepository repository;

    public CsvFileServiceImpl(CsvFileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void processCsv(MultipartFile file) throws IOException, CSVInvalidException {
        if(CsvFileHelper.hasCSVFormat(file)) {
            List<CsvFile> csvFiles = CsvFileHelper.csvToObject(file.getInputStream());
            repository.saveAll(csvFiles);
        } else {
            throw new CSVInvalidException();
        }

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