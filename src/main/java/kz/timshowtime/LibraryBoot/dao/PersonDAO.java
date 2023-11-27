package kz.timshowtime.LibraryBoot.dao;//package kz.timshowtime.dao;
//
//import kz.timshowtime.models.Person;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PersonDAO {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Person> index() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("SELECT p FROM Person p", Person.class)
//                .getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Person show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, id);
//        System.out.println(!person.getBooks().isEmpty());
//        return person;
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<Person> show(String name) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("SELECT p FROM Person p WHERE p.name =: personName", Person.class)
//                .setParameter("personName", name).getResultList().stream().findAny();
//    }
//
//    @Transactional
//    public void save(Person person) {
//        sessionFactory.getCurrentSession().persist(person);
//    }
//
//    @Transactional
//    public void update(int id, Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        Person updatePerson = session.get(Person.class, id);
//        updatePerson.setName(person.getName());
//        updatePerson.setYear_of_birth(person.getYear_of_birth());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        sessionFactory.getCurrentSession().remove(show(id));
//    }
//}
