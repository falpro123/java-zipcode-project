package com.kenzie.app.zipcode.format;

//utility class - class that has static helper methods

//example: Math class : Math.min() or Math.random()

import java.util.HashMap;

public class AddressFormatter {
    //declare properties
    // lookup table -HashMap
    private static HashMap<String, String> abbreviationTable;

    // initialize map -- hardcode
    public static void initializeAddressMap() {
        abbreviationTable = new HashMap<>();

        abbreviationTable.put("ST", "STREET");
        abbreviationTable.put("ST.", "STREET");
        abbreviationTable.put("St", "STREET");
        abbreviationTable.put("St.", "STREET");
        abbreviationTable.put("AVE", "AVENUE");
        abbreviationTable.put("AVE.", "AVENUE");
        abbreviationTable.put("Ave", "AVENUE");
        abbreviationTable.put("Ave.", "AVENUE");
        abbreviationTable.put("RD", "ROAD");
        abbreviationTable.put("RD.", "ROAD");
        abbreviationTable.put("Rd", "ROAD");
        abbreviationTable.put("Rd.", "ROAD");
        abbreviationTable.put("BLVD", "BOULEVARD");
        abbreviationTable.put("BLVD.", "BOULEVARD");
        abbreviationTable.put("Blvd", "BOULEVARD");
        abbreviationTable.put("Blvd.", "BOULEVARD");
        //ST | STREET
        //St.| STREET
        //ST.| STREET
        //St | STREET
    }


    // replace address string - use the map
    // Example: 123 Main St.
    // Output: 123 Main STREET
    public static String replaceAbbreviation(String input) {
        String resultStr = input;
        //write replace logic
        //1. For each entry in HashMap, search key, replace with value?
        // Problem: Loop is causing more than one replace
        for (String currentKey : abbreviationTable.keySet()) {
            resultStr = resultStr.replace(currentKey, abbreviationTable.get(currentKey));
        }
        return resultStr;
    }

    public static String formatAddress(String str) {
        return str.toUpperCase().trim();
    }

    public static String formatStreetAddress(String str) {
        return formatAddress(replaceAbbreviation(str));
    }


    public static void main(String[] args) {
        AddressFormatter.initializeAddressMap();
        System.out.println(AddressFormatter.replaceAbbreviation("123 Main St."));
    }
}
