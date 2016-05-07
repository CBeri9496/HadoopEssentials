Topic: Hadoop Multiple Outputs

Requirement:
Redirect the records to different output files based on its value

the number of output files should be equal to number of distinct values of that particular column

Solution:
Hadoop Multiple Outputs

*************************************************************************************
Input File Format (layout):
TRANSACTIONID,TRANSACTIONDATE,PRODUCTID,UNITPRICE,PAYMENTTYPE,CARDNUMBER,QUANTITY,CATEGORYID,SUPPLIERID,CUSTOMERID,FIRSTNAME,LASTNAME,PHONE,EMAIL,ADDRESS,CITY,STATE,ZIP,COUNTRY,SUPPLIERNAME,SUPPLIERPHONE,SUPPLIEREMAIL,CATEGORYBRAND,CATEGORYTYPE

Sample Input Data:
166363,6/17/2015,9967,39.83,mastercard,5108750000000000,14,128,868,9651,Nicholas,Snyder,7-(279)927-0280,nsnyder2@linkedin.com,282 Mifflin Plaza,Louisville,Kentucky,40215,United States,Quimba,9-(295)433-7543,nsnyder2@paginegialle.it,Labetalol hydrochloride,Labetalol hydrochloride

*************************************************************************************
Required Output Format:
TRANSACTIONID|TRANSACTIONDATE|PRODUCTID|UNITPRICE|CARDNUMBER|QUANTITY|TOTALPRICE|CUSTOMERID|FIRSTNAME|LASTNAME|PHONE|EMAIL|ADDRESS|CITY|STATE|ZIP|COUNTRY
where TOTALPRICE =  UNITPRICE * QUANTITY

Required Output Data:
184528|12/1/2014|1935|66.89|348815000000000|8|535.12|5182|Amy|Morris|6-(465)410-1889|amorrisdn@businesswire.com|United States
224754|12/18/2014|6717|42.96|374288000000000|26|1116.96|2329|Walter|Brown|5-(794)449-9714|wbrownds@reverbnation.com|United States
192491|12/3/2014|7410|61.89|349563000000000|24|1485.3600000000001|5249|Laura|Walker|0-(754)876-0833|lwalkerjp@fc2.com|United States

Sample output files:
-rw-r--r--   3 kdeenath staff    3003152 2016-05-06 23:09 HadoopEssentials/output/MultipleOutputDriver/americanexpress-m-00000
-rw-r--r--   3 kdeenath staff    2842848 2016-05-06 23:09 HadoopEssentials/output/MultipleOutputDriver/americanexpress-m-00001
-rw-r--r--   3 kdeenath staff    2112041 2016-05-06 23:09 HadoopEssentials/output/MultipleOutputDriver/bankcard-m-00000
-rw-r--r--   3 kdeenath staff    1998959 2016-05-06 23:09 HadoopEssentials/output/MultipleOutputDriver/bankcard-m-00001