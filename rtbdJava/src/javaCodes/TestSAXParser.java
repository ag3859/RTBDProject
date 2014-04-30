package javaCodes;

import java.io.File;

import javax.xml.parsers.*;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class TestSAXParser {
	public static void main(String [] args){
		try{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      Document doc = docBuilder.parse (new File("temp"));
      doc.getDocumentElement ().normalize ();
      System.out.println ("Root element of the doc is " + 
           doc.getDocumentElement().getNodeName());
      NodeList listOfAuthors = doc.getElementsByTagName("author");
      int numOfAuthors = listOfAuthors.getLength();
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
	

}
