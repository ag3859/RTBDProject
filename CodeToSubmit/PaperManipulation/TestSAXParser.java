package javaCodes;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.*;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 
import org.w3c.dom.*;

public class TestSAXParser {
	public static void main(String [] args){
		try{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      Document doc = docBuilder.parse (new File("dblp_db.xml"));
      doc.getDocumentElement ().normalize ();
      System.out.println ("Root element of the doc is " + 
           doc.getDocumentElement().getNodeName());
      
      NodeList listOfAuthors = doc.getElementsByTagName("author");
      int numOfAuthors = countUnique(listOfAuthors);
      System.out.println("Total no of people : " + numOfAuthors);
      
      
		} catch (SAXParseException err) {
      System.out.println ("** Parsing error" + ", line " 
          + err.getLineNumber () + ", uri " + err.getSystemId ());
     System.out.println(" " + err.getMessage ());

     }catch (SAXException e) {
     Exception x = e.getException ();
     ((x == null) ? e : x).printStackTrace ();

     }catch (Throwable t) {
     t.printStackTrace ();
     }
		
		
	}
	
	//Returns the number of unique nodes in a given list, by value
	public static int countUnique(NodeList nl){
		int count = 0;
		Set <String> uniqueNodes = new HashSet <String>();
		for (int i =0;i<nl.getLength();i++){
			uniqueNodes.add(nl.item(i).getNodeName());
		}
	
		return uniqueNodes.size();
	}
	
	public static int countByYear (String tag, int year) {
		int count = 0;
		
		return count;
	}

}
