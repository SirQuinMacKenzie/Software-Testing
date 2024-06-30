package project;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts;

    public ContactService() {
        this.contacts = new HashMap<>();
    }

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }
        String contactId = contact.getContactId();
        if (contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID must be unique.");
        }
        contacts.put(contactId, contact);
    }

    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID does not exist.");
        }
        contacts.remove(contactId);
    }

    public void updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID does not exist.");
        }
        if (firstName != null) {
            if (firstName.length() > 10) {
                throw new IllegalArgumentException("First name cannot be longer than 10 characters.");
            }
            contact = new Contact(contactId, firstName, contact.getLastName(), contact.getPhone(), contact.getAddress());
        }
        if (lastName != null) {
            if (lastName.length() > 10) {
                throw new IllegalArgumentException("Last name cannot be longer than 10 characters.");
            }
            contact = new Contact(contactId, contact.getFirstName(), lastName, contact.getPhone(), contact.getAddress());
        }
        if (phone != null) {
            if (phone.length() != 10 || !phone.matches("\\d+")) {
                throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
            }
            contact = new Contact(contactId, contact.getFirstName(), contact.getLastName(), phone, contact.getAddress());
        }
        if (address != null) {
            if (address.length() > 30) {
                throw new IllegalArgumentException("Address cannot be longer than 30 characters.");
            }
            contact = new Contact(contactId, contact.getFirstName(), contact.getLastName(), contact.getPhone(), address);
        }
        contacts.put(contactId, contact);
    }
}
