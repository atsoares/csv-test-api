package csvtest.api.service;

import csvtest.api.entities.CsvFile;
import csvtest.api.exceptions.CSVInvalidException;
import csvtest.api.exceptions.ObjectAlreadyExistsException;
import csvtest.api.exceptions.ObjectDoesNotExistException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CsvFileService {

    void processCsv(MultipartFile file) throws IOException, CSVInvalidException, ObjectAlreadyExistsException;

    CsvFile getObjectByPrimaryKey(String primary_key) throws ObjectDoesNotExistException;

    void deleteObjectByPrimaryKey(String primary_key) throws ObjectDoesNotExistException;
}
