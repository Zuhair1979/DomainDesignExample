package com.assessor.filemanagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import java.util.LinkedHashMap;
import java.util.Map;

/*
* 1- create Cash list based on LRU Least Recently Used
* 2- when person retrieved by GET or POST , this element will be at tail of this List so it can be retrieved
* without iterating over the list
* */
@Profile("csv")
public class CSVCashList<K,V> extends LinkedHashMap<K,V> {

   private int cashSize;

    public CSVCashList(int cashSize) {
        super(cashSize,0.75F, true);
        this.cashSize=cashSize;
    }



    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cashSize;
    }
}
