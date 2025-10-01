package com.assessor.filemanagement.domainport;

import com.assessor.filemanagement.domain.Person;

import java.io.IOException;
import java.util.List;

public interface PersonRepositoryDM {
    List<Person> getAllPersons() throws IOException;
    List<Person> getAllByColor(String color) throws IOException;
    Person getById(int id) throws IOException;
    boolean addNewPerson(Person person) throws IOException;
}
