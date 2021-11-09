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

public class ContactPhoneTests extends TestBase{
    private ContactData contact;
    private ContactData contactInfoFromEditForm;
    @BeforeMethod
    public void getInfo() {
        app.goTo().homePage();
        contact = app.contact().all().iterator().next();
        contactInfoFromEditForm=app.contact().infoFromEditForm(contact);

    }
    @Test
    public void testContactPhones() {
 /*       app.goTo().homePage();
        contact = app.contact().all().iterator().next();
        contactInfoFromEditForm=app.contact().infoFromEditForm(contact);
*/        assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));
     }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomephone(),contact.getMobilephone(),contact.getWorkphone()).stream().filter((s) -> !s.equals("")).map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
    @Test (enabled = true)
    public void testContactEmail() {
        assertThat(contact.getAllemails(),equalTo(mergeEmails(contactInfoFromEditForm)));

    }
    private String mergeEmails(ContactData contact){
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3()).stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }

}
