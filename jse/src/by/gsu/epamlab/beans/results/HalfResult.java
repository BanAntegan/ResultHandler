package by.gsu.epamlab.beans.results;

import java.text.ParseException;
import java.util.Date;

public class HalfResult extends Result{
    int mark;
    public HalfResult(String student, String testName, Date sDate, String mark) throws ParseException {
        super(student, testName, sDate, mark);
    }

//    public String toString(){
//        return getStudent() + ";" + getTestName() + ";" + getStringDate() + ";" + mark;
//    }
}
