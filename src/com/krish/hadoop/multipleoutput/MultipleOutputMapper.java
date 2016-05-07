package com.krish.hadoop.multipleoutput;

import java.io.IOException;
import java.net.InetAddress;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class MultipleOutputMapper extends
		Mapper<LongWritable, Text, NullWritable, Text> {
	private Text processedRecord = new Text();

	@SuppressWarnings("unused")
	private String transactiondate, paymenttype, cardnumber, firstname,
			lastname, phone, email, address, city, state, country,
			suppliername, supplierphone, supplieremail, categorybrand,
			categorytype;
	@SuppressWarnings("unused")
	private int productid, quantity, categoryid, supplierid, customerid, zip;
	private long transactionid;
	private double unitprice, totalprice;

	private MultipleOutputs<NullWritable, Text> multipleOutputs;
	private String delimiter = "|";

	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		// Initialize multiple outputs
		multipleOutputs = new MultipleOutputs<NullWritable, Text>(context);
		// Get the delimiter from job configuration
		delimiter = context.getConfiguration().get("job.maponly.delimiter");
	}

	public void map(LongWritable byteOffset, Text inputRecord, Context context)
			throws IOException, InterruptedException {
		StringTokenizer stringTokenizer = null;
		StringBuilder outputRecord = null;
		try {
			outputRecord = new StringBuilder("");

			// Tokenize the input record, parse and assign the variables
			stringTokenizer = new StringTokenizer(inputRecord.toString(), ",");
			while (stringTokenizer.hasMoreElements()) {

				transactionid = Long.parseLong(stringTokenizer.nextElement()
						.toString());
				transactiondate = stringTokenizer.nextElement().toString();
				productid = Integer.parseInt(stringTokenizer.nextElement()
						.toString());
				unitprice = Double.parseDouble(stringTokenizer.nextElement()
						.toString());
				paymenttype = stringTokenizer.nextElement().toString();
				cardnumber = stringTokenizer.nextElement().toString();
				quantity = Integer.parseInt(stringTokenizer.nextElement()
						.toString());
				categoryid = Integer.parseInt(stringTokenizer.nextElement()
						.toString());
				supplierid = Integer.parseInt(stringTokenizer.nextElement()
						.toString());
				customerid = Integer.parseInt(stringTokenizer.nextElement()
						.toString());
				firstname = stringTokenizer.nextElement().toString();
				lastname = stringTokenizer.nextElement().toString();
				phone = stringTokenizer.nextElement().toString();
				email = stringTokenizer.nextElement().toString();
				address = stringTokenizer.nextElement().toString();
				city = stringTokenizer.nextElement().toString();
				state = stringTokenizer.nextElement().toString();
				zip = Integer
						.parseInt(stringTokenizer.nextElement().toString());
				country = stringTokenizer.nextElement().toString();
				suppliername = stringTokenizer.nextElement().toString();
				supplierphone = stringTokenizer.nextElement().toString();
				supplieremail = stringTokenizer.nextElement().toString();
				categorybrand = stringTokenizer.nextElement().toString();
				categorytype = stringTokenizer.nextElement().toString();

				// Calculate the total price
				totalprice = unitprice * quantity;

				// Construct the output for mapper
				outputRecord.append(transactionid).append(delimiter);
				outputRecord.append(transactiondate).append(delimiter);
				outputRecord.append(productid).append(delimiter);
				outputRecord.append(unitprice).append(delimiter);
				outputRecord.append(cardnumber).append(delimiter);
				outputRecord.append(quantity).append(delimiter);
				outputRecord.append(totalprice).append(delimiter);
				outputRecord.append(customerid).append(delimiter);
				outputRecord.append(firstname).append(delimiter);
				outputRecord.append(lastname).append(delimiter);
				outputRecord.append(phone).append(delimiter);
				outputRecord.append(email).append(delimiter);
				outputRecord.append(country);

				processedRecord.set(outputRecord.toString());
				// Write the map output with key as null and value as the
				// constructed string
				multipleOutputs.write(NullWritable.get(), processedRecord,
						paymenttype);
			}
		} catch (Exception e) {
			throw new IOException(InetAddress.getLocalHost().getHostName()
					+ ":" + e.getMessage());
		}
	}

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		multipleOutputs.close();
	}

}
