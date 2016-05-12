Follow the below order if you are new to hadoop

1) Map Only job (package com.krish.hadoop.maponly)
2) Map Only job using distributed cache- Map join (package com.krish.hadoop.distcache)
3) Map Reduce Job: Using Reducer as Combiner for simple aggregations like sum, count, min and max (package com.krish.hadoop.mapcombinereduce)
4) Map Reduce Job using combiner and different reducer where combiner calculates the sum and count for each mapper and reducer calculates the mean (package com.krish.hadoop.mean)
5) Map Reduce job with no combiner to calculate variance and standard deviation (package com.krish.hadoop.variance)
6) Join 2 large datasets in the reducer (package com.krish.hadoop.join) 
7) Counters (package com.krish.hadoop.counter)
8) Multiple Inputs to process different input files using different mapper in the same job (package com.krish.hadoop.multipleinput)
9) Multiple Outputs to write the output records to different file based on a fileds/columns distinct values (package com.krish.hadoop.multipleoutput)

Watch out for more:
********************
Custom record reader
custom partitioner
custom writables
Change Data Capture (CDC) using MR
File Formats
	Reading and Writing sequence file 
	Reading and Writing avro file 
	Reading and Writing parquet file 
Compressions


	


