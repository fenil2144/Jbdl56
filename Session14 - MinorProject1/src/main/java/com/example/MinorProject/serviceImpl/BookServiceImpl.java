package com.example.MinorProject.serviceImpl;

import com.example.MinorProject.dtos.BookCreateRequest;
import com.example.MinorProject.enums.BookFilterType;
import com.example.MinorProject.models.Author;
import com.example.MinorProject.models.Book;
import com.example.MinorProject.repository.BookRepositoryInterf;
import com.example.MinorProject.service.AuthorServiceInterf;
import com.example.MinorProject.service.BookServiceInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookServiceInterf {

    @Autowired
    BookRepositoryInterf bookRepositoryInterf;

    @Autowired
    AuthorServiceInterf authorServiceInterf;

    @Autowired
    EntityManager em;

    @Override
    public Book create(BookCreateRequest bookCreateRequest) {

        Book book = bookCreateRequest.toBook();

        //Logic to check if Book is duplicate

        Author author = book.getAuthor();

        //Logic to check if Author already exists
        Author authorFromDb = authorServiceInterf.findByEmail(author.getEmail());
        if(authorFromDb == null){
            authorFromDb = authorServiceInterf.createAuthor(author);
        }
        book.setAuthor(authorFromDb);
        return bookRepositoryInterf.save(book);
    }

    @Override
    public List<Book> find(BookFilterType bookFilterType, String value) {
        switch(bookFilterType){
            case NAME:
                return bookRepositoryInterf.findByName(value);
            case AUTHOR_NAME:
                return bookRepositoryInterf.findByAuthor_name(value);
            case GENRE:
                return bookRepositoryInterf.findByGenre(value);
            case COST:
                return bookRepositoryInterf.findByCost(value);
            case ID:
                return bookRepositoryInterf.findAllById(new ArrayList<>( Integer.parseInt(value)));
            default:
                return new ArrayList<Book>();
        }
        return null;
    }

    public List<Book> getBooks(BookCreateRequest bookCreateRequest){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> book = cq.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();

        if(bookCreateRequest.getName() !=null){
            predicates.add(cb.like(book.get("name"),"%"+bookCreateRequest.getName()+"%"));
        }
        if(bookCreateRequest.getCost() != 0){
            predicates.add(cb.equal(book.get("cost"),bookCreateRequest.getCost()));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();

    }
}
