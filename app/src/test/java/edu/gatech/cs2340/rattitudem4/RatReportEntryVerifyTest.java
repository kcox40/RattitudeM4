package edu.gatech.cs2340.rattitudem4;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kcox40 on 11/20/2017.
 */
public class RatReportEntryVerifyTest {
    @Test
    public void checkZip() throws Exception {
        assertEquals(RatReportEntryVerify.checkZip(""), "Zip field is empty");
        assertEquals(RatReportEntryVerify.checkZip("1"), "Zip must be 5 digits long");
        assertEquals(RatReportEntryVerify.checkZip("1234t"), "Zip Must Contain Digits Only");
        assertEquals(RatReportEntryVerify.checkZip("12345"), "12345");
    }
}