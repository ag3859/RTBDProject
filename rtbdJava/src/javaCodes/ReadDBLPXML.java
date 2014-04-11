package javaCodes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadDBLPXML {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new FileReader("..\\..\\RTBDData\\oaiharvesterdemo\\citeseerx_alldata.xml"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("..\\..\\RTBDData\\oaiharvesterdemo\\citeseerx_alldata_small.xml"));
		for (int i =0;i<10000; i++)
		{
			bw.write(br.readLine());
			bw.newLine();
		}
		br.close();bw.close();
	}
}
