package com.assessor.filemanagement.mappers;

import com.assessor.filemanagement.domain.Person;
import com.assessor.filemanagement.entity.DataBasePerson;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("postgres")
public class DataBaseToPersonMapper {
    public Person toPerson(DataBasePerson person){
        return new Person(
                person.getFirstName(),
                person.getLastName(),
                person.getAddress(),
                person.getColor(),
                person.getId()
        );
    }
}
