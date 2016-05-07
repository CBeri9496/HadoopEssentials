Topic: Hadoop Map Reduce Job (Counters)

Requirement:
1) Find the number of transactions having total price < average total price (Need to be done in reducer)
2) Find the number of transactions having total price > average total price < 2*average total price (Need to be done in reducer)
3) Find the number of transactions having quantity < 10 (can be done in mapper itself)
4) the number of transactions having unit price < 25 (can be done in mapper itself)
5) the number of transactions having unit price > 25 and < 100 (can be done in mapper itself)

Solution:
Hadoop Counters
*************************************************************************************
Input File Format (layout):
TRANSACTIONID,TRANSACTIONDATE,PRODUCTID,UNITPRICE,PAYMENTTYPE,CARDNUMBER,QUANTITY,CATEGORYID,SUPPLIERID,CUSTOMERID,FIRSTNAME,LASTNAME,PHONE,EMAIL,ADDRESS,CITY,STATE,ZIP,COUNTRY,SUPPLIERNAME,SUPPLIERPHONE,SUPPLIEREMAIL,CATEGORYBRAND,CATEGORYTYPE

Sample Input Data:
166363,6/17/2015,9967,39.83,mastercard,5108750000000000,14,128,868,9651,Nicholas,Snyder,7-(279)927-0280,nsnyder2@linkedin.com,282 Mifflin Plaza,Louisville,Kentucky,40215,United States,Quimba,9-(295)433-7543,nsnyder2@paginegialle.it,Labetalol hydrochloride,Labetalol hydrochloride

*************************************************************************************
Required Output Format:
PAYMENTTYPE|COUNT_TRANSACTIONS|AVERAGE

COUNTERS:
TOTALPRICE_LESS_AVERAGE
TOTALPRICE_MORE_AVERAGE_LESS_DOUBLE_AVERAGE
TOTALPRICE_MORE_DOUBLE_AVERAGE
QTY_LESS_10
QTY_MORE_10
PRICE_LESS_25
PRICE_MORE_25_LESS_100
PRICE_MORE_100

Required Output Data:

