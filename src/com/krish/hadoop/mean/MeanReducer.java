package com.krish.hadoop.mean;

import java.io.IOException;
import java.net.InetAddress;
import java.util.StringTokenizer;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MeanReducer extends Reducer<Text, Text, NullWritable, Text> {
	private String delimiter = "|";

	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		// Get the delimiter from job configuration
		delimiter = context.getConfiguration().get("job.mean.delimiter");
	}

	public void reduce(Text paymenttype, Iterable<Text> inputRecords,
			Context context) throws IOException, InterruptedException {
		double sumTotalPrice = 0;
		int countTransactions = 0;
		StringBuilder outputRecord = null;
		StringTokenizer stringTokenizer = null;
		try {
			for (Text inputRecord : inputRecords) {
				outputRecord = new StringBuilder("");
				// Tokenize the input record, parse and assign the variables
				stringTokenizer = new StringTokenizer(inputRecord.toString(),
						delimiter);

				double price = 0d;
				int count = 0;
				if (stringTokenizer.hasMoreElements()) {
					count = Integer.parseInt(stringTokenizer.nextElement()
							.toString());
					price = Double.parseDouble(stringTokenizer.nextElement()
							.toString());
				}
				// Calculate the aggregates
				countTransactions += count;
				sumTotalPrice += price;
			}

			// Construct the output
			outputRecord.append(paymenttype.toString()).append(delimiter);
			outputRecord.append(countTransactions).append(delimiter);
			outputRecord.append(sumTotalPrice).append(delimiter);
			outputRecord.append(sumTotalPrice / countTransactions);

			context.write(NullWritable.get(), new Text(outputRecord.toString()));
		} catch (Exception e) {
			throw new IOException(InetAddress.getLocalHost().getHostName()
					+ ":" + e);
		}
	}
}
