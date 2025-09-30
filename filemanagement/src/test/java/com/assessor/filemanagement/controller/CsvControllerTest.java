package com.assessor.filemanagement.controller;

import com.assessor.filemanagement.entity.Farbe;
import com.assessor.filemanagement.entity.Person;
import com.assessor.filemanagement.exceptions.FileEmptyException;
import com.assessor.filemanagement.service.CsvService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;


import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CsvController.class)
class CsvControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CsvService csvService;


    @Test
    void getPersons() throws Exception {
        Person p1 = new Person("T1", "T1", "A1", Farbe.BLAU, 0);
        Person p2 = new Person("T2", "T2", "A2", Farbe.ROT, 1);
        ArrayList<Person> expected = new ArrayList<>();
        expected.add(p1);
        expected.add(p2);
        when(csvService.getAllPersons()).thenReturn(expected);

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("T1"))
                .andExpect(jsonPath("$[1].firstName").value("T2"));

        verify(csvService).getAllPersons();

    }

    @Test
    void getPersons_null_map() throws Exception {
        when(csvService.getAllPersons()).thenThrow(new FileEmptyException("List is Null"));

        mockMvc.perform(get("/persons"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getPersons_empty_map() throws Exception {
        when(csvService.getAllPersons()).thenThrow(new FileEmptyException("File not Found"));

        mockMvc.perform(get("/persons"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getPersonByColor() {
    }

    @Test
    void addNewPerson() throws Exception {


        Person p1 = new Person("T1", "T1", "A1", Farbe.BLAU, 0);

        when(csvService.addNewPerson(any(Person.class))).thenReturn(true);


        mockMvc.perform(post("/persons/new")
                        .contentType(MediaType.APPLICATION_JSON)   // set request type
                        .content("{\"firstName\":\"T1\",\"lastName\":\"T1\",\"address\":\"A1\",\"color\":\"BLAU\",\"id\":0}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));


        verify(csvService).addNewPerson(any(Person.class));
    }
}