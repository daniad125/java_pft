package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{
    @Test
    public void testContactModification() {
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Sergey", "Vertibutylkin", "Super House", "ul Dlinnaya, 8, 235", "995996", "vertiseychac@yandex.ru","test1"));
        }
        app.getContactHelper().selectContacts();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Karaul","Karaulkin","5 otd","Vertlivaya 24","232453","karaul12345@gmail.com",null),false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();

    }
}
