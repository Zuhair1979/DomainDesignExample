package com.assessor.filemanagement.service;

import com.assessor.filemanagement.configuration.CSVClient;
import com.assessor.filemanagement.configuration.CSVConfiguration;
import com.assessor.filemanagement.entity.Farbe;
import com.assessor.filemanagement.entity.Person;
import com.assessor.filemanagement.exceptions.ColorNotFoundException;
import com.assessor.filemanagement.exceptions.FileEmptyException;
import com.assessor.filemanagement.exceptions.PersonNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CsvServiceTest {

    @Mock
    CSVClient csvClient;
    @Mock
    CSVConfiguration csvConfiguration;

    @InjectMocks
    CsvService csvService;

    @Test
    void getAllPersons_happy_path() throws IOException {
        Person p1=new Person("T1","T1","A1", Farbe.BLAU,0);
        Person p2=new Person("T2","T2","A2", Farbe.ROT,1);
        Map<Integer,Person> expected=new HashMap<>();
        expected.put(0,p1);
        expected.put(1,p2);

        when(csvClient.getPersonCach()).thenReturn(expected);

        List<Person> actual=csvService.getAllPersons();

        assertThat(actual).containsExactly(p1,p2);

        verify(csvClient).getPersonCach();
    }

    @Test
    void getAllPerson_null_map() throws IOException {
      //  Map<Integer,Person> expectedMap=new HashMap<>();
        when(csvClient.getPersonCach()).thenReturn(null);
        assertThrowsExactly(FileEmptyException.class, ()-> csvService.getAllPersons());
    }

    @Test
    void getAllPerson_empty_map() throws IOException {

        when(csvClient.getPersonCach()).thenReturn(Map.of());
        assertThrowsExactly(FileEmptyException.class, ()-> csvService.getAllPersons());
    }

    @Test
    void getAllByColor_happy_path() throws IOException {
        Person p1=new Person("T1","T1","A1", Farbe.BLAU,0);
        Person p2=new Person("T2","T2","A2", Farbe.ROT,1);
        Person p3=new Person("T3","T3","A3", Farbe.ROT,2);
        Map<Integer,Person> expected=new HashMap<>();
        //expected.put(0,p1);
        expected.put(1,p2);
        expected.put(2,p3);

        when(csvClient.getPersonCach()).thenReturn(expected);

        List<Person> actual=csvService.getAllByColor("ROT");

        assertThat(actual).containsExactly(p2,p3);
        verify(csvClient).getPersonCach();
    }

    @Test
    void getAllByColor_color_not_found() throws IOException {
        Person p1=new Person("T1","T1","A1", Farbe.BLAU,0);
        Person p2=new Person("T2","T2","A2", Farbe.ROT,1);
        Person p3=new Person("T3","T3","A3", Farbe.ROT,2);
        Map<Integer,Person> expected=new HashMap<>();
        //expected.put(0,p1);
        expected.put(1,p2);
        expected.put(2,p3);

        when(csvClient.getPersonCach()).thenReturn(expected);

       //List<Person> actual=csvService.getAllByColor("BLAU");
        assertThrowsExactly(ColorNotFoundException.class, ()-> csvService.getAllByColor("BLAU"));
        verify(csvClient).getPersonCach();

    }

    @Test
    void getById_happy_path() throws IOException {
        Person p1=new Person("T1","T1","A1", Farbe.BLAU,0);
        Person p2=new Person("T2","T2","A2", Farbe.ROT,1);
        Map<Integer,Person> expected=new HashMap<>();
        expected.put(0,p1);

        when(csvClient.getPersonCach()).thenReturn(expected);

        Person actual=csvService.getById(0);

        assertThat(actual).isEqualTo(p1);
        verify(csvClient).getPersonCach();

    }
    @Test
    void getById_id_Not_Found() throws IOException {
        Person p1=new Person("T1","T1","A1", Farbe.BLAU,0);
        Person p2=new Person("T2","T2","A2", Farbe.ROT,1);
        Map<Integer,Person> expected=new HashMap<>();
        expected.put(0,p1);

        when(csvClient.getPersonCach()).thenReturn(expected);

        assertThrowsExactly(PersonNotFoundException.class,()-> csvService.getById(3));
        verify(csvClient).getPersonCach();

    }
    @Test
    void addNewPerson(@TempDir Path tmpDir) throws IOException {

        // 1. Arrange
        Path csvFile = tmpDir.resolve("people.csv");
        // create an empty file
        Files.createFile(csvFile);

        Person p1 = new Person("T1", "T1", "A1", Farbe.BLAU, 0);

        when(csvConfiguration.filePath()).thenReturn(csvFile.toString());
        when(csvConfiguration.separator()).thenReturn(",");

        // 2. Act
        boolean result = csvService.addNewPerson(p1);

        // 3. Assert return value
        assertThat(result).isTrue();

        // 4. Assert that cache refresh was triggered
        verify(csvClient).refreshPersonCach();

        // 5. Assert file contents: read all lines, last line contains p1’s data
        List<String> lines = Files.readAllLines(csvFile);
        assertThat(lines).isNotEmpty();
        String lastLine = lines.get(lines.size() - 1);

        // expect "T1,T1,A1,<colorId>"
        assertThat(lastLine).contains("T1,T1,A1," + p1.getColor().id());
    }
}