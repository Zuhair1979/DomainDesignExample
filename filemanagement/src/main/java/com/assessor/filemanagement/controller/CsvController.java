package com.assessor.filemanagement.controller;

import com.assessor.filemanagement.entity.Person;
import com.assessor.filemanagement.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class CsvController {

    @Autowired
    CsvService csvService;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getPersons() throws IOException {
        List<Person> list = csvService.getAllPersons();
       /* if (list == null ) {
            return ResponseEntity.noContent().build(); // 204
        }*/
        return ResponseEntity.ok(list);
    }
    @GetMapping("/persons/color/{color}")
    public ResponseEntity<List<Person>> getPersonByColor(@PathVariable String color) throws IOException {
        return ResponseEntity.ok(csvService.getAllByColor(color));
    }
    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) throws IOException {
        return ResponseEntity.ok(csvService.getById(id));
    }
    @PostMapping("persons/new")
    public boolean addNewPerson(@RequestBody Person person) throws IOException {
        return csvService.addNewPerson(person);
    }
}
