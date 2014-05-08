package project;

// cc MaxTemperatureReducer Reducer for maximum temperature example
// vv MaxTemperatureReducer
import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class reducerOne extends Reducer<IntWritable, Text, IntWritable, IntWritable> 
{
	@Override
	public void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		HashSet<String> AE = new HashSet<String>();
		String AuthorsPerPaper[];
		for (Text value : values) 
		{
			if(value.getLength()!=0)
			{
				AuthorsPerPaper = value.toString().split("\\|");
				for(String Author:AuthorsPerPaper)
					AE.add(Author);
			}
		}
		context.write(key, new IntWritable(AE.size()));
	}
}