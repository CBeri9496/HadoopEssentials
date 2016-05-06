package com.krish.hadoop.mapcombinereduce;

import java.io.IOException;
import java.net.InetAddress;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MapReduceReducer extends Reducer<Text, Text, Text, Text> {
	private String delimiter = "|";

	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		// Get the delimiter from job configuration
		delimiter = context.getConfiguration().get(
				"job.mapcombinereduce.delimiter");
	}

	public void reduce(Text paymenttype, Iterable<Text> inputRecords,
			Context context) throws IOException, InterruptedException {
		int minQuantity = Integer.MAX_VALUE;
		double maxUnitPrice = Double.MIN_VALUE;
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

				double unitPrice = 0d, price = 0d;
				int quantity = 0, count = 0;
				if (stringTokenizer.hasMoreElements()) {
					count = Integer.parseInt(stringTokenizer.nextElement()
							.toString());
					price = Double.parseDouble(stringTokenizer.nextElement()
							.toString());
					unitPrice = Double.parseDouble(stringTokenizer
							.nextElement().toString());
					quantity = Integer.parseInt(stringTokenizer.nextElement()
							.toString());
				}

				// Calculate the aggregrates
				countTransactions += count;
				sumTotalPrice += price;
				maxUnitPrice = maxUnitPrice < unitPrice ? unitPrice
						: maxUnitPrice;
				minQuantity = minQuantity > quantity ? quantity : minQuantity;
			}

			// Construct the output
			outputRecord.append(countTransactions).append(delimiter);
			outputRecord.append(sumTotalPrice).append(delimiter);
			outputRecord.append(maxUnitPrice).append(delimiter);
			outputRecord.append(minQuantity);

			context.write(paymenttype, new Text(outputRecord.toString()));
		} catch (Exception e) {
			throw new IOException(InetAddress.getLocalHost().getHostName()
					+ ":" + e);
		}
	}
}
