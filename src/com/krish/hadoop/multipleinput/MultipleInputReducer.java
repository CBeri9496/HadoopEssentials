package com.krish.hadoop.multipleinput;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MultipleInputReducer extends Reducer<Text, Text, NullWritable, Text> {
	private String delimiter = "|";
	
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

			double deviation = 0d, variance = 0d, standarddeviation = 0d;
			for (String price : inputRecordsCache) {
				// Calculate the deviation
				deviation += Math.pow(Double.parseDouble(price)
						- mean, 2);
			}
			// Calculate the variance and standarddeviation
			variance = deviation / countTransactions;
			standarddeviation = Math.sqrt(variance);

			outputRecord = new StringBuilder("");
			// Construct the output
			outputRecord.append(paymenttype.toString()).append(delimiter);
			outputRecord.append(countTransactions).append(delimiter);
			outputRecord.append(sumTotalPrice).append(delimiter);
			outputRecord.append(mean).append(delimiter);
			outputRecord.append(deviation).append(delimiter);
			outputRecord.append(variance).append(delimiter);
			outputRecord.append(standarddeviation);

			context.write(NullWritable.get(), new Text(outputRecord.toString()));
		} catch (Exception e) {
			throw new IOException(InetAddress.getLocalHost().getHostName()
					+ ":" + e);
		}
	}
}
