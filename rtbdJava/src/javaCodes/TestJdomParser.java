package javaCodes;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class TestJdomParser {
	Document xml = null;
	Element root = null;
	
	
	public static void main(String args[]){
     
     //creating JDOM SAX parser
     SAXBuilder builder = new SAXBuilder();
   
     //reading XML document
     TestJdomParser t = new TestJdomParser();
     
     try {
             t.xml = builder.build(new File("dblp_db.xml"));
     } catch (JDOMException e) {
             e.printStackTrace();
     } catch (IOException e) {
             e.printStackTrace();
     }
     
     
     
     t.root = t.xml.getRootElement();

     for (int i = 1975; i<=2015;i++){
    	 t.getAuthorsByYear(i);
     }
     System.out.println("Root element of XML document is : " + t.root.getName());
     System.out.println("Number of publications in this XML : " + t.root.getChildren().size());
	 }
	 
	 public int getAuthorsByYear(int year){
		 int count=0;
		 for (Element e : root.getChildren()){
			 int y = Integer.parseInt(e.getChild("year").getText()); 
			 if (y==year){
				 count+=e.getChildren("author").size();
			 }
		 }
		 System.out.println(year+" "+count);
		 return count;
	 }

}
