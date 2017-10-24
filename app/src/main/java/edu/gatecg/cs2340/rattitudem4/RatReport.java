package edu.gatecg.cs2340.rattitudem4;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Russell on 10/11/2017.
 * @author team 57 
 * @version 1 
 */

public class RatReport {
    private static int newReports;
    private static final int lastItemPointer = 37018532;
    private int id;
    private String date;
    private String locationType;
    private int incidentZip;
    private String address;
    private String city;
    private String borough;
    private Double latitude;
    private Double longitude;

//    public static List<String> allowedLocationTypes = Arrays.asList(LocationType.displayLocations());
    public static List<String> allowedLocationTypes = Arrays.asList(
            "1-2 Family Dwelling", "1-2 Family Mixed Use Building", "3+ Family Dwelling",
        "3+ Family Mixed Use Building", "Catch Basin/Sewer", "Commercial Building",
        "Construction Site", "Day Care/Nursery", "Government Building", "Hospital",
        "Office Building", "Other - Please explain:", "Parking Lot/Garage", "Public Garden",
        "Public Stairs", "School/Pre-school","Single Room Occupancy", "Summer Camp",
        "Vacant Building", "Vacant Lot");
    public static List<String> allowedCities = Arrays.asList("New York City", "Atlanta");
    public static List<String> allowedBoroughs = Arrays.asList("Manhattan", "Brooklyn", "Queens", "Bronx", "Staten Island");

    /** 
     * report of the rat info 
     */ 

    public RatReport() {}
    /** 
     * constructor for rat report 
     * @param id number of the rat report  
     * @param date of the rat report 
     * @param locationType where the rat is 
     * @param incidentZip of the location 
     * @param address street address of the rat 
     * @param city of the rat sighting 
     * @param borough of rat sighting 
     * @param latitude of sighting 
     * @param longitude of sighting 
     */ 
    public RatReport(int id, String date, String locationType,
                     int incidentZip, String address, String city,
                     String borough, Double latitude, Double longitude) {
        this.id = id;
        this.date = date;
        this.locationType = locationType;
        this.incidentZip = incidentZip;
        this.address = address;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //You must use this constructor to update the database.
    //The no arg or the report requiring an id are available for dummy reports.
    public RatReport(String date, String locationType,
                     int incidentZip, String address, String city,
                     String borough, Double latitude, Double longitude) {

        this.id = OptionsActivity.dbManager.addNewRatReport(date, locationType, incidentZip,
                address, city, borough, latitude, longitude);
        this.date = date;
        this.locationType = locationType;
        this.incidentZip = incidentZip;
        this.address = address;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    /** 
     * gets the id of rat report 
     * @return id 
     */ 
    public int getId() {
        return id;
    }
     /** 
     * sets id 
     * @param id is the id of the rat report 
     */
    public void setId(int id) {
        this.id = id;
    }
     /** 
     * gets the date of rat report 
     * @return date 
     */ 
    public String getDate() {
        return date;
    }
     /** 
     * sets date 
     * @param date of the rat report 
     */ 
    public void setDate(String date) {
        this.date = date;
    }
    /** 
     * gets the location of rat report 
     * @return location of the report 
     */ 
    public String getLocationType() {
        return locationType;
    }
     /** 
     * sets location type 
     * @param locationType of rat 
     */ 
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
    /** 
     * gets the zip of rat report 
     * @return zip of the report 
     */ 
    public int getIncidentZip() {
        return incidentZip;
    }
     /** 
     * sets zip 
     * @param incidentZip zip of the rat sighting 
     */ 
    public void setIncidentZip(int incidentZip) {
        this.incidentZip = incidentZip;
    }
     /** 
     * gets address 
     * @return address 
     */
    public String getAddress() {
        return address;
    }
    /** 
     * sets address 
     * @param address sets address  
     */
    public void setAddress(String address) {
        this.address = address;
    }
     /** 
     * gets city 
     * @return city 
     */ 
    public String getCity() {
        return city;
    }
     
    /** 
     * sets city of sighting 
     * @param city where sighting is  
     */ 
    public void setCity(String city) {
        this.city = city;
    }
     /** 
     * gets borough 
     * @return borough 
     */ 
    public String getBorough() {
        return borough;
    }
    /** 
     * sets borough of sighting 
     * @param borough where sighting is  
     */ 
    public void setBorough(String borough) {
        this.borough = borough;
    }
    /** 
     * gets latitude 
     * @return latitude 
     */ 
    public Double getLatitude() {
        return latitude;
    }
    /** 
     * sets latitude of sighting 
     * @param latitude where sighting is  
     */ 
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    /** 
     * gets longitude 
     * @return longitude 
     */ 
    public Double getLongitude() {
        return longitude;
    }
    /** 
     * sets longitude of sighting 
     * @param longitude where sighting is  
     */ 
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "RatReport{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", locationType='" + locationType + '\'' +
                ", incidentZip=" + incidentZip +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", borough='" + borough + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
    /*
        Returns the number of new rat reports
        @return number of new reports
     */
    public int getNewReports() {
        return newReports;
    }
}
