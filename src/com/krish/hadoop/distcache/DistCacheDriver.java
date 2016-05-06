package com.krish.hadoop.distcache;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DistCacheDriver extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		if (args == null || args.length < 4 || args[0] == null
				|| args[1] == null || args[2] == null || args[3] == null) {
			System.out
					.println("Usage: hadoop jar HadoopEssentials.jar com.krish.hadoop.distcache.DistCacheDriver <inputpath> <distcachefilepath> <outputpath> <delimiter>");
		}
		int result = ToolRunner.run(new Configuration(), new DistCacheDriver(),
				args);
		System.exit(result);
	}

	@Override
	public int run(String[] args) throws Exception {
		// Get configuration using getConf() function
		Configuration configuration = getConf();
		// Set the delimiter and distributed cache file name as configuration
		// parameters
		configuration.set("job.distcache.delimiter", args[3]);
		configuration.set("job.distcache.cachefile", args[1]);
		// Get the instance of job
		Job job = Job.getInstance(configuration,
				"Hadoop Essentials: Map Join using Distributed Cache");
		// Add distributed cache file to the job
		job.addCacheFile(new URI(args[1]));

		// Set the Driver and mapper class
		job.setJarByClass(com.krish.hadoop.distcache.DistCacheDriver.class);
		job.setMapperClass(com.krish.hadoop.distcache.DistCacheMapper.class);
		// Set number of reducers to 0. This can also be set using -D
		// mapreduce.job.reduces=0 while running job
		job.setNumReduceTasks(0);

		// Set the job's output Key and Value types
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);

		// Set the job Key and Value of Mapper output
		job.setMapOutputKeyClass(NullWritable.class);
		job.setMapOutputValueClass(Text.class);

		// Set input and output file as text file format
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		// Get input and output location and set it in job
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		return job.waitForCompletion(true) ? 0 : 1;
	}
}