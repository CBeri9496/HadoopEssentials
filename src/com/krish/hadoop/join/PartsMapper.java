package com.krish.hadoop.join;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PartsMapper extends
		Mapper<LongWritable, Text, IntWritable, Text> {
	private String sSourceFlag = "Parts", sPartName, sManuName, sBrandNo,
			sBrandName;
	private int iPartKey;
	private Scanner oScanner = null;
	private StringBuilder sOutputRecord = null;

	public void map(LongWritable lByteOffset, Text sInputRecord,
			Context oContext) throws IOException, InterruptedException {
		oScanner = new Scanner(sInputRecord.toString());
		oScanner.useDelimiter("\\|");
		if (oScanner.hasNext()) {
			iPartKey = oScanner.nextInt();
			sPartName = oScanner.next();
			sManuName = oScanner.next();
			sBrandNo = oScanner.next();
			sBrandName = oScanner.next();
		}
		sOutputRecord = new StringBuilder("");
		sOutputRecord.append(sSourceFlag).append("|").append(iPartKey)
				.append("|").append(sPartName).append("|").append(sManuName)
				.append("|").append(sBrandNo).append("|").append(sBrandName);
		oContext.write(new IntWritable(iPartKey), new Text(sOutputRecord.toString()));
	}
}
