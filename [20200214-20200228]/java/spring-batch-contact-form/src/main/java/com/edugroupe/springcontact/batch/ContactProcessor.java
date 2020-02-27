package com.edugroupe.springcontact.batch;

import org.springframework.batch.item.ItemProcessor;

import com.edugroupe.springcontact.metier.Contact;

public class ContactProcessor implements ItemProcessor<Contact, Contact> {

	@Override
	public Contact process(Contact item) throws Exception {
		System.out.println("processing: " + item);
		return new Contact(item.getNom().toUpperCase(),
						  item.getPrenom().trim(),
						  item.getEmail().trim());
	}

}
