package javaCodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class TestJdomParser {
	Document xml = null;
	Element root = null;
	Set <Element> tier1Authors=new HashSet <Element>();
	Set <Element> tier2Authors=new HashSet <Element>();
	Set <Element> tier3Authors=new HashSet <Element>();
	Set <Element> tier4Authors=new HashSet <Element>();
	
	
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
     
     t.computeJaccard();
	}
	
	public void computeJaccard(){
		HashMap <String, HashSet<String>> authconf = new HashMap <String, HashSet<String>>();
		String conf;
		List <String> conflist = new ArrayList <String> ();
		for (Element e : root.getChildren()){ //down to the inproceedings
			conf = e.getAttributeValue("key").split("/")[1];
			if (!conflist.contains(conf)){
				authconf.put(conf, new HashSet <String>());
				conflist.add(conf);
			}
			for (Element a : e.getChildren("author")){
			 authconf.get(conf).add(a.getText());
			}
			
		}
		//printHashMap(authconf);s
		for (String l:conflist){
			System.out.println(l);
		}
		
		int size = conflist.size();
		Double [][] jcd = new Double[size][size];
		int k1=0, k2=0;
		
		for (String i : conflist){
			for (String j : conflist){
				Set <String> union = new HashSet<String>(authconf.get(i));
				union.addAll(authconf.get(j));
				Set <String> intersection = new HashSet<String>(authconf.get(i));
				intersection.retainAll(authconf.get(j));
				jcd[k1][k2] = (double)intersection.size()/(double)union.size();
				k2++;
			}
			k1++;
			k2=0;
		}
		/*
		for (int i =0;i<size;i++){
			for (int j=0;j<size;j++){
				System.out.print(jcd[i][j]+" ");
			}
			System.out.println();
		}
		*/
		
	}
	
	private void printHashMap(HashMap <String, HashSet<String>> hm){
		Iterator it = hm.entrySet().iterator();
		try{
		PrintWriter writer = new PrintWriter("auth_conf.txt", "UTF-8");
		while (it.hasNext()) {
		    Map.Entry entry = (Map.Entry) it.next();
		    String key = (String)entry.getKey();
		    HashSet val = (HashSet)entry.getValue();
		    writer.println(key + "," + val);
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void textForHiveMR(){
		try {
			PrintWriter writer = new PrintWriter("dblp_hive.txt", "UTF-8");
			StringBuffer line = new StringBuffer();
			String key="", year="";
			ArrayList <String> authors = new ArrayList <String> ();
			for (Element e : root.getChildren()){
				key = e.getAttributeValue("key");
				year = e.getChild("year").getText();
				for (Element a : e.getChildren("author")){
					authors.add(a.getText());
				}
				line.append(key+","+year+",");
				
				if (authors.size()>0){
					line.append(authors.get(0));
					for (int i =1;i<authors.size();i++){
						line.append("|"+authors.get(i));
					}
				}
				System.out.println(line.toString());
				writer.println(line.toString());
				line.setLength(0);
				authors.clear();
			}
			
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
	public void textForHiveMRwithConf(){
		try {
			PrintWriter writer = new PrintWriter("dblp_hive_conf.txt", "UTF-8");
			StringBuffer line = new StringBuffer();
			String key="", year="", conf="";
			ArrayList <String> authors = new ArrayList <String> ();
			for (Element e : root.getChildren()){
				key = e.getAttributeValue("key");
				conf = key.split("/")[1];
				year = e.getChild("year").getText();
				for (Element a : e.getChildren("author")){
					authors.add(a.getText());
				}
				line.append(key+"\t"+year+"\t"+conf+"\t");
				
				if (authors.size()>0){
					line.append(authors.get(0));
					for (int i =1;i<authors.size();i++){
						line.append(","+authors.get(i));
					}
				}
				System.out.println(line.toString());
				writer.println(line.toString());
				line.setLength(0);
				authors.clear();
			}
			
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	public void textForHiveMRExploded(){
		try {
			PrintWriter writer = new PrintWriter("dblp_hive_exp.txt", "UTF-8");
			StringBuffer line = new StringBuffer();
			String key="", year="", conf="";
			ArrayList <String> authors = new ArrayList <String> ();
			for (Element e : root.getChildren()){
				key = e.getAttributeValue("key");
				conf = key.split("/")[1];
				year = e.getChild("year").getText();
				for (Element a : e.getChildren("author")){
					authors.add(a.getText());
				}
				line.append(key+"\t"+year+"\t"+conf+"\t");
				String temp = line.toString();
				if (authors.size()>0){
					for (int i =0;i<authors.size();i++){
						String temp1 = temp + authors.get(i); 
					
//						line.append(","+authors.get(i));
						System.out.println(temp1);
						writer.println(temp1);
					}
				}
				
				line.setLength(0);
				authors.clear();
			}
			
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	
	 // Used to get the list of Tags in the xml, only interested in those for the first two levels
	 public void getListofTags(){
		 Set listOfTags1 = new HashSet<String> ();
		 Set listOfTags2 = new HashSet<String> ();
		 Set listOfTags3 = new HashSet<String> ();
		 for (Element e : root.getChildren()){
			 listOfTags1.add(e.getName());
			 for (Element sub_e : e.getChildren()){
				 listOfTags2.add(sub_e.getName());
			 }
		 }
		 for (Object s : listOfTags1){
			 System.out.println(s);
		 }
		 System.out.println("-----------");
		 for (Object s : listOfTags2){
			 System.out.println(s);
		 }
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
