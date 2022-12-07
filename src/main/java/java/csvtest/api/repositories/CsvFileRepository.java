package java.csvtest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.csvtest.api.entities.CsvFile;

public interface CsvFileRepository extends JpaRepository<CsvFile, Long> {

    
}
