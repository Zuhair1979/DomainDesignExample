package com.assessor.filemanagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

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
