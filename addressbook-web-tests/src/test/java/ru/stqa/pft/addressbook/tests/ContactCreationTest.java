package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{




  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Sergey", "Vertibutylkin", "Super House", "ul Dlinnaya, 8, 235", "995996", "vertiseychac@yandex.ru","test1"),true);
    app.getContactHelper().submitContactCreation();
    app.logout();
  }


}
