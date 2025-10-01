package com.assessor.filemanagement.CSVCommandLineRunner;

import com.assessor.filemanagement.configuration.CSVClient;
import com.assessor.filemanagement.domain.Person;
import com.assessor.filemanagement.service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.*;

@SpringBootApplication
@Profile("csv")
public class CSVRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CSVRunner.class);

    @Autowired
    private CSVClient csvClient;


    @Autowired
    private CsvService csvService;

    public CSVRunner(CSVClient client, CsvService csvService) {
        this.csvClient = client;
        this.csvService = csvService;
    }


    public static void main(String[] args) {
        SpringApplication.run(CSVRunner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Running .......");
        log.info("Getting Configuration From System ..");
        csvClient.confirmConfig();

        log.info(" Populating Data into Memory...");
        Map<Integer, Person> list;
        list = csvClient.createPersonCach();
        log.info(list.toString());

          /*log.info("Testing Color");
          System.out.println( csvService.getAllByColor("grün"));
         create new Person

        Person person=new Person("Karam","Al-Zu'bi","Jordanien 23", Farbe.ROT,1);
        System.out.println(csvService.addNewPerson(person));

        log.info("Refresh the list");
        list=csvClient.createPersonCach();
        log.info(list.toString());*/
    }
}
