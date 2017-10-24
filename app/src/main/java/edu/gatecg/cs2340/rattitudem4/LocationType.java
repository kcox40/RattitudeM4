package edu.gatecg.cs2340.rattitudem4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dalton on 10/18/17.
 */

public enum LocationType implements Serializable {
    SMALLFAMILYDWELLING("1-2 Family Dwelling"),
    SMALLFAMILYMIXEDUSE("1-2 Family Mixed Use Building"),
    LARGEFAMILYDWELLING("3+ Family Dwelling"),
    LARGEFAMILYMIXEDUSE("3+ Family Mixed Use Building"),
    CATCHBASINSEWER("Catch Basin/Sewer"),
    COMMERCIAL("Commercial Building"),
    CONSTRUCTION("Construction Site"),
    DAYCARE("Day Care/Nursery"),
    GOVERNMENT("Government Building"),
    HOSPITAL("Hospital"),
    OFFICE("Office Building"),
    OTHER("Other - Please explain:"),
    PARKING("Parking Lot/Garage"),
    GARDEN("Public Garden"),
    STAIRS("Public Stairs"),
    SCHOOL("School/Pre-school"),
    SRO("Single Room Occupancy"),
    SUMMERCAMP("Summer Camp"),
    VACANTBUILDING("Vacant Building"),
    VACANTLOT("Vacant Lot");


    private final String string;

    LocationType(String string) {
        this.string = string;
    }

    /**
     *
     * @return the display string for the Location Type
     */
    public String getString() {
        return string;
    }

    /**
     *
     * @return arraylist of enum strings
     */
    public static String[] displayLocations() {
        ArrayList<String> stringArrayList = null;
        String[] displayLocations = new String[19];
        for (LocationType locationtype : LocationType.values()) {
            stringArrayList.add(locationtype.getString());
        }
        return stringArrayList.toArray(displayLocations);
    }
}
