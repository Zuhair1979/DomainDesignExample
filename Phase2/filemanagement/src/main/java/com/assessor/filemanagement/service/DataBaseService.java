package com.assessor.filemanagement.service;

import com.assessor.filemanagement.domain.Person;
import com.assessor.filemanagement.domainport.PersonRepositoryDM;
import com.assessor.filemanagement.entity.DataBasePerson;
import com.assessor.filemanagement.entity.Farbe;
import com.assessor.filemanagement.exceptions.ColorNotFoundException;
import com.assessor.filemanagement.exceptions.PersonNotFoundException;
import com.assessor.filemanagement.mappers.DataBaseToPersonMapper;
import com.assessor.filemanagement.mappers.PersonToDatabasePersonMapper;
import com.assessor.filemanagement.repository.PersonRepositoty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Profile("postgres")
public class DataBaseService implements PersonRepositoryDM {

    private final PersonRepositoty repository;
    private final PersonToDatabasePersonMapper personToDatabasePersonMapper;
    private final DataBaseToPersonMapper dataBaseToPersonMapper;

    public DataBaseService(PersonRepositoty repository, PersonToDatabasePersonMapper personToDatabasePersonMapper, DataBaseToPersonMapper dataBaseToPersonMapper) {
        this.repository = repository;
        this.personToDatabasePersonMapper = personToDatabasePersonMapper;
        this.dataBaseToPersonMapper = dataBaseToPersonMapper;
    }

    @Override
    public List<Person> getAllPersons() throws IOException {

        return this.repository.findAll().stream().map(this.dataBaseToPersonMapper::toPerson).toList();
    }

    @Override
    public List<Person> getAllByColor(String color) throws IOException {
        Farbe enumColor = Farbe.fromStringIgnoreCase(color);
       // return this.repository.findByColor(enumColor).stream().map(this.dataBaseToPersonMapper::toPerson).toList();
        List<DataBasePerson> dbPersons = repository.findByColor(enumColor);

        if (dbPersons.isEmpty()) {
            throw new ColorNotFoundException(color); // known enum, but no results
        }

        return dbPersons.stream()
                .map(this.dataBaseToPersonMapper::toPerson)
                .toList();
    }

    @Override
    public Person getById(int id) throws IOException {
          return repository.findById(id)
                .map(dataBaseToPersonMapper::toPerson)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public boolean addNewPerson(Person person) throws IOException {
        this.repository.save(personToDatabasePersonMapper.toDbPerson(person));
        return true;
    }
}
