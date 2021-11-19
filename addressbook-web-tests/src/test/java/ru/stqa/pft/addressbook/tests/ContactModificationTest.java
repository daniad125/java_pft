package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase{
    GroupData group;
    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().contacts().size()==0){
            app.goTo().homePage();
            Groups allGroups=app.db().selectGroupsByName("test1");
            group = allGroups.iterator().next();
            app.contact().create(new ContactData().withName("Sergey").withLastname("Vertibutylkin").withCompany("Super House").withAddress("ul Dlinnaya, 8, 235").withHomephone("995996").withEmail("vertiseychac@yandex.ru").inGroup(group));
        }
    }
    @Test (enabled = true)
    public void testContactModification() {

        Contacts before = app.db().contacts();
        ContactData contactToModify = before.iterator().next();
        System.out.println(contactToModify.getGroups());
        System.out.println("HAHA");

        ContactData contact = new ContactData()
                .withId(contactToModify.getId()).withName("Karaul").withLastname("Karaulkin").withCompany("5 otd").withAddress("Vertlivaya 24").withHomephone("232453").withEmail("karaul12345@gmail.com");
        Iterator<GroupData> groups=contactToModify.getGroups().iterator();
        while (groups.hasNext()) {
            contact=contact.inGroup(groups.next());
        }
        app.goTo().homePage();
        app.contact().modify(contact);
        assertEquals(before.size(), app.contact().count());
        Contacts after = app.db().contacts();
        assertThat(after,equalTo(before.without(contactToModify).withAdded(contact)));
        app.goTo().homePage();

    }


}
