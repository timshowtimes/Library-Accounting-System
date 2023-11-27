package kz.timshowtime.LibraryBoot.dao;//package kz.timshowtime.dao;
//
//import kz.timshowtime.models.Book;
//import kz.timshowtime.models.Person;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//public class BookDAO {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public BookDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Book> index() {
//        return sessionFactory.getCurrentSession()
//                .createQuery("SELECT b FROM Book b", Book.class)
//                .getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Book show(int id) {
//        return sessionFactory.getCurrentSession().get(Book.class, id);
//    }
//
//    @Transactional
//    public void save(Book book) {
//        sessionFactory.getCurrentSession().persist(book);
//    }
//
//    @Transactional
//    public void update(int id, Book book) {
//        Session session = sessionFactory.getCurrentSession();
//        Book updateBook = session.get(Book.class, id);
//        updateBook.setAuthor(book.getAuthor());
//        updateBook.setName(book.getName());
//        updateBook.setYear(book.getYear());
//    }
//
//    @Transactional
//    public void setPerson(int person_id, int book_id) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, book_id);
//        Person person = session.get(Person.class, person_id);
//        book.setPerson(person);
//        person.getBooks().add(book);
//    }
//
//    @Transactional
//    public void undoPerson_id(Book book) {
//        Session session = sessionFactory.getCurrentSession();
//        Book removeBook = session.get(Book.class, book.getBook_id());
//        removeBook.setPerson(null);
//    }
//
//    @Transactional
//    public void delete(int id) {
//        sessionFactory.getCurrentSession().remove(show(id));
//    }
//
//}
