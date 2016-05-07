package com.krish.hadoop.mapcombinereduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MapReduceDriver extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		if (args == null || args.length < 3 || args[0] == null
				|| args[1] == null || args[2] == null) {
			System.out
					.println("Usage: hadoop jar HadoopEssentials.jar com.krish.hadoop.mapcombinereduce.MapReduceDriver <inputpath> <outputpath> <delimiter>");
		}
		int result = ToolRunner.run(new Configuration(), new MapReduceDriver(),
				args);
		System.exit(result);
	}

	@Override
	public int run(String[] args) throws Exception {
		// Get configuration using getConf() function
		Configuration configuration = getConf();
		// Set the delimiter as configuration parameter
		configuration.set("job.mapcombinereduce.delimiter", args[2]);
		// Get the instance of job
		Job job = Job.getInstance(configuration,
				"Hadoop Essentials: Map Reduce (Aggregation using combiner)");
		
		// Set the Driver, mapper and reducer class
		job.setJarByClass(com.krish.hadoop.mapcombinereduce.MapReduceDriver.class);
		job.setMapperClass(com.krish.hadoop.mapcombinereduce.MapReduceMapper.class);
		job.setReducerClass(com.krish.hadoop.mapcombinereduce.MapReduceReducer.class);

		// Set the job's output Key and Value types
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		// Set input and output file as text file format
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		// Set the job Key and Value of Mapper output
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		// Get input and output location and set it in job
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		return job.waitForCompletion(true) ? 0 : 1;
	}
}