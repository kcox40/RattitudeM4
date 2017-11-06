package edu.gatecg.cs2340.rattitudem4;

/**
 * Created by kcox8 on 10/30/2017.
 */

public enum Months {
    JAN("January"),
    FEB("February"),
    MAR("March"),
    APR("April"),
    MAY("May"),
    JUN("June"),
    JUL("July"),
    AUG("August"),
    SEP("September"),
    OCT("October"),
    NOV("November"),
    DEC("December");

    private String month;

    private Months (String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return month;
    }
}
