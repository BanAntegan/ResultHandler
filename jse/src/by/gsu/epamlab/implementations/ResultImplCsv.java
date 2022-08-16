package by.gsu.epamlab.implementations;

import by.gsu.epamlab.beans.results.Result;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.interfaces.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ResultImplCsv implements IResultDAO {
    ResultFactory factory;
    Scanner scanner = null;
    public ResultImplCsv(String filename, ResultFactory factory){
        String file = filename + ".csv";
        try{
            scanner = new Scanner(new FileReader("src/" + file));
            this.factory = factory;
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public boolean hasResult(){
        if(scanner.hasNext()){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Result nextResult() {
        return ResultFactory.getClassFromFactory(scanner.nextLine());
    }
    public void closeReader(){
        scanner.close();
    }
}
