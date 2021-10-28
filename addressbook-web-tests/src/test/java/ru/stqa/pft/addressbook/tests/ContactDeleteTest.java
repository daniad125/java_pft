package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;


public class ContactDeleteTest extends TestBase {
    @Test
    public void testDeleteContact() throws Exception{
        app.getContactHelper().selectContacts();
        app.getContactHelper().deleteSelectedContacts();
    }
}
