package edu.gatech.cs2340.rattitudem4;

/**
 * Created by kcox40 on 10/23/2017.
 * @author team 57
 * @version 1
 */

public enum Borough {
    MANHATTAN("Manhattan"),
    STATEN_ISLAND("Staten Island"),
    QUEENS("Queens"),
    BROOKLYN("Brooklyn"),

    BRONX("Bronx");

    private final String borough;

    Borough(String borough) {
        this.borough = borough;
    }

    @Override
    public String toString() {
        return borough;
    }
}
