package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase{




  @Test (enabled = true)
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo=new File("src/test/resources/smile1.png");
    ContactData contact = new ContactData().withName("Sergey").withLastname("Vertibutylkin").withCompany("Super House").withAddress("ul Dlinnaya, 8, 235").withHomephone("995996").withEmail("vertiseychac@yandex.ru").withGroup("test1").withPhoto(photo);
    app.contact().create(contact);
    assertEquals(before.size(),app.contact().count()-1);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    app.goTo().homePage();
  }
  @Test (enabled = true)
  public void testBadContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withName("HaHa'").withGroup("test1");
    app.contact().create(contact);
    assertEquals(before.size(),app.contact().count());
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before));
    app.goTo().homePage();
  }

  @Test
  public void testCurrentDir() {
    File curDir=new File(".");
    File photo=new File("src/test/resources/smile1.png");
    System.out.println(curDir.getAbsolutePath());
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
