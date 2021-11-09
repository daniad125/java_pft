package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase{

    private Contacts contactCache=null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void submitContactCreation() {
        click(By.xpath("//input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getName());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("company"),contactData.getCompany());
        type(By.name("address"),contactData.getAddress());
        type(By.name("home"),contactData.getHomephone());
        type(By.name("email"),contactData.getEmail());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContacts(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }
    public void selectContactsById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"'")).click();
    }
    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void initContactModificationById(int id) {
        click(By.cssSelector("a[href='edit.php?id="+id+"']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contactData) {
        initContactCreation();
        fillContactForm(contactData,true);
        submitContactCreation();
        contactCache=null;
    }
    public void modify(ContactData contact) {
        int index =contact.getId();
        selectContactsById(index);
        initContactModificationById(index);
        fillContactForm(contact,false);
        submitContactModification();
        contactCache=null;
    }
    public void delete(int index) {
        selectContacts(index);
        deleteSelectedContacts();

    }
    public void delete(ContactData contact) {
        selectContactsById(contact.getId());
        deleteSelectedContacts();
        contactCache=null;
    }

    public Contacts all() {
        if (contactCache!=null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row: rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String name= cells.get(2).getText();
            String address=cells.get(3).getText();
            String email=cells.get(4).getText();
            String allphones=cells.get(5).getText();

            //System.out.println(id);
            contactCache.add(new ContactData()
                    .withId(id).withName(name).withLastname(lastname).withAddress(address).withEmail(email).withAllphones(allphones));
        }
        return new Contacts(contactCache);
    }


    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    /*public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row: rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();


        }
    }

     */
    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
        String home=wd.findElement(By.name("home")).getAttribute("value");
        String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
        String work=wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstname).withLastname(lastname).withHomephone(home).withMobilephone(mobile).withWorkphone(work);
    }
}
