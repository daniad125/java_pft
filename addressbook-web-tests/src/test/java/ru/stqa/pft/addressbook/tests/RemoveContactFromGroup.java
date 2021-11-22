package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RemoveContactFromGroup extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().selectGroupsByName("test99").size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test99"));
        }
        if(app.db().contacts().size()==0) {
            app.goTo().homePage();
            Groups allGroups=app.db().selectGroupsByName("test99");
            GroupData group = allGroups.iterator().next();
            app.contact().create(new ContactData().withName("Dmitry").withLastname("Skuperfild").withCompany("Pasta fabric").withAddress("ul Ogromnaya, 47").withHomephone("993399").withEmail("skupersuper@yandex.ru").inGroup(group));
        }
        else {
            GroupData group = app.db().selectGroupsByName("test99").iterator().next();
            int id = group.getId();
            Contacts contacts = app.db().selectContactsByGroupName("test99");
            if(contacts.size()==0){
                app.goTo().homePage();
                ContactData contact = app.db().contacts().iterator().next();
                app.contact().addContactToGroup(contact,group);
            }
        }
    }
    @Test
    public void ContactRemovingTest() {
        Contacts contacts_before = app.db().contacts();
        GroupData group= app.db().selectGroupsByName("test99").iterator().next();
        ContactData contact=app.db().selectContactsByGroupName("test99").iterator().next();
        int id=contact.getId();
        Groups before=contact.getGroups();
        app.goTo().homePage();
        app.contact().removeContactFromGroup(contact,group);
        Contacts contacts_after=app.db().contacts();
        assertEquals(contacts_before.size(),contacts_after.size());
        contact=app.db().selectContactById(id);
        Groups after=contact.getGroups();
        assertThat(after, equalTo(before.without(group)));
    }
}
