package by.gsu.epamlab.implementations;

import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.beans.results.*;
import by.gsu.epamlab.interfaces.IResultDAO;

import java.util.Scanner;

public class ResultImplCsv3 extends ResultImplCsv{
    public ResultImplCsv3(String filename, ResultFactory factory) {
        super(filename, factory);
    }

    public Result nextResult(){
        return ResultFactory.getHalfClassFromFactory(scanner.nextLine());
    }
}
