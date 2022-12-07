package csvtest.api.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CsvFile {
    @Id
	private String primaryKey;
	
    private String name;

    private String description;

    private LocalDateTime updated_timestamp;

    public CsvFile(){

    };
    public CsvFile(String primaryKey, String name, String description, LocalDateTime updated_timestamp) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.description = description;
        this.updated_timestamp = updated_timestamp;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUpdated_timestamp() {
        return updated_timestamp;
    }

    public void setUpdated_timestamp(LocalDateTime updated_timestamp) {
        this.updated_timestamp = updated_timestamp;
    }
}