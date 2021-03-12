package com.psl.training.model;

import java.io.Serializable;
import java.util.List;

public class Contact implements Serializable{
	int contactId;
	String contactName;
	String emailAddress;
	List<String> contactNumbers;
	
	public Contact(int id,String name, String email, List<String> contactNum){
		this.contactId=id;
		this.contactName=name;
		this.emailAddress=email;
		this.contactNumbers=contactNum;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public List<String> getContactNumbers() {
		return contactNumbers;
	}

	public void setContactNumbers(List<String> contactNumbers) {
		this.contactNumbers = contactNumbers;
	}
	}
