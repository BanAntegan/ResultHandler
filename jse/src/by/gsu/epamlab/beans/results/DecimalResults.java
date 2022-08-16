package by.gsu.epamlab.beans.results;

import java.text.ParseException;

public class DecimalResults extends Result {
    public DecimalResults(String student, String testName, String sDate, String mark) throws ParseException {
        super(student, testName, sDate, mark);
    }
    public String toString(){
        return getStudent() + ";" + getTestName() + ";" + getStringDate() + ";" + getMark()/10 + "." + getMark()%10;
    }
}
