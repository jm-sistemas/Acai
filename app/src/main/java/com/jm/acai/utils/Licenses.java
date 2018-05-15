package com.jm.acai.utils;


import java.util.Arrays;
import java.util.List;

class Licenses {

    private static final List<String> blockCodes = Arrays.asList("BLOCK_CODE", "90a6454124514d8ce5d897f86b188c18");

    static boolean isLicenseValid(String license) {
        return !blockCodes.contains(license);
    }
}