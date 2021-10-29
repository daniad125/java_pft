package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String name;
    private final String lastname;
    private final String company;
    private final String address;
    private final String homephone;
    private final String email;
    private String group;

    public ContactData(String name, String lastname, String company, String address, String homephone, String email, String group) {
        this.name = name;
        this.lastname = lastname;
        this.company = company;
        this.address = address;
        this.homephone = homephone;
        this.email = email;
        this.group = group;
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

    public String getGroup() {
        return group;
    }
}