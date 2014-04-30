package javaCodes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GetConfJournals {

	static ArrayList<String> ListOfKeys;

	public static void main(String[] args) throws IOException 
	{
		BufferedReader br;
		BufferedWriter bw;
		String line = "";
		String tag = "";
		boolean tagOpen = false;
		br = new BufferedReader(new FileReader("..\\..\\RTBDData\\dblp.xml"));
		bw = new BufferedWriter(new FileWriter("..\\..\\RTBDData\\dblp_conf_journals.xml"));

		readFile();
		while ((line = br.readLine()) != null) 
		{
		//	if(line.contains("conf/balt"))
		//		System.out.println();
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
}
