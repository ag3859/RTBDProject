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

public class mapperFivePart2
  extends Mapper<LongWritable, Text, Text, Text> {
  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
	  
	  String firstYear = value.toString().split("\t")[2];
	  String allYears[] = value.toString().split("\t")[1].split("\\|");
	  String author = value.toString().split("\t")[0];
	  
	  context.write(new Text(firstYear), new Text(author));
	  for(int authorsForEveryYear = 0; authorsForEveryYear<allYears.length; authorsForEveryYear++)
	  {
		  context.write(new Text(allYears[authorsForEveryYear]), new Text("1"));
	  }
		
  } 
}

