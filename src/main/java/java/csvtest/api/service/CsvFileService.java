package java.csvtest.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.csvtest.api.entities.CsvFile;
import java.io.IOException;
import java.util.List;

public interface CsvFileService {

    List<CsvFile> processCsv(MultipartFile file) throws IOException;

}
