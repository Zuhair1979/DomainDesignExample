package com.assessor.filemanagement.interfaces;

import com.assessor.filemanagement.entity.Farbe;
import com.assessor.filemanagement.entity.Person;

public interface PersonBuilder <T>{

    String getFname(T record);
    String getLname(T record);
    String getAddress(T record);
    Farbe getColor(T record);
    Person getPerson(T record, int id);

}
