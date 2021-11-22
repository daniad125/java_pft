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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class AddContactToGroupTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test99"));
        }
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
    }
    @Test
    public void ContactAddingTest(){
        Contacts contacts_before = app.db().contacts();
        ContactData contact=contacts_before.iterator().next();
        int id=contact.getId();
        Groups before=contact.getGroups();
        GroupData group = app.db().selectGroupsByName("test99").iterator().next();
        app.goTo().homePage();
        app.contact().addContactToGroup(contact,group);
        Contacts contacts_after=app.db().contacts();
        assertEquals(contacts_before.size(),contacts_after.size());
        contact=app.db().selectContactById(id);
        Groups after=contact.getGroups();
        assertThat(after, equalTo(before.withAdded(group)));
    }
}