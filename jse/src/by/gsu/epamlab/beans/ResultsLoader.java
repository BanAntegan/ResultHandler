package by.gsu.epamlab.beans;

import by.gsu.epamlab.beans.results.Result;
import by.gsu.epamlab.interfaces.IResultDAO;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.gsu.epamlab.*;
public class ResultsLoader {
    public static  void loadResults(IResultDAO reader){
        final int IND_LOGIN = 1;
        final int IND_TEST = 2;
        final int IND_DATE = 3;
        final int IND_MARK = 4;
        String psSelectLogin = "select * from results.logins where results.logins.name = ?";
        String psInsertLogin = "insert into results.logins(name) values (?)";
        String psSelectTest = "select * from results.tests where results.tests.name = ?";
        String psInsertTest = "insert into results.tests(name) values (?)";
        Statement st = null;
        PreparedStatement ps;
        DatabaseConnection conn = null;
        java.sql.Date sDate;
        String insertStatement = "insert into results.results(idlogin, idtest, date, mark) values (?,?,?,?)";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date uDate;
        try{
            Result latestResult = new Result("-", "-", "2020-02-01", "0");
            DatabaseConnection.getInstance();
            conn = new DatabaseConnection();
            st = conn.getCn().createStatement();
            st.executeUpdate(Constants.TABLE_RESULTS_DROP);
            st.executeUpdate(Constants.TABLE_TESTS_DROP);
            st.executeUpdate(Constants.TABLE_LOGINS_DROP);
            st.executeUpdate(Constants.TABLE_LOGINS_CREATION);
            st.executeUpdate(Constants.TABLE_TESTS_CREATION);
            st.executeUpdate(Constants.TABLE_RESULTS_CREATION);

            while(reader.hasResult()){
                Result result = reader.nextResult();
                String student = result.getStudent();
                String test = result.getTestName();
                Date date = result.getDate();
                int mark = result.getMark();
                System.out.println(result);
                int idStudent = getId(student,psSelectLogin, psInsertLogin);
                int idTest = getId(test,psSelectTest, psInsertTest);
                sDate = new java.sql.Date(date.getTime());
                ps = conn.getCn().prepareStatement(insertStatement);
                ps.setInt(IND_LOGIN,idStudent);
                ps.setInt(IND_TEST, idTest);
                ps.setDate(IND_DATE, sDate);
                ps.setInt(IND_MARK, mark);
                ps.addBatch();
                ps.executeBatch();
            }
        }catch (SQLException | ParseException e){
            System.out.println(e);
        }
        finally {
            reader.closeReader();
        }
    }
    private static int getId(String name, String select, String insert) {
        final int IND_ID = 1;
        final int IND_NAME = 2;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps;
        int id = 0;
        try {
            DatabaseConnection conn = new DatabaseConnection();
            ps = conn.getCn().prepareStatement(select);
            ps.setString(IND_ID, name);
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = conn.getCn().prepareStatement(insert);
                ps.setString(IND_ID, name);
                ps.addBatch();
                ps.executeBatch();
            }
            ps = conn.getCn().prepareStatement(select);
            ps.setString(IND_ID,name);
            rs = ps.executeQuery();
            rs.next();
            id = rs.getInt(IND_ID);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }
}

