package javaCodes;

import java.io.File;
import java.util.HashSet;

import javax.xml.parsers.*;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class TestSAXParser {
	public static void main(String[] args) {
		HashSet<String> Authors = new HashSet<String>();
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("dblp_db.xml"));
			doc.getDocumentElement().normalize();
			System.out.println("Root element of the doc is "
					+ doc.getDocumentElement().getNodeName());

			NodeList listOfAuthors = doc.getElementsByTagName("author");
			int numOfAuthors = listOfAuthors.getLength();
			System.out.println("Total no of people : " + numOfAuthors);
			
			for(int index = 0; index<numOfAuthors; index++)
				Authors.add(listOfAuthors.item(index).getTextContent());
			
			System.out.println(Authors.size());
			//listOfAuthors.item(0).getTextContent()
			

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	public static int countByYear(String tag, int year) {
		int count = 0;

		return count;
	}

}
