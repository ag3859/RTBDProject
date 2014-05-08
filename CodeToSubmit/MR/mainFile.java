package project;

// cc MaxTemperature Application to find the maximum temperature in the weather dataset
// vv MaxTemperature
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.FileSystem;

public class mainFile {

  public static void main(String[] args) throws Exception {
    /*if (args.length != 2) {
      System.err.println("Usage: PageRank <input path> <output path>");
      System.exit(-1);
    }*/
    
    Configuration config = new Configuration();
    
    /*Path outPath = new Path(args[1]);
    
    FileSystem dfs = FileSystem.get(outPath.toUri(), config);
    if (dfs.exists(outPath)) {
    dfs.delete(outPath, true);
    }
    
    outPath = new Path(args[1]+"1");
    
    dfs = FileSystem.get(outPath.toUri(), config);
    if (dfs.exists(outPath)) {
    dfs.delete(outPath, true);
    }*/
    
    
    Job job;
    for (int tier=0; tier<5; tier++)  
    {
    	/*    	
 		job = new Job();

    	job.setJarByClass(mainFile.class);
    	job.setJobName("Authors per year");

    	FileInputFormat.addInputPath(job, new Path(args[tier]));
    	FileOutputFormat.setOutputPath(job, new Path("Authors per year "+tier));

    	job.setMapperClass(mapperOne.class);
    	job.setReducerClass(reducerOne.class);

    	job.setOutputKeyClass(IntWritable.class);
    	job.setOutputValueClass(Text.class);

    	job.waitForCompletion(true);


    	job = new Job();
    	job.setJarByClass(mainFile.class);
    	job.setJobName("Average number of papers per author in that year");

    	FileInputFormat.addInputPath(job, new Path(args[tier]));
    	FileOutputFormat.setOutputPath(job, new Path("Average number of papers per author in that year "+tier));

    	job.setMapperClass(mapperTwo.class);
    	job.setReducerClass(reducerTwo.class);

    	job.setOutputKeyClass(IntWritable.class);
    	job.setOutputValueClass(Text.class);

    	job.waitForCompletion(true);


    	job = new Job();
    	job.setJarByClass(mainFile.class);
    	job.setJobName("Average number of collaborators per author in that year");

    	FileInputFormat.addInputPath(job, new Path(args[tier]));
    	FileOutputFormat.setOutputPath(job, new Path("Average number of collaborators per author in that year "+tier));

    	job.setMapperClass(mapperThree.class);
    	job.setReducerClass(reducerThree.class);

    	job.setOutputKeyClass(IntWritable.class);
    	job.setOutputValueClass(Text.class);

    	job.waitForCompletion(true);
    	
    	
    	job = new Job();
    	job.setJarByClass(mainFile.class);
    	job.setJobName("Number of Single Authored papers every year");

    	FileInputFormat.addInputPath(job, new Path(args[tier]));
    	FileOutputFormat.setOutputPath(job, new Path("Number of Single Authored papers every year "+tier));

    	job.setMapperClass(mapperFour.class);
    	job.setReducerClass(reducerFour.class);

    	job.setOutputKeyClass(IntWritable.class);
    	job.setOutputValueClass(Text.class);

    	job.waitForCompletion(true);
    	*/
    	
    	job = new Job();
    	job.setJarByClass(mainFile.class);
    	job.setJobName("Number of new authors every year");

    	FileInputFormat.addInputPath(job, new Path(args[tier]));
    	FileOutputFormat.setOutputPath(job, new Path("Number of new authors every year "+tier));

    	job.setMapperClass(mapperFivePart1.class);
    	job.setReducerClass(reducerFivePart1.class);

    	job.setOutputKeyClass(Text.class);
    	job.setOutputValueClass(IntWritable.class);

    	job.waitForCompletion(true);

    	job = new Job();
    	job.setJarByClass(mainFile.class);
    	job.setJobName("Number of new authors every year final");

    	FileInputFormat.addInputPath(job, new Path("Number of new authors every year "+tier+"/part-r-00000"));
    	FileOutputFormat.setOutputPath(job, new Path("Number of new authors every year final "+tier));

    	job.setMapperClass(mapperFivePart2.class);
    	job.setReducerClass(reducerFivePart2.class);

    	job.setOutputKeyClass(Text.class);
    	job.setOutputValueClass(Text.class);

    	job.waitForCompletion(true);
    	
    }
  }
}