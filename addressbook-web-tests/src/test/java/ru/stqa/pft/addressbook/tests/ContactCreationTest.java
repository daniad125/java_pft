package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase{
  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line!=null) {
        xml+=line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g)->new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
      String json = "";
      String line = reader.readLine();
      while (line!=null) {
        json+=line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json,new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((g)->new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }


  @Test (enabled = true,dataProvider = "validContactsFromXml")
  public void testContactCreation(ContactData contact) throws Exception {
    Groups groups = app.db().groups();
    GroupData group;
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    Groups allGroups=app.db().selectGroupsByName("generator1");
    group=allGroups.iterator().next();
    contact=contact.inGroup(group);
    app.contact().create(contact);
    assertEquals(before.size(),app.contact().count()-1);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    app.goTo().homePage();
  }
  @Test (enabled = true)
  public void testBadContactCreation() throws Exception {
    Contacts before = app.db().contacts();
    GroupData group;
    app.goTo().homePage();
    Groups allGroups=app.db().selectGroupsByName("test1");
//    if (allGroups.size()==1) {
      group = allGroups.iterator().next();
//    }
//    else {
      System.out.println(allGroups);
//    }
    ContactData contact = new ContactData().withName("HaHa'").inGroup(group);
    app.contact().create(contact);
    assertEquals(before.size(),app.contact().count());
    Contacts after = app.db().contacts();
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
