import by.gsu.epamlab.Constants;
import by.gsu.epamlab.beans.DatabaseConnection;
import by.gsu.epamlab.beans.ResultsLoader;
import by.gsu.epamlab.beans.results.Result;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.handlers.XMLHandler;
import by.gsu.epamlab.implementations.*;
import by.gsu.epamlab.interfaces.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    public static void main(String[] args) {
        final int LOGIN_IND = 1;
        final int TEST_IND = 2;
        final int DATE_IND = 3;
        final int MARK_IND = 4;
        Statement st;
        ResultSet rs;

        ResultFactory factory = new ResultFactory();
        IResultDAO resultDAO = new ResultImplCsv("results", factory);
//        XMLHandler handler = new XMLHandler();
//        IResultDAO resultDAO = new ResultImplXml("results", handler);
//        ResultFactory factory = new ResultFactory();
//        IResultDAO resultDAO = new ResultImplCsv3("results3", factory);
        ResultsLoader.loadResults(resultDAO);
        try{
            DatabaseConnection.getInstance();
            DatabaseConnection cn = new DatabaseConnection();

            //getting average mark for each student
            st = cn.getCn().createStatement();
            rs = st.executeQuery(Constants.SELECT_AVERAGE_MARK);
            while(rs.next()){
                System.out.println("Student : " + rs.getString(LOGIN_IND) + ", Mark : " + rs.getDouble(TEST_IND));
            }

            //creating a LinkedList implementation of test results for current month
            LinkedList<Result> linkedList = new LinkedList<>();
            rs = st.executeQuery(Constants.CURRENT_MONTH_RESULTS);
            while(rs.next()){
                linkedList.add(new Result(rs.getString(LOGIN_IND), rs.getString(TEST_IND), rs.getDate(DATE_IND), rs.getInt(MARK_IND)));
            }
            System.out.println(linkedList);

            //getting last day purchase for current month
            System.out.println("Test results in the latest day : " + linkedList.getLast());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
