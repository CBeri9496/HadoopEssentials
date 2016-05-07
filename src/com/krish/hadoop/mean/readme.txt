Topic: Hadoop Map Reduce Job (Mean)

Requirement:
Mean and group by
Example: Get the mean of total amount (unitprice*quantity) group by paymenttype

Solution:
Hadoop Map Reduce (using combiner for calculating sum of amount and transaction count of each mapper group by paymenttype. Combiner cannot be used for variance, standard deviations, etc)

*************************************************************************************
Input File Format (layout):
TRANSACTIONID,TRANSACTIONDATE,PRODUCTID,UNITPRICE,PAYMENTTYPE,CARDNUMBER,QUANTITY,CATEGORYID,SUPPLIERID,CUSTOMERID,FIRSTNAME,LASTNAME,PHONE,EMAIL,ADDRESS,CITY,STATE,ZIP,COUNTRY,SUPPLIERNAME,SUPPLIERPHONE,SUPPLIEREMAIL,CATEGORYBRAND,CATEGORYTYPE

Sample Input Data:
166363,6/17/2015,9967,39.83,mastercard,5108750000000000,14,128,868,9651,Nicholas,Snyder,7-(279)927-0280,nsnyder2@linkedin.com,282 Mifflin Plaza,Louisville,Kentucky,40215,United States,Quimba,9-(295)433-7543,nsnyder2@paginegialle.it,Labetalol hydrochloride,Labetalol hydrochloride

*************************************************************************************
Required Output Format:
PAYMENTTYPE|COUNT_TRANSACTIONS|SUM_TOTALPRICE|MEAN_TOTALPRICE
where TOTALPRICE =  UNITPRICE * QUANTITY and MEAN_TOTALPRICE = SUM_TOTALPRICE / COUNT_TRANSACTIONS

Required Output Data:
americanexpress|46000|6.226004999999945E7|1353.479347826075
