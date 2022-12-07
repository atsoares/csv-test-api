package csvtest.api.controllers;

import csvtest.api.entities.CsvFile;
import csvtest.api.exceptions.CSVInvalidException;
import csvtest.api.exceptions.ObjectDoesNotExistException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import csvtest.api.service.CsvFileService;
import java.io.IOException;

import static csvtest.api.constants.Constants.*;

@RestController
@RequestMapping("/csv")
@Api(value = "CsvReader Api")
@CrossOrigin(origins = "*")
public class CsvFileController {

    @Autowired
    private CsvFileService csvFileService;

    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = STATUS_201_CREATED),
                    @ApiResponse(code = 400, message = STATUS_400_BAD_REQUEST),
            })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Process Csv File")
    public ResponseEntity<Void> processReading(@Valid @RequestParam MultipartFile file) throws IOException, CSVInvalidException {
        csvFileService.processCsv(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = STATUS_200_GET_OK),
                    @ApiResponse(code = 404, message = STATUS_404_NOT_FOUND)
            })
    @GetMapping("/{primary_key}")
    @ApiOperation(value = "Get object by primaryKey")
    public ResponseEntity<CsvFile> getObjectByPrimaryKey(@PathVariable String primary_key) throws ObjectDoesNotExistException {
        return new ResponseEntity<>(csvFileService.getObjectByPrimaryKey(primary_key), HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = STATUS_200_GET_OK),
                    @ApiResponse(code = 404, message = STATUS_404_NOT_FOUND)
            })
    @DeleteMapping("/{primary_key}")
    @ApiOperation(value = "Delete object by primaryKey")
    public ResponseEntity<Void> deleteObjectByPrimaryKey(@PathVariable String primary_key) throws ObjectDoesNotExistException {
        csvFileService.deleteObjectByPrimaryKey(primary_key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}