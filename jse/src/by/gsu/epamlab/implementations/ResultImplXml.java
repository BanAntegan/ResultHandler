package by.gsu.epamlab.implementations;

import by.gsu.epamlab.beans.results.DecimalResults;
import by.gsu.epamlab.beans.results.Result;
import by.gsu.epamlab.interfaces.IResultDAO;
import by.gsu.epamlab.handlers.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class ResultImplXml implements IResultDAO {
    XMLHandler handler;
    List<DecimalResults> resultList;
    Iterator<DecimalResults> iterator;
    public ResultImplXml(String filename, XMLHandler handler){
        String file = filename + ".xml";
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File("src/" + file), handler);
            this.handler = handler;
            resultList = handler.getResults();
        }catch (ParserConfigurationException | SAXException | IOException e){

        }
        assert resultList != null;
        iterator = resultList.iterator();
    }
    public boolean hasResult(){
        if (iterator.hasNext()){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Result nextResult() {
        return iterator.next();
    }
    public void closeReader(){
    }
}
