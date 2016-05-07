package com.krish.hadoop.multipleinput;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MultipleInputDriver extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		if (args == null || args.length < 3 || args[0] == null
				|| args[1] == null || args[2] == null) {
			System.out
					.println("Usage: hadoop jar HadoopEssentials.jar com.krish.hadoop.multipleinput.MultipleInputDriver <inputpath1> <inputpath2> <outputpath>");
		}
		int result = ToolRunner.run(new Configuration(),
				new MultipleInputDriver(), args);
		System.exit(result);
	}

	@Override
	public int run(String[] args) throws Exception {
		// Get configuration using getConf() function
		Configuration configuration = getConf();
		// Get the instance of job
		Job job = Job.getInstance(configuration,
				"Hadoop Essentials: Map Reduce (Multiple Inputs)");

		// Set the Driver, mapper, and reducer class
		job.setJarByClass(com.krish.hadoop.multipleinput.MultipleInputDriver.class);
		
		// Multiple Inputs providing different mappers for processing different
		// input files
		MultipleInputs.addInputPath(job, new Path(args[0]),
				TextInputFormat.class,
				com.krish.hadoop.multipleinput.MultipleInputCommaMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]),
				TextInputFormat.class,
				com.krish.hadoop.multipleinput.MultipleInputPipeMapper.class);
		job.setReducerClass(com.krish.hadoop.multipleinput.MultipleInputReducer.class);

		// Set the job Key and Value of Mapper output
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		// Set the job's output Key and Value types
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);

		// Set output file as text file format
		job.setOutputFormatClass(TextOutputFormat.class);

		// Get output location and set it in job
		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		return job.waitForCompletion(true) ? 0 : 1;
	}
}