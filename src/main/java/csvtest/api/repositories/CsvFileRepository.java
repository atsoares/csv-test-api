package csvtest.api.repositories;

import csvtest.api.entities.CsvFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CsvFileRepository extends JpaRepository<CsvFile, Long> {

    Optional<CsvFile> findByPrimaryKey(String primaryKey);
}
