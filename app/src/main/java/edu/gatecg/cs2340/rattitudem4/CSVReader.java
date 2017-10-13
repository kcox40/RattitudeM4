package edu.gatecg.cs2340.rattitudem4;

/**
 * Created by abbiewilliams on 10/12/17.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class CSVReader {

    private ArrayList<String[]> ratData;
    //TODO: Add general filepath
    static final String csvFile = "/Users/dalton/Dropbox/RattitudeM4/app/src/main/java/edu/gatecg/cs2340/rattitudem4/Rat_Sightings.csv";
    String line = "";
    BufferedReader br = null;

//    public static void main(String[] args) {
//        CSVReader csvReader = new CSVReader();
//        csvReader.csvToArray();
//        for (int i = 0; i < 100; i++) {
//            System.out.println(((String[]) csvReader.getRatData().get(i))[0]);
//        }
//    }

    public CSVReader() {
        ratData = new ArrayList<String[]>();
    }

    public ArrayList<String[]> csvToArray() {

        String cvsSplitBy = ",";

        try {
            FileReader file = new FileReader(csvFile);
            br = new BufferedReader(file);
            line = br.readLine();
            String[] selectData;
//            while ((line = br.readLine()) != null) {
            while (line != null) {

                // use comma as separator
                String[] lineData = line.split(cvsSplitBy);

//                String date = lineData[1];
//                String location = lineData[7];
//                String zip = lineData[8];
//                String address = lineData[9];
//                String city = lineData[16];
//                String borough = lineData[23];
//                String latitude = lineData[49];
//                String longitude = lineData[50];

//                String[] selectData = new String[8];
                selectData = copyToNewArray(lineData);
//                selectData[0] = date;
//                selectData[1] = location;
//                selectData[2] = zip;
//                selectData[3] = address;
//                selectData[4] = city;
//                selectData[5] = borough;
//                selectData[6] = latitude;
//                selectData[7] = longitude;

                ratData.add(selectData);
//                System.out.println(selectData);
                line = br.readLine();
            }
            return ratData;

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
        return ratData;
    }

    public ArrayList getRatData() {
        return ratData;
    }

    /**
     * Helper method to catch ArrayIndexOutOfBoundsException raised when a field in the CSV is empty
     * @param lineData array from line of csv data
     * @return Array to add to ratData
     */
    public String[] copyToNewArray(String[] lineData) {
        String[] outStringArr = new String[8];
        Integer[] relevantIndices = new Integer[]{1,7,8,9,16,23,49,50};
        int i = 0;
        for (int oldIndex : relevantIndices) {
            try {
                outStringArr[i] = lineData[oldIndex];
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                outStringArr[i] = "";
            }
            i++;
        }
        return outStringArr;
    }


}
