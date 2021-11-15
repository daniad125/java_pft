package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {
    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }



    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData withEmail2(String email) {
        this.email2=email;
        return this;
    }
    public ContactData withEmail3(String email) {
        this.email3=email;
        return this;
    }
    public ContactData withAllemails(String allemails) {
        this.allemails=allemails;
        return this;
    }
    public ContactData withMobilephone(String mobilephone) {
        this.mobilephone=mobilephone;
        return this;
    }
    public ContactData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }
    public ContactData withAllphones(String allphones) {
        this.allphones=allphones;
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String name;
    @Expose
    private String lastname;
    @Expose
    private String company;
    @Expose
    private String address;
    @Expose
    private String homephone;
    @Expose
    private String mobilephone;
    @Expose
    private String email;
    @Expose
    private String email2;
    @Expose
    private String email3;
    @Expose
    private String group;
    private String workphone;
    private String allphones;
    private String allemails;
    @Expose
    private File photo;





    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getEmail() {
        return email;
    }
    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getGroup() {
        return group;
    }

    public String getMobilephone() {
        return mobilephone;
    }
    public String getWorkphone() {
        return workphone;
    }
    public String getAllphones() {
        return allphones;
    }
    public String getAllemails() {
        return allemails;
    }
    public File getPhoto() {
        return photo;
    }



}
