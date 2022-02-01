package phoneBook.controller.tools;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;
import phoneBook.controller.mainScreen.MainScreenController;
import phoneBook.model.Contact;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    final private static String FILE_NAME = "currentContacts.contacts";

    @Test
    void saveAndGetCurrentStateOfContactsTest() {
        File file = new File(FILE_NAME);
        file.delete();
        List<Contact> contacts = Storage.getContactsCurrentState();
        assertEquals(Collections.emptyList(), contacts);

        Contact contact = new Contact();
        contact.setName("aaa");
        contact.setSurname("bbb");
        contact.setPatronymic("ccc");
        contact.setMobilePhone("01111");

        List<Contact> con = new ArrayList<>();
        con.add(contact);

        MainScreenController.setContacts(FXCollections.observableArrayList(con));
        Storage.saveCurrentStateOfContacts();
        assertTrue(file.exists());
        contacts = Storage.getContactsCurrentState();
        assertEquals(con, contacts);
        file.delete();
    }
}