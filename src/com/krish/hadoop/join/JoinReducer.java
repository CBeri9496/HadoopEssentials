package com.krish.hadoop.join;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JoinReducer extends
		Reducer<IntWritable, Text, NullWritable, Text> {
	private Scanner oScanner = null;
	private StringBuilder sOutputRecord = null;
	private String sSourceFlag, sSupplyKey, sLineNumber, sLineStatus,
			sShipDate, sCommitDate, sReceiptDate, sShipInstuct, sShipMode,
			sPartName, sManuName, sBrandNo, sBrandName;
	private int iOrderID, iPartKey, iQuantity;
	private double dExtendedPrice, dDiscount, dAdditionalDiscount;

	public void reduce(IntWritable iMapPartKey, Iterable<Text> sInputRecords,
			Context oContext) throws IOException, InterruptedException {

		ArrayList<LineItemsParts> alLineItemsParts = new ArrayList<LineItemsParts>();
		for (Text sInputRecord : sInputRecords) {
			oScanner = new Scanner(sInputRecord.toString());
			oScanner.useDelimiter("\\|");
			LineItemsParts lineItemsParts = null;
			if (oScanner.hasNext()) {
				sSourceFlag = oScanner.next();
				if ("Parts".equals(sSourceFlag.trim())) {
					iPartKey = oScanner.nextInt();
					sPartName = oScanner.next();
					sManuName = oScanner.next();
					sBrandNo = oScanner.next();
					sBrandName = oScanner.next();
				} else {
					iOrderID = oScanner.nextInt();
					iPartKey = oScanner.nextInt();
					sSupplyKey = oScanner.next();
					sLineNumber = oScanner.next();
					iQuantity = oScanner.nextInt();
					dExtendedPrice = oScanner.nextDouble();
					dDiscount = oScanner.nextDouble();
					dAdditionalDiscount = oScanner.nextDouble();
					sLineStatus = oScanner.next();
					sShipDate = oScanner.next();
					sCommitDate = oScanner.next();
					sReceiptDate = oScanner.next();
					sShipInstuct = oScanner.next();
					sShipMode = oScanner.next();

					lineItemsParts = new LineItemsParts();
					lineItemsParts.setiOrderID(iOrderID);
					lineItemsParts.setiPartKey(iPartKey);
					lineItemsParts.setsSupplyKey(sSupplyKey);
					lineItemsParts.setsLineNumber(sLineNumber);
					lineItemsParts.setiQuantity(iQuantity);
					lineItemsParts.setdExtendedPrice(dExtendedPrice);
					lineItemsParts.setdDiscount(dDiscount);
					lineItemsParts.setdAdditionalDiscount(dAdditionalDiscount);
					lineItemsParts.setsLineStatus(sLineStatus);
					lineItemsParts.setsShipDate(sShipDate);
					lineItemsParts.setsCommitDate(sCommitDate);
					lineItemsParts.setsReceiptDate(sReceiptDate);
					lineItemsParts.setsShipInstuct(sShipInstuct);
					lineItemsParts.setsShipMode(sShipMode);
					alLineItemsParts.add(lineItemsParts);
					lineItemsParts = null;
				}
			}
		}
		for (LineItemsParts oLineItemsParts : alLineItemsParts) {
			sOutputRecord = new StringBuilder("");
			sOutputRecord.append(oLineItemsParts.getiOrderID()).append("|")
					.append(oLineItemsParts.getiPartKey()).append("|")
					.append(oLineItemsParts.getsSupplyKey()).append("|")
					.append(oLineItemsParts.getsLineNumber()).append("|")
					.append(oLineItemsParts.getiQuantity()).append("|")
					.append(oLineItemsParts.getdExtendedPrice()).append("|")
					.append(oLineItemsParts.getdDiscount()).append("|")
					.append(oLineItemsParts.getdAdditionalDiscount())
					.append("|").append(oLineItemsParts.getsLineStatus())
					.append("|").append(oLineItemsParts.getsShipDate())
					.append("|").append(oLineItemsParts.getsCommitDate())
					.append("|").append(oLineItemsParts.getsReceiptDate())
					.append("|").append(oLineItemsParts.getsShipInstuct())
					.append("|").append(oLineItemsParts.getsShipMode())
					.append("|").append(sPartName).append("|")
					.append(sManuName).append("|").append(sBrandNo).append("|")
					.append(sBrandName);
			oContext.write(NullWritable.get(),new Text(sOutputRecord.toString()));
		}
	}
}
