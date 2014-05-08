package javaCodes;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Aashu_test {

	public static void main(String [] args){

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
	     
	     //System.out.println("Root element of XML document is : " + root.getName());
	     //System.out.println("Number of records in this XML : " + root.getChildren().size());
	     
	     Set<String> years = new HashSet<String>();
	     //Element inproceed = root.getChild("article");
	     //System.out.println(inproceed.getContentSize());
	     System.out.println(root.getChildren("proceedings").size());
	     
	     
//	     System.out.println(inproceed.getChildText("year"));
	     //List ns = root.getChildren("inproceedings").getChildren("year");
	     //System.out.println(ns.size());
	
	     /*for (int i = 0; i < 23; i++)
	     { System.out.println(root.getChild("inproceedings").getChildText("year"));
	     }
	     for (int i = 1975; i < 2012; i++)
	     { if(years.contains(String.valueOf(i))){
	    		 System.out.println(i);
	     
	     }}*/
	
	}

}
