package edu.gatecg.cs2340.rattitudem4;

/**
 * Created by kcox40 on 10/23/2017.
 * @author team 57
 * @version 1
 */

public enum Brough {
    MANHATTAN("Manhattan"),
    STATENISLAND("Staten Island"),
    QUEENS("Queens"),
    BROOKLYN("Brooklyn"),
    BRONX("Bronx");

    private String brough;

    private Brough (String brough) {
        this.brough = brough;
    }

    @Override
    public String toString() {
        return brough;
    }
}
