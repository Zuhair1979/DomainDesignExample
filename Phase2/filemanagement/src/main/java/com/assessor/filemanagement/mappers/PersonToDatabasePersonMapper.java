package com.assessor.filemanagement.mappers;

import com.assessor.filemanagement.builders.ToDatabasePersonValidator;
import com.assessor.filemanagement.domain.Person;
import com.assessor.filemanagement.entity.DataBasePerson;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("postgres")
public class PersonToDatabasePersonMapper {

    private final ToDatabasePersonValidator toDatabasePersonValidator;

    public PersonToDatabasePersonMapper(ToDatabasePersonValidator toDatabasePersonValidator) {
        this.toDatabasePersonValidator = toDatabasePersonValidator;
    }

    public DataBasePerson toDbPerson(Person person){
        return this.toDatabasePersonValidator.getPerson(person,null);
    }
}
