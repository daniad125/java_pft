package ru.stqa.pft.addressbook.model;

import java.util.Objects;

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
    private int id = Integer.MAX_VALUE;
    private String name;
    private String lastname;
    private String company;
    private String address;
    private String homephone;
    private String mobilephone;
    private String email;
    private String email2;
    private String email3;
    private String group;
    private String workphone;
    private String allphones;
    private String allemails;



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



}
