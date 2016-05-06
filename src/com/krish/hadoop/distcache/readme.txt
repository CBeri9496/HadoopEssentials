Topic: Hadoop Map Join Job

Requirement:
Filter and select only few columns from the input file and/or 
Perform some simple processing like manipulating few columns (sum, difference, product, etc) on the same record without any aggregations
Join the columns from the cache file

Solution:
Hadoop Map Join using Distributed Cache 

*************************************************************************************
Input File Format (layout):
TRANSACTIONID,TRANSACTIONDATE,PRODUCTID,UNITPRICE,PAYMENTTYPE,CARDNUMBER,QUANTITY,CATEGORYID,SUPPLIERID,CUSTOMERID,FIRSTNAME,LASTNAME,PHONE,EMAIL,ADDRESS,CITY,STATE,ZIP,COUNTRY,SUPPLIERNAME,SUPPLIERPHONE,SUPPLIEREMAIL,CATEGORYBRAND,CATEGORYTYPE

Sample Input Data:
166363,6/17/2015,9967,39.83,mastercard,5108750000000000,14,128,868,9651,Nicholas,Snyder,7-(279)927-0280,nsnyder2@linkedin.com,282 Mifflin Plaza,Louisville,Kentucky,40215,United States,Quimba,9-(295)433-7543,nsnyder2@paginegialle.it,Labetalol hydrochloride,Labetalol hydrochloride

Cache File Format (layout):
PRODUCTID,PRODUCTGROUPID,ACADEMYNAME,PRODUCTNAME

Sample Cache Data:
1,14,ACADEMY ACADEMY,PENELOPE GUINESS

*************************************************************************************
Required Output Format:
TRANSACTIONID~TRANSACTIONDATE~PRODUCTID~PRODUCTGROUPID~ACADEMYNAME~PRODUCTNAME~UNITPRICE~PAYMENTTYPE~CARDNUMBER~QUANTITY~TOTALPRICE~CUSTOMERID~FIRSTNAME~LASTNAME~PHONE~EMAIL~ADDRESS~CITY~STATE~ZIP~COUNTRY
where TOTALPRICE =  UNITPRICE * QUANTITY

Required Output Data:
166363~6/17/2015~9967~8~ALADDIN WEEKEND~HALLE TANDY~39.83~mastercard~5108750000000000~14~557.62~9651~Nicholas~Snyder~7-(279)927-0280~nsnyder2@linkedin.com~United States
