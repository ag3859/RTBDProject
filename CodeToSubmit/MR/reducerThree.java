package project;

// cc MaxTemperatureReducer Reducer for maximum temperature example
// vv MaxTemperatureReducer
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class reducerThree extends Reducer<IntWritable, Text, IntWritable, FloatWritable> 
{
	@Override
	public void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		String AuthorsPerPaper[];
		int numOfCollaborators = 0;
		int numOfAuthors = 0;
		for (Text value : values) 
		{
			if(value.getLength()!=0)
			{
				AuthorsPerPaper = value.toString().split("\\|");
				numOfCollaborators=numOfCollaborators+AuthorsPerPaper.length*(AuthorsPerPaper.length-1);
				numOfAuthors=numOfAuthors+AuthorsPerPaper.length;
			}
		}
		context.write(key, new FloatWritable((float)numOfCollaborators/numOfAuthors));
	}
}