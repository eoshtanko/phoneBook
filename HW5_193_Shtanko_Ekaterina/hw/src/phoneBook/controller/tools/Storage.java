package phoneBook.controller.tools;

import phoneBook.controller.mainScreen.MainScreenController;
import phoneBook.model.Contact;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * "Необходимо и обязательно чтобы данные телефонной книги не терялись
 * при закрытии приложения. Это значит, что закрытие приложения не
 * должно терять уже введенные и сохраненные данные"
 * <p>
 * Класс позволяет приложению не терять ужевведенные и сохраненные данные.
 */
public class Storage {

    /**
     * Метод для записис текущих данных, для считывания их в последующих сессиях
     * использования приложения.
     */
    public static void saveCurrentStateOfContacts() {
        try {
            ContactsSerializer.serialize(List.copyOf(MainScreenController.getContacts()),
                    new File("currentContacts.contacts"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод для считывания ранее введенных и сохраненных данных.
     *
     * @return сохраненные при прошлой сессии использования программы
     * контакты или пустой список,если таковых не оказалось
     */
    public static List<Contact> getContactsCurrentState() {
        File file = new File("currentContacts.contacts");
        if (file.exists() && file.isFile() && file.canRead()) {
            List<Contact> contacts = ContactsSerializer.deserialize(file);
            if (contacts != null) {
                return contacts;
            }
        }
        return new ArrayList<>();
    }
}
