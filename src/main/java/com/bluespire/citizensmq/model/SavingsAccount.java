package com.bluespire.citizensmq.model;

public class SavingsAccount {
private long accountNumber;
private int routingNumber;
public long getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(long accountNumber) {
	this.accountNumber = accountNumber;
}
public int getRoutingNumber() {
	//lsUIc
	return routingNumber;
}
public SavingsAccount(long accountNumber, int routingNumber) {
	super();
	this.accountNumber = accountNumber;
	this.routingNumber = routingNumber;
}
public SavingsAccount() {
	super();
	// TODO Auto-generated constructor stub
}
public void setRoutingNumber(int routingNumber) {
	this.routingNumber = routingNumber;
}
 public String toString() {
	 return "account number:"+this.accountNumber+"/n"+"routing number:"+this.routingNumber;
	 
 }


}
