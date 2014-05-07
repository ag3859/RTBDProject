package javaCodes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadCiteSeerXML {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		int count=0;
		int indexOfLastRecord=0;
		int lineNumber = 0;
		BufferedReader br;
		BufferedWriter bw;
		String line;
		int i;
		
		//remove all lines not relevant for xml parsing later
		br = new BufferedReader(new FileReader("..\\..\\RTBDData\\oaiharvesterdemo\\citeseerx_alldata.xml"));
		bw = new BufferedWriter(new FileWriter("..\\..\\RTBDData\\oaiharvesterdemo\\citeseerx_alldata_clean.xml"));
		line = br.readLine();
		while(line!=null)
		{
//			count++;
			if(line.contains("<dc:creator>"))
			{
				bw.write(line.replace("<dc:creator>", "<creator>").replace("</dc:creator>", "</creator>"));			
				bw.newLine();
				lineNumber++;
			}
			else if(line.contains("<ListRecords>")||line.contains("<record>")||line.contains("<metadata>")||line.contains("</ListRecords>")||line.contains("</metadata>"))
			{	
				bw.write(line);			
				bw.newLine();
				lineNumber++;
			}
			else if(line.contains("</record>"))
			{
				bw.write(line);
				bw.newLine();
				lineNumber++;
				indexOfLastRecord = lineNumber; 
			}
			line = br.readLine();
		}
		br.close();bw.close();
		/*count = 62561761;
		lineNumber = 16916120;
		indexOfLastRecord = 16916119;
		System.out.println(count);
		System.out.println(lineNumber);
		System.out.println(indexOfLastRecord);*/		
		
		
		//make sure that the new xml created is a valid xml
		br = new BufferedReader(new FileReader("..\\..\\RTBDData\\oaiharvesterdemo\\citeseerx_alldata_clean.xml"));
		bw = new BufferedWriter(new FileWriter("..\\..\\RTBDData\\oaiharvesterdemo\\citeseerx_alldata_formatted.xml"));
		for (i =0;i<indexOfLastRecord;i++)
		{
			bw.write(br.readLine());
			bw.newLine();
		}
		bw.write("</ListRecords>");
		bw.newLine();
		br.close(); bw.close();
		
		
		//cut out a small section, top 10k lines, just to view
		br = new BufferedReader(new FileReader("..\\..\\RTBDData\\oaiharvesterdemo\\citeseerx_alldata_formatted.xml"));
		bw = new BufferedWriter(new FileWriter("..\\..\\RTBDData\\oaiharvesterdemo\\citeseerx_alldata_small.xml"));
		
		for (i =0;i<10000; i++)
		{
			bw.write(br.readLine());
			bw.newLine();
		}
		br.close(); bw.close();
					
		/*for (;i<indexOfLastRecord;i++)
		{
			br.readLine();
		}
		line=br.readLine();
		while(line!=null)
		{			
			System.out.println(line);
			line = br.readLine();			
		}		
		br.close();*/
	}
}
