package com.frikwensi.billingintegration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String transactionReference;
    private String clientSwiftAddress;
    private String messageStatus;
    private String currency;
    private int amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeCreated;

    public Bill() {
    }
    
    public Bill(String transactionReference, String clientSwiftAddress, String messageStatus, String currency, int amount) {
	this.transactionReference = transactionReference;
	this.clientSwiftAddress = clientSwiftAddress;
	this.messageStatus = messageStatus;
	this.currency = currency;
	this.amount = amount;
    }

    @Override
    public String toString() {
	return "Bill{" + "id=" + id + ", transactionReference='" + transactionReference + '\'' + ", clientSwiftAddress='" + clientSwiftAddress + '\''  + '}';
    }

    
}
