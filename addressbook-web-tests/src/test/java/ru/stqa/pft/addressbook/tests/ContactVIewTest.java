package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactVIewTest extends TestBase{
    private ContactData contact;
    private ContactData contactInfoFromEditForm;


    @BeforeMethod
    public void getInfo() {
        app.goTo().homePage();
        contact = app.contact().all().iterator().next();
        contactInfoFromEditForm=app.contact().infoFromEditForm(contact);
    }
    @Test (enabled = true)
    public void getViewInfo() {
        ContactData viewInfo=app.contact().infoView(contact);
        assertThat(contactInfoFromEditForm.getName(), equalTo(viewInfo.getName()));
        assertThat(contactInfoFromEditForm.getLastname(),equalTo(viewInfo.getLastname()));
        assertThat(contactInfoFromEditForm.getAddress(),equalTo(viewInfo.getAddress()));
        assertThat(viewInfo.getHomephone(),equalTo(contactInfoFromEditForm.getHomephone()));
        assertThat(viewInfo.getMobilephone(),equalTo(contactInfoFromEditForm.getMobilephone()));
        assertThat(viewInfo.getWorkphone(),equalTo(contactInfoFromEditForm.getWorkphone()));
        assertThat(viewInfo.getAllemails().replaceAll(" ",""),equalTo(mergeEmails(contactInfoFromEditForm).replaceAll("\n","").replaceAll(" ","")));

    }
    private String mergeEmails(ContactData contact){
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3()).stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }
}
