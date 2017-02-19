package com.lohika.myazin.octane.testdata;

import java.util.Arrays;

/**
 * Created by amyazin on 2/16/2017.
 */
public class ColumnChooserTestData extends BaseTestData {

    public void populateData(){
        list = Arrays.asList(new Object[][] {
                {"ID"},
                {"Creation time"},
                {"Name"},
                {"Severity"},
                {"Application modules"},
                {"Detected in release"},
                {"Detected in build"},
                {"Story points"}
        });
    }
}
