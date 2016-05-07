package com.krish.hadoop.join;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class JoinDriver extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		if (args == null || args.length < 3 || args[0] == null
				|| args[1] == null || args[2] == null ) {
			System.out
					.println("Usage: hadoop jar HadoopEssentials.jar com.krish.hadoop.join.JoinDriver <inputpath1> <inputpath2> <outputpath>");
		}
		int result = ToolRunner
				.run(new Configuration(), new JoinDriver(), args);
		System.exit(result);
	}

	public int run(String[] args) throws Exception {
		// Get configuration using getConf() function
		Configuration configuration = getConf();
		// Get the instance of job
		Job job = Job.getInstance(configuration,
				"Hadoop Essentials: Hadoop Join");

		job.setJarByClass(com.krish.hadoop.join.JoinDriver.class);
		job.setMapperClass(com.krish.hadoop.join.LineItemsMapper.class);
		job.setMapperClass(com.krish.hadoop.join.PartsMapper.class);
		job.setReducerClass(com.krish.hadoop.join.JoinReducer.class);

		MultipleInputs.addInputPath(job, new Path(args[0]),
				TextInputFormat.class,
				com.krish.hadoop.join.LineItemsMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]),
				TextInputFormat.class, com.krish.hadoop.join.PartsMapper.class);

		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);

		job.setOutputFormatClass(TextOutputFormat.class);

		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		return job.waitForCompletion(true) ? 0 : 1;
	}
}
