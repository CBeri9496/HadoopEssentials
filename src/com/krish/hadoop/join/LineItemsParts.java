package com.krish.hadoop.join;

public class LineItemsParts {
	private String sSupplyKey, sLineNumber, sLineStatus,
			sFiller, sShipDate, sCommitDate, sReceiptDate, sShipInstuct,
			sShipMode, sComment;
	public String getsSupplyKey() {
		return sSupplyKey;
	}
	public void setsSupplyKey(String sSupplyKey) {
		this.sSupplyKey = sSupplyKey;
	}
	public String getsLineNumber() {
		return sLineNumber;
	}
	public void setsLineNumber(String sLineNumber) {
		this.sLineNumber = sLineNumber;
	}
	public String getsLineStatus() {
		return sLineStatus;
	}
	public void setsLineStatus(String sLineStatus) {
		this.sLineStatus = sLineStatus;
	}
	public String getsFiller() {
		return sFiller;
	}
	public void setsFiller(String sFiller) {
		this.sFiller = sFiller;
	}
	public String getsShipDate() {
		return sShipDate;
	}
	public void setsShipDate(String sShipDate) {
		this.sShipDate = sShipDate;
	}
	public String getsCommitDate() {
		return sCommitDate;
	}
	public void setsCommitDate(String sCommitDate) {
		this.sCommitDate = sCommitDate;
	}
	public String getsReceiptDate() {
		return sReceiptDate;
	}
	public void setsReceiptDate(String sReceiptDate) {
		this.sReceiptDate = sReceiptDate;
	}
	public String getsShipInstuct() {
		return sShipInstuct;
	}
	public void setsShipInstuct(String sShipInstuct) {
		this.sShipInstuct = sShipInstuct;
	}
	public String getsShipMode() {
		return sShipMode;
	}
	public void setsShipMode(String sShipMode) {
		this.sShipMode = sShipMode;
	}
	public String getsComment() {
		return sComment;
	}
	public void setsComment(String sComment) {
		this.sComment = sComment;
	}
	public int getiOrderID() {
		return iOrderID;
	}
	public void setiOrderID(int iOrderID) {
		this.iOrderID = iOrderID;
	}
	public int getiPartKey() {
		return iPartKey;
	}
	public void setiPartKey(int iPartKey) {
		this.iPartKey = iPartKey;
	}
	public int getiQuantity() {
		return iQuantity;
	}
	public void setiQuantity(int iQuantity) {
		this.iQuantity = iQuantity;
	}
	public double getdExtendedPrice() {
		return dExtendedPrice;
	}
	public void setdExtendedPrice(double dExtendedPrice) {
		this.dExtendedPrice = dExtendedPrice;
	}
	public double getdDiscount() {
		return dDiscount;
	}
	public void setdDiscount(double dDiscount) {
		this.dDiscount = dDiscount;
	}
	public double getdAdditionalDiscount() {
		return dAdditionalDiscount;
	}
	public void setdAdditionalDiscount(double dAdditionalDiscount) {
		this.dAdditionalDiscount = dAdditionalDiscount;
	}
	private int iOrderID, iPartKey, iQuantity;
	private double dExtendedPrice, dDiscount, dAdditionalDiscount;
}
