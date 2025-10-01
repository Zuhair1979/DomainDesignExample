package com.assessor.filemanagement.service;

import com.assessor.filemanagement.CSVCommandLineRunner.CSVRunner;
import com.assessor.filemanagement.configuration.CSVClient;
import com.assessor.filemanagement.configuration.CSVConfiguration;
import com.assessor.filemanagement.domain.Person;
import com.assessor.filemanagement.domainport.PersonRepositoryDM;
import com.assessor.filemanagement.exceptions.ColorNotFoundException;
import com.assessor.filemanagement.exceptions.FileEmptyException;
import com.assessor.filemanagement.exceptions.PersonNotFoundException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Profile("csv")
public class CsvService implements PersonRepositoryDM {

    private static final Logger log = LoggerFactory.getLogger(CSVRunner.class);

    private final CSVClient csvClient;
    private final CSVConfiguration csvConfiguration;

    public CsvService(CSVConfiguration csvConfiguration, CSVClient csvClient) {
        this.csvConfiguration = csvConfiguration;
        this.csvClient = csvClient;
    }

    public List<Person> getAllPersons() throws IOException {
        Map<Integer, Person> allData = csvClient.getPersonCach();
        if(allData==null)
            throw new FileEmptyException("The list is Null");

        List<Person> returnedList = allData.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        if ( returnedList.size()==0)
            throw new FileEmptyException("CSV data is empty or invalid");

        return returnedList;

    }


    public List<Person> getAllByColor(String color) throws IOException {
        Map<Integer, Person> allData = csvClient.getPersonCach();
        if(allData==null)
            throw new NullPointerException();

        var matchingKeys = allData.entrySet().stream()
                .filter(p -> p.getValue().findByColor(color))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // touch the cash
        if (matchingKeys.size() != 0) {
            matchingKeys.forEach(allData::get);
            log.info(matchingKeys.size() + " list size");
        }
        if (matchingKeys.isEmpty())
            throw new ColorNotFoundException(color);

        return matchingKeys.stream().map(allData::get).collect(Collectors.toList());

        /* this code to get the map key if needed, in case of color it does not needed, we need to iterate
        return   allData.entrySet().stream().filter(p -> p.getValue().findByColor(color)).collect(
                  Collectors.toMap(
                          Map.Entry::getKey,
                          Map.Entry::getValue
                  ));
*/
    }

    public Person getById(int id) throws IOException {

        Map<Integer, Person> allData = csvClient.getPersonCach();
        if(allData==null)
            throw new NullPointerException();

        System.out.println(allData);
        Person returnedPerson = allData.get(id);

        if (returnedPerson == null)
            throw new PersonNotFoundException(id);

        return allData.get(id);  // we  cach and reorder the element in cach list
    }

    public boolean addNewPerson(Person person) throws IOException {

        boolean worteDone = false;
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setTrim(true)
                .setIgnoreEmptyLines(true)
                .setDelimiter(csvConfiguration.separator().charAt(0))
                .setNullString("")
                .build();

        try (FileWriter out = new FileWriter(csvConfiguration.filePath(), true)) {
            CSVPrinter printer = new CSVPrinter(out, csvFormat);
            out.write(System.lineSeparator());
            printer.printRecord(person.getFirstName(),
                    person.getLastName(),
                    person.getAddress(),
                    person.getColor().id());
            worteDone = true;
            return worteDone;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (worteDone)
                csvClient.refreshPersonCach();
        }

        // return worteDone;
    }


}
