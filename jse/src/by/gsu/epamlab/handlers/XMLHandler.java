package by.gsu.epamlab.handlers;

import by.gsu.epamlab.beans.results.DecimalResults;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler extends DefaultHandler {
    List<DecimalResults> list = new ArrayList<>();
    String login = "";
    public List<DecimalResults> getResults(){
        return list;
    }
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        if(qName.equals("test")){
            String name = attributes.getValue("name");
            String date = attributes.getValue("date");
            String cMark = attributes.getValue("mark");
            String mark = "";
            for(String s : cMark.split("\\.")){
                mark += s;
            }
            try {
                list.add(new DecimalResults(login ,name, date, mark));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public void characters(char[] ch, int start, int length) throws SAXException{
        String str = new String(ch,start,length).trim();
        if(!str.isEmpty()){
            login = str;
        }
    }
}
