package java.csvtest.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.csvtest.api.entities.CsvFile;
import java.csvtest.api.exceptions.CSVInvalidException;
import java.csvtest.api.exceptions.ObjectAlreadyExistsException;
import java.csvtest.api.exceptions.ObjectDoesNotExistException;
import java.io.IOException;
import java.util.List;

public interface CsvFileService {

    void processCsv(MultipartFile file) throws IOException, CSVInvalidException, ObjectAlreadyExistsException;

    CsvFile getObjectByPrimaryKey(String primary_key) throws ObjectDoesNotExistException;

    void deleteObjectByPrimaryKey(String primary_key) throws ObjectDoesNotExistException;
}
