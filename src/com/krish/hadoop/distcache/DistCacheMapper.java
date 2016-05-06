package com.krish.hadoop.distcache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DistCacheMapper extends
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
	private String delimiter = "|";
	private HashMap<String, String> hashMap = new HashMap<String, String>();

	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		// Get the delimiter and cache file name from job configuration
		Configuration configuration = context.getConfiguration();
		String cacheFile = configuration.get("job.distcache.cachefile");
		cacheFile = cacheFile.substring(cacheFile.lastIndexOf("/") + 1);
		delimiter = configuration.get("job.distcache.delimiter");

		// Exit if the cache is empty
		if (context.getCacheFiles() == null
				|| context.getCacheFiles().length == 0) {
			System.err.println("Cannot find the cache file");
			System.exit(1);
		}

		// Read the dist cache file and construct the hashmap with product id as
		// key and other fields as value
		String record = null;
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				new File(cacheFile)));
		while ((record = bufferedReader.readLine()) != null) {
			String[] cacheRecord = record.split(",");
			hashMap.put(cacheRecord[0], cacheRecord[1] + delimiter
					+ cacheRecord[2] + delimiter + cacheRecord[3]);
		}
		bufferedReader.close();
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

				// Get the value (product detials) from the hashmap using
				// product id as key
				outputRecord.append(hashMap.get(String.valueOf(productid)))
						.append(delimiter);
				outputRecord.append(unitprice).append(delimiter);
				outputRecord.append(paymenttype).append(delimiter);
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
				context.write(NullWritable.get(), processedRecord);
			}
		} catch (Exception e) {
			throw new IOException(InetAddress.getLocalHost().getHostName()
					+ ":" + e.getMessage());
		}

	}

}
