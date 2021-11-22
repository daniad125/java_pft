package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData where deprecated is null" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated is null" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public Groups selectGroupsByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result =session.createQuery("from GroupData where deprecated is null and group_name='"+name+"'").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }
    public ContactData selectContactById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result=session.createQuery("from ContactData where deprecated is null and id="+id).list();
        session.getTransaction().commit();
        session.close();
        ContactData contact = result.get(0);
        return contact;
    }
    public Contacts selectContactsByGroupName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> groups =session.createQuery("from GroupData where deprecated is null and group_name='"+name+"'").list();
        GroupData group = groups.get(0);
        List<ContactData> contacts = session.createQuery("from ContactData").list();

        session.getTransaction().commit();
        session.close();
        return new Contacts(contacts);
    }


}
