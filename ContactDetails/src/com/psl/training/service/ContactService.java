package com.psl.training.service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import com.psl.training.model.*;

public class ContactService{
	
	List<Contact> contacts = new ArrayList(); ;
	
	public void addContact(Contact contact) {
		this.contacts.add(contact);
		System.out.println("contact added");
		
	}
	public void removeContact(Contact contact) throws ContactNotFoundException {
		int count=0;
		Contact removeCont=null;
		for(Contact c:this.contacts) {
			if(c.getContactId()==contact.getContactId()) {
				count=1;
				removeCont=c;
				break;
			}
		}
			if(count==1) {
				this.contacts.remove(removeCont);
				System.out.println("contact removed");
			}
			else {
				throw new ContactNotFoundException("unable to remove contact, contact does not exist");
			}
			
		}
		
	public void readContactsFromFile(String fileName) throws IOException {
		BufferedReader bf;
		try {
			File f = new File(fileName);
			bf = new BufferedReader(new FileReader(f));
			String thisLine;
			while((thisLine=bf.readLine())!=null) {
				String[] temp = thisLine.split(",");
				List<String> contactNum=new ArrayList();
				for(int i=3;i<temp.length;i++) {
					contactNum.add(temp[i]);
				}
				Contact c = new Contact(Integer.parseInt(temp[0]),temp[1],temp[2],contactNum);
				addContact(c);
				
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	public void searchContactByName(String name) throws ContactNotFoundException {
		for(Contact c:this.contacts) {
			if(c.getContactName()!=name)
				throw new ContactNotFoundException("contact does not exist");
		}
	}
	public List<Contact> searchContactByNumber(String number){
		List<Contact> contactFound=new ArrayList();
		for(Contact c :this.contacts) {
			for(String contNum:c.getContactNumbers()) {
				if(contNum.contains(number)) {
					contactFound.add(c);
					break;
				}
			}
		}
		return contactFound;
	}
	public void addContactNumber(int contactId, String contactNo) {
		for(Contact c:this.contacts) {
			if(c.getContactId()==contactId) {
				c.getContactNumbers().add(contactNo);
				for(String s:c.getContactNumbers()) {
					System.out.println(s);
				}
			}
		}
	}
	public void sortContactsByName() {
		this.contacts.sort(new Comparator<Contact>() {
		    @Override
		    public int compare(Contact o1, Contact o2) {
		    	return o1.getContactName().compareTo(o2.getContactName());
		    }
		});
		for(Contact c:this.contacts) {
			System.out.println(c.getContactName());
		}
	}
	public void serializeContactDetails(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.contacts);
			oos.close();
			fos.close();
			System.out.println("Objects serialized");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Contact> deserializeContact(String filename){
		List<Contact> contacts = new ArrayList<>();
		//File file = new File(filename);
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			contacts = (List<Contact>) ois.readObject();
			System.out.println("Objects deserialized");
			System.out.println(contacts.size());
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacts;
	}


}

