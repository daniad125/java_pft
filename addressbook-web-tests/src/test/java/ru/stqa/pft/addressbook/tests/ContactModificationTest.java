package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase{
    @Test (enabled = false)
    public void testContactModification() {
        if(!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Sergey", "Vertibutylkin", "Super House", "ul Dlinnaya, 8, 235", "995996", "vertiseychac@yandex.ru","test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContacts(before.size()-1);
        app.getContactHelper().initContactModification(before.size()+1);
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Karaul","Karaulkin","5 otd","Vertlivaya 24","232453","karaul12345@gmail.com",null);
        app.getContactHelper().fillContactForm(contact,false);
        app.getContactHelper().submitContactModification();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(),after.size());
        before.remove(before.size()-1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
        app.goTo().gotoHomePage();

    }
}
