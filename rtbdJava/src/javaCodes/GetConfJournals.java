package javaCodes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GetConfJournals {

	static ArrayList<String> ListOfKeys;

	public static void main(String[] args) throws IOException 
	{
		//Read the filter file, only the conferences we are interested in. Filter file name coded as dbconf.txt
		readFile();
		//Extract relevant records based on the filter file. input file name coded as dblp.xml
		cleanFile();
		//flatten the XML records to a text file for use in map reduce, appending xml tags as labels
		flatten();
	}

	static void readFile() {
		BufferedReader Keys = null;
		String key;
		try {
			Keys = new BufferedReader(new FileReader("..\\..\\RTBDData\\dbconf.txt"));
			ListOfKeys = new ArrayList<String>();
			while ((key = Keys.readLine()) != null) 
			{
				ListOfKeys.add(key);
			}
			Keys.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	static void cleanFile() throws IOException
	{
		String line = "";
		String tag = "";
		boolean tagOpen = false;
		BufferedReader br;
		BufferedWriter bw;
		br = new BufferedReader(new FileReader("..\\..\\RTBDData\\dblp.xml"));
		bw = new BufferedWriter(new FileWriter("..\\..\\RTBDData\\dblp_conf_journals.xml"));

		while ((line = br.readLine()) != null) 
		{
			if (tagOpen && line.contains("</")&& (line.contains("</"+tag)))
			{
				tagOpen = false;
				bw.write(line);
				bw.newLine();
				bw.newLine();
			} 
			else if (tagOpen) 
			{
				bw.write(line);
				bw.newLine();
			}
			else if (line.contains("key=\"conf")|| line.contains("key=\"journals")) 
			{
				if (SpecificConfJourn(line)) 
				{
					tagOpen = true;
					bw.write(line);
					bw.newLine();
					tag = line.split(" ")[0].split("<")[1];
				}
			}
		}
		br.close();
		bw.close();

	}
	
	static boolean SpecificConfJourn(String line) throws IOException 
	{
		for (int index = 0; index < ListOfKeys.size(); index++) 
		{
			if (line.contains(ListOfKeys.get(index))) 
			{
				return true;
			}
		}
		return false;
	}
	
	static void flatten() throws IOException
	{
		BufferedReader br;
		BufferedWriter bw;
		br = new BufferedReader(new FileReader("..\\..\\RTBDData\\dblp_conf_journals.xml"));
		bw = new BufferedWriter(new FileWriter("..\\..\\RTBDData\\dblp_conf_journals_flattened.txt"));
		
		String line = "";
		String recordTag = "";
		String entryTag = "";
		String previousTag = "";
		boolean tagOpen = false;
		
		while ((line = br.readLine()) != null) 
		{
			if(line.length()>0)
			{
				if (tagOpen && line.contains("</"+recordTag))
				{
					tagOpen = false;				
					bw.newLine();
				} 
				else if (tagOpen) 
				{	
					String text = "";
					if((line.charAt(0)=='<')&&(line.charAt(line.length()-1)=='>'))
					{
						previousTag = entryTag;
						entryTag = line.split(">")[0].split("<")[1];
						text = line.split(">")[1].split("<")[0] ;
						if(!previousTag.equals(entryTag))
							bw.write("\t"+entryTag+":"+text);
						else
							bw.write("|"+text);
					}
					else if (line.charAt(0)=='<')
					{
						previousTag = entryTag;
						entryTag = line.split(">")[0].split("<")[1];
						text = line.split(">")[1];
						if(!previousTag.equals(entryTag))
							bw.write("\t"+entryTag+":"+text);
						else
							bw.write("|"+text);
					}
					else if (line.charAt(line.length()-1)=='>')
					{
						text = line.split("<")[0]; 
						bw.write(text);
					}									
				}
				else if (line.contains("key")) 
				{				
						tagOpen = true;					
						recordTag = line.split(" ")[0].split("<")[1];
						bw.write(recordTag+"\t");	
				}
			}
		}
		br.close();
		bw.close();
	}
}
