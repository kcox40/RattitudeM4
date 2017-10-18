package edu.gatecg.cs2340.rattitudem4;

/**
 * Created by Russell on 10/11/2017.
 */

public class RatReport {
    private int id;
    private String date;
    private String locationType;
    private int incidentZip;
    private String address;
    private String city;
    private String borough;
    private Double latitude;
    private Double longitude;
    public RatReport() {}
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public int getIncidentZip() {
        return incidentZip;
    }

    public void setIncidentZip(int incidentZip) {
        this.incidentZip = incidentZip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

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
}
