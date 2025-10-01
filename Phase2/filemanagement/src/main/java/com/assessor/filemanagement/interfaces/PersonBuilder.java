package com.assessor.filemanagement.interfaces;

import com.assessor.filemanagement.entity.Farbe;
import com.assessor.filemanagement.domain.Person;

public interface PersonBuilder <T,R>{

    String getFname(T record);
    String getLname(T record);
    String getAddress(T record);
    Farbe getColor(T record);
    R getPerson(T record, Integer id);

}
