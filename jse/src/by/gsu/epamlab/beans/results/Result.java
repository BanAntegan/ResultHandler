package by.gsu.epamlab.beans.results;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Result {
    private String student;
    private String testName;
    private Date date;
    private int mark;
    SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");

    public Result(String student, String testName, Date date, int mark){
        this.student = student;
        this.testName = testName;
        this.date = date;
        this.mark = mark;
    }
    public Result(String student, String testName, String sDate, String mark) throws ParseException {
        this.student = student;
        this.testName = testName;
        date = formats.parse(sDate);
        this.mark = Integer.parseInt(mark);
    }
    public Result(String student, String testName, Date date, String mark){
        this.student = student;
        this.testName = testName;
        this.date = date;
        this.mark = Integer.parseInt(mark) * 2 / 10;
    }
    public Result(){}

    public String getStudent(){
        return student;
    }
    public String getTestName(){
        return testName;
    }
    public Date getDate(){
        return date;
    }
    public String getStringDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }
    public int getMark(){
        return mark;
    }
    public String getStringMark(){
        return (mark/10) + "." + (mark%10);
    }
    public void setStudent(String student){
        this.student = student;
    }
    public void setTestName(String testName){
        this.testName = testName;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public void setMark(int mark){
        this.mark = mark;
    }

    public String toString(){
        return student + ";" + testName + ";" + getStringDate() + ";" + mark;
    }
}
