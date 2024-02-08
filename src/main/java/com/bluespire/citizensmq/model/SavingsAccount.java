package com.bluespire.citizensmq.model;

public class SavingsAccount {
private int accountNumber;
private int routingNumber;
public int getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(int accountNumber) {
	this.accountNumber = accountNumber;
}
public int getRoutingNumber() {
	return routingNumber;
}
public SavingsAccount(int accountNumber, int routingNumber) {
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
	 return "account number"+this.accountNumber+"/n"+"routing number"+this.routingNumber;
	 
 }


}
