package project;

// cc MaxTemperatureReducer Reducer for maximum temperature example
// vv MaxTemperatureReducer
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class reducerFour extends Reducer<IntWritable, Text, IntWritable, FloatWritable> 
{
	@Override
	public void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		String AuthorsPerPaper[];		
		float numOfSingleAuthor = 0;
		float totalPapers = 0;
		for (Text value : values) 
		{
			if(value.getLength()!=0)
			{
				AuthorsPerPaper = value.toString().split("\\|");
				if(AuthorsPerPaper.length==1)
				{
					numOfSingleAuthor++;
					totalPapers++;
				}
				else if(AuthorsPerPaper.length>=1)
					totalPapers++;
			}
		}
		context.write(key, new FloatWritable(numOfSingleAuthor/totalPapers));
	}
}