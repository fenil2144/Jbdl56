package com.example.minorProject1.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minorProject1.enums.BookFilterType;
import com.example.minorProject1.enums.Genre;
import com.example.minorProject1.models.Author;
import com.example.minorProject1.models.Book;
import com.example.minorProject1.repository.BookRepositoryInterf;
import com.example.minorProject1.requestDto.BookCreateRequest;
import com.example.minorProject1.service.AuthorServiceInterf;
import com.example.minorProject1.service.BookServiceInterf;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		//Logic to check if Book is already existing in database
		Book book = bookCreateRequest.toBook();
		
		Author author = book.getAuthor();
		
		//Logic to check if Author already exist in database
		Author authorFromDb = authorServiceInterf.findByEmail(author.getEmail());
//		System.out.println("After AUthor");
//		System.out.println(authorFromDb.getBookList());
		if(authorFromDb == null) {
			authorFromDb = authorServiceInterf.createAuthor(author);
		}
		//this author object will have id
		book.setAuthor(authorFromDb);
		return bookRepositoryInterf.save(book);
	}

	@Override
	public List<Book> find(BookFilterType bookFilterType, String value) {
		switch(bookFilterType) {
		case NAME:
			return bookRepositoryInterf.findByName(value);
		case AUTHOR_NAME:
			return bookRepositoryInterf.findByAuthor_name(value);
		case GENRE:
			return bookRepositoryInterf.findByGenre(Genre.valueOf(value));
		case ID:
			return bookRepositoryInterf.findAllById(new ArrayList<Integer>(Integer.parseInt(value)));
		default:
			return bookRepositoryInterf.findAll();

		}
	}

	@Override
	public Optional<Book> findById(int bookId) {
		return bookRepositoryInterf.findById(bookId);
	}

	@Override
	public Book save(Book book) {
		return bookRepositoryInterf.save(book);
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
