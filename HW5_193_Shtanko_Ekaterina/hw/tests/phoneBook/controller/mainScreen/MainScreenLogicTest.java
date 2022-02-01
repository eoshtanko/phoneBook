package phoneBook.controller.mainScreen;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;
import phoneBook.model.Contact;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainScreenLogicTest {

    @Test
    void doAddContact() {
        Contact contact = new Contact();
        contact.setName("Test");
        contact.setSurname("Testovich");
        contact.setMobilePhone("+79176574637");

        List<Contact> con =  new ArrayList<>();
        MainScreenController.contacts = FXCollections.observableArrayList(con);
        assertEquals( 0, MainScreenController.contacts.size());
        MainScreenLogic.addContact(contact);
        assertEquals( 1, MainScreenController.contacts.size());

        assertThrows(IllegalArgumentException.class, () -> MainScreenLogic.addContact(contact));
    }

    @Test
    void doEditContact() {
        Contact contact1 = new Contact();
        contact1.setName("AA");
        contact1.setSurname("AAA");
        contact1.setMobilePhone("02");

        Contact contact2 = new Contact();
        contact2.setName("BB");
        contact2.setSurname("BBB");
        contact2.setMobilePhone("02");

        Contact contact3 = new Contact();
        contact2.setName("CC");
        contact2.setSurname("CCC");
        contact2.setMobilePhone("02");

        List<Contact> con =  new ArrayList<>();
        MainScreenController.contacts = FXCollections.observableArrayList(con);
        MainScreenLogic.addContact(contact1);
        MainScreenLogic.addContact(contact2);

        MainScreenController.indexOfSelectedContactInContacts = 0;

        // Если полученный в результате исправления контакт уже существует
        assertThrows(IllegalArgumentException.class, () -> MainScreenLogic.editContact(contact2));
        assertEquals(MainScreenController.contacts.get(0), contact1);

        // Корректный вариант
        assertDoesNotThrow(() -> MainScreenLogic.editContact(contact3));
        assertNotEquals(contact1, MainScreenController.contacts.get(0));
    }

    @Test
    void doDeleteContact() {
        Contact contact = new Contact();
        contact.setName("Test");
        contact.setSurname("Testovich");
        contact.setMobilePhone("+79176574637");

        List<Contact> con =  new ArrayList<>();
        MainScreenController.contacts = FXCollections.observableArrayList(con);
        MainScreenLogic.addContact(contact);
        assertEquals( 1, MainScreenController.contacts.size());
        MainScreenController.indexOfSelectedContactInContacts = 0;
        MainScreenLogic.deleteContact();
        assertEquals( 0, MainScreenController.contacts.size());
    }

    @Test
    void doSearch() {
        Contact contact1 = new Contact();
        contact1.setName("rrr");
        contact1.setSurname("aab");
        contact1.setPatronymic("ccc");
        contact1.setMobilePhone("01");

        Contact contact2 = new Contact();
        contact2.setName("ppp");
        contact2.setSurname("abc");
        contact2.setPatronymic("ppp");
        contact2.setMobilePhone("02");

        Contact contact3 = new Contact();
        contact3.setName("aaa");
        contact3.setSurname("Ргрг");
        contact3.setPatronymic("СССР");
        contact3.setMobilePhone("03");

        List<Contact> con = new ArrayList<>();
        MainScreenController.contacts = FXCollections.observableArrayList(con);
        MainScreenLogic.addContact(contact1);
        MainScreenLogic.addContact(contact2);
        MainScreenLogic.addContact(contact3);


        List<Contact> contactsAfter = MainScreenLogic.search("abc");
        assertEquals(1, contactsAfter.size());
        assertEquals("02", contactsAfter.get(0).getMobilePhone());

        contactsAfter = MainScreenLogic.search("СССР");
        assertEquals(1, contactsAfter.size());
        assertEquals("03", contactsAfter.get(0).getMobilePhone());

        contactsAfter = MainScreenLogic.search("rrr");
        assertEquals(1, contactsAfter.size());
        assertEquals("01", contactsAfter.get(0).getMobilePhone());

        contactsAfter = MainScreenLogic.search("a");
        assertEquals(3, contactsAfter.size());

        contactsAfter = MainScreenLogic.search("");
        assertEquals(3, contactsAfter.size());
    }
}