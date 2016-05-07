package com.krish.hadoop.counter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CounterDriver extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		if (args == null || args.length < 3 || args[0] == null
				|| args[1] == null || args[2] == null) {
			System.out
					.println("Usage: hadoop jar HadoopEssentials.jar com.krish.hadoop.counter.CounterDriver <inputpath> <outputpath> <delimiter>");
		}
		int result = ToolRunner.run(new Configuration(), new CounterDriver(),
				args);
		System.exit(result);
	}

	@Override
	public int run(String[] args) throws Exception {
		// Get configuration using getConf() function
		Configuration configuration = getConf();
		// Set the delimiter as configuration parameter
		configuration.set("job.counter.delimiter", args[2]);
		// Get the instance of job
		Job job = Job.getInstance(configuration,
				"Hadoop Essentials: Map Reduce (Counters)");

		// Set the Driver, mapper, combiner and reducer class
		job.setJarByClass(com.krish.hadoop.counter.CounterDriver.class);
		job.setMapperClass(com.krish.hadoop.counter.CounterMapper.class);
		job.setReducerClass(com.krish.hadoop.counter.CounterReducer.class);

		// Set the job Key and Value of Mapper output
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		// Set the job's output Key and Value types
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);

		// Set input and output file as text file format
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		// Get input and output location and set it in job
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		int returnCode = job.waitForCompletion(true) ? 0 : 1;

		System.out.println("\n*************** USER COUNTERS *****************");		
		Counters counters = job.getCounters();
		Counter c1 = counters.findCounter(CounterEnum.PRICE_LESS_25);
		System.out.println(c1.getDisplayName() + " = " + c1.getValue());
		c1 = counters.findCounter(CounterEnum.PRICE_MORE_25_LESS_100);
		System.out.println(c1.getDisplayName() + " = " + c1.getValue());
		c1 = counters.findCounter(CounterEnum.PRICE_MORE_100);
		System.out.println(c1.getDisplayName() + " = " + c1.getValue());
		c1 = counters.findCounter(CounterEnum.QTY_LESS_10);
		System.out.println(c1.getDisplayName() + " = " + c1.getValue());
		c1 = counters.findCounter(CounterEnum.QTY_MORE_10);
		System.out.println(c1.getDisplayName() + " = " + c1.getValue());
		c1 = counters.findCounter(CounterEnum.TOTALPRICE_LESS_AVERAGE);
		System.out.println(c1.getDisplayName() + " = " + c1.getValue());
		c1 = counters
				.findCounter(CounterEnum.TOTALPRICE_MORE_AVERAGE_LESS_DOUBLE_AVERAGE);
		System.out.println(c1.getDisplayName() + " = " + c1.getValue());
		c1 = counters.findCounter(CounterEnum.TOTALPRICE_MORE_DOUBLE_AVERAGE);
		System.out.println(c1.getDisplayName() + " = " + c1.getValue());
		System.out.println("*********************************************");
		
		return returnCode;
	}
}