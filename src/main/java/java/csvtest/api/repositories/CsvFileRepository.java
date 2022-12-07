package java.csvtest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.csvtest.api.entities.CsvFile;
import java.util.Optional;

public interface CsvFileRepository extends JpaRepository<CsvFile, Long> {

    @Query("SELECT * FROM CsvFile c WHERE c.primary_key = ?1")
    Optional<CsvFile> findByPrimaryKey(String primary_key);

    Boolean existsByPrimaryKey(String primary_key);
}
