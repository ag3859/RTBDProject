package project;

// cc MaxTemperatureMapper Mapper for maximum temperature example
// vv MaxTemperatureMapper
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

public class mapperOne
  extends Mapper<LongWritable, Text, IntWritable, Text> {

  private static final int MISSING = 9999;
  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
	  
	  HashMap<String, String> caseMap = new HashMap();	  
	  
	  String record = value.toString();
	  
	  int year = (Integer.parseInt(record.contains("year:")?record.split("year:")[1].split("\t")[0]:"-9999"));
	  String authors = record.contains("author:")?record.split("author:")[1].split("\t")[0]:"";
	  //String editors = record.contains("editor:")?record.split("editor:")[1].split("\t")[0]:"";
	  context.write(new IntWritable(year), new Text(authors/*+"|"+editors*/));		
  } 
}

