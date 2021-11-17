package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if(app.contact().all().size()==0){
            app.contact().create(new ContactData().withName("Sergey").withLastname("Vertibutylkin").withCompany("Super House").withAddress("ul Dlinnaya, 8, 235").withHomephone("995996").withEmail("vertiseychac@yandex.ru"));
                    //.withGroup("test1"));
        }
    }
    @Test (enabled = true)
    public void testContactModification() {

        Contacts before = app.contact().all();
        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(contactToModify.getId()).withName("Karaul").withLastname("Karaulkin").withCompany("5 otd").withAddress("Vertlivaya 24").withHomephone("232453").withEmail("karaul12345@gmail.com");
        app.contact().modify(contact);
        assertEquals(before.size(), app.contact().count());
        Contacts after = app.contact().all();
        assertThat(after,equalTo(before.without(contactToModify).withAdded(contact)));
        app.goTo().homePage();

    }


}
