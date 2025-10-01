package com.assessor.filemanagement.builders;

import com.assessor.filemanagement.configuration.CSVConfiguration;
import com.assessor.filemanagement.entity.Farbe;
import com.assessor.filemanagement.domain.Person;
import com.assessor.filemanagement.interfaces.PersonBuilder;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
/*
build a Person object from CSVRecord , record retireved from CSV File
it handle the line short length  in defending way
* */
@Component
@Profile("csv")
public class ToPersonValidatorFromCsv implements PersonBuilder<CSVRecord, Person> {
    @Autowired
    CSVConfiguration csvConfiguration;

    @Override
    public String getFname(CSVRecord record) {
        String fName = "";
        if (record.size() > 0)
            fName = record.get(0);
        return fName;
    }

    @Override
    public String getLname(CSVRecord record) {
        String lName = "";
        if (record.size() > 1)
            lName = record.get(1);
        return lName;

    }

    @Override
    public String getAddress(CSVRecord record) {

        String address = "";
        if (record.size() > 2)
            address = record.get(2);
        return address;

    }

    @Override
    public Farbe getColor(CSVRecord record) {
        String color = "0";

        if (record.size() > 3)
            color = record.get(3);

        int colorId = Integer.parseInt(color);
        switch (colorId) {
            case 1 -> {
                return Farbe.BLAU;
            }
            case 2 -> {
                return Farbe.GRÜN;
            }
            case 3 -> {
                return Farbe.VIOLETT;
            }
            case 4 -> {
                return Farbe.ROT;
            }
            case 5 -> {
                return Farbe.GELB;
            }
            case 6 -> {
                return Farbe.TÜRKIS;
            }
            case 7 -> {
                return Farbe.WEIß;
            }
            default -> {
                return Farbe.UNKNOWN;
            }

        }


    }

    @Override
    public Person getPerson(CSVRecord record,Integer id) {
        return new Person(getFname(record), getLname(record), getAddress(record), getColor(record),id);
    }
}
