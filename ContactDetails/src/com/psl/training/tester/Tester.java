package com.psl.training.tester;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Set;

import com.psl.training.model.Contact;
import com.psl.training.service.ContactNotFoundException;
import com.psl.training.service.ContactService;
import com.psl.training.util.DBConnection;

public class Tester {

	public static void main(String[] args) throws IOException, ContactNotFoundException {
		// TODO Auto-generated method stub
		ContactService cs = new ContactService();
		cs.readContactsFromFile(".//contacts.txt");
		//for(Contact c:cs.searchContactByNumber("23")) {
			//System.out.println(c.getContactName());
		//}
		
		//cs.addContact(new Contact(4,"Diksha","diksh2a@gmail.com",Arrays.asList("334444","5576687")));
		//cs.removeContact(new Contact(4,"Diksha","diksh2a@gmail.com",Arrays.asList("334444","5576687")));
		
		//cs.addContactNumber(1, "768685");
		//cs.sortContactsByName();
		//cs.serializeContactDetails("object.ser");
		//cs.deserializeContact("object.ser");
		
		Connection con =DBConnection.getConnection(); 
		//DBConnection.createTables();
		//DBConnection.insertData();
		Set<Contact> contactSet=cs.populateContactFromDb();
		cs.addContacts(contactSet);
	}

}
