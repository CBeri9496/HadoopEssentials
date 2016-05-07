package com.krish.hadoop.multipleinput;

import java.io.IOException;
import java.net.InetAddress;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MultipleInputCommaMapper extends Mapper<LongWritable, Text, Text, Text> {
	@SuppressWarnings("unused")
	private String transactiondate, paymenttype, cardnumber, firstname,
			lastname, phone, email, address, city, state, country,
			suppliername, supplierphone, supplieremail, categorybrand,
			categorytype;
	@SuppressWarnings("unused")
	private int productid, quantity, categoryid, supplierid, customerid, zip;
	@SuppressWarnings("unused")
	private long transactionid;
	private double unitprice;

	public void map(LongWritable byteOffset, Text inputRecord, Context context)
			throws IOException, InterruptedException {
		StringTokenizer stringTokenizer = null;
		try {
			// Tokenize the input record, parse and assign the variables
			//Notice here that the delimiter is Comma ","
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

				// Construct the output for mapper (totalprice)
				context.write(new Text(paymenttype),
						new Text(String.valueOf(unitprice * quantity)));
			}
		} catch (Exception e) {
			throw new IOException(InetAddress.getLocalHost().getHostName()
					+ ":" + e);
		}

	}

}
