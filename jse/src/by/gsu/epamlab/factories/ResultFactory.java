package by.gsu.epamlab.factories;
import by.gsu.epamlab.beans.results.HalfResult;
import by.gsu.epamlab.beans.results.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultFactory {
    public static Result getClassFromFactory(String csvLine) {
        String[] values = csvLine.split(";");
        Result r = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String student = values[0];
            String test = values[1];
            Date date = format.parse(values[2]);
            String a = "";
            for(String s : values[3].split("\\.")){
                a += s;
            }
            int mark = Integer.parseInt(a);
            r = new Result(student,test,date,mark);
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        return r;
    }
    public static Result getHalfClassFromFactory(String csvLine){
        String[] values = csvLine.split(";");
        Result r = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String student = values[0];
            String test = values[1];
            Date date = format.parse(values[2]);
            String a = "";
            for(String s : values[3].split("\\.")){
                a += s;
            }
            if(values[3].split("\\.").length == 1){
                a += "0";
            }
            String mark = a;
            r = new HalfResult(student,test,date,mark);
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        return r;
    }
}
