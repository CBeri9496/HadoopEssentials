Topic: Hadoop Map Reduce Job (Aggregation)

Requirement:
Aggregation and group by
Example: Get the number of transactions, sum of total amount (unitprice*quantity), min of quantity, max of unitprice group by paymenttype

Solution:
Hadoop Map Reduce (using combiner when the aggregation is sum, min, max, and/or count. Combiner cannot be used for average, mean, etc)

*************************************************************************************
Input File Format (layout):
TRANSACTIONID,TRANSACTIONDATE,PRODUCTID,UNITPRICE,PAYMENTTYPE,CARDNUMBER,QUANTITY,CATEGORYID,SUPPLIERID,CUSTOMERID,FIRSTNAME,LASTNAME,PHONE,EMAIL,ADDRESS,CITY,STATE,ZIP,COUNTRY,SUPPLIERNAME,SUPPLIERPHONE,SUPPLIEREMAIL,CATEGORYBRAND,CATEGORYTYPE

Sample Input Data:
166363,6/17/2015,9967,39.83,mastercard,5108750000000000,14,128,868,9651,Nicholas,Snyder,7-(279)927-0280,nsnyder2@linkedin.com,282 Mifflin Plaza,Louisville,Kentucky,40215,United States,Quimba,9-(295)433-7543,nsnyder2@paginegialle.it,Labetalol hydrochloride,Labetalol hydrochloride

*************************************************************************************
Required Output Format:
PAYMENTTYPE|COUNT_TRANSACTIONS|SUM_TOTALPRICE|MIN_QUANTITY|MAX_UNITPRICE
where TOTALPRICE =  UNITPRICE * QUANTITY

Required Output Data:
mastercard^93^144776.33^1^99.67
