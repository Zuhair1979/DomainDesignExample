package com.assessor.filemanagement.entity;

public enum Farbe {
    BLAU("#0000FF", 1),
    GRÜN("#00FF00", 2),
    VIOLETT("#9400D3", 3),
    ROT("#FF0000", 4),
    GELB("#FFFF00", 5),
    TÜRKIS("#40E0D0", 6),
    WEIß("#FFFFFF", 7),
    UNKNOWN("UnKNOWN",0);

    private final String codeRgb;
    private final int id;

    Farbe(String codeRgb, int id) {
        this.codeRgb = codeRgb;
        this.id = id;
    }
    public int id(){return id;}
}
