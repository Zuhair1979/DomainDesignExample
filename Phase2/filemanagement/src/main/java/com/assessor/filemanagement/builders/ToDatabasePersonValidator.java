package com.assessor.filemanagement.builders;

import com.assessor.filemanagement.domain.Person;
import com.assessor.filemanagement.entity.DataBasePerson;
import com.assessor.filemanagement.entity.Farbe;
import com.assessor.filemanagement.interfaces.PersonBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("postgres")
@Component
public class ToDatabasePersonValidator implements PersonBuilder<Person, DataBasePerson> {
    @Override
    public String getFname(Person record) {
        return record.getFirstName();
    }

    @Override
    public String getLname(Person record) {
        return record.getLastName();
    }

    @Override
    public String getAddress(Person record) {
        return record.getAddress();
    }

    @Override
    public Farbe getColor(Person record) {
        return record.getColor() != null ? record.getColor() : Farbe.UNKNOWN;
    }

    @Override
    public DataBasePerson getPerson(Person record, Integer id) {
        return new DataBasePerson(null,getFname(record),getLname(record),getAddress(record),getColor(record));
    }
}
