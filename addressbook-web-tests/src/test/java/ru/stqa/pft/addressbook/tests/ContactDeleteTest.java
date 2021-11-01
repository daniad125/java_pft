package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactDeleteTest extends TestBase {
    @Test
    public void testDeleteContact() throws Exception{
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Sergey", "Vertibutylkin", "Super House", "ul Dlinnaya, 8, 235", "995996", "vertiseychac@yandex.ru","test1"));
        }
        app.getContactHelper().selectContacts();
        app.getContactHelper().deleteSelectedContacts();
    }
}
