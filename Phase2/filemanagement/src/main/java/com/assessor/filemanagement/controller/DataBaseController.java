package com.assessor.filemanagement.controller;


import com.assessor.filemanagement.domain.Person;
import com.assessor.filemanagement.exceptions.PersonNotFoundException;
import com.assessor.filemanagement.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/db/persons")
@Profile("postgres")
public class DataBaseController {

    @Autowired
    DataBaseService service;


    @GetMapping
    public ResponseEntity<List<Person>> getPersons() throws IOException {
        List<Person> list = service.getAllPersons();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Person>> getPersonByColor(@PathVariable String color) throws IOException {
        return ResponseEntity.ok(service.getAllByColor(color));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) throws IOException {
        Person person = service.getById(id);

        return ResponseEntity.ok(person);
    }

    @PostMapping("/new")
    public boolean addNewPerson(@RequestBody Person person) throws IOException {
        return service.addNewPerson(person);
    }
}
