package com.example.minorProject1.serviceImpl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.minorProject1.enums.TransactionType;
import com.example.minorProject1.exceptions.TransactionServiceException;
import com.example.minorProject1.models.Book;
import com.example.minorProject1.models.Student;
import com.example.minorProject1.models.Transaction;
import com.example.minorProject1.repository.TransactionRepositoryInterf;
import com.example.minorProject1.service.BookServiceInterf;
import com.example.minorProject1.service.StudentServiceInterf;
import com.example.minorProject1.service.TransactionServiceInterf;

@Service
public class TransactionServiceImpl implements TransactionServiceInterf {
	
	@Autowired
	TransactionRepositoryInterf transactionRepositoryInterf;
	
	@Autowired
	StudentServiceInterf studentServiceInterf;
	
	@Autowired
	BookServiceInterf bookServiceInterf;
	
	@Value("${book.return.due_date}")
	int max_days;

	@Override
	public String issueTxn(int studentId, int bookId) throws TransactionServiceException {
//		Student is valid
//		book is present and is also available
//		Create transaction 
//		Making the book Unavailable
		
		Student student = studentServiceInterf.findById(studentId);
		
		if(student == null)
			throw new TransactionServiceException("Student Not Presnt in the Library");
		
		Book book = bookServiceInterf.findById(bookId);
		if(book == null || book.getStudent() != null) {
			throw new TransactionServiceException("Book Not Presnt in the Library");
		}
		
		Transaction transaction = Transaction.builder()
				.externalId(UUID.randomUUID().toString())
				.transactionType(TransactionType.ISSUE)
				.payment(book.getCost())
				.book(book)
				.student(student)
				.build();
		
		transactionRepositoryInterf.save(transaction);
		
		book.setStudent(student);
		bookServiceInterf.save(book);
				
		
		return transaction.getExternalId();
	}
	
	public String returntxn(int studentId, int bookId) throws TransactionServiceException {
		
//		Student is valid
//		book is issued to this student
//		Calculate the fine
//		Create transaction 
//		Making the book available
		
		
		Student student = studentServiceInterf.findById(studentId);
		
		if(student == null)
			throw new TransactionServiceException("Student Not Present in the Library");
		
		Book book = bookServiceInterf.findById(bookId);
		if(book == null) {
			throw new TransactionServiceException("Book Not Present in the Library");
		}
		
		if(book.getStudent().getId() != studentId) {
			throw new TransactionServiceException("Book Not Issued to the Student");
		}
		
		//select * from transaction where book_id=bookId and student_id=studentId and 
		//transaction_type=issue order by transaction_date desc;
        Transaction issueTxn = transactionRepositoryInterf
                .findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(book, student, TransactionType.ISSUE);


        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.RETURN)
                .externalId(UUID.randomUUID().toString())
                .book(book)
                .student(student)
                .payment(calculateFine(issueTxn))
                .build();

        transactionRepositoryInterf.save(transaction);

        book.setStudent(null);
        bookServiceInterf.save(book);
        return transaction.getExternalId();
		
	}
	
	private double calculateFine(Transaction issueTxn) {
		long issueTime = issueTxn.getCreatedOn().getTime();
		long returnTime = System.currentTimeMillis();
		
		long diff = returnTime - issueTime;
		long daysPassed = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
		
		if(daysPassed > max_days) {
			return (daysPassed-max_days)*10.0;
		}
		
		return 0.0;
	}

}
