package project;

// cc MaxTemperatureReducer Reducer for maximum temperature example
// vv MaxTemperatureReducer
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class reducerFivePart2 extends Reducer<Text, Text, Text, FloatWritable> 
{
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		float numOfAuthors = 0;
		float numOfNewAuthors = 0;
		//HashSet<String> authors = new HashSet<String>();
		for (Text value : values) 
		{
			numOfAuthors++;
			if(!value.toString().equalsIgnoreCase("1"))
				numOfNewAuthors++;
			//authors.add(value.toString());
		}
		/*if(authors.size()!=numOfAuthors)
			System.out.println();*/
		context.write(key, new FloatWritable(numOfAuthors/numOfNewAuthors));
		//context.write(key, new Text(allYears+"    "+firstYear));
	}
}