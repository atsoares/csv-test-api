package java.csvtest.api.entities;

import java.time.LocalDateTime;

public class CsvFile {
	private String primary_key;
	
    private String name;

    private String description;

    private LocalDateTime updated_timestamp;

    public CsvFile(String primary_key, String name, String description) {
        this.primary_key = primary_key;
        this.name = name;
        this.description = description;
    }

    public String getPrimary_key() {
        return primary_key;
    }

    public void setPrimary_key(String primary_key) {
        this.primary_key = primary_key;
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