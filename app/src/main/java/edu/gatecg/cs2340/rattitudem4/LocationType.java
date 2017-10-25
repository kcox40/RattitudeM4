package edu.gatecg.cs2340.rattitudem4;

/**
 * Created by kcox40 on 10/23/2017.
 * @author team 57
 * @version 1
 */

public enum LocationType {
    FAMILY1("1-2 Family Dwelling"),
    FAMILY2("3+ Family Apt. Building"),
    FAMILY3("Family Mixed Use Building"),
    COMMERCIAL("Commercial Building"),
    VACANT("Vacant Lot"),
    CONSTRUCTION("Construction Site"),
    HOSPITAL("Hospital"),
    SEWER("Catch Basin/Sewer");

    private String locationType;

    private LocationType(String locationType) {
        this.locationType = locationType;
    }

    @Override
    public String toString() {
        return locationType;
    }
}
