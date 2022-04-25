package com.vineeth.learning.spring.microservice.model;

public enum Region {
Central_Coast("Central_Coast"),
Southern_California("Southern_California"),
Nourthern_California("Nourthern_California"),
Varies("Varies");


    private String label;
    private Region(String label){
        this.label = label;
    }
    public static Region findByLabel(String label) {
        for (Region region : Region.values()){
            if (region.label.equalsIgnoreCase(label)){
                return region;
            }
        }
        return null;
    }

}
