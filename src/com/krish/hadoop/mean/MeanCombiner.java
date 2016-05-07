package com.krish.hadoop.mean;

import java.io.IOException;
import java.net.InetAddress;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MeanCombiner extends Reducer<Text, Text, Text, Text> {
	private String delimiter = "|";

	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		// Get the delimiter from job configuration
		delimiter = context.getConfiguration().get(
				"job.mean.delimiter");
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
			outputRecord.append(countTransactions).append(delimiter);
			outputRecord.append(sumTotalPrice);

			context.write(paymenttype, new Text(outputRecord.toString()));
		} catch (Exception e) {
			throw new IOException(InetAddress.getLocalHost().getHostName()
					+ ":" + e);
		}
	}
}
