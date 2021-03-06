package ru.stqa.pft.addressbook.bdd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupStepDefinitions {
    private ApplicationManager app;
    private Groups groups;
    private GroupData newGroup;

    @Before
    public void init() {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        app.init();
    }

    @After
    public void stop() {
        app.stop();
        app = null;
    }
    @Given("^a set of groups$")
    public void loadGroups() {
        groups = app.db().groups();
    }

    @When("^I create a new group with name (.+), header (.+), footer (.+)")
    public void createGroup(String name,String header,String footer) {
        newGroup = new GroupData().withName(name).withHeader(header).withFooter(footer);
        app.group().create(new GroupData().withName(name).withHeader(header).withFooter(footer));
    }

    @Then("^the new set of groups is equal to the old set of groups with added group$")
    public void verifyCreatedGroup() {
        Groups newGroups = app.db().groups();
        assertThat(newGroups, equalTo(groups.withAdded(newGroup)));
    }
}
