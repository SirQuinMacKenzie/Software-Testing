package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Contact;
import project.ContactService;

public class ContactServiceTest {
    private ContactService contactService;
    private Contact validContact;

    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
        validContact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
    }

    @Test
    public void testAddContactWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(null);
        });
    }

    @Test
    public void testAddContactWithDuplicateId() {
        contactService.addContact(validContact);
        Contact duplicateContact = new Contact("1234567890", "Jane", "Doe", "0987654321", "456 Elm St");
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(duplicateContact);
        });
    }

    @Test
    public void testDeleteNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.deleteContact("1234567890");
        });
    }

    @Test
    public void testUpdateNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testUpdateContactWithInvalidFirstName() {
        contactService.addContact(validContact);
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContact("1234567890", "JohnJohnJohn", null, null, null);
        });
    }

    @Test
    public void testUpdateContactWithInvalidLastName() {
        contactService.addContact(validContact);
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContact("1234567890", null, "DoeDoeDoe01", null, null);
        });
    }

    @Test
    public void testUpdateContactWithInvalidPhoneNumber() {
        contactService.addContact(validContact);
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContact("1234567890", null, null, "123", null);
        });
    }

    @Test
    public void testUpdateContactWithInvalidAddress() {
        contactService.addContact(validContact);
        assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContact("1234567890", null, null, null, "1234567890123456789012345678901");
        });
    }
}

