package project;

// cc MaxTemperatureReducer Reducer for maximum temperature example
// vv MaxTemperatureReducer
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class reducerFivePart1 extends Reducer<Text, IntWritable, Text, Text> 
{
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		IntWritable firstYear = new IntWritable(Integer.MAX_VALUE);
		String allYears = "";
		for (IntWritable value : values) 
		{
			if((value.get() - firstYear.get()) < 0)
			{
				firstYear.set(value.get());
			}
				allYears = allYears +"|"+ value.toString();
		}
		//context.write(key, firstYear);
		context.write(key, new Text(allYears+"\t"+firstYear));
	}
}