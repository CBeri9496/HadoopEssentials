Topic: Hadoop Map Reduce Job (Variance and standard deviation)

Requirement:
Variance and standard deviation with group by
Example: Get the Variance and standard deviation of total amount (unitprice*quantity) group by paymenttype

Solution:
Hadoop Map Reduce 

*************************************************************************************
Input File Format (layout):
TRANSACTIONID,TRANSACTIONDATE,PRODUCTID,UNITPRICE,PAYMENTTYPE,CARDNUMBER,QUANTITY,CATEGORYID,SUPPLIERID,CUSTOMERID,FIRSTNAME,LASTNAME,PHONE,EMAIL,ADDRESS,CITY,STATE,ZIP,COUNTRY,SUPPLIERNAME,SUPPLIERPHONE,SUPPLIEREMAIL,CATEGORYBRAND,CATEGORYTYPE

Sample Input Data:
166363,6/17/2015,9967,39.83,mastercard,5108750000000000,14,128,868,9651,Nicholas,Snyder,7-(279)927-0280,nsnyder2@linkedin.com,282 Mifflin Plaza,Louisville,Kentucky,40215,United States,Quimba,9-(295)433-7543,nsnyder2@paginegialle.it,Labetalol hydrochloride,Labetalol hydrochloride

*************************************************************************************
Required Output Format:
PAYMENTTYPE|COUNT_TRANSACTIONS|SUM_TOTALPRICE|MEAN_TOTALPRICE|DEVIATION|VARIANCE|STANDARD_DEVIATION
where 
TOTALPRICE =  UNITPRICE * QUANTITY 
MEAN_TOTALPRICE = SUM_TOTALPRICE / COUNT_TRANSACTIONS
DEVIATION = ( SUM_TOTALPRICE - MEAN_TOTALPRICE )sq
VARIANCE = MEAN( DEVIATION ) = DEVIATION / COUNT_TRANSACTIONS
STANDARD_DEVIATION = SQRT(VARIANCE)

Required Output Data:
americanexpress|46000|6.226004999999156E7|1353.4793478259035|5.512749305467525E10|1198423.7620581575|1094.7254277023794

