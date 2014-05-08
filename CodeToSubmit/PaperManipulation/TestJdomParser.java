package javaCodes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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
	HashMap <String, HashSet<String>> authconf = new HashMap <String, HashSet<String>>();
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
		
		String conf;
		String [] t3= {
		           "adc","bncod","comad","dawak","ideas",
				"mda",/*"mda/mdm","adbis","mdm",*/"ride","sbbd","vdb"
		};
		String [] t1 = {"icde",
				"icdm",
				"pods",
				"sigmod",
				"vldb",
				/*"sigkdd"*/};
		String [] t2 = {"cidr","cikm","coopis","dasfaa","dbsec","dexa","dood","edbt",
				"fodo","icdt","ida","pakdd","pkdd","sdm",/*"siam"*/"ssdbm","wise"};
		String [] t4 = {"artdb","cdb","dmdw","dmkd","dolap","fqas","ideal",
				"krdb",
				"nldb",
				"oodbs",
				"oois",
				"rtdb",
				"webdb",
				"widm",
				/*"dblp","efis/efdbs","efis","efdbs","iw-mmdbms","kr",*/};
		
		String [] tAll = new String[0];
		
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
		//printHashMap(authconf);
		
		/*
		 * code to clean out conferences not in the database
		for (int i =0;i<t4.length;i++){
			if (!conflist.contains(t4[i])){
				System.out.println("remove "+ t4[i]);
			}
		}
		*/
		tAll = concat(tAll,t1);
		tAll = concat(tAll,t2);
		tAll = concat(tAll,t3);
		tAll = concat(tAll,t4);
		
		for (int i =0;i<tAll.length;i++){
			System.out.println(tAll[i]);
		}
		
		
	 JaccardComputeWorker(t1,"JaccardT1.txt");
	 JaccardComputeWorker(t2,"JaccardT2.txt");
		JaccardComputeWorker(t3,"JaccardT3.txt");
		JaccardComputeWorker(t4,"JaccardT4.txt");
		JaccardComputeWorker(tAll,"JaccardAll.txt");
	}
	
	/**Concat 2 strings
	 */
	public String[] concat(String[] first, String[] second) {
	  String[] result = Arrays.copyOf(first, first.length + second.length);
	  System.arraycopy(second, 0, result, first.length, second.length);
	  return result;
	}
	
	public void JaccardComputeWorker(String [] conflist, String fname){
		int size = conflist.length;
		System.out.println("size ="+size);
		Double [][] jcd = new Double[size][size];
		for (int i =0;i<size;i++){
			for  (int j =0;j<size;j++){
				String conf1 = conflist[i];
				String conf2 = conflist[j];
				Set <String> union = new HashSet <String>(authconf.get(conf1));
				union.addAll(authconf.get(conf2));
				Set <String> intersection = new HashSet <String>(authconf.get(conf1));
				intersection.retainAll(authconf.get(conf2));
				jcd[i][j]= (double)intersection.size()/(double)union.size();
				if (jcd[i][j]==1){
					jcd[i][j]= 0.2;
				}
			}
		}
		BufferedWriter outputWriter = null;
	  try {
			outputWriter = new BufferedWriter(new FileWriter(fname));
			for (int i=0;i<size;i++){
				outputWriter.write("\""+conflist[i]+"\""+",");
			}
			outputWriter.newLine();
	  for (int i = 0; i < size; i++) {
	  	outputWriter.write("[");
	  	for (int j =0;j < size;j++){
	  		outputWriter.write(jcd[i][j]+",");
	  	}
	  	outputWriter.write("],");
	    outputWriter.newLine();
	  	}
	  	outputWriter.flush();  
	  	outputWriter.close(); 
	  } catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
		    System.out.println(key + "," + val.size());
//		    writer.println(key);
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
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
