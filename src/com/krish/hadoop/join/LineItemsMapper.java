package com.krish.hadoop.join;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LineItemsMapper extends
		Mapper<LongWritable, Text, IntWritable, Text> {
	private String sSourceFlag = "Orders", sSupplyKey, sLineNumber, sLineStatus,
			sShipDate, sCommitDate, sReceiptDate, sShipInstuct,
			sShipMode;
	private int iOrderID, iPartKey, iQuantity;
	private double dExtendedPrice, dDiscount, dAdditionalDiscount;
	private Scanner oScanner = null;
	private StringBuilder sOutputRecord = null;

	public void map(LongWritable lByteOffset, Text sInputRecord,
			Context oContext) throws IOException, InterruptedException {
		oScanner = new Scanner(sInputRecord.toString());
		oScanner.useDelimiter("\\|");
		if (oScanner.hasNext()) {
			iOrderID = oScanner.nextInt();
			iPartKey = oScanner.nextInt();
			sSupplyKey = oScanner.next();
			sLineNumber = oScanner.next();
			iQuantity = oScanner.nextInt();
			dExtendedPrice = oScanner.nextDouble();
			dDiscount = oScanner.nextDouble();
			dAdditionalDiscount = oScanner.nextDouble();
			sLineStatus = oScanner.next();
			//String sFiller = oScanner.next();
			oScanner.next(); // This is filler
			sShipDate = oScanner.next();
			sCommitDate = oScanner.next();
			sReceiptDate = oScanner.next();
			sShipInstuct = oScanner.next();
			sShipMode = oScanner.next();
			//String sComment = oScanner.next();
		}
		sOutputRecord = new StringBuilder("");
		sOutputRecord.append(sSourceFlag).append("|").append(iOrderID)
				.append("|").append(iPartKey).append("|").append(sSupplyKey)
				.append("|").append(sLineNumber).append("|").append(iQuantity)
				.append("|").append(dExtendedPrice).append("|")
				.append(dDiscount).append("|").append(dAdditionalDiscount)
				.append("|").append(sLineStatus).append("|").append(sShipDate)
				.append("|").append(sCommitDate).append("|")
				.append(sReceiptDate).append("|").append(sShipInstuct)
				.append("|").append(sShipMode);
		oContext.write(new IntWritable(iPartKey), new Text(sOutputRecord.toString()));
	}
}
