package javaCodes;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class TestJdomParser {
	 public static void main(String args[]){
     
     //creating JDOM SAX parser
     SAXBuilder builder = new SAXBuilder();
   
     //reading XML document
     Document xml = null;
     try {
             xml = builder.build(new File("dblp_db.xml"));
     } catch (JDOMException e) {
             e.printStackTrace();
     } catch (IOException e) {
             e.printStackTrace();
     }
     
     Element root = xml.getRootElement();
     
     System.out.println("Root element of XML document is : " + root.getName());
     System.out.println("Number of books in this XML : " + root.getChildren().size());
	 }
}
