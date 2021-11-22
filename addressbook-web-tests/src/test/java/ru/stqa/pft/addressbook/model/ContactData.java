package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
//@Where( clause = "deprecated is null" )
public class ContactData {


    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
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
        this.photo = photo.getPath();
        return this;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
//        this.groupName=group.getName();
        return this;
    }
    @XStreamOmitField

    @Id
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String name=null;
    @Expose
    @Column
    private String lastname;
    @Expose
    @Column
    private String company=null;
    @Expose
    @Column
    @Type(type = "text")
    private String address=null;
    @Expose
    @Column(name="home")
    @Type(type = "text")
    private String homephone=null;
    @Expose
    @Column(name="mobile")
    @Type(type = "text")
    private String mobilephone=null;
    @Expose
    @Column
    @Type(type = "text")
    private String email=null;
    @Expose
    @Column
    @Type(type = "text")
    private String email2=null;
    @Expose
    @Column
    @Type(type = "text")
    private String email3=null;
    @Expose

    @Column(name="work")
    @Type(type = "text")
    private String workphone=null;
    @Transient
    private String allphones;
    @Transient
    private String allemails;
    @Expose
    @Column(name="photo")
    @Type(type = "text")
    private String photo=null;

//    private String groupName;

    public Groups getGroups() {
        return new Groups(groups);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="address_in_groups", joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public boolean equalnullstrings(String a,String b) {
        if (a==null) {
            if (b==null || b=="") {
                return true;
            }
        }
        if (b==null)  {
            if (a==null || a=="") {
                return true;
            }
        }
        if (a=="") {
            if (b=="") {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && (Objects.equals(name, that.name)||equalnullstrings(name,that.name)) && ((Objects.equals(lastname, that.lastname)||equalnullstrings(lastname,that.lastname)) && Objects.equals(company, that.company)||equalnullstrings(company,that.company)) && (Objects.equals(address, that.address)||equalnullstrings(address,that.address)) && (Objects.equals(homephone, that.homephone)||equalnullstrings(homephone,that.homephone)) && (Objects.equals(mobilephone, that.mobilephone)||equalnullstrings(mobilephone,that.mobilephone)) && (Objects.equals(email, that.email)||equalnullstrings(email,that.email)) && (Objects.equals(email2, that.email2)||equalnullstrings(email2,that.email2)) && (Objects.equals(email3, that.email3)||equalnullstrings(email3,that.email3)) && (Objects.equals(workphone, that.workphone)||equalnullstrings(workphone,that.workphone)) && (Objects.equals(photo, that.photo)||equalnullstrings(photo,that.photo))&& Objects.equals(groups, that.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, company, address, homephone, mobilephone, email, email2, email3, workphone, photo, groups);
    }

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
        return new File(photo);
    }



}
