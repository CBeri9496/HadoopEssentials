package com.krish.hadoop.counter;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CounterReducer extends Reducer<Text, Text, NullWritable, Text> {
	private String delimiter = "|";

	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		// Get the delimiter from job configuration
		delimiter = context.getConfiguration().get("job.counter.delimiter");
	}

	public void reduce(Text paymenttype, Iterable<Text> inputRecords,
			Context context) throws IOException, InterruptedException {
		double sumTotalPrice = 0d, mean = 0d;
		int countTransactions = 0;
		StringBuilder outputRecord = null;
		List<String> inputRecordsCache = new ArrayList<String>();
		try {
			for (Text price : inputRecords) {
				// Calculate the aggregates
				countTransactions++;
				sumTotalPrice += Double.parseDouble(price.toString());
				inputRecordsCache.add(price.toString());
			}
			// Calculate the aggregates
			mean = sumTotalPrice / countTransactions;

			// Check and increment the Counters
			Double temp = 0d;
			for (String price : inputRecordsCache) {
				temp = Double.parseDouble(price);
				if (temp < mean) {
					context.getCounter(CounterEnum.TOTALPRICE_LESS_AVERAGE)
							.increment(1);
				} else if (temp > mean && temp < 2 * mean) {
					context.getCounter(
							CounterEnum.TOTALPRICE_MORE_AVERAGE_LESS_DOUBLE_AVERAGE)
							.increment(1);
				} else {
					context.getCounter(CounterEnum.TOTALPRICE_MORE_DOUBLE_AVERAGE)
							.increment(1);
				}
			}
			outputRecord = new StringBuilder("");
			// Construct the output
			outputRecord.append(paymenttype.toString()).append(delimiter);
			outputRecord.append(countTransactions).append(delimiter);
			outputRecord.append(mean);

			context.write(NullWritable.get(), new Text(outputRecord.toString()));
		} catch (Exception e) {
			throw new IOException(InetAddress.getLocalHost().getHostName()
					+ ":" + e);
		}
	}
}
