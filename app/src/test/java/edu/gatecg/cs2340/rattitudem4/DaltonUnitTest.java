package edu.gatecg.cs2340.rattitudem4;

/**
 * Created by dalton on 11/1/17.
 */
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A test for the equals() method in User.java
 *
 * @author Dalton Touchberry
 * @version 1.0
 */
public class DaltonUnitTest {
    private User fox;
    private User foxCopy;
    private User foxRef;
    private User foxWrongPass;
    private User scully;
    private User scullyCopy;
    private User queequeg;
    private String notAUser;

    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        fox = new User("Fox", "Mulder",
                "aliensarereal@fbi.gov", "foxmulder", "aliens",
                "normal");
        foxCopy = new User("Fox", "Mulder",
                "aliensarereal@fbi.gov", "foxmulder", "aliens",
                "normal");
        foxWrongPass = new User("Fox", "Mulder",
                "aliensarereal@fbi.gov", "foxmulder", "scully",
                "normal");
        scully = new User("Dana", "Scully", "foxiscrazy@fbi.gov",
                "danascully", "queequeg", "normal");
        scullyCopy = new User("Dana", "Scully", "foxiscrazy@fbi.gov",
                "danascully", "queequeg", "normal");
        foxRef = fox;
        notAUser = "I'm just a string";
    }

    @Test(timeout = TIMEOUT)
    public void testExactCopyEqual() {
        assertTrue(fox.equals(foxCopy));
    }

    @Test(timeout = TIMEOUT)
    public void testSecondReference() {
        assertTrue(fox.equals(foxRef));
    }

    @Test(timeout = TIMEOUT)
    public void testNullUser() {
        assertTrue(!fox.equals(queequeg));
    }

    @Test(timeout = TIMEOUT)
    public void testWrongClassType() {
        assertTrue(!fox.equals(notAUser));
    }

    @Test(timeout = TIMEOUT)
    public void testWrongPassword() {
        assertTrue(!fox.equals(foxWrongPass));
    }



}

