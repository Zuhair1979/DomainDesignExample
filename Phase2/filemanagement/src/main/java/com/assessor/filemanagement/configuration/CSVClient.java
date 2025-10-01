package com.assessor.filemanagement.configuration;

import com.assessor.filemanagement.builders.ToPersonValidatorFromCsv;
import com.assessor.filemanagement.domain.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*
*  1- get CSV env variable from YML file
*  2- get lines from CSV file and cache it to personList
*
* */
@Component
@Profile("csv")
public class CSVClient {


    private final ToPersonValidatorFromCsv personBuilder;
    private final CSVConfiguration csvConfiguration;
    // main cach
    private Map<Integer, Person> personList;

    @Autowired
    public CSVClient(ToPersonValidatorFromCsv personBuilder, CSVConfiguration csvConfiguration) {
        this.personBuilder = personBuilder;
        this.csvConfiguration = csvConfiguration;
    }


    // confirm Config / ignore it
    public void confirmConfig() {
        System.out.println("File Path :: " + csvConfiguration.filePath() + ".  Columns No :: " + csvConfiguration.columns()
                + ". Csv Separator :: " + csvConfiguration.separator() + ". Cash Size :: " + csvConfiguration.cachsize());
    }


    // get the list of CSVRecord
    public List<CSVRecord> getCsvRecord() throws IOException {

        List<CSVRecord> personCsvList = new ArrayList<>();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setTrim(true)
                .setIgnoreEmptyLines(true)
                .setDelimiter(csvConfiguration.separator().charAt(0))
                .setNullString("")
                .build();


        try (Reader in = new FileReader(csvConfiguration.filePath())) {
            Iterable<CSVRecord> iterable = csvFormat.parse(in);
            iterable.forEach(personCsvList::add);
        }

        return personCsvList;

    }

    // get the data from CSV file before the Spring boot Context fully created
    // the LRU cach algorith will be used in this type of approach
    // this will be called from the Runner
    public Map<Integer, Person> createPersonCach() throws IOException {
        List<CSVRecord> recordList = getCsvRecord();
        this.personList = new CSVCashList<>(csvConfiguration.cachsize());// cache list LRU
        Integer i = 0;
        for (var record : recordList) {

            this.personList.put(i, personBuilder.getPerson(record,i));
            i++;
        }
       /* if(this.personList.isEmpty())
            throw  new FileNotFoundException(csvConfiguration.filePath());
*/
        return this.personList;

    }

    // get PersonCach
    public Map<Integer, Person> getPersonCach() throws IOException {

        return this.personList;
    }

    public Map<Integer, Person> refreshPersonCach() throws IOException {
    return   createPersonCach();

    }
}
