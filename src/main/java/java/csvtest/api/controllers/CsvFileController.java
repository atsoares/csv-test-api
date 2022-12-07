package java.csvtest.api.controllers;

import static java.csvtest.api.constants.Constants.STATUS_201_CREATED;
import static java.csvtest.api.constants.Constants.STATUS_400_BAD_REQUEST;
import static org.springframework.http.ResponseEntity.ok;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.csvtest.api.entities.CsvFile;
import java.csvtest.api.service.CsvFileService;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/csv")
@Api(value = "CsvReader Api")
@CrossOrigin(origins = "*")
public class CsvFileController {

    @Autowired
    private CsvFileService service;

    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = STATUS_201_CREATED),
                    @ApiResponse(code = 400, message = STATUS_400_BAD_REQUEST)
            })
    @PostMapping
    @ApiOperation(value = "Process Csv File")
    public ResponseEntity<List<CsvFile>> processReading(@RequestParam MultipartFile file) throws IOException {
        return ok(service.processCsv(file));
    }
}