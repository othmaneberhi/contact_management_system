package com.ensah.gs_contact;

import com.ensah.gs_contact.bo.contact.Contact;
import com.ensah.gs_contact.bo.contact.Gender;
import com.ensah.gs_contact.bo.group.Group;
import com.ensah.gs_contact.service.contact.ContactService;
import com.ensah.gs_contact.service.contact.IContactService;
import com.ensah.gs_contact.service.group.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class GsContactApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GsContactApplication.class, args);
	}

	private final IContactService contactService;
	private final IGroupService groupService;

	public GsContactApplication(final IContactService contactService,final IGroupService groupService) {
		this.contactService = contactService;
		this.groupService = groupService;
	}

	@Override
	public void run(String... args) throws Exception {

	}

	private int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	private void addRandomContacts(){

		String[] FIRST_NAMES = {"Alice", "Bob", "Carol", "David", "Eve", "Frank", "Grace", "Heidi", "Ivan", "Judy"};
		String[] LAST_NAMES = {"Smith", "Jones", "Taylor", "Brown", "Johnson", "Davis", "Miller", "Wilson", "Moore", "Anderson"};
		String[] ADDRESSES = {"123 Main St", "456 Oak Ave", "789 Pine Rd", "321 Elm Blvd", "654 Cedar Ln", "987 Maple Dr", "246 Cherry Ave", "135 Birch Pl", "864 Walnut Way", "579 Spruce Cir"};
		String[] EMAIL_DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "aol.com", "icloud.com", "protonmail.com", "zoho.com", "gmx.com", "mail.com"};
		Random RANDOM = new Random();

		for (int i = 0; i < 20; i++) {
			String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
			String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
			String persoPhone = "+212" + String.format("%010d", RANDOM.nextInt(10000));
			String proPhone = "+212" + String.format("%010d", RANDOM.nextInt(10000));
			String address = ADDRESSES[RANDOM.nextInt(ADDRESSES.length)];
			String persoEmail = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + EMAIL_DOMAINS[RANDOM.nextInt(EMAIL_DOMAINS.length)];
			String proEmail = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@company.com";
			Gender gender = RANDOM.nextBoolean() ? Gender.MALE : Gender.FEMALE;

			Contact contact = new Contact(firstName, lastName, persoPhone, proPhone, address, persoEmail, proEmail, gender);
			contactService.addContact(contact);
		}
	}

	private void addRandomGroups(){
		String[] groupNames = {"Family", "Friends", "Coworkers", "Schoolmates", "Acquaintances", "Neighbours", "Teammates", "Club members", "Travel companions", "Volunteers"};
		String[] groupDescriptions = {"Closest relatives and relatives-by-marriage", "People you enjoy spending time with", "People you work with or have worked with", "Fellow students and alumni", "People you have met casually or through others", "People who live near you", "Teammates in a sport or game", "People who share a common interest or hobby", "People you have traveled with", "People who volunteer for a cause or charity"};

		for (int i = 0; i < 5; i++) {
			Group group = new Group(groupNames[i], groupDescriptions[i]);
			groupService.addGroup(group);
		}
	}

	public void addRandomContactGroups(){
		Random RANDOM = new Random();
		List<Group> allGroups = groupService.getAllGroupsByOrderByName();
		List<Contact> allContacts = contactService.getAllContacts();
		Collections.shuffle(allGroups);

		List<Group> randomGroups = allGroups.subList(0, 3);

		for (Group randomGroup : randomGroups) {
			int numContacts = getRandomNumber(2, 15);
			for (int i = 0; i < numContacts; i++) {
				Contact randomContact = allContacts.get(RANDOM.nextInt(allContacts.size()));
				System.out.println("test1");
				//bug
				System.out.println(randomGroup.getContacts());
				randomContact.getGroups().add(randomGroup);
				System.out.println("tes2");
				randomGroup.getContacts().add(randomContact);
				System.out.println("test3");
				contactService.addContact(randomContact);
				System.out.println("test4");
			}

		}
	}
}
