package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.List;

public class HbConnectionTest {


    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        System.out.println(new File(".").getAbsolutePath());
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Test
    public void testHbConnection() {


        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> contacts = session.createQuery("from ContactData where deprecated is null").list();


        for (ContactData contact : contacts) {
            Groups allContactGroups = contact.getGroups();
            for (GroupData group : allContactGroups) {
                System.out.println(group.getName());
                List<GroupData> groups = session.createQuery("from GroupData where group_name='" + group.getName()+"'").list();
                for (GroupData cur_group : groups) {
                    System.out.println(cur_group);
                    //               System.out.println(group.getName() + group.getId());
                }
            }
        }
        session.getTransaction().commit();
        session.close();
    }


}
