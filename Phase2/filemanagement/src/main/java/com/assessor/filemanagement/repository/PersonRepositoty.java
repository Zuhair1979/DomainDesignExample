package com.assessor.filemanagement.repository;

import com.assessor.filemanagement.entity.DataBasePerson;
import com.assessor.filemanagement.entity.Farbe;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Profile("postgres")
public interface PersonRepositoty extends JpaRepository<DataBasePerson,Integer> {

    List<DataBasePerson> findByColor(Farbe color);

}
