package edu.gatecg.cs2340.rattitudem4;

/**
 * Created by abbiewilliams on 10/12/17.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    //public static void main(String[] args) {

    private ArrayList ratData;

    public ArrayList getRatData() {
        return ratData;
    }

    public void csvToArray() {
        //file path for my computer
        //String csvFile = "/Users/abbiewilliams/Documents/CS2340/RattitudeM4/app/Rat_Sightings.csv";
        //general file path
        String csvFile = "/RattitudeM4/app/Rat_Sightings.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] lineData = line.split(cvsSplitBy);

                String date = lineData[1];
                String location = lineData[7];
                String zip = lineData[8];
                String address = lineData[9];
                String city = lineData[16];
                String borough = lineData[23];
                String latitude = lineData[49];
                String longitude = lineData[50];

                String[] selectData = new String[8];
                selectData[0] = date;
                selectData[1] = location;
                selectData[2] = zip;
                selectData[3] = address;
                selectData[4] = city;
                selectData[5] = borough;
                selectData[6] = latitude;
                selectData[7] = longitude;

                ratData.add(selectData);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
