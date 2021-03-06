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

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDeleteTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().contacts().size()==0){
            GroupData group;
            app.goTo().homePage();
            Groups allGroups=app.db().selectGroupsByName("test1");
            group = allGroups.iterator().next();
            app.contact().create(new ContactData().withName("Sergey").withLastname("Vertibutylkin").withCompany("Super House").withAddress("ul Dlinnaya, 8, 235").withHomephone("995996").withEmail("vertiseychac@yandex.ru").inGroup(group));
        }
    }
    @Test (enabled = true)
    public void testDeleteContact() throws Exception{
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertEquals(before.size(), app.contact().count()+1);
        Contacts after = app.db().contacts();
        assertThat(after,equalTo(before.without(deletedContact)));
    }


}
