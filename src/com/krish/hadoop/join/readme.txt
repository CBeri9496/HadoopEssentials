Topic: Hadoop Map Reduce (Reducer Side Join)

Requirement:
Given 2 big files and need to join these 2 big files bassed on id

Solution:
Hadoop Map Reduce (Reducer Side Join)

*************************************************************************************
Sample Input File1 Data (LineItems):
iOrderID|iPartKey|sSupplyKey|sLineNumber|iQuantity|dExtendedPrice|dDiscount|dAdditionalDiscount|sLineStatus|Filler|sShipDate|sCommitDate|sReceiptDate|sShipInstuct|sShipMode|

1|155190|7706|1|17|21168.23|0.04|0.02|N|O|1996-03-13|1996-02-12|1996-03-22|DELIVER IN PERSON|TRUCK|egular courts above the|
1|67310|7311|2|36|45983.16|0.09|0.06|N|O|1996-04-12|1996-02-28|1996-04-20|TAKE BACK RETURN|MAIL|ly final dependencies: slyly bold|

Sample Input File2 Data (Parts):
iPartKey|sPartName|sManuName|sBrandNo|sBrandName|Filler1|Filler2|Filler3|Filler4|

1|goldenrod lace spring peru powder|Manufacturer#1|Brand#13|PROMO BURNISHED COPPER|7|JUMBO PKG|901|ly. slyly ironi|
2|blush rosy metallic lemon navajo|Manufacturer#1|Brand#13|LARGE BRUSHED BRASS|1|LG CASE|902|lar accounts amo|

*************************************************************************************
Required Output Data:
iOrderID|iPartKey|sSupplyKey|sLineNumber|iQuantity|dExtendedPrice|dDiscount|dAdditionalDiscount|sLineStatus|sShipDate|sCommitDate|sReceiptDate|sShipInstuct|sShipMode|sPartName|sManuName|sBrandNo|sBrandName

5362629|1|5002|3|33|29733.0|0.0|0.07|R|1995-04-06|1995-02-11|1995-04-25|TAKE BACK RETURN|AIR|goldenrod lace spring peru powder|Manufacturer#1|Brand#13|PROMO BURNISHED COPPER
5618244|1|7502|5|21|18921.0|0.02|0.08|N|1998-10-17|1998-08-31|1998-10-25|NONE|REG AIR|goldenrod lace spring peru powder|Manufacturer#1|Brand#13|PROMO BURNISHED COPPER


